package com.rackspacecloud.blueflood.metricsqueryservice.repository;

import com.rackspacecloud.blueflood.metricsqueryservice.domain.MetricsFullModel;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMetricsFullRepository extends CrudRepository<MetricsFullModel, String> {
    @Query("SELECT * FROM metrics_full WHERE key = ?0 AND column1 >= ?1 AND  column1 <= ?2 ALLOW FILTERING")
    Iterable<MetricsFullModel> findByKeyAndCollectionTimeBetween(String key, long from, long to);
}
