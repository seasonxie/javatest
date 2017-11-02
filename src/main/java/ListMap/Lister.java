package ListMap;

/**
 * Created by liwenjing on 2017/11/1.
 */
public class Lister {
    Lister next;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    int value;

    public Lister(int n){
        this.next=null;
        this.value=n;
    }
}
