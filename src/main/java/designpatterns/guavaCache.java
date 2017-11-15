package designpatterns;

import com.google.common.cache.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class guavaCache {
    public static void main(String[] args) throws ExecutionException {
        for (int i = 0; i <10 ; i++) {

            System.out.println(adAppIdCache.get("1")+"  )))");
        }
    }


    private static final LoadingCache<String, Integer> adAppIdCache = callableCached(new CacheLoader<String, Integer>() {
        @Override
        public Integer load(String key) throws Exception {
            try {
                int v = getAdAppIdByPkgName(key);
                System.out.println(v +"  -");
                return v;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return 0;
        }
    }, 10000, 5, TimeUnit.MINUTES);

    public static Integer getAdAppIdByPkgName(String key){
        java.util.Random r=new java.util.Random(10);
        System.out.println("---");
        return  r.nextInt();
    }

    private static <K, V> LoadingCache<K, V> callableCached(CacheLoader<? super K, V> loader,
                                                            long maximumSize, long duration, TimeUnit unit) {
        LoadingCache<K, V> cache = CacheBuilder.newBuilder()
                .concurrencyLevel(16)
                .maximumSize(maximumSize)
                .expireAfterWrite(duration, unit)
                .recordStats()
                .removalListener(new RemovalListener<Object, Object>() {

                    @Override
                    public void onRemoval(RemovalNotification<Object, Object> notification) {
                        System.out.println("remove rockman local cache");
                        //logger.info("remove rockman local cache: {}|{}", notification.getKey(), notification.getCause());
                    }
                }).build(loader);
        return cache;
    }
}
