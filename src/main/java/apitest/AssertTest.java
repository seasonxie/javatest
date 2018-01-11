package apitest;

import apitest.assertimpl.assertbase.AssertBase;
import apitest.assertimpl.assertbase.JsonAssertBase;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssertTest {


    static JsonAssertBase a=new JsonAssertBase();
    static String sjson = "{\"store\": {\"book\": [{\"category\": \"reference\",\"author\": \"Nigel Rees\",\"title\": \"Sayings of the Century\",\"price\": 8.95},{\"category\": \"fiction\",\"author\": \"Evelyn Waugh\",\"title\": \"Sword of Honour\",\"price\": 12.99},{\"category\": \"fiction\",\"author\": \"Herman Melville\",\"title\": \"Moby Dick\",\"isbn\": \"0-553-21311-3\",\"price\": 8.99},{\"category\": \"fiction\",\"author\": \"J. R. R. Tolkien\",\"title\": \"The Lord of the Rings\",\"isbn\": \"0-395-19395-8\",\"price\": 22.99}],\"bicycle\": {\"color\": \"red\",\"price\": 19.95}},\"expensive\": 10}";


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
