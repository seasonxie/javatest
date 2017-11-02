package ListMap;

/**
 * Created by liwenjing on 2017/11/1.
 */
public class TestLister {
    public static void main(String[] args) {
        Lister head=new Lister(0);

        Lister tem=head;

        int num=10;
        for (int i = 1; i < num; i++) {
            System.out.println(tem.getValue()+"  ----"+i);
            tem.next=new Lister(i);
            tem=tem.next;


        }

        size(head);
        add(head,new Lister(11));
        size(head);
    }

    public static void size(Lister lister){
         int size=0;
        while(lister.next!=null){
            size++;
            lister=lister.next;

        }
        System.out.println(size+"--"+lister.getValue());
    }

    public static void add(Lister head,Lister lister){
         while(head.next!=null){
             head=head.next;
         }
        System.out.println(head.next==null);
        //System.out.println(head.getValue());
        head.next=lister;
    }
}
