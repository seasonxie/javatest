package apitest;

import org.testng.Assert;

import java.util.*;

public class AssertC extends Assert {








    public static boolean isJson(Object expect, Object actual) {
        if ((expect.toString().startsWith("{") && expect.toString().endsWith("}"))
                || (actual.toString().startsWith("{") && actual.toString().endsWith("}"))) {
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

    public static boolean isBaseMapObject(Object expectedValue) {
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
                || expectedValue.getClass().isAssignableFrom(Date.class) || isList(expectedValue)) {
            return true;
        } else {
            return false;
        }
    }

}
