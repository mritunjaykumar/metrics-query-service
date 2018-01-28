package com.rackspacecloud.blueflood.metricsqueryservice.service;

import com.rackspacecloud.blueflood.metricsqueryservice.model.MetricsResponse;

public interface IMetricsService {
    MetricsResponse getMetricFullResolution(String key, long from, long to);
}
