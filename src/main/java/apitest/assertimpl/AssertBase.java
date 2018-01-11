package apitest.assertimpl;

import com.jayway.jsonpath.JsonPath;
import apitest.EumAction;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.testng.Assert;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssertBase {

    static AssertBase a=new AssertBase();
    static String sjson = "{\"store\": {\"book\": [{\"category\": \"reference\",\"author\": \"Nigel Rees\",\"title\": \"Sayings of the Century\",\"price\": 8.95},{\"category\": \"fiction\",\"author\": \"Evelyn Waugh\",\"title\": \"Sword of Honour\",\"price\": 12.99},{\"category\": \"fiction\",\"author\": \"Herman Melville\",\"title\": \"Moby Dick\",\"isbn\": \"0-553-21311-3\",\"price\": 8.99},{\"category\": \"fiction\",\"author\": \"J. R. R. Tolkien\",\"title\": \"The Lord of the Rings\",\"isbn\": \"0-395-19395-8\",\"price\": 22.99}],\"bicycle\": {\"color\": \"red\",\"price\": 19.95}},\"expensive\": 10}";


    public static boolean getAction(Class<?> c, String act, Object response, Object assertValue) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Object obj = c.newInstance();
        Method[] methods=c.getDeclaredMethods();
        for (EumAction.AssertActions e : EumAction.AssertActions.values()) {
            if(e.toString().equals(act.toString())){
                for(Method m:methods){
                    if(m.getName().contains(act.toString())){
                        Method method=c.getMethod(m.getName(), Object.class,Object.class);
                        boolean str2= (Boolean) method.invoke(obj, new Object[]{response,assertValue});
                        System.out.println(str2);
                        return str2;
                    }
                }
            }
        }
        Assert.fail("No assertAction: "+act +" in "+c.getSimpleName());
        return true;
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


    public static void main(String[] args) {
        System.out.println(a.getJsonObject(sjson,"$.store.*").getClass());
        System.out.println(a.getJsonObject(sjson,"$.expensive").getClass());
        System.out.println(a.getJsonObject(sjson,"$.store.book[*]").getClass());
        System.out.println(a.getJsonObject(sjson,"$.store.book[*].author").getClass());
        System.out.println(a.getJsonObject(sjson,"$.store.bicycle").getClass());

        for (EumAction.AssertActions e : EumAction.AssertActions.values()) {
            System.out.println(e.toString());
        }
    }

    public static void handlerJsonAssert(Object o,String checkValue,Class checkType,EumAction.AssertActions act){
          if(o.getClass()==JSONArray.class){

          }else if(o.getClass()==JSONObject.class){

          }else{
              System.out.println(o.getClass()==checkType);
              System.out.println(String.valueOf(o).contains(checkValue));
          }
    }

    public static void main1(String[] args) {
        List<Object> authors1 =null;
        //authors1=a.getJsonObject(authors1,sjson,"$.store.book[*].author");
        authors1=a.getJsonObject(authors1,sjson,"$.store.*");
        System.out.println(authors1.size());
        if(authors1.size()>0){
            System.out.println(authors1.get(0));
        }
        for(Object as:authors1){
            System.out.println(as.getClass());
            System.out.println(as.toString());

            if(as.getClass()==JSONArray.class){
                System.out.println(((JSONArray)as).toJSONString());
                System.out.println(((JSONArray)as).toJSONString().contains("ssssssssss"));
            }
            if(as.getClass()==JSONObject.class){
                System.out.println(((JSONObject)as).toString());
                System.out.println(((JSONObject)as).toString().contains("ssssssssss"));
            }
        }

        Map ss=new HashMap();
        ss=a.getJsonObject(ss,sjson,"$.store.bicycle");
        System.out.println(ss);
    }
}
