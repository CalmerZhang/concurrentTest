package concurrent.test.A1B2C3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * @author 张卓
 * @Description
 * @Date 创建于 2020/6/12 10:24
 */
public class LockSupportImp {
    static Thread printCharThread = null, printNumberThread = null;

    public static void main(String[] args) {
        printCharThread = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 1; i < 27; i++) {
                    System.out.print(Character.toUpperCase((char) (96 + i)));//大写
                    // 通知打印数字
                    LockSupport.unpark(printNumberThread);
                    LockSupport.park();
                }
            }
        });

        printNumberThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 27; i++) {
                    LockSupport.park();

                    System.out.print(i);
                    // 通知打印字母
                    LockSupport.unpark(printCharThread);

                }
            }
        });

        printNumberThread.start();
        printCharThread.start();
    }
}
