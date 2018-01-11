package apitest.assertimpl;

import apitest.Assertinfo;
import apitest.assertimpl.assertbase.AssertBase;


import java.util.Map;

import static apitest.assertimpl.assertbase.JsonAssertBase.convertToMap;


/**
 * Created by zhaotang on 2017/10/19.
 */

public class CommonObjectImpl<T,R> extends AssertBase implements AssertActionI<T,R> {


    @Override
    public boolean api_Assert(Assertinfo assertinfo, T request, R Response) {
       Map<String, Object> res1 = convertToMap(Response);
        Map<String, Object> res = convertToMap(request);
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
