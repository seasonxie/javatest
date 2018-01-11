package apitest.assertimpl;

import com.mashape.unirest.http.HttpResponse;
import apitest.Assertinfo;
import apitest.EumAction;


public class CodeAssertImpl<T,R extends HttpResponse> implements AssertActionI<T,R> {


    @Override
    public boolean api_Assert(Assertinfo assertinfo, T apiTest, R Response) {

        if(assertinfo.getAssert_action().equals(EumAction.AssertActions.EQUAL.toString())){
            System.out.println("code EQUAL");
            System.out.println( String.valueOf( Response.getStatus()).equals(assertinfo.getAssert_value())==true);
        }else if((assertinfo.getAssert_action().equals(EumAction.AssertActions.CONTAINS.toString()))){
            System.out.println("code CONTAINS");
            System.out.println( String.valueOf( Response.getStatus()).contains(assertinfo.getAssert_value())==true);
        }
        return false;
    }

    @Override
    public Enum getFlag() {
        return EumAction.AssertTypes.CODE;
    }

    @Override
    public boolean matchCheckType(Assertinfo assertinfo, T request, R Response) {
        return false;
    }
}
