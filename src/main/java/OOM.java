import java.util.ArrayList;
import java.util.List;

/**
 * @author 张卓
 * @Description
 * @Date 创建于 2020/7/23 14:54
 */
public class OOM {
    public static void main(String[] args) throws InterruptedException {
        List<Object> list = new ArrayList<Object>();
        for (int i = 0; i < 100; i++) {
            byte[] bytes = new byte[1024 * 1024];
            list.add(bytes);
            Thread.sleep(100);
        }
    }
}
