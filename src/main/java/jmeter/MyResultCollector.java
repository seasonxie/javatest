package jmeter;

import kg.apc.jmeter.vizualizers.AggregateReportGui;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.samplers.SampleEvent;
import org.apache.jmeter.samplers.SampleResult;

import java.util.*;

public class MyResultCollector extends ResultCollector {

    private static final long serialVersionUID = -8648350950445938218L;

    //private List<SampleResult> sampleResults;
    private Map<String,List<SampleResult>> threadResultsMap;

    public MyResultCollector() {
        threadResultsMap = new HashMap<String,List<SampleResult>>();
    }

    @Override
    public void sampleOccurred(SampleEvent e) {
        System.out.println("--------------------11111111111111111111111111111111111111111111");
        super.sampleOccurred(e);
        SampleResult r = e.getResult();
        //int threadGroupName = Integer.parseInt(e.getThreadGroup());
        String threadGroupName= e.getThreadGroup();
        System.out.println(threadGroupName);

        System.out.println(r.getEndTime() +"  getEndTime");
        System.out.println(r.getStartTime() +"  getStartTime");
        System.out.println(r.getErrorCount() +"  getErrorCount");
        System.out.println(r.getIdleTime() +"  getIdleTime");
        System.out.println(r.getLatency() +"  getLatency");
        System.out.println(r.getTime() +"  getTime");
        System.out.println(r.getTimeStamp() +"  getTimeStamp");
        System.out.println(r.getSubResults() +"  getSubResults");
        System.out.println(Arrays.toString(r.getSubResults()) +"  getSubResults");

        if(threadResultsMap.containsKey(threadGroupName)){
            List<SampleResult> sampleResults = threadResultsMap.get(threadGroupName);
            sampleResults.add(r);
        }else{
            List<SampleResult> sampleResults = new ArrayList<SampleResult>();
            sampleResults.add(r);
            threadResultsMap.put(threadGroupName, sampleResults);
        }
    }

    public Map<String,List<SampleResult>> getSampleResults() {
        return threadResultsMap;
    }
}
