import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 〈〉
 *
 * @author LiLin
 * @date 2020/9/2 0002
 */
public class Test {
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