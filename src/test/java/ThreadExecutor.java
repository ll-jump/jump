/**
 * @author LiLin
 * @desc
 * @create 2021-06-29 21:40
 **/
public class ThreadExecutor {
    /**
     * 执行线程
     */
    private Thread executeThread;
    /**
     * 运行状态
     */
    private volatile boolean isRunning = false;

    /**
     *
     * @param task 发生阻塞的线程任务
     */
    public void execute(Runnable task) {
        executeThread = new Thread(() -> {
            Thread childThread = new Thread(task);

            // 子线程设置为守护线程
            childThread.setDaemon(true);

            childThread.start();
            try {
                // 强行执行子线程，使其进入休眠状态
                childThread.join();
                isRunning= true;
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
        });

        executeThread.start();
    }

    /**
     *
     * @param mills 强制结束任务的时长阈值
     */
    public void shutdown(long mills) {
        long currentTime = System.currentTimeMillis();
        while (!isRunning) {
            if ((System.currentTimeMillis() - currentTime) >= mills) {
                System.out.println("任务超时，需要结束他!");
                executeThread.interrupt();
                break;
            }


        }

        isRunning = false;
    }
    public static void main(String[] args) {

        ThreadExecutor executor = new ThreadExecutor();
        long start = System.currentTimeMillis();
        executor.execute(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executor.shutdown(1000);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
