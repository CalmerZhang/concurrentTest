package concurrent.test.A1B2C3;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.LockSupport;

/**
 * @author 张卓
 * @Description
 * @Date 创建于 2020/6/9 12:54
 */
public class WaitNotifyImp {
    public static void main(String[] args) {
        notifyImp();
    }


    private static void notifyImp() {
        final Object monitor = new Object();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 27; i++) {
                    synchronized (monitor) {
                        try {
                            monitor.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        System.out.println(i);

                        monitor.notify();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 26; i++) {
                    synchronized (monitor) {
                        try {

                            System.out.println(Character.toUpperCase((char) (96 + i)));//大写
                            monitor.notify();
                            monitor.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();


    }
}
