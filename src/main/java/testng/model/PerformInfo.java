package testng.model;

public class PerformInfo {

    private String name;
    private String className;
    private String methodName;
    private String description;
    private String status;
    private String url;
    private String method;
    private String Requests;
    private String Throughput;
    private String BytesPerRequestAvg;
    private String Error;
    private String AvgLatency;
    private String MinLatency;
    private String MaxLatency;
    private String _90thPercentile;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRequests() {
        return Requests;
    }

    public void setRequests(String requests) {
        Requests = requests;
    }

    public String getThroughput() {
        return Throughput;
    }

    public void setThroughput(String throughput) {
        Throughput = throughput;
    }

    public String getBytesPerRequestAvg() {
        return BytesPerRequestAvg;
    }

    public void setBytesPerRequestAvg(String bytesPerRequestAvg) {
        BytesPerRequestAvg = bytesPerRequestAvg;
    }

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }

    public String getAvgLatency() {
        return AvgLatency;
    }

    public void setAvgLatency(String avgLatency) {
        AvgLatency = avgLatency;
    }

    public String getMinLatency() {
        return MinLatency;
    }

    public void setMinLatency(String minLatency) {
        MinLatency = minLatency;
    }

    public String getMaxLatency() {
        return MaxLatency;
    }

    public void setMaxLatency(String maxLatency) {
        MaxLatency = maxLatency;
    }

    public String get_90thPercentile() {
        return _90thPercentile;
    }

    public void set_90thPercentile(String _90thPercentile) {
        this._90thPercentile = _90thPercentile;
    }




}
