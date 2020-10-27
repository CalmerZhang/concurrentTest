package reference;

import java.lang.ref.WeakReference;
import java.util.Map;

/**
 * @author 张卓
 * @Description
 * @Date 创建于 2020/10/26 17:00
 */
public class WeakReferenceTest {

    class Entry<K,V> extends WeakReference<K> implements Map.Entry {

        public Entry(K referent) {
            super(referent);
        }

        @Override
        public Object getKey() {
            return null;
        }

        @Override
        public Object getValue() {
            return null;
        }

        @Override
        public Object setValue(Object value) {
            return null;
        }
    }
}
