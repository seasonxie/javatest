package apitest.assertimpl.assertbase;

import com.alibaba.fastjson.JSON;
import com.jayway.jsonpath.JsonPath;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonAssertBase extends AssertBase {

    public  static Map<String, Object> convertToMap(Object data) {
        Map<String, Object> res = new HashMap<>();
        if (isJson(data)) {
            getObjects(res, data.toString());
            System.out.println("[Data] " + res.toString());
            return res;
        }
        if (isMap(data)) {
            res.putAll((Map) data);
            System.out.println( "[Data] " + res.toString());
            return res;
        }
        String jsonString = JSON.toJSONString(data);
        getObjects(res, jsonString);
        System.out.println( "[Data] " + res.toString());
        System.out.println("[DataMapSize] " + res.size());
        return res;
    }


    public static void getObjects(Map<String, Object> res1, String Response_Json){
        Map<String, Object> res = null;
        try {
            if(Response_Json.startsWith("[")){
                Response_Json=getArray(Response_Json,res1).get(0);
            }
            res = (Map) JSON.parse(Response_Json);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        if(res!=null) {
            for (String s : res.keySet()) {
                String value = res.get(s).toString();
                if (value.startsWith("{")) {
                    getObjects(res1, value);
                }
                if (value.startsWith("[")) {
                    List<String> result = getArray(value,res1);
                    if (result != null) {
                        res.put(s, result);
                    }
                }
            }
            res1.putAll(res);
        }
    }

    public static List<String> getArray(String target,Map<String, Object> res1){
        List<String> result=null;
        try {
            result= JSON.parseArray(target, String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public <K> K getJsonObject(K expect,String json,String value){
        try {
            expect= JsonPath.read(json,value);
        } catch (Exception e) {
            Assert.fail("JsonPath: "+value+" is incorrect |or| Can't cast to type: "+expect.getClass());
            e.printStackTrace();
        }
        return expect;
    }

    public static Object getJsonObject(String json, String value){
        try {
            return JsonPath.read(json,value);
        } catch (Exception e) {
            Assert.fail("JsonPath: "+value+" is incorrect");
            e.printStackTrace();
        }
        return null;
    }



}
