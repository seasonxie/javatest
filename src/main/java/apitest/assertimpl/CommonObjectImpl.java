package apitest.assertimpl;

import apitest.Assertinfo;


import java.util.Map;

import static apitest.AssertC.isBaseMapObject;


/**
 * Created by zhaotang on 2017/10/19.
 */

public class CommonObjectImpl<T,R> implements AssertActionI<T,R> {


    @Override
    public boolean api_Assert(Assertinfo assertinfo, T request, R Response) {
    /*    Map<String, Object> res1 = Asserts.convertToMap(Response);
        Map<String, Object> res = Asserts.convertToMap(request);
        return Asserts.RETURN(Valid.compareCommon(res1, res, Asserts.getCheckKey(assertinfo.getAssert_value()))).get(CustomAssert.RETURN);*/
    return false;
    }

    @Override
    public Enum getFlag() {
        return null;
    }

    @Override
    public boolean matchCheckType(Assertinfo assertinfo, T request, R Response) {
        return isBaseMapObject(Response);
    }


}
