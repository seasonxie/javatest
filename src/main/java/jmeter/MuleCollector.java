package jmeter;

import org.apache.jmeter.engine.util.NoThreadClone;
import org.apache.jmeter.reporters.AbstractListenerElement;
import org.apache.jmeter.samplers.*;
import org.apache.jmeter.testelement.TestStateListener;
import org.apache.jmeter.testelement.ThreadListener;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.log.Logger;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;


public class MuleCollector extends AbstractListenerElement implements SampleListener, Clearable, Serializable, TestStateListener, Remoteable, NoThreadClone, ThreadListener {
    AtomicLong activeThreads = new AtomicLong(0);
    private static final Logger log = LoggingManager.getLoggerForClass();
    Map<String,List<Long>> responseTimes = Collections.synchronizedMap(new HashMap<String,List<Long>>());
    private List<Long> allResponseTimes =  Collections.synchronizedList(new ArrayList<Long>());
    private long startTimeInMillis;
    private AtomicInteger failedReq = new AtomicInteger(0);
    private boolean sorted = false;
    private boolean saveSamples = false;
    private AtomicInteger finishedThreads = new AtomicInteger(0);
    private AtomicInteger startedThreads = new AtomicInteger(0);
    private int expectedThreads = 1;
    private String logFile;
    private HashMap<String, Long> requestSizes = new HashMap<String, Long>();
    private long finishTimeInMillis = 0;
    private Thread sorter;
    private HashMap<String, Double> slaResults;
    private String currentThreadName;
    public static Map<String, List<String>> allResultTime = new LinkedHashMap<>();

    public String getCurrentThreadName() {
        return currentThreadName;
    }

    public void setCurrentThreadName(String currentThreadName) {
        this.currentThreadName = currentThreadName;
    }

    MuleCollector(int expectedThreads, String logFile){
        this.expectedThreads = expectedThreads;
        this.logFile = logFile;
    }

    //TODO: figure out a way to get this information programmatically instead of an user entry.
    public String getExpectedThreads() {
        return Integer.toString(expectedThreads);
    }

    public void setExpectedThreads(String val) {
        expectedThreads = Integer.parseInt(val);
    }

    @Override
    public void sampleOccurred(SampleEvent sampleEvent)
    {
          sorted = false;
     /*   if (saveSamples){*/
            SampleResult result = sampleEvent.getResult();
            long latency = result.getEndTime() - result.getStartTime();
            String threadName = result.getThreadName();

            long bytes = result.getBytes();

            if (responseTimes.containsKey(threadName) ) {
                responseTimes.get(threadName).add(latency);

                requestSizes.put(threadName, requestSizes.get(threadName) + bytes);
            } else {
                List<Long> thisThreadLatencies = Collections.synchronizedList(new  ArrayList<Long>(10000));
                thisThreadLatencies.add(latency);
                responseTimes.put(threadName, thisThreadLatencies);

                requestSizes.put(threadName, (long) bytes);
            }

            if(!result.isSuccessful()) {
                failedReq.incrementAndGet();
            }
        setCurrentThreadName(sampleEvent.getThreadGroup());

/*        }else {
            String threadName = sampleEvent.getResult().getThreadName();
            if (!responseTimes.containsKey(threadName) ) {
                responseTimes.put(threadName, new  ArrayList<Long>(10000));
                requestSizes.put(threadName,0L);
            }

        }*/

    }

    @Override
    public void sampleStarted(SampleEvent sampleEvent)
    {
        System.out.println("sampleStarted  ---------------------------------------");
    }

    @Override
    public void sampleStopped(SampleEvent sampleEvent)
    {
        System.out.println("sampleStopped  ---------------------------------------");
    }

    @Override
    public void testStarted()
    {
        System.out.println("testStarted  ---------------------------------------");
        startTimeInMillis = System.currentTimeMillis();
        sorted = false;
    }

    @Override
    public void testStarted(String s)
    {
        System.out.println("testStarted  ---------------------------------------");
    }

    @Override
    public void testEnded()
    {
        System.out.println("testEnded  ---------------------------------------");
        //check valid finish
        if (activeThreads.get() != 0) {
            log.error("Test ended with non zero active threads, instead of zero got: " + activeThreads);
        }

        //check for sorted information
        try
        {
            sorter.join() ; //wait for the thread to finish
        }
        catch (InterruptedException e)
        {
            //Do nothing and sortIfNeeded.
        }
        this.sortResponsesIfNeeded();
        long latencySum = 0;
        for (long latency : allResponseTimes){
            latencySum += latency;
        }


        //parse data for report
        double totalTimeInSeconds = (finishTimeInMillis - startTimeInMillis) /1000.0;
        long totalBytes = 0;
        for (long bytes : requestSizes.values()){
            totalBytes += bytes;
        }

        int totalReq = allResponseTimes.size();
        double bytesPerSecond = totalBytes * 1.0/totalTimeInSeconds;
        double avgBytesPerRequest = totalBytes * 1.0/totalReq;
        double tps = totalReq * 1.0/totalTimeInSeconds;
        double failPercentage = 100 * failedReq.intValue() * 1.0 /totalReq;
        double avgLatency = latencySum * 1.0 /totalReq;

        this.slaResults = new HashMap<String, Double>();
        this.slaResults.put("TotalTime" , totalTimeInSeconds);
        this.slaResults.put("ExpectedThreads" , ((double) expectedThreads));
        this.slaResults.put("StartedThreads" , startedThreads.doubleValue());
        this.slaResults.put("StoppedThreads" , finishedThreads.doubleValue());
        this.slaResults.put("Requests" , ((double) totalReq));
        this.slaResults.put("Throughput" , tps);
        this.slaResults.put("BytesPerSecond" , bytesPerSecond);
        this.slaResults.put("BytesPerRequestAvg " , avgBytesPerRequest);
        this.slaResults.put("Error% " , failPercentage);
        this.slaResults.put("AvgLatency " , avgLatency);
        this.slaResults.put("MinLatency " , ((double) this.getPercentile(0)));
        this.slaResults.put("50thPercentile " , ((double) this.getPercentile(0.50)));
        this.slaResults.put("90thPercentile " , ((double) this.getPercentile(0.90)));
        this.slaResults.put("95thPercentile " , ((double) this.getPercentile(0.95)));
        this.slaResults.put("99thPercentile " , ((double) this.getPercentile(0.99)));
        this.slaResults.put("MaxLatency " , ((double) this.getPercentile(1)));

        try {
            Charset utf8 = StandardCharsets.UTF_8;
            List<String> lines = Arrays.asList("MULE TotalTime" + totalTimeInSeconds,
                    "MULE ExpectedThreads " + expectedThreads,
                    "MULE StartedThreads " + startedThreads,
                    "MULE StoppedThreads " + finishedThreads,
                    "MULE Requests " + totalReq,
                    "MULE Throughput " + tps,
                    "MULE BytesPerSecond " + toPrintableBytes(bytesPerSecond),
                    "MULE BytesPerRequestAvg " + toPrintableBytes(avgBytesPerRequest),
                    "MULE Error% " + failPercentage,
                    "MULE AvgLatency " + avgLatency,
                    "MULE MinLatency " + this.getPercentile(0),
                    "MULE 50thPercentile " + this.getPercentile(0.50),
                    "MULE 90thPercentile " + this.getPercentile(0.90),
                    "MULE 95thPercentile " + this.getPercentile(0.95),
                    "MULE 99thPercentile " + this.getPercentile(0.99),
                    "MULE MaxLatency " + this.getPercentile(1));
            System.out.println("------------------");
            for(String line:lines){
                System.out.println(line);
            }
            System.out.println("------------------");
            Files.write(Paths.get("D://testjmeter.txt"),lines, utf8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, Double> getSlaResults(){
        return this.slaResults;
    }

    private String toPrintableBytes(double bytes){
        double bytesTmp = bytes;

        if ( bytesTmp < 1024){
            return bytesTmp + " B";
        }

        bytesTmp = bytesTmp / 1024;

        if ( bytesTmp < 1024){
            return bytesTmp + " KB";
        }

        bytesTmp = bytesTmp / 1024;

        if ( bytesTmp < 1024){
            return bytesTmp + " MB";
        }

        bytesTmp = bytesTmp / 1024;

        if ( bytesTmp < 1024){
            return bytesTmp + " GB";
        }

        bytesTmp = bytesTmp / 1024;

        return bytesTmp + " TB";
    }

    protected long getPercentile(double percentile){
        if ( percentile < 0 || percentile > 1){
            log.error("Requested percentile is invalid:  " + percentile);
            return -1;
        }

        sortResponsesIfNeeded();

        int index = (int) (percentile * allResponseTimes.size());
        if (index <= 0) { index = 1; }
        return allResponseTimes.get(index - 1);
    }

    synchronized private void sortResponsesIfNeeded()
    {
        if (!sorted){
            allResponseTimes.clear();
            for (String key: responseTimes.keySet() ){
                allResponseTimes.addAll(responseTimes.get(key));
                Collections.sort(allResponseTimes);
            }
            sorted = true;
        }
    }

    @Override
    public void testEnded(String s)
    {

    }

    @Override
    public void threadStarted()
    {
        startedThreads.incrementAndGet();
        long currentActive = activeThreads.incrementAndGet();

        if (currentActive == expectedThreads){
            startTimeInMillis = System.currentTimeMillis();
            saveSamples = true;
        }
        System.out.println(startedThreads +"  -threadStarted-  "+activeThreads+saveSamples);
        System.out.println("A Thread was started");
    }

    @Override
    public void threadFinished()
    {
        if (activeThreads.get() == expectedThreads){
            saveSamples = false;
            finishTimeInMillis = System.currentTimeMillis();
            sorter = new Thread( new ReportSorter(this));
            sorter.run();

        }
        finishedThreads.incrementAndGet();
        activeThreads.decrementAndGet();
        System.out.println(startedThreads +"  -threadStarted-  "+activeThreads+saveSamples+finishedThreads);
        System.out.println("A Thread was finished");

    }

    @Override
    public void clearData()
    {
        startedThreads.set(0);
        finishedThreads.set(0);
        activeThreads.set(0);
        responseTimes.clear();
        allResponseTimes.clear();
        failedReq.set(0);
        sorted = false;
        saveSamples = false;
        startTimeInMillis = 0;
        finishTimeInMillis = 0;
    }

    private class ReportSorter implements Runnable
    {
        MuleCollector reporter;

        ReportSorter(MuleCollector reporter){
            this.reporter = reporter;
        }

        @Override
        public void run()
        {
            reporter.sortResponsesIfNeeded();
        }
    }
}
