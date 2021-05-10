package com.ll.jump.service.redis.list;

import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import redis.clients.jedis.Jedis;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author jump
 * @desc 秒杀Demo （redis list数据结构的应用）
 * @create 2021-04-29 15:56
 **/
public class SecKillDemo {
    private Jedis jedis = new Jedis("127.0.0.1", 6379);
    private static AtomicInteger secKillCount = new AtomicInteger(0);
    /**
     * 秒杀最大数
     */
    private static final long SEC_KILL_NUMBER_MAX = 10000;

    /**
     * 处理秒杀队列的请求
     * 此处可以改成启一个定时任务处理秒杀队列请求，等秒杀结束后停止该任务
     * 如果是用static代码块，要注意redis的内存清理机制不知道哪天可能会把 secKill:queue:dealCount 清除，
     * 导致重启服务执行该代码块，又从头处理秒杀队列
     */
    static {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        SecKillDemo secKillDemo = new SecKillDemo();
        jedis.del("secKill:queue:dealCount");
        String dealSecKillCountStr = jedis.get("secKill:queue:dealCount");
        Long dealSecKillCount = StringUtils.isEmpty(dealSecKillCountStr) ? 0 : Long.valueOf(dealSecKillCountStr);
        if (dealSecKillCount < SEC_KILL_NUMBER_MAX) {
            while (true) {
                try {
                    String secKillRequest = secKillDemo.dequeueSecKill();
                    if (StringUtils.isBlank(secKillRequest) || "null".equalsIgnoreCase(secKillRequest)) {
                        continue;
                    }
                    System.out.println(secKillRequest);

                    dealSecKillCount = jedis.incr("secKill:queue:dealCount");
                    if (dealSecKillCount >= SEC_KILL_NUMBER_MAX) {
                        break;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Executor executor = getExecutor();
        //模拟秒杀请求
        for (int i = 0; i < 100000; i++) {
            int j = i;
            CompletableFuture.runAsync(() -> {
                try {
                    SecKillDemo secKillDemoOne = new SecKillDemo();
                    secKillDemoOne.enqueueSecKill("第" + (j + 1) + "次秒杀请求");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }, executor);
        }
        //此处在秒杀过程中，不要执行该方法，会抛异常的
//        long secKillQueueLength = secKillDemo.getSecKillQueueLength();
//        System.out.println("秒杀队列长度：" + secKillQueueLength);
    }

    /**
     * 获取异步配置
     *
     * @return
     */
    private static ThreadPoolTaskExecutor getExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(40);
        executor.setQueueCapacity(5000);
        executor.setKeepAliveSeconds(120);
        executor.setThreadNamePrefix("taskAsyncExecutor-");
        executor.setAllowCoreThreadTimeOut(true);
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    /**
     * 秒杀队列请求入队
     *
     * @param secKillRequest
     */
    public boolean enqueueSecKill(String secKillRequest) {
        jedis.lpush("secKill:queue", secKillRequest);
        return true;
    }

    /**
     * 秒杀队列请求出队
     *
     * @return
     */
    public String dequeueSecKill() {
        return jedis.rpop("secKill:queue");
    }

    /**
     * 获取秒杀队列长度
     *
     * @return
     */
    public long getSecKillQueueLength() {
        return jedis.llen("secKill:queue");
    }
}
