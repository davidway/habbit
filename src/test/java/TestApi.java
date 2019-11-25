import org.testng.annotations.Test;
import sun.nio.ch.ThreadPool;

import java.util.concurrent.*;

public class TestApi {
    @Test
    public void test(){
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

        //Stats before tasks execution
        System.out.println("Largest executions: "
                + executor.getLargestPoolSize());
        System.out.println("Maximum allowed threads: "
                + executor.getMaximumPoolSize());
        System.out.println("Current threads in pool: "
                + executor.getPoolSize());
        System.out.println("Currently executing threads: "
                + executor.getActiveCount());
        System.out.println("Total number of threads(ever scheduled): "
                + executor.getTaskCount());

        executor.submit(new Task());
        executor.submit(new Task());

        //Stats after tasks execution
        System.out.println("Core threads: " + executor.getCorePoolSize());
        System.out.println("Largest executions: "
                + executor.getLargestPoolSize());
        System.out.println("Maximum allowed threads: "
                + executor.getMaximumPoolSize());
        System.out.println("Current threads in pool: "
                + executor.getPoolSize());
        System.out.println("Currently executing threads: "
                + executor.getActiveCount());
        System.out.println("Total number of threads(ever scheduled): "
                + executor.getTaskCount());
        System.out.println();
        executor.shutdown();
    }

    @Test
    public void testBlockingQueue(){
        BlockingQueue<Runnable> queue = new LinkedBlockingDeque<Runnable>();
        System.out.println("hello world!");
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,6,1,TimeUnit.DAYS,queue);
        for ( int i=0 ; i< 10 ; i++){
            Worker work = new Worker();
            String testString = "testThread_"+i;
            Thread test = new Thread(work,testString);

            executor.execute(test);
            int threadSize = queue.size();
            System.out.println("线程队列大小为-->"+threadSize);
        }
        executor.shutdown();

    }
    class Worker implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());

        }
    }

    static class Task implements Runnable {

        public void run() {

            try {
                Long duration = (long) (Math.random() * 5);
                System.out.println("Running Task! Thread Name: " +
                        Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(duration);
                System.out.println("Task Completed! Thread Name: " +
                        Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
