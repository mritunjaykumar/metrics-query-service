package com.rackspacecloud.blueflood.metricsqueryservice.domain;

import lombok.Data;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Data
@Table("metrics_full")
public class MetricsFullModel {
    @PrimaryKey(value = "key")
    private String key;

    @Column(value = "column1")
    private long collectionTime;

    @Column(value = "value")
    private double metricValue;
}
