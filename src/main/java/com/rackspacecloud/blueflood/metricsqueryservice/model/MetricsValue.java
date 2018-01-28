package com.rackspacecloud.blueflood.metricsqueryservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MetricsValue {

    public MetricsValue(int numPoints, double average ,long timestamp){
        this.numPoints = numPoints;
        this.average = average;
        this.timestamp = timestamp;
    }

    @JsonProperty("numPoints")
    private int numPoints;

    @JsonProperty("average")
    private double average;

    @JsonProperty("timestamp")
    private long timestamp;
}
