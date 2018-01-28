package com.rackspacecloud.blueflood.metricsqueryservice.model;

public class MetricsRequest {
    String tenantId;
    String metricName;
    long from;
    long to;
    String resolution;

    public MetricsRequest(String tenantId, String metricName, long from, long to, String resolution){
        this.tenantId = tenantId;
        this.metricName = metricName;
        this.from = from;
        this.to = to;
        this.resolution = resolution;
    }

    public String getKey(){
        return String.format("%s.%s", tenantId, metricName);
    }
}
