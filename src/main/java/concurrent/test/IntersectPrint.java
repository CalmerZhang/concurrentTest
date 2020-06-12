package concurrent.test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.LockSupport;

/**
 * @author 张卓
 * @Description
 * @Date 创建于 2020/6/9 12:54
 */
public class IntersectPrint {
    public static void main(String[] args) {
//        notifyImp();
        lockSupportImp();
    }

    /**
     * 使用LockSupport
     */
    private static void lockSupportImp() {
        PrintNumberRunnable printNumberRunnable = new PrintNumberRunnable(null);
        Thread printNumberThread = new Thread(printNumberRunnable);
        Thread printCharThread = new Thread(new PrintCharRunnable(printNumberThread));
        printNumberRunnable.setNotifyThread(printCharThread);

        printCharThread.start();
        printNumberThread.start();
    }

    static class PrintNumberRunnable implements Runnable {
        private Thread notifyThread;

        PrintNumberRunnable(Thread notifyThread) {
            this.notifyThread = notifyThread;
        }

        public void setNotifyThread(Thread notifyThread) {
            this.notifyThread = notifyThread;
        }

        @Override
        public void run() {
            for (int i = 1; i < 27; i++) {
                LockSupport.park();

                System.out.println(i);
                // 通知打印字母
                LockSupport.unpark(notifyThread);

            }
        }
    }

    static class PrintCharRunnable implements Runnable {
        private Thread notifyThread;

        PrintCharRunnable(Thread notifyThread) {
            this.notifyThread = notifyThread;
        }

        public void setNotifyThread(Thread notifyThread) {
            this.notifyThread = notifyThread;
        }

        @Override
        public void run() {
            for (int i = 1; i < 27; i++) {
                System.out.println(Character.toUpperCase((char) (96 + i)));//大写
                // 通知打印数字
                LockSupport.unpark(notifyThread);
                LockSupport.park();
            }
        }
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
