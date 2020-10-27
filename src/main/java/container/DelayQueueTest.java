package container;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author 张卓
 * @Description
 * @Date 创建于 2020/10/27 14:49
 */
public class DelayQueueTest {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<DelayTask> delayQueue = new DelayQueue<DelayTask>();

        delayQueue.put(new DelayTask(LocalDateTime.now().plusSeconds(10L)));
        delayQueue.put(new DelayTask(LocalDateTime.now().plusSeconds(20L)));

        while(true) {
            System.out.println(LocalDateTime.now().toString());
            DelayTask task = delayQueue.take();

            System.out.println(new Date());
            System.out.println(task.getToDateTime());
        }
    }

    static class DelayTask implements Delayed {

        private LocalDateTime toDateTime;

        public String getToDateTime() {
            return toDateTime.toString();
        }

        DelayTask(LocalDateTime toDateTime) {
            this.toDateTime = toDateTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(Duration.between(LocalDateTime.now(), toDateTime).toMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return Math.toIntExact(this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
        }
    }
}
