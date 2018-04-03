package datastructure;

/**
 * Created by liwenjing on 2017/11/1.
 */
public class Link {
    Link next;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    int value;

    public Link(int n){
        this.next=null;
        this.value=n;
    }

    public static void main(String[] args) {
        Link head=new Link(0);

        Link tem=head;

        int num=10;
        for (int i = 1; i < num; i++) {
            System.out.println(tem.getValue()+"  ----"+i);
            tem.next=new Link(i);
            tem=tem.next;


        }

        size(head);
        add(head,new Link(11));
        size(head);
    }

    public static void size(Link lister){
        int size=0;
        while(lister.next!=null){
            size++;
            lister=lister.next;

        }
        System.out.println(size+"--"+lister.getValue());
    }

    public static void add(Link head, Link lister){
        while(head.next!=null){
            head=head.next;
        }
        System.out.println(head.next==null);
        //System.out.println(head.getValue());
        head.next=lister;
    }
}
