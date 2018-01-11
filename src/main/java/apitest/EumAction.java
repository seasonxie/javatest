package apitest;

public class EumAction {
    public enum AssertTypes{
        CODE,CONTEXT,JSONTEXT,JSONLIST,JSONMAP;
    }

    public enum AssertActions{
        EQUAL,CONTAINS,SIZE,ISEMPTY,KEY_VALUE,CONTAINS_KEY,CONTAINS_VALUE;
    }


    public enum Method{
        GET,POST;
    }
}
