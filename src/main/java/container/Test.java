package container;

import java.util.HashMap;

/**
 * @author 张卓
 * @Description
 * @Date 创建于 2020/6/23 12:18
 */
public class Test {
    public static void main(String[] args) {
        HashMap map = new HashMap();
        map.put(1, "第一人");

        System.out.println(map.get("1"));
    }
}
