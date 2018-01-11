package designpatterns;

import org.assertj.core.api.SoftAssertions;
import org.omg.CORBA.Object;
import org.testng.Assert;

import javax.xml.transform.Source;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AssertJJ {
    public static void main(String[] args) {
        assertThat("abc").as("校验abc")
                .startsWith("ab")
                .endsWith("c").contains("","").doesNotContain("");
//                .isEqualToIgnoringCase("frodo")
//                .hasSize(9);
      java.lang.Object ss="";
      java.lang.Object ss1="";

    }

    public static void test(String sss){
        SoftAssertions ss=new SoftAssertions();

    }
}
