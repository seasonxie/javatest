package apitest.assertimpl.assertbase;

import apitest.AssertC;
import apitest.model.Assertinfo;
import apitest.model.EumAction;
import org.testng.Assert;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class AssertBase {
    public final static String ONLY_CHECK = "only_check";
    public final static String NOT_CHECK = "not_check";

    public static String getMessage(Object expect,Object actual,String action){
        return "[["+action+"]]  [actual] "+actual.toString()+"  [Expect] "+expect.toString();
    }
    public static boolean checkType(Object response,String type){
        System.out.println("CheckType: "+response +" is "+type);
        AssertC.assertEquals(response.getClass().getSimpleName().toUpperCase(),type.trim().toUpperCase());
        return true;
    }


    public static boolean base_EQUAL(Object response, Object assertValue) {
        AssertC.assertEquals(response, assertValue, getMessage(response, assertValue,
                EumAction.AssertActions.EQUAL.toString()));
        return true;
    }

    public static boolean base_EQUAL(String response, String assertValue) {
        AssertC.assertEquals(response.trim(), assertValue.trim(), getMessage(response, assertValue,
                EumAction.AssertActions.EQUAL.toString()));
        return true;
    }

    public static boolean base_CONTAINS(Object response, Object assertValue) {
        AssertC.assertTrue(response.toString().trim().contains(assertValue.toString().trim()), getMessage(response, assertValue,
                EumAction.AssertActions.CONTAINS.toString()));
        return true;
    }

    public static boolean base_SIZE(Object response, Object assertValue) {
        AssertC.assertEquals(response.toString().length(),assertValue, getMessage(response, assertValue,
                EumAction.AssertActions.SIZE.toString()));
        return true;
    }

    public static boolean base_ISEMPTY(Object response) {
        AssertC.assertTrue(response.toString().isEmpty(), getMessage(response, "not empty",
                EumAction.AssertActions.ISEMPTY.toString()));
        return true;
    }


    public boolean base_CONTAINS_KEY(Object response, Object assertValue) {
        AssertC.assertTrue(((Map)response).containsKey(assertValue), getMessage(response, assertValue,
                EumAction.AssertActions.CONTAINS_KEY.toString()));
        return true;
    }

    public static boolean base_CONTAINS_VALUE(Object response, Object assertValue) {
        AssertC.assertTrue(((Map)response).containsValue(assertValue), getMessage(response, assertValue,
                EumAction.AssertActions.CONTAINS_VALUE.toString()));
        return true;
    }


    public static boolean getAction(Class<?> c, String act, Object response, Object assertValue) {
        Object obj = null;
        try {
            obj = c.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Method[] methods = c.getDeclaredMethods();
        for (EumAction.AssertActions e : EumAction.AssertActions.values()) {
            if (e.toString().equals(act.toString())) {
                for (Method m : methods) {
                    if (m.getName().replace("action_","").trim().equals(act.toString().trim())) {
                        boolean str2=false;
                        try {
                            Method method = c.getMethod(m.getName(), Object.class, Object.class);
                            str2 = (Boolean) method.invoke(obj, new Object[]{response, assertValue});
                            System.out.println(str2);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                            if(assertValue.getClass()== Assertinfo.class){
                                assertValue=((Assertinfo)assertValue).getAssert_value();
                            }
                            System.out.println(m.getName()+" -- "+response+" -- "+assertValue);
                        }
                        return str2;
                    }
                }
            }
        }
        Assert.fail("No assertAction: " + act + " in " + c.getSimpleName());
        return true;
    }

    public static boolean compareCommon(Map<String, Object> expect, Map<String, Object> actual, String onlyCheck) {
        int checkTimes = 0;
        String result = "";
        for (Object map : expect.entrySet()) {
            String key = (String) ((Map.Entry) map).getKey();
            Object actual_value = actual.get(key);
            Object expect_value = ((Map.Entry) map).getValue();
            if (actual.containsKey(key))
                if (expect_value != null) {
                    if (!onlyCheck.isEmpty()) {
                        if (onlyCheck.contains(ONLY_CHECK))
                            if (!onlyCheck.replace(ONLY_CHECK, "").contains(key)) {
                                System.out.println(ONLY_CHECK + key);
                                continue;
                            }

                        if (onlyCheck.contains(NOT_CHECK))
                            if (onlyCheck.replace(NOT_CHECK, "").contains(key)) {
                                System.out.println(NOT_CHECK + key);
                                continue;
                            }
                    }
                    checkTimes++;
                    System.out.println("CheckKey " + key);
                    result += asserts(key, expect_value, actual_value);
                }
        }
        System.out.println("checkTimes " + checkTimes);
        Assert.assertTrue(result.trim().isEmpty(), result);
        return true;
    }


    public static String asserts(String key, Object ex, Object ac) {
        String result = "";
        if (ac == null) {
            result += "【" + key + "】 false actual_value is null ";
            return result;
        }
        if (ex.getClass() != ac.getClass()) {
            result += "【" + key + "】 " + ex.toString() + " [false type not match] " + ac.toString() + " ";
        }
        if (!ac.equals(ex)) {
            result += "【" + key + "】 " + ex.toString() + " [false value not match] " + ac.toString() + " ";
        }
       /* if(result.isEmpty()){
            result="【"+key+"】 check passed";
        }*/
        return result;
    }


    public static boolean isJson(Object expect, Object actual) {
        if ((expect.toString().startsWith("{") && expect.toString().endsWith("}"))
                && (actual.toString().startsWith("{") && actual.toString().endsWith("}"))) {
            return true;
        }
        return false;
    }

    public static boolean isJson(Object expect) {
        if ((expect.toString().startsWith("{") && expect.toString().endsWith("}"))
                || (expect.toString().startsWith("[") && expect.toString().endsWith("]"))) {
            return true;
        }
        return false;
    }

    public static boolean isPrimitive(Object expect, Object actual) {
        if (expect.getClass().isPrimitive() || actual.getClass().isPrimitive()) {
            return true;
        }
        return false;
    }

    public static boolean isList(Object expect) {
        if (expect.getClass().isAssignableFrom(List.class)
                || expect.getClass().isAssignableFrom(ArrayList.class)
                || expect.getClass().isAssignableFrom(LinkedList.class)
                || expect.getClass().isAssignableFrom(List.class)) {
            return true;
        }
        return false;
    }

    public static boolean isList(Object expect, Object actual) {
        if (expect.getClass().isAssignableFrom(List.class)
                || expect.getClass().isAssignableFrom(ArrayList.class)
                || expect.getClass().isAssignableFrom(LinkedList.class)
                || actual.getClass().isAssignableFrom(List.class)
                || actual.getClass().isAssignableFrom(ArrayList.class)
                || actual.getClass().isAssignableFrom(LinkedList.class)) {
            return true;
        }
        return false;
    }


    public static boolean isMap(Object expect) {
        if (expect.getClass().isAssignableFrom(Map.class)
                || expect.getClass().isAssignableFrom(HashMap.class)
                || expect.getClass().isAssignableFrom(LinkedHashMap.class)) {
            return true;
        }
        return false;
    }

    public static boolean isJsonMapObject(Object expectedValue) {
        if (isPrimitiveAndList(expectedValue)) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isPrimitiveAndList(Object expectedValue) {
        if (isJson(expectedValue.toString())) {
            return false;
        }
        if (expectedValue.getClass().isPrimitive() || expectedValue.getClass().isAssignableFrom(String.class)
                || expectedValue.getClass().isAssignableFrom(Boolean.class)
                || expectedValue.getClass().isAssignableFrom(Integer.class)
                || expectedValue.getClass().isAssignableFrom(Double.class)
                || expectedValue.getClass().isAssignableFrom(Float.class)
                || expectedValue.getClass().isAssignableFrom(Short.class)
                || expectedValue.getClass().isAssignableFrom(Byte.class)
                || expectedValue.getClass().isAssignableFrom(Long.class)
                || expectedValue.getClass().isAssignableFrom(Float.class)
                || expectedValue.getClass().isAssignableFrom(Date.class) || isList(expectedValue) || expectedValue.getClass().isArray()) {
            return true;
        } else {
            return false;
        }
    }
}
