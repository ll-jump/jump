import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.kafka.common.utils.CopyOnWriteMap;
import sun.misc.Unsafe;

import javax.validation.Valid;
import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 〈〉
 *
 * @author LiLin
 * @date 2020/9/2 0002
 */
public class Test {
    private final static  ReentrantLock lock = new ReentrantLock();
    private final static Condition condition = lock.newCondition();

    @org.junit.Test
    public void testThreadPoll(){
//        Executors.defaultThreadFactory().
    }

    @org.junit.Test
    public void testFutureTask() throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(()->{
            System.out.println("futureTask run start.");
            Thread.sleep(3000);
            System.out.println("futureTask run end.");
            return "a";
        });
        Thread thread1 = new Thread(futureTask);
        thread1.start();
        System.out.println("等待futureTask结果");
        String r = futureTask.get();
        System.out.println("获取futureTask结果：" + r);

//        lock.lock();
//        condition.await();
//        condition.signal();
//        lock.unlock();
    }

    @org.junit.Test
    public void testArrayBlockingQueue(){
        Thread thread1 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("thread1 await start.");
                condition.await();
                System.out.println("thread1 await end.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                System.out.println("thread1 unlock start.");
                lock.unlock();
                System.out.println("thread1 unlock end.");
            }
        });
        thread1.setName("thread1");
        thread1.start();
        //确保thread1先执行
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread thread2 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("thread2 sleep start.");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread2 notify start.");
                condition.signal();
                System.out.println("thread2 notify end.");
            }finally {
                System.out.println("thread2 unlock start.");
                lock.unlock();
                System.out.println("thread2 unlock end.");
            }
        });
        thread2.setName("thread2");
        thread2.start();

        //确保thread2先执行
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread thread3 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("thread3 do some thing.");
            }finally {
                System.out.println("thread3 unlock start.");
                lock.unlock();
                System.out.println("thread3 unlock end.");
            }
        });
        thread3.setName("thread3");
        thread3.start();

        try {
            Thread.sleep(3000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void testScheduledExecutorService(){
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat(
                "my-test-pool-%d").build();
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1, threadFactory);
        Date startTime = new Date();
        System.out.println("startTime: " + startTime.toString());
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println("beginTime: " + new Date().toString());
//            try {
//                Thread.sleep(5 * 1000L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println("endTime: " + new Date().toString());
        }, 0, 1, TimeUnit.HOURS);
    }

    @org.junit.Test
    public void test(){
        final Semaphore semaphore = new Semaphore(5);
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 20; i++) {
            final int no = i;
            Runnable run = () -> {
                try{
                    semaphore.acquire();
                    System.out.println("ing:"+no);
                    Thread.sleep((long)(Math.random()*10000));
                    semaphore.release();
                    System.out.println("---------" + semaphore.availablePermits());
                }catch (Exception e){
                    System.out.println(e);
                }
            };

            try {
                Thread.sleep(50000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            exec.execute(run);
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        exec.shutdown();
    }
}