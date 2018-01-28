package com.rackspacecloud.blueflood.metricsqueryservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MetaData {
    public MetaData(String marker, int count, String limit, String nextHref){
        this.marker = marker;
        this.count = count;
        this.limit = limit;
        this.nextHref = nextHref;
    }

    @JsonProperty("marker")
    private String marker;

    @JsonProperty("count")
    private int count;

    @JsonProperty("limit")
    private String limit;

    @JsonProperty("next_href")
    private String nextHref;
}
