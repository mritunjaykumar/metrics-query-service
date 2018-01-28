package com.rackspacecloud.blueflood.metricsqueryservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MetricsResponse {
    @JsonProperty("metadata")
    private MetaData metaData;

    @JsonProperty("unit")
    private String unit;

    @JsonProperty("values")
    private MetricsValue[] values;
}
