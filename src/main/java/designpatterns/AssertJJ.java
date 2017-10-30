package designpatterns;

import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AssertJJ {
    public static void main(String[] args) {
        assertThat("abc").as("校验abc")
                .startsWith("ab")
                .endsWith("do")
                .isEqualToIgnoringCase("frodo")
                .hasSize(9);






    }

    public static void test(String sss){
        SoftAssertions ss=new SoftAssertions();

    }
}
