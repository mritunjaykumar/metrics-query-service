package com.rackspacecloud.blueflood.metricsqueryservice.service;

import com.rackspacecloud.blueflood.metricsqueryservice.domain.MetricsFullModel;
import com.rackspacecloud.blueflood.metricsqueryservice.model.MetaData;
import com.rackspacecloud.blueflood.metricsqueryservice.model.MetricsResponse;
import com.rackspacecloud.blueflood.metricsqueryservice.model.MetricsValue;
import com.rackspacecloud.blueflood.metricsqueryservice.repository.IMetricsFullRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MetricsService implements IMetricsService {
    @Autowired
    IMetricsFullRepository metricsFullRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(MetricsService.class);

    @Override
    public MetricsResponse getMetricFullResolution(String key, long from, long to){
        Iterable<MetricsFullModel> records = null;
        try {
            LOGGER.debug("Before call of 'getMetricFullResolution': key:[{}],from:[{}],to:[{}]", key, from, to);
            records = metricsFullRepository.findByKeyAndCollectionTimeBetween(key, from, to);
            LOGGER.debug("After call of 'getMetricFullResolution'");
        }
        catch(Exception ex){
            LOGGER.error("'findByKeyAndCollectionTimeBetween' failed with exception message:{}", ex.getMessage());
        }

        MetricsResponse response = new MetricsResponse();
        List<MetricsValue> metricsValues = new ArrayList<>();

        int numberOfRecords = 0;
        for(MetricsFullModel record : records){
            metricsValues.add(new MetricsValue(1, record.getMetricValue(), record.getCollectionTime()));
            numberOfRecords++;
        }
        MetricsValue[] metricsValueArray = new MetricsValue[metricsValues.size()];
        response.setValues(metricsValues.toArray(metricsValueArray));
        response.setUnit("unknown");
        response.setMetaData(new MetaData(null, numberOfRecords, null, null));

        return response;
    }
}
