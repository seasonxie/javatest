package apitest.assertimpl;

import apitest.AssertC;
import apitest.assertimpl.assertbase.JsonAssertBase;
import com.mashape.unirest.http.HttpResponse;
import apitest.model.Assertinfo;
import apitest.model.EumAction;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

import java.lang.reflect.InvocationTargetException;

public class JsonTextAssertImpl<T, R> extends JsonAssertBase implements AssertActionI<T, R> {
    @Override
    public boolean api_Assert(Assertinfo assertinfo, T apiTest, R Response) {
        HttpResponse Response1 = (HttpResponse) Response;
        if ((assertinfo.getJsonpath() == null || assertinfo.getJsonpath().isEmpty())) {
            assertinfo.setJsonpath("$.");
        }
        return getAction(JsonTextAssertImpl.class, assertinfo.getAssert_action(),
                getJsonObject(Response1.getBody().toString().trim(), assertinfo), assertinfo);

    }


    @Override
    public Enum getFlag() {
        return EumAction.AssertTypes.JSONTEXT;
    }

    @Override
    public boolean matchCheckType(Assertinfo assertinfo, T request, R Response) {
        return false;
    }


    public static boolean action_TYPE(Object response, Object assertValue) {
        String valueType = ((Assertinfo) assertValue).getValue_class();
        checkType(response, valueType);
        return true;
    }

    public static boolean action_EQUAL(Object response, Object assertValue) {
        Object expect = ((Assertinfo) assertValue).getAssert_value();
        String valueType = ((Assertinfo) assertValue).getValue_class();
        Object actual = null;
        if (response.getClass() == JSONArray.class) {
            base_EQUAL(actual = ((JSONArray) response).toJSONString(), expect);
        } else if (response.getClass() == JSONObject.class) {
            base_EQUAL(actual = ((JSONObject) response).toJSONString(), expect);
        } else {
            base_EQUAL(actual = response, expect);
        }
        if (valueType != null && !valueType.isEmpty()) {
            checkType(actual, valueType);
        }
        return true;
    }

    public static boolean action_CONTAINS(Object response, Object assertValue) {
        Object expect = ((Assertinfo) assertValue).getAssert_value();
        String valueType = ((Assertinfo) assertValue).getValue_class();
        Object actual = null;
        if (response.getClass() == JSONArray.class) {
            base_CONTAINS(actual = ((JSONArray) response).toJSONString(), expect);
        } else if (response.getClass() == JSONObject.class) {
            base_CONTAINS(actual = ((JSONObject) response).toJSONString(), expect);
        } else {
            base_CONTAINS(actual = response, expect);
        }
        if (valueType != null && !valueType.isEmpty()) {
            checkType(actual, valueType);
        }
        return true;
    }

    public boolean action_SIZE(Object response, Object assertValue) {
        Object expect = ((Assertinfo) assertValue).getAssert_value();
        if (response.getClass() == JSONArray.class) {
            json_SIZE(((JSONArray) response).size(), Integer.valueOf(expect.toString()));
        } else if (response.getClass() == JSONObject.class) {
            json_SIZE(((JSONObject) response).size(), Integer.valueOf(expect.toString()));
        } else {
            base_CONTAINS(response.toString(), expect);
        }
        return true;
    }

    public boolean action_ISEMPTY(Object response, Object assertValue) {
        Object expect = ((Assertinfo) assertValue).getAssert_value();
        if (response.getClass() == JSONArray.class) {
            base_ISEMPTY(((JSONArray) response).toJSONString());
        } else if (response.getClass() == JSONObject.class) {
            base_ISEMPTY(((JSONObject) response).toJSONString());
        } else {
            base_ISEMPTY(response);
        }
        return true;
    }

    public boolean action_KEY_VALUE(Object response, Object assertValue) {
        Object expect = ((Assertinfo) assertValue).getAssert_value();
        try {
            String[] key_value = expect.toString().split(":");
            if (response.getClass() == JSONObject.class) {
                base_EQUAL(((JSONObject) response).get(key_value[0]).toString(), key_value[1].toString());
                return true;
            }
        } catch (Exception e) {
            System.out.println(expect + " is not format of  $key:$value");
            e.printStackTrace();
        }
        AssertC.fail("Json is not jsonObject,  " + response);
        return false;
    }

    public boolean action_CONTAINS_KEY(Object response, Object assertValue) {
        Object expect = ((Assertinfo) assertValue).getAssert_value();
        if (response.getClass() == JSONObject.class) {
            AssertC.assertTrue(((JSONObject) response).containsKey(expect), getMessage(response, expect, EumAction.AssertActions.CONTAINS_KEY.toString()));
            return true;
        }
        AssertC.fail("Json is not jsonObject,  " + response);
        return false;
    }

    public boolean action_CONTAINS_VALUE(Object response, Object assertValue) {
        Object expect = ((Assertinfo) assertValue).getAssert_value();
        try {
            expect= new Integer(expect.toString());
        } catch (NumberFormatException e) {
        }
        if (response.getClass() == JSONArray.class) {
            try {
                expect= JSONValue.parse(expect.toString());
            } catch (NumberFormatException e) {
            }
            AssertC.assertTrue(((JSONArray) response).contains(expect), getMessage(response, expect, "JSONArray list contains"));
        } else if (response.getClass() == JSONObject.class) {
            AssertC.assertTrue(((JSONObject) response).containsValue(expect), getMessage(response, expect, EumAction.AssertActions.CONTAINS_VALUE.toString()));
        } else {
            AssertC.assertTrue(response.toString().indexOf(expect.toString()) !=-1, getMessage(response, expect, "String indexOf"));
        }
        return true;
    }
}
