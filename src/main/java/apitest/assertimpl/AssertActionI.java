package apitest.assertimpl;

import apitest.model.Assertinfo;

public interface AssertActionI<T,R> {

    public boolean api_Assert(Assertinfo assertinfo, T request, R Response);

    public Enum getFlag();

    public boolean matchCheckType(Assertinfo assertinfo, T request, R Response);
}
