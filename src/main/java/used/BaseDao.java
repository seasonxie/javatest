package used;

import java.util.Map;

/**
 * Created by zhaotang on 2017/8/28.
 */
public interface BaseDao<T> {
    public static final String LIMIT="limit";
    public static final String ORDER_BY="order by";

    T getObjectFromPrams(String tableName);


}
