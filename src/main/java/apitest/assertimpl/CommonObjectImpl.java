package apitest.assertimpl;

import apitest.model.Assertinfo;
import apitest.assertimpl.assertbase.AssertBase;


import java.util.Map;

import static apitest.assertimpl.assertbase.JsonAssertBase.convertToMap;


/**
 * Created by zhaotang on 2017/10/19.
 */

public class CommonObjectImpl<T,R> extends AssertBase implements AssertActionI<T,R> {

    @Override
    public boolean api_Assert(Assertinfo assertinfo, T request, R Response) {
        Map<String, Object> actual = convertToMap(Response);
        Map<String, Object> expect = convertToMap(request);
        return AssertBase.compareCommon(actual,expect,assertinfo.getAssert_value());
    }

    @Override
    public boolean matchCheckType(Assertinfo assertinfo, T request, R Response) {
        return isJsonMapObject(Response);
    }

    @Override
    public Enum getFlag() {
        return null;
    }




}
