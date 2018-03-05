package jmeter;

import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.protocol.http.control.CookieManager;
import org.apache.jmeter.protocol.http.sampler.HTTPSampler;
import org.apache.jmeter.protocol.java.sampler.JavaSampler;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.samplers.SampleSaveConfiguration;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.ThreadGroup;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

public class notebookcase {

    @Test
    public void testPost() {
        // Engine
        StandardJMeterEngine jm = new StandardJMeterEngine();
        // jmeter.properties
        JMeterUtils.loadJMeterProperties("c://apache-jmeter-2.1//bin//jmeter.properties");
        JMeterUtils.setJMeterHome("c://apache-jmeter-2.1");
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
        httpSampler.setDomain("www.sojson.com");
        httpSampler.setPort(80);
        httpSampler.setProtocol("http");
        httpSampler.setPath("open/api/weather/json.shtml?city=珠海");
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
        threadGroup.setNumThreads(10);
       // threadGroup.setRampUp(3);
        threadGroup.setSamplerController((LoopController) loopCtrl);


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
        requestCollector.setFilename("C:\\code\\jmeter\\aaaaaaresult.log");
        requestCollector.setSaveConfig(conf);
      /* requestCollector.setListener(instance);
        requestCollector.sampleOccurred(new SampleEvent(res, ""));
*/
        //ResultCollector
        String logFile = "C:\\code\\jmeter\\file.jtl";
        ResultCollector logger = new ResultCollector(summer);
        logger.setSaveConfig(conf);
        logger.setFilename(logFile);


        //CookieManager
        CookieManager cookieManager = new CookieManager();

        //MuleCollector
        MuleCollector collector = new MuleCollector(1, "");


        // hashTree.add
        hashTree.add("threadGroup", threadGroup);
        hashTree.add("testPlan", testPlan);
        //hashTree.add("loopCtrl", loopCtrl);
         hashTree.add("httpSampler", httpSampler);
        // hashTree.add("JavaSampler", javaSample);
        //hashTree.add(hashTree.getArray()[0], requestCollector);
        //hashTree.add(hashTree.getArray()[0], logger);
       hashTree.add(hashTree.getArray()[0], collector);
        //hashTree.add("ra", ra);
        //System.err.println(hashTree.getArray()[0] + " -------------");
        try {
            SaveService.saveTree(hashTree, new FileOutputStream("C:\\code\\jmeter\\example1.jmx"));
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
