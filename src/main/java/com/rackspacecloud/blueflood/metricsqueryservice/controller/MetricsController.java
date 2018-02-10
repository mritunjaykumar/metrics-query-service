package com.rackspacecloud.blueflood.metricsqueryservice.controller;

import com.rackspacecloud.blueflood.metricsqueryservice.model.MetricsRequest;
import com.rackspacecloud.blueflood.metricsqueryservice.model.MetricsResponse;
import com.rackspacecloud.blueflood.metricsqueryservice.service.MetricsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/v3")
@RestController
public class MetricsController {
    @Autowired
    MetricsService metricsService;

    private static final Logger LOGGER = LoggerFactory.getLogger(MetricsController.class);

    @RequestMapping(
            value = "{tenantId}/{metricName:.+}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<MetricsResponse> fetch(
            @PathVariable(value = "tenantId") final String tenantId,
            @PathVariable(value = "metricName") final String metricName,
            @RequestParam(value = "from") final long from,
            @RequestParam(value = "to") final long to,
            @RequestParam(value = "resolution") String resolution
    ){
        //TODO: Get tracking ID from the record to stitch the tracing
        String passedTrackingId = "";
        String currentTrackingId = "";

        currentTrackingId = StringUtils.isEmpty(passedTrackingId)
                ? UUID.randomUUID().toString() : String.format("%s|%s", passedTrackingId, currentTrackingId);

        String requestVars = String.format("tenantId:[%s], metricName:[%s], from:[%d], to:[%d], resolution:[%s]",
                tenantId, metricName, from, to, resolution);

        LOGGER.info("TrackingId:{}, START: Processing", currentTrackingId);
        LOGGER.debug("TrackingId:{}, Request variables:{}", currentTrackingId, requestVars);

        // TODO: Do input validation and return 400 errors to the users
        // TODO: validate from is always "from" is never bigger than "to" value.

        if(StringUtils.isEmpty(resolution)) resolution = "full";
        MetricsRequest metricRequest = new MetricsRequest(tenantId, metricName, from, to, resolution);

        MetricsResponse metricsResponse = metricsService.getMetricFullResolution(metricRequest.getKey(), from, to);

        LOGGER.info("TrackingId:{}, FINISH: Processing", currentTrackingId);
        return ResponseEntity.status(HttpStatus.OK).body(metricsResponse);
    }
}
