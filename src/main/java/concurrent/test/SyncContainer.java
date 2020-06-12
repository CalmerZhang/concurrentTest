package concurrent.test;

import java.util.ArrayDeque;
import java.util.LinkedList;

/**
 * @author 张卓
 * @Description
 * @Date 创建于 2020/6/8 19:35
 */
public class SyncContainer {

    private final int CAPACITY = 16;
    private int count = 0;

    private ArrayDeque<String> list = new ArrayDeque(CAPACITY);

    private final Object lock = new Object();

    private String get() throws InterruptedException {
        String last = null;
        synchronized (lock) {
            last = list.pollLast();
            while (last == null) {
                lock.wait();
                last = list.pollLast();
            }
        }
        return last;

    }

    private void put(String item) throws InterruptedException {
        synchronized (lock) {
            while (list.size() == CAPACITY) {
                lock.wait();
            }

            list.add(item);
        }
    }

    private int getCount() {
        return count;
    }

    public static void main(String[] args) {

    }
}
