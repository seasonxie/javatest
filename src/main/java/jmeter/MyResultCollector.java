package jmeter;

import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.samplers.SampleEvent;
import org.apache.jmeter.samplers.SampleResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyResultCollector extends ResultCollector {

    private static final long serialVersionUID = -8648350950445938218L;

    //private List<SampleResult> sampleResults;
    private Map<Integer,List<SampleResult>> threadResultsMap;

    public MyResultCollector() {
        threadResultsMap = new HashMap<Integer,List<SampleResult>>();
    }

    @Override
    public void sampleOccurred(SampleEvent e) {
        System.out.println("--------------------11111111111111111111111111111111111111111111");
        super.sampleOccurred(e);
        SampleResult r = e.getResult();
        int threadGroupName = Integer.parseInt(e.getThreadGroup());
        if(threadResultsMap.containsKey(threadGroupName)){
            List<SampleResult> sampleResults = threadResultsMap.get(threadGroupName);
            sampleResults.add(r);
        }else{
            List<SampleResult> sampleResults = new ArrayList<SampleResult>();
            sampleResults.add(r);
            threadResultsMap.put(threadGroupName, sampleResults);
        }
    }

    public Map<Integer,List<SampleResult>> getSampleResults() {
        return threadResultsMap;
    }
}
