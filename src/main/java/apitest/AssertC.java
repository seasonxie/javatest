package apitest;

import apitest.assertimpl.assertbase.AssertBase;
import apitest.model.EumAction;
import org.testng.Assert;

import java.util.*;

import static apitest.assertimpl.assertbase.JsonAssertBase.convertToMap;

    public class AssertC extends Assert {

    /**
     json->map （原则：只取第一层的keyvalue，如果有多层请处理后再传入）
     map->map
     bean->map
     Amap（actual）-  Bmap（expect）
     1.以expect为验证中心，遍历expect的key
     2.检查actual是否存在这个key，没有记录错误信息（not check和only check 排除）
     3.expect和acutal都存在key，检查key-value
     4.有not check和only check关键字处理
     5.检查key value对应的（type，value）
     */

    static public boolean assertMapEquals(Object actual, Object expect, String... checkInfo) {
        if (AssertBase.isJsonMapObject(actual)) {
            Map<String, Object> actualMap = convertToMap(actual);
            Map<String, Object> expectMap = convertToMap(expect);
            AssertBase.compareCommon(actualMap, expectMap, getCheckKey(checkInfo));
            return true;
        }
        return false;
    }

    static public void assertEquals(Object actual, Object expect) {
        if (!assertMapEquals(actual, expect)) {
            Assert.assertEquals(actual, expect);
        }
    }

        /**
         *
         * @param actual
         * @param expect
         * @param message
         * 当actual和expect不能转换成map做对比的时候，调用testng原生的Asssert
         */
    static public void assertEquals(Object actual, Object expect, String message) {
        if (!assertMapEquals(actual, expect)) {
            Assert.assertEquals(actual, expect, message);
        }
    }

    static public void assertEqualWithNotCheck(Object actual, Object expect, String checkKey) {
        if (!assertMapEquals(actual, expect, AssertBase.NOT_CHECK + checkKey)) {
            Assert.assertEquals(actual, expect);
        }
    }

    static public void assertEqualWithOnlyCheck(Object actual, Object expect, String checkKey) {
        if (!assertMapEquals(actual, expect, AssertBase.ONLY_CHECK + checkKey)) {
            Assert.assertEquals(actual, expect);
        }
    }

    static public void assertEquals(String actual, String expect) {
        if (AssertBase.isJson(actual, expect)) {
            assertMapEquals(actual, expect);
        }
        Assert.assertEquals(actual, expect);
    }

    static public void assertEquals(String actual, String expect, String message) {
        if (AssertBase.isJson(actual, expect)) {
            assertMapEquals(actual, expect);
        }
        Assert.assertEquals(actual, expect,message);
    }

    static public String getCheckKey(String... data) {
        String result = "";
        if (data.length > 0) {
            return data[0];
        }
        return result;
    }

}
