package apitest.assertimpl;

import apitest.model.Assertinfo;

public interface AssertActionI<T,R> {

    /**
     *
     * @param assertinfo
     * @param actual
     * @param expect
     * @return
     */
    public boolean api_Assert(Assertinfo assertinfo, T actual, R expect);

    /**
     * 是否符合验证类型
     * @param assertinfo
     * @param actual
     * @param expect
     * @return
     */
    public boolean matchCheckType(Assertinfo assertinfo, T actual, R expect);

    /**
     * EumAction.
     * @return
     */
    public Enum getFlag();


}
