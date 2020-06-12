package concurrent.test.A1B2C3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 张卓
 * @Description
 * @Date 创建于 2020/6/12 10:30
 */
public class ReentrantLockHighLevelImp {
    static Thread printCharThread = null, printNumberThread = null;

    public static void main(String[] args) {
        final ReentrantLock reentrantLock = new ReentrantLock();
        // 两个Condition定向通知
        final Condition printCharCond = reentrantLock.newCondition(); // 输出char condition
        final Condition printNumCond = reentrantLock.newCondition(); // 输出Num condition

        printCharThread = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 1; i < 27; i++) {
                    System.out.print(Character.toUpperCase((char) (96 + i)));//大写
                    try {
                        reentrantLock.lock();
                        // 通知打印数字
                        printNumCond.signal();
                        printCharCond.await();
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
                        printNumCond.await();

                        System.out.print(i);
                        // 通知打印字母
                        printCharCond.signal();
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
