package jmeter;

import org.apache.jmeter.assertions.ResponseAssertion;
import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.protocol.http.control.CookieManager;
import org.apache.jmeter.protocol.http.sampler.HTTPSampler;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.samplers.SampleSaveConfiguration;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import org.testng.annotations.Test;
import org.apache.jmeter.threads.ThreadGroup;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

public class new1 {

    @Test
    public  void testPost(){



        // Engine
        StandardJMeterEngine jm = new StandardJMeterEngine();
        // jmeter.properties
        JMeterUtils.loadJMeterProperties("D://ajmeter//bin//jmeter.properties");
        JMeterUtils.setJMeterHome("D://ajmeter");
        JMeterUtils.setLocale(new Locale("ignoreResources"));
        HashTree hashTree = new HashTree();


      /*  //reponse asertion
        ResponseAssertion ra = new ResponseAssertion();
        ra.setTestFieldResponseData();
        ra.setToContainsType();
        ra.addTestString("kakalot8x08");
*/

        // HTTP Sampler

        HTTPSampler httpSampler = new HTTPSampler();
        httpSampler.setName("TestCodeproject");
        httpSampler.setDomain("rtb.flyme.cn");
        httpSampler.setPort(80);
        httpSampler.setProtocol("http");
        httpSampler.setPath("/dsp/track/click?p=QUM2QjdGQTY5RkEzNzVEMEZENzA0NjkwNzcwNDVGOTQ4Q0VDN0NGRkQxRDNDQTZEMkUyN0Q5NENDNTJFNkMyQzkzNTUxMTQzRDU3NDUxN0MxNjk4NUNDRUQ4OTEwMTE4OEYxM0ZBNjM0REM5OTBEOTMzMzA1QjMwM0Q0MTQ3NzcyMEJEMTM1ODAxNjJEMUMxNEEwNjA5Qzc3RkQ4MTk2NzAwNDhBNkUzMUZFQUI4Q0MzRDc4Q0I3RDM5OEQ1ODZGRkEzMkI1QUU4RjVDRUUzOTAwOUJDM0EzRTVDRTVFOUI0QjExQjdCNjU0Q0ZFNDU0QUYyNDEzRTcwRDgxQzRGMkRDNTMxOEE4MThERDZCOTRCMzVBRUFBNDNCNEVEOEI1Qzc5MTYxQzUzREIwRDAxMTBFQjIyMzBFN0VEMkM1RDE1MDA5ODYzMjM5OTgyMjdEOEJFQkZBMzUxNDlDREUyNkQwRjE0NDE0OENENENENkJFMzIxOTc0ODYzNTA1M0M1RDI4MjQzODA1RTZCRThFMDU4MTEzMzBDN0I5NDQ0QjE3ODQ2NjYyNkU3MEYwMjlEQUYyNThERUJEOEU3Q0MwRDFBRThFMUFFRTYyMEQ4OTlFOTdENDUzMDBFQTAyQkE5OTg0MkJEN0Y0Njg3NkFBQTI5NUI0NEE0NzhGM0M2MjlCNEE3N0I2Qjc3MzE1MDU2ODY1RjI3RjVCNTRGMTc5MDExRUE0NzAzREVBNDk0RDg5OUE4RDc5QTc4NzhERjkxRkRFQ0MwREY2Mzg2QkVBNkFBNUY4QUVDNEVBRjJCM0Q3ODNFQzVENDE5NkJFNEFFNEQ5MTRCMzg2MjExQjg1RjJFRkQ4MjUxM0U1RDYyM0FDNjdGQTg4NjczRTYwOTBD&price=");
        httpSampler.setMethod("GET");
      /*  httpSampler.addArgument("FormName", "MenuBarForm");
        httpSampler.addArgument("Email", "kakalot8x08@gmail.com");
        httpSampler.addArgument("Password", "tititi");
        httpSampler.setFollowRedirects(true);
        httpSampler.setUseKeepAlive(true);*/




        //httpSampler.addTestElement(ra);




        //httpSampler.addTestElement(ra);









        // Loop Controller



        TestElement loopCtrl = new LoopController();
        ((LoopController)loopCtrl).setLoops(1);
        ((LoopController)loopCtrl).addTestElement(httpSampler);
        // ((LoopController)loopCtrl).addTestElement(ra);
        ((LoopController)loopCtrl).setFirst(true);





        // Thread Group
        ThreadGroup threadGroup = new ThreadGroup();
        threadGroup.setName("TestGoogle");

        //SetupThreadGroup threadGroup = new SetupThreadGroup();

        threadGroup.setNumThreads(1);
        threadGroup.setRampUp(1);

        threadGroup.setSamplerController((LoopController)loopCtrl);
        // threadGroup.addTestElement(ra);








        // Test plan
        TestPlan testPlan = new TestPlan("MY TEST PLAN");

        testPlan.addTestElement(loopCtrl);


        Summariser summer = null;
        String summariserName = JMeterUtils.getPropDefault("summariser.name", "summary");//$NON-NLS-1$
        if (summariserName.length() > 0) {
            summer = new Summariser(summariserName);
        }

        MyResultCollector requestCollector = new MyResultCollector();
        requestCollector.setFilename("d://aaaaaaresult.log");

        String logFile = "d://file.jtl";
        ResultCollector logger = new ResultCollector(summer);

        SampleSaveConfiguration conf = new SampleSaveConfiguration(true);
        conf.setResponseData(true);
        logger.setSaveConfig(conf);
        logger.setFilename(logFile);

        hashTree.add("threadGroup", threadGroup);
        hashTree.add("testPlan", testPlan);
        hashTree.add("loopCtrl", loopCtrl);

        hashTree.add("httpSampler", httpSampler);
        //httpSampler.add("httpSampler",logger);
        //hashTree.add(hashTree.getArray()[0], logger);
        //hashTree.add("ctrlogger", logger);
        hashTree.add(hashTree.getArray()[0], logger);
        hashTree.add("my"+hashTree.getArray()[0], requestCollector);
        CookieManager cookieManager = new CookieManager();
        hashTree.add("cookieManage", cookieManager);
        //hashTree.add("ra", ra);
        System.err.println(hashTree.getArray()[0]+ " -------------");
        try {
            SaveService.saveTree(hashTree, new FileOutputStream("d://example1.jmx"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block1
            e.printStackTrace();
        }

        // run distribute
//        JMeterUtils.setProperty(DistributedRunner.RETRIES_NUMBER, "1");
//        JMeterUtils.setProperty(DistributedRunner.CONTINUE_ON_FAIL, "true");
//
//
//		 DistributedRunner distributedRunner = new DistributedRunner();
//
//
//
//
//		 List<String> hosts = new ArrayList<>();
//		// hosts.add("192.168.184.1");
//		 hosts.add("192.168.184.4");
//		 hosts.add("192.168.184.5");
//
//		 distributedRunner.init(hosts, hashTree);
//
////		 List<String> hosts1 = Arrays.asList("192.168.184.4");
//		 distributedRunner.start(hosts);
//
//		 distributedRunner.shutdown(hosts);
//		 distributedRunner.stop();
        // distributedRunner.exit(hosts);

//		 distributedRunner.stop();


        //

        jm.configure(hashTree);
        jm.run();
        // System.exit(0);

        System.out.println("END OF SCRIPT");



    }
}
