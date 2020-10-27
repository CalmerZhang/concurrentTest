package concurrent.test;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author 张卓
 * @Description
 * @Date 创建于 2020/10/26 12:43
 */
public class ReentrantReadWriteLockTest {
    public static void main(String[] args) {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        lock.writeLock().lock();

        lock.readLock().lock();
        lock.readLock().unlock();
    }
}
