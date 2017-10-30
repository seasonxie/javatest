package designpatterns;

public class signer {
    private static final signer INSTANCE = new signer();
    public static signer instance() {
        return INSTANCE;
    }
}
