package jmeter;

import kg.apc.jmeter.vizualizers.AggregateReportGui;
import org.apache.jmeter.assertions.ResponseAssertion;
import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.protocol.http.control.CookieManager;
import org.apache.jmeter.protocol.http.sampler.HTTPSampler;
import org.apache.jmeter.protocol.java.sampler.JavaSampler;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.samplers.SampleEvent;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.SampleSaveConfiguration;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jmeter.visualizers.Visualizer;
import org.apache.jorphan.collections.HashTree;
import org.testng.annotations.Test;
import org.apache.jmeter.threads.ThreadGroup;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

public class case1 {

    @Test
    public void testPost() {
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
        httpSampler.setPath("/dsp/track?action=CLICK&p=NTQ2RTI3QUIwRENEN0U3QzZEQzA5RkM2MzA4MzFEQ0U4Q0VDN0NGRkQxRDNDQTZEMkUyN0Q5NENDNTJFNkMyQ0YyN0I5NUMzNkZCQjU1OTBDREI4MUU1OTcwOERFRUQ0REY2RUI3QTYxNjc0MTQ0RTI5QjVFNTNGOUU0NTE0Qzg5N0Q2M0Q1NTc3OEI2QzY2NzkwQUNBNDIzOUE1NEUwMzhBRkE0QzFGMjQxNEZFMEFFMzQ5RjdGNDE3QkVCMzVGMDFGMjZBNkVEQjYwNEM3NERENTk1REE0NUYyOEQ1Qzc3RTZBQ0E4RjgyQUQ1OTY4NjAxOUJENUYxNEY3MkNEMThCMkY4Mjg0NzUzNEZCRkYwNzM3MTMxNkQwQTNCRDFFQjgzRkM2Qzc1MDUzRjc3RkNDMTdBRkFEQjMzNEE0NzRGQkVDNUI0REE4QTQ3MDlENjlCMzZEODZGN0Y5MUVGOEQwRjE0NDE0OENENENENkJFMzIxOTc0ODYzNTA1M0M1RDI4MjQzODA1RTZCRThFMDU4MTEzMzBDN0I5NDQ0QjE3ODQ2NjYyNkU3MEYwMjlEQUYyNThERUJEOEU3Q0MwREVDMEUxMzNGOEEzMDJBMzI5QTVBQzA2OUZGQjhCNjk2OUEyOENBRDZBRDAzOUY5Nzg3RTA3MkJEREUwODA3QUNEOTE1OURCMDdBMTVGQzFCNUVGRTVDNkRCNjk1QTVDMzYxNTZGQzZGN0EwQUIzQzQ5Q0U4MDIzNzUyMzNCMTQzREM2NzVGOTU2MTFFQUYzRjI5RkM3MjY4MTkzNjRGMTM5RDI4MzM2MTcxODE0MjI5REFDM0E5QUREREY2RTc0QzY0M0VDNkQ5OTU4OTgyOUIxQkJCMTZGNjM0NDJCQkU1&price=&lp=");
        httpSampler.setMethod("GET");
      /*  httpSampler.addArgument("FormName", "MenuBarForm");
        httpSampler.addArgument("Email", "kakalot8x08@gmail.com");
        httpSampler.addArgument("Password", "tititi");
        httpSampler.setFollowRedirects(true);
        httpSampler.setUseKeepAlive(true);*/
        //httpSampler.addTestElement(ra);

        // Loop Controller
        TestElement loopCtrl = new LoopController();
        ((LoopController) loopCtrl).setLoops(1);
        ((LoopController) loopCtrl).addTestElement(httpSampler);
        // ((LoopController)loopCtrl).addTestElement(ra);
        ((LoopController) loopCtrl).setFirst(true);

        // Thread Group
        ThreadGroup threadGroup = new ThreadGroup();
        threadGroup.setName("TestGoogle");
        //SetupThreadGroup threadGroup = new SetupThreadGroup();
        threadGroup.setNumThreads(1);
        threadGroup.setRampUp(1);
        threadGroup.setSamplerController((LoopController) loopCtrl);
        // threadGroup.addTestElement(ra);


        // Thread Group1
        ThreadGroup threadGroup1 = new ThreadGroup();
        threadGroup1.setName("TestGoogle1");
        //SetupThreadGroup threadGroup = new SetupThreadGroup();
        threadGroup1.setNumThreads(1);
        threadGroup1.setRampUp(1);
        threadGroup1.setSamplerController((LoopController) loopCtrl);
        // threadGroup.addTestElement(ra);


        // Test plan
        TestPlan testPlan = new TestPlan("MY TEST PLAN");
        //testPlan.addTestElement(loopCtrl);

/*        //AggregateReportGui
        AggregateReportGui instance = new AggregateReportGui();
        SampleResult res = new SampleResult();
        instance.add(res);*/

        //SampleSaveConfiguration
        SampleSaveConfiguration conf = new SampleSaveConfiguration(true);
        conf.setResponseData(true);

        //Summariser
        Summariser summer = null;
        String summariserName = JMeterUtils.getPropDefault("summariser.name", "summary");//$NON-NLS-1$
        if (summariserName.length() > 0) {
            summer = new Summariser(summariserName);
        }

        //JavaSampler
        JavaSampler javaSample = new JavaSampler();
        javaSample.setClassname(TestJava.class.getName());

        //MyResultCollector
        MyResultCollector requestCollector = new MyResultCollector();
        requestCollector.setFilename("d://aaaaaaresult.log");
        requestCollector.setSaveConfig(conf);
      /* requestCollector.setListener(instance);
        requestCollector.sampleOccurred(new SampleEvent(res, ""));
*/
        //ResultCollector
        String logFile = "d://file.jtl";
        ResultCollector logger = new ResultCollector(summer);
        logger.setSaveConfig(conf);
        logger.setFilename(logFile);


        //CookieManager
        CookieManager cookieManager = new CookieManager();

        //MuleCollector
        MuleCollector collector = new MuleCollector(1, "");


        // hashTree.add
        hashTree.add("threadGroup", threadGroup);
        hashTree.add("threadGroup1", threadGroup1);
        hashTree.add("testPlan", testPlan);
        hashTree.add("loopCtrl", loopCtrl);
        hashTree.add("httpSampler", httpSampler);
        // hashTree.add("JavaSampler", javaSample);
        hashTree.add(hashTree.getArray()[0], requestCollector);
        hashTree.add(hashTree.getArray()[0], logger);
       hashTree.add(hashTree.getArray()[0], collector);
        hashTree.add("cookieManage", cookieManager);
        //hashTree.add("ra", ra);
        System.err.println(hashTree.getArray()[0] + " -------------");
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
//		 DistributedRunner distributedRunner = new DistributedRunner();
//
//		 List<String> hosts = new ArrayList<>();
//		// hosts.add("192.168.184.1");
//		 hosts.add("192.168.184.4");
//		 hosts.add("192.168.184.5");
//
//		 distributedRunner.init(hosts, hashTree);
//
//	List<String> hosts1 = Arrays.asList("192.168.184.4");
//		 distributedRunner.start(hosts);
//		 distributedRunner.shutdown(hosts);
//		 distributedRunner.stop();
// distributedRunner.exit(hosts);
//		 distributedRunner.stop();


        jm.configure(hashTree);
        jm.run();
        // System.exit(0);
        System.out.println(requestCollector.getSampleResults().size());
        System.out.println("END OF SCRIPT");

    }
}
