package apitest.model;

public class EumAction {

    public static final String EMPTY_STR = ""; //空字符串

    public enum AssertTypes{
        CODE,CONTEXT,JSONTEXT,JSONLIST,JSONMAP;
    }

    public enum AssertActions{
        EQUAL,CONTAINS,SIZE,ISEMPTY,KEY_VALUE,CONTAINS_KEY,CONTAINS_VALUE,TYPE;
    }


    public enum Method{
        GET,POST;
    }
}
