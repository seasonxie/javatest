package apitest.assertimpl;

import apitest.AssertC;
import apitest.assertimpl.assertbase.AssertBase;
import com.mashape.unirest.http.HttpResponse;
import apitest.model.Assertinfo;
import apitest.model.EumAction;

import java.lang.reflect.InvocationTargetException;

public class ContextAssertImpl<T, R> extends AssertBase implements AssertActionI<T, R> {
    @Override
    public boolean api_Assert(Assertinfo assertinfo, T apiTest, R Response) {
        String response = "";
        if (Response.getClass() == HttpResponse.class) {
            response = ((HttpResponse) Response).getBody().toString();
        } else {
            response = Response.toString();
        }
        String actionType = assertinfo.getAssert_action().trim();
        return getAction(this.getClass(), actionType, response, assertinfo.getAssert_value());
    }

    @Override
    public Enum getFlag() {
        return EumAction.AssertTypes.CONTEXT;
    }

    @Override
    public boolean matchCheckType(Assertinfo assertinfo, T request, R Response) {
        return false;
    }


    public  boolean action_EQUAL(Object response, Object assertValue) {
        base_EQUAL(response.toString(),assertValue.toString());
        return true;
    }

    public  boolean action_CONTAINS(Object response, Object assertValue) {
        base_CONTAINS(response,assertValue);
        return true;
    }

    public boolean action_SIZE(Object response, Object assertValue) {
        base_SIZE(response,assertValue);
        return true;
    }

    public boolean action_ISEMPTY(Object response, Object assertValue) {
        base_ISEMPTY(response);
        return true;
    }

    public boolean action_KEY_VALUE(Object response, Object assertValue) {

        return false;
    }

    public boolean action_CONTAINS_KEY(Object response, Object assertValue) {

        return false;
    }

    public boolean action_CONTAINS_VALUE(Object response, Object assertValue) {

        return false;
    }
}
