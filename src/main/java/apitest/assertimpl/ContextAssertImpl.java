package apitest.assertimpl;

import apitest.assertimpl.assertbase.AssertBase;
import com.mashape.unirest.http.HttpResponse;
import apitest.Assertinfo;
import apitest.EumAction;

import java.lang.reflect.InvocationTargetException;

public class ContextAssertImpl<T,R> extends AssertBase implements AssertActionI<T,R> {
    @Override
    public boolean api_Assert(Assertinfo assertinfo, T apiTest, R Response) {
        String response="";
        if(Response.getClass()==HttpResponse.class){
            response=((HttpResponse)Response).getBody().toString();
        }else{
            response=Response.toString();
        }
        String actionType=assertinfo.getAssert_action().trim();

        try {
            getAction(this.getClass(),actionType,response,assertinfo.getAssert_value());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Enum getFlag() {
        return EumAction.AssertTypes.CONTEXT;
    }

    @Override
    public boolean matchCheckType(Assertinfo assertinfo, T request, R Response) {
        return false;
    }


    public static boolean action_EQUAL(Object response,Object assertValue){
        System.out.println("-------equal  "+assertValue);
        return true;
    }

    public static boolean action_CONTAINS(Object response,Object assertValue){
        System.out.println("-------ccccc  "+assertValue);
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
