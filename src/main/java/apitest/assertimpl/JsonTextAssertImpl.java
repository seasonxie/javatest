package apitest.assertimpl;

import apitest.assertimpl.assertbase.JsonAssertBase;
import com.mashape.unirest.http.HttpResponse;
import apitest.Assertinfo;
import apitest.EumAction;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import java.lang.reflect.InvocationTargetException;

public class JsonTextAssertImpl<T, R>  extends JsonAssertBase implements AssertActionI<T, R>{
    @Override
    public boolean api_Assert(Assertinfo assertinfo, T apiTest, R Response) {
        HttpResponse Response1 = (HttpResponse) Response;
   /*     if ((assertinfo.getAssert_action().equals(EumAction.AssertActions.CONTAINS.toString()))) {
            if (assertinfo.getJsonpath() == null || assertinfo.getJsonpath().isEmpty()) {
                System.out.println("json CONTAINS");
                System.out.println(String.valueOf(Response1.getBody()).contains(assertinfo.getAssert_value()) == true);
            } else {
                System.out.println("jsonpath CONTAINS");
                String verifyText = "";
                verifyText = String.valueOf(getJsonObject(verifyText,Response1.getBody().toString().trim(),assertinfo.getJsonpath()));
                System.out.println(String.valueOf(verifyText.contains(assertinfo.getAssert_value()) == true));
            }

        }*/
        if (assertinfo.getJsonpath() == null || assertinfo.getJsonpath().isEmpty()) {
            System.out.println("json CONTAINS");
            System.out.println(String.valueOf(Response1.getBody()).contains(assertinfo.getAssert_value()) == true);
        } else {
            System.out.println("jsonpath CONTAINS");
            handlerJsonAssert(Response1.getBody().toString().trim(),assertinfo.getJsonpath(),assertinfo.getAssert_value(),assertinfo.getAssert_action());
        }

        return false;
    }

    public static void handlerJsonAssert(String json,String jsonPath,Object checkValue,String act){
        Object o=getJsonObject(json,jsonPath);
        try {
            getAction(JsonTextAssertImpl.class,act.toString(),o,checkValue);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }



    @Override
    public Enum getFlag() {
        return EumAction.AssertTypes.JSONTEXT;
    }

    @Override
    public boolean matchCheckType(Assertinfo assertinfo, T request, R Response) {
        return false;
    }


    public static boolean action_EQUAL(Object response,Object assertValue){
        System.out.println("-------equal  "+assertValue);
        if(response.getClass()==JSONArray.class){

        }else if(response.getClass()==JSONObject.class){

        }else{

        }
        return true;
    }

    public static boolean action_CONTAINS(Object response,Object assertValue){
        if(response.getClass()==JSONArray.class){
            //((JSONArray)response)
        }else if(response.getClass()==JSONObject.class){
            //((JSONObject)response)
        }else{
            System.out.println("-------ccccc11  "+response);
            System.out.println("-------ccccc  "+assertValue);
        }
        return true;
    }

    public boolean action_SIZE(Object response,Object assertValue){

        return true;
    }

    public boolean action_ISEMPTY(Object response,Object assertValue){

        return true;
    }

    public boolean action_KEY_VALUE(Object response,Object assertValue){

        return true;
    }

    public boolean action_CONTAINS_KEY(Object response,Object assertValue){

        return true;
    }

    public boolean action_CONTAINS_VALUE(Object response,Object assertValue){

        return true;
    }
}
