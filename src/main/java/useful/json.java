package useful;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import java.util.List;
import java.util.Map;

/**
 * Created by liwenjing on 2018/1/3.
 */
public class json {
    static String jsons="{\"action\":\"/interface.service/xxx/queryBlackUserData\",\"all\":\"1\",\"result\":{\"count\":2,\"tenant_count\":2,\"records\":[{\"name\":\"张三\",\"pid\":\"500234199212121212\",\"mobile\":\"18623456789\",\"applied_at\":\"3\",\"confirmed_at\":\"5\",\"confirm_type\":\"overdue\",\"loan_type\":1,\"test\":\"mytest\",\"all\":\"2\"},{\"name\":\"李四\",\"pid\":\"500234199299999999\",\"mobile\":\"13098765432\",\"applied_at\":\"1\",\"confirmed_at\":\"\",\"confirm_type\":\"overdue\",\"loan_type\":3,\"all\":\"3\"},{\"name\":\"王五\",\"pid\":\"50023415464654659\",\"mobile\":\"1706454894\",\"applied_at\":\"-1\",\"confirmed_at\":\"\",\"confirm_type\":\"overdue\",\"loan_type\":3}],\"all\":\"4\"},\"code\":200,\"subtime\":\"1480495123550\",\"status\":\"success\",\"ok\":3}";

    public static void main(String[] args) {

        ReadContext context = JsonPath.parse(jsons);

        //1 返回所有name
        Object name= context.read("$.result.count");
        System.out.println(name);
        System.out.println(name.getClass());


        //1 返回所有name
        List<Object> names = context.read("$.result.records[*].name");
        //["张三","李四","王五"]
        System.out.println(names);
        System.out.println(names.contains("张三"));
        System.out.println(names.size());


        //2
        System.out.println("------------");
        Map<String, Object> obj1 = context.read("$.result.records[1]");
        System.out.println(obj1.toString().contains("name=李四"));
        System.out.println(obj1);
        for(String s:obj1.keySet()){
            System.out.println(obj1.get(s) +" -- "+obj1.get(s).getClass());
        }
    }
}
