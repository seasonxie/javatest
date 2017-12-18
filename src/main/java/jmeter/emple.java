package jmeter;


import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.protocol.http.sampler.HTTPSampler;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.SetupThreadGroup;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jmeter.threads.ThreadGroup;
import org.apache.jorphan.collections.HashTree;
import org.apache.jorphan.logging.LoggingManager;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class emple {

    @Test
    public void example() throws IOException {
        System.setProperty("http.proxyHost", "localhost");
        System.setProperty("http.proxyPort", "8888");
        System.setProperty("https.proxyHost", "localhost");
        System.setProperty("https.proxyPort", "8888");

        //JMeter Engine
        StandardJMeterEngine jmeter = new StandardJMeterEngine();

        //JMeter initialization (properties, log levels, locale, etc)
        JMeterUtils.setJMeterHome("D:\\ajmeter");
        JMeterUtils.loadJMeterProperties("D:\\ajmeter\\bin\\jmeter.properties");
        JMeterUtils.setProperty("jmeter.save.saveservice.output_format", "xml");
        File log = new File("D:\\perfTest.log");

        JMeterUtils.setProperty(LoggingManager.LOG_FILE, log.getAbsolutePath());
        JMeterUtils.initLogging();// you can comment this line out to see extra log messages of i.e. DEBUG level
        JMeterUtils.initLocale();
        // Initialize JMeter SaveService
        SaveService.loadProperties();

        // JMeter Test Plan, basic all u JOrphan HashTree
        HashTree testPlanTree = new HashTree();

        // HTTP Sampler
        HTTPSampler httpSampler = new HTTPSampler();
       httpSampler.setDomain("http://rtb.flyme.cn");
        httpSampler.setPort(80);
        httpSampler.setPath("/dsp/track/click?p=QUM2QjdGQTY5RkEzNzVEMEZENzA0NjkwNzcwNDVGOTQ4Q0VDN0NGRkQxRDNDQTZEMkUyN0Q5NENDNTJFNkMyQzkzNTUxMTQzRDU3NDUxN0MxNjk4NUNDRUQ4OTEwMTE4OEYxM0ZBNjM0REM5OTBEOTMzMzA1QjMwM0Q0MTQ3NzcyMEJEMTM1ODAxNjJEMUMxNEEwNjA5Qzc3RkQ4MTk2NzAwNDhBNkUzMUZFQUI4Q0MzRDc4Q0I3RDM5OEQ1ODZGRkEzMkI1QUU4RjVDRUUzOTAwOUJDM0EzRTVDRTVFOUI0QjExQjdCNjU0Q0ZFNDU0QUYyNDEzRTcwRDgxQzRGMkRDNTMxOEE4MThERDZCOTRCMzVBRUFBNDNCNEVEOEI1Qzc5MTYxQzUzREIwRDAxMTBFQjIyMzBFN0VEMkM1RDE1MDA5ODYzMjM5OTgyMjdEOEJFQkZBMzUxNDlDREUyNkQwRjE0NDE0OENENENENkJFMzIxOTc0ODYzNTA1M0M1RDI4MjQzODA1RTZCRThFMDU4MTEzMzBDN0I5NDQ0QjE3ODQ2NjYyNkU3MEYwMjlEQUYyNThERUJEOEU3Q0MwRDFBRThFMUFFRTYyMEQ4OTlFOTdENDUzMDBFQTAyQkE5OTg0MkJEN0Y0Njg3NkFBQTI5NUI0NEE0NzhGM0M2MjlCNEE3N0I2Qjc3MzE1MDU2ODY1RjI3RjVCNTRGMTc5MDExRUE0NzAzREVBNDk0RDg5OUE4RDc5QTc4NzhERjkxRkRFQ0MwREY2Mzg2QkVBNkFBNUY4QUVDNEVBRjJCM0Q3ODNFQzVENDE5NkJFNEFFNEQ5MTRCMzg2MjExQjg1RjJFRkQ4MjUxM0U1RDYyM0FDNjdGQTg4NjczRTYwOTBD&price=");
        httpSampler.setMethod("GET");

/*        // Loop Controller
        LoopController loopController = new LoopController();
        loopController.setLoops(1);
        loopController.addTestElement(httpSampler);
        loopController.setFirst(true);
        loopController.initialize();
        // Thread Group
        ThreadGroup threadGroup = new ThreadGroup();
        threadGroup.setNumThreads(1);
        threadGroup.setRampUp(1);
        threadGroup.setSamplerController(loopController);

        // Test Plan
        TestPlan testPlan = new TestPlan("Create JMeter Script From Java Code");
        // Construct Test Plan from previously initialized elements
        testPlanTree.add("testPlan", testPlan);
        testPlanTree.add("loopController", loopController);
        testPlanTree.add("threadGroup", threadGroup);
        testPlanTree.add("httpSampler", httpSampler);
        // Run Test Plan
        jmeter.configure(testPlanTree);
        jmeter.run();*/


        TestElement loopCtrl =new LoopController();
        ((LoopController)loopCtrl).setLoops(1);
        ((LoopController)loopCtrl).addTestElement(httpSampler);
        ((LoopController)loopCtrl).setFirst(true);
        SetupThreadGroup threadGroup1=new SetupThreadGroup(); //线程组
        threadGroup1.setNumThreads(1);
        threadGroup1.setRampUp(1);
        threadGroup1.setSamplerController((LoopController)loopCtrl);
        TestPlan testPlan1 = new TestPlan("Create JMeter Script From Java Code");  //测试计划
        testPlanTree.add(testPlan1);
        testPlanTree.add(threadGroup1);
        testPlanTree.add(httpSampler);
        testPlanTree.add(loopCtrl);

        MyResultCollector requestCollector = new MyResultCollector();
        requestCollector.setFilename("d://aaaaaaresult.log");
        testPlanTree.add( requestCollector);

        jmeter.configure(testPlanTree);
        System.out.println(jmeter.isActive());
        jmeter.run();

        Map<Integer,List<SampleResult>> sampleResultsMap = requestCollector.getSampleResults();
        System.out.println(sampleResultsMap.size());
        for (Object key : sampleResultsMap.keySet()) {
            List<SampleResult> list = sampleResultsMap.get(key);
            /*for(SampleResult s : list){
                System.out.println(s.getTime()+"fffff");
            }*/
            System.out.println(list.size()+"kkkkkkkkkkk");

        }
    }
}
