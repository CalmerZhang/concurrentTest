package basic;

/**
 * @author 张卓
 * @Description
 * @Date 创建于 2020/8/3 19:14
 */
public class FloatTest {
    public static void main(String[] args) {
        float percentOnWeek = 0.12f;
        float versionPercent = 0.4f;
        float skuPercent = 0.1f;
        float siteP = 0.01f;
        float sellerP = 0.01f;

        float percent = percentOnWeek * versionPercent * skuPercent * siteP * sellerP;
        System.out.println(percent);
        System.out.println(100f * percent);
    }
}
