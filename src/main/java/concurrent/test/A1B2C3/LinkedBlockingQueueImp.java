package concurrent.test.A1B2C3;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author 张卓
 * @Description
 * @Date 创建于 2020/6/12 10:43
 */
public class LinkedBlockingQueueImp {
    public static void main(String[] args) {
        final LinkedBlockingQueue<Character> charQueue = new LinkedBlockingQueue();
        final LinkedBlockingQueue<Integer> numQueue = new LinkedBlockingQueue();
        final String FINISH = "OK";

        Thread printCharThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 27; i++) {
                    try {
                        Character printChar = charQueue.take();
                        System.out.print(printChar);
                        numQueue.put(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread printNumThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 27; i++) {
                    try {
                        charQueue.put(Character.toUpperCase((char) (96 + i)));
                        Integer printNum = numQueue.take();
                        System.out.print(printNum);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        printCharThread.start();
        printNumThread.start();
    }
}
