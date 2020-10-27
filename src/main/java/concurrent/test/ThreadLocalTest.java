package concurrent.test;

/**
 * @author 张卓
 * @Description
 * @Date 创建于 2020/7/31 14:33
 */
public class ThreadLocalTest {

    private static ThreadLocal<Long> userIdThreadLocal = new ThreadLocal<Long>() {
        @Override
        protected Long initialValue() {
            return -1L;
        }
    };

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                userIdThreadLocal.set(100L);
                try {
                    Thread.sleep(3 * 100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Thread1 :" + userIdThreadLocal.get());
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread2 :" + userIdThreadLocal.get());
                userIdThreadLocal.set(200L);
                System.out.println("Thread2 :" + userIdThreadLocal.get());
            }
        });

        thread1.start();
        thread2.start();
    }
}
