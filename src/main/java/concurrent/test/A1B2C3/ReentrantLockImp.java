package concurrent.test.A1B2C3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 张卓
 * @Description
 * @Date 创建于 2020/6/12 10:40
 */
public class ReentrantLockImp {
    static Thread printCharThread = null, printNumberThread = null;

    public static void main(String[] args) {
        final ReentrantLock reentrantLock = new ReentrantLock();
        final Condition condition = reentrantLock.newCondition();

        printCharThread = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 1; i < 27; i++) {
                    System.out.print(Character.toUpperCase((char) (96 + i)));//大写
                    try {
                        reentrantLock.lock();
                        // 通知打印数字
                        condition.signal();
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        reentrantLock.unlock();

                    }
                }
            }
        });

        printNumberThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 27; i++) {
                    try {
                        reentrantLock.lock();
                        condition.await();

                        System.out.print(i);
                        // 通知打印字母
                        condition.signal();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        reentrantLock.unlock();
                    }

                }
            }
        });

        printNumberThread.start();
        printCharThread.start();
    }
}
