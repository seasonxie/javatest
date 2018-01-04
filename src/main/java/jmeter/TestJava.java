package jmeter;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import java.util.ArrayList;
import java.util.LinkedList;
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

        ArrayList ss= new ArrayList();
        //此处是调用逻辑
        int a=100110;
        for (int i = 0; i < a; i++) {
            ss.add(i);
        }
        for (int i = 0; i < ss.size(); i++) {
            ss.get(i);
        }
        ss.clear();
        sr.setSuccessful(true);
        sr.sampleEnd();
        return sr;
    }

}
