package jmeter;

import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.protocol.http.sampler.HTTPSampler;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.ThreadGroup;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import org.testng.annotations.Test;

import java.util.Locale;

public class case3 {

    @Test
    public void test() throws InterruptedException {
        testPost("test70",700,0,0);
        Thread.sleep(5000);
        testPost("test80",800,0,0);
        Thread.sleep(5000);
        testPost("test90",900,0,0);
        Thread.sleep(5000);
        testPost("test100",1000,0,0);
        Thread.sleep(2000);
        System.out.println(MuleCollector.allResultTime.size());
        for(String d: MuleCollector.allResultTime.keySet()){
            System.out.println(d+"  -----------------");
            System.out.println(MuleCollector.allResultTime.get(d));
        }
    }

    @Test
    public void test1() throws InterruptedException {
        testPost("test70",300,0,20);
    }

    public HTTPSampler getHTTPSampler(){
        HTTPSampler httpSampler = new HTTPSampler();
        httpSampler.setName("TestCodeproject");
        httpSampler.setDomain("172.16.185.65");
        httpSampler.setPort(7000);
        httpSampler.setProtocol("http");
        httpSampler.setPath("/ad/v1/ad/display");
        httpSampler.parseArguments("{\"IMEI\":\"862841035782683\",\"Adcount\":50,\"ABParames\":\"{\\\\\\\"preclick_model_name\\\\\\\": \\\\\\\"lymodel1\\\\\\\", \\\\\\\"local_extract_feature\\\\\\\": false}\",\"AdLocations\":[{\"PageID\":5007,\"appId\":0,\"positionType\":1,\"Position\":2,\"BlockID\":10468,\"rankId\":5007,\"tagId\":0,\"specialId\":0,\"PositionID\":1563}]}");
        httpSampler.setMethod("POST");
        return httpSampler;
    }

    public StandardJMeterEngine getJmeter(){
        StandardJMeterEngine jm = new StandardJMeterEngine();
        // jmeter.properties
        JMeterUtils.loadJMeterProperties("D://ajmeter//bin//jmeter.properties");
        JMeterUtils.setJMeterHome("D://ajmeter");
        JMeterUtils.setLocale(new Locale("ignoreResources"));
        return jm;
    }

    public ResultCollector getResultCollector(){
        //Summariser
        Summariser summer = null;
        String summariserName = JMeterUtils.getPropDefault("summariser.name", "summary");//$NON-NLS-1$
        if (summariserName.length() > 0) {
            summer = new Summariser(summariserName);
        }
        ResultCollector logger = new ResultCollector(summer);
        return logger;
    }

    public ThreadGroup getThreadGroup(String threadGroupName,int NumThreads,int ramgup,int duration){
        // Loop Controller
        TestElement loopCtrl = new LoopController();
        ((LoopController) loopCtrl).setLoops(-1);
        ((LoopController) loopCtrl).setFirst(false);
        // Thread Group
        ThreadGroup threadGroup = new ThreadGroup();
        threadGroup.setName(threadGroupName);
        threadGroup.setNumThreads(NumThreads);
        if(duration>0)
        threadGroup.setDuration(duration);
        if(ramgup>0)
        threadGroup.setRampUp(ramgup);
        //threadGroup.setDelay(2);
        threadGroup.setScheduler(true);
        threadGroup.setSamplerController((LoopController) loopCtrl);
        return threadGroup;
    }





    public void testPost(String threadGroupName,int NumThreads,int ramgup,int duration) throws InterruptedException {
       /* PerGet p=new PerGet();
        p.getTop(5);*/
        StandardJMeterEngine jm =getJmeter();
        HashTree hashTree = new HashTree();
        // hashTree.add
        hashTree.add("threadGroup", getThreadGroup(threadGroupName,NumThreads,ramgup,duration));
        hashTree.add("testPlan", new TestPlan("MY TEST PLAN"));
        //hashTree.add("loopCtrl", loopCtrl);
        hashTree.add("httpSampler", getHTTPSampler());
        hashTree.add(hashTree.getArray()[0], new MuleCollector(1, ""));
        hashTree.add(hashTree.getArray()[0], getResultCollector());
        jm .configure(hashTree);
        jm.run();
        System.out.println("END OF SCRIPT");
        System.out.println(MuleCollector.allResultTime.size());
      /*  Thread.sleep(5000);
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
            System.out.println(LinuxForShellUtil.runShell("cat /data/mq/tt_"+i+".txt | grep Mem | awk '{print $6}'",true)+ " ------------");
            System.out.println(LinuxForShellUtil.runShell("cat /data/mq/tt_"+i+".txt |  grep Swap | awk '{print $4}'",true)+ " ------------");
            System.out.println(LinuxForShellUtil.runShell("cat /data/mq/tt_"+i+".txt |  grep Cpu | awk '{print $5}'",true)+ " ------------");
            System.out.println(LinuxForShellUtil.runShell("cat /data/mq/tt_"+i+".txt |  sed -n '7,10p'",true)+ " ------------");
        }*/
    }
}
