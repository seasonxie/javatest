package useful;

import com.google.common.cache.*;
import com.google.common.collect.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class guavaCache {


    public static void main(String[] args) throws ExecutionException {
        //cache
        for (int i = 0; i < 10; i++) {
            System.out.println(adAppIdCache.get("1") + "  )))");
        }

        //table
        Table<String, String, String> employeeTable = HashBasedTable.create();
        employeeTable.put("IBM", "101", "Mahesh");
        employeeTable.put("IBM", "102", "Ramesh");
        employeeTable.put("IBM", "103", "Suresh");
        System.out.println("List of IBM Employees");
        Map<String, String> ibmEmployees = employeeTable.row("IBM");
        for (Map.Entry<String, String> entry : ibmEmployees.entrySet()) {
            System.out.println("Emp Id: " + entry.getKey() + ", Name: " + entry.getValue());
        }


        //反转
        BiMap<Integer, String> empIDNameMap = HashBiMap.create();

        empIDNameMap.put(new Integer(101), "Mahesh");
        empIDNameMap.put(new Integer(102), "Sohan");
        empIDNameMap.put(new Integer(103), "Ramesh");

        //Emp Id of Employee "Mahesh"
        System.out.println(empIDNameMap.inverse().get("Mahesh"));

        //
        Multiset<String> multiset = HashMultiset.create();
        multiset.add("a");
        multiset.add("b");
        multiset.add("c");
        multiset.add("d");
        multiset.add("a");
        multiset.add("b");
        multiset.add("c");
        multiset.add("b");
        multiset.add("b");
        multiset.add("b");
        //print the occurrence of an element
        System.out.println("Occurrence of 'b' : "+multiset.count("b"));
        //print the total size of the multiset
        System.out.println("Total Size : "+multiset.size());
        //get the distinct elements of the multiset as set
        Set<String> set = multiset.elementSet();
        System.out.println(set.size());

    }

    private Multimap<String,String> getMultimap(){
        //Map<String, List<String>>
        // lower -> a, b, c, d, e
        // upper -> A, B, C, D

        Multimap<String,String> multimap = ArrayListMultimap.create();

        multimap.put("lower", "a");
        multimap.put("lower", "b");
        multimap.put("lower", "c");
        multimap.put("lower", "d");
        multimap.put("lower", "e");

        multimap.put("upper", "A");
        multimap.put("upper", "B");
        multimap.put("upper", "C");
        multimap.put("upper", "D");

        List<String> lowerList = (List<String>)multimap.get("lower");
        return multimap;
    }


    private static final LoadingCache<String, Integer> adAppIdCache = callableCached(new CacheLoader<String, Integer>() {
        @Override
        public Integer load(String key) throws Exception {
            try {
                int v = getAdAppIdByPkgName(key);
                System.out.println(v + "  -");
                return v;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return 0;
        }
    }, 10000, 5, TimeUnit.MINUTES);

    public static Integer getAdAppIdByPkgName(String key) {
        java.util.Random r = new java.util.Random(10);
        System.out.println("---");
        return r.nextInt();
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
