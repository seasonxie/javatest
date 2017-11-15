package ListMap;

public class LinkTable {

    public static void main(String[] args) {
        LinkTables a = new LinkTables();
        a.add(1);
        a.add("sss");
        a.add(3);
        a.get(1);
        a.getLast(1);
        a.size();
    }

}

class LinkTables {
    LinkTables next;
    LinkTables last;
    Object Value;

    public Object getValue() {
        return Value;
    }

    public void setValue(Object value) {
        Value = value;
    }

    static LinkTables after;
    static LinkTables start;

    public void add(Object value) {
        if (after == null) {
            after = new LinkTables();
            after.setValue(value);
            start = after;
            return;
        }
        after.next = new LinkTables();
        LinkTables last = after;
        after = after.next; //开启新链表
        after.setValue(value);
        after.last = last;
    }

    public void get(Object value) {
        LinkTables find = start;
        while (!find.Value.equals(value)) {
            find = find.next;
        }
        System.out.println(find.next.Value + " ----get----");
    }

    public void getLast(Object value) {
        LinkTables find = after;
        while (!find.Value.equals(value)) {
            find = find.last;
        }
        System.out.println(find.next.Value + " ----getLast----");
    }

    public void size() {
        LinkTables find = start;
        int num = 0;
        while (find.next != null) {
            find = find.next;
            num++;
        }
        System.out.println(num + " ----num----");
    }
}
