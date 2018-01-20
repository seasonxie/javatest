package jmeter;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class TestJava  extends AbstractJavaSamplerClient {
    private static AtomicInteger temp = new AtomicInteger();

    @Override
    public Arguments getDefaultParameters() {
        System.out.println("===========init parameters ========");
        Arguments arg = new Arguments();

        return arg;
    }
    @Override
    public SampleResult runTest(JavaSamplerContext paramJavaSamplerContext) {
        SampleResult sr = new SampleResult();
        sr.sampleStart();
       System.out.println("12311111111111111111111111111111111111111111111111111111111111111111");

        int a=new Random().nextInt(1000) +1;
        if(a%4==0){
            try {
                Integer.valueOf(a+"ddd");
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            sr.setSuccessful(false);
        }else{
            sr.setSuccessful(true);
        }



        sr.sampleEnd();
        return sr;
    }

}
