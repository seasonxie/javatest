package designpatterns;

import org.assertj.db.type.Changes;
import org.assertj.db.type.Source;

public class AssertJDB {
    public static void main(String[] args) {
        Source source = new Source("jdbc:mysql://172.27.1.219:3306/dango?useUnicode=yes&amp;characterEncoding=UTF8&zeroDateTimeBehavior=convertToNull", "write", "!@#$1234%^&*5678ABCDabcd");
        Changes changes = new Changes(source);


    }
}
