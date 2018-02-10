package com.rackspacecloud.blueflood.metricsqueryservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories(basePackages = "com.rackspacecloud.blueflood.metricsqueryservice.repository")
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Value("${cassandra.keyspace-name}")
    String keyspaceName;

    @Value("${cassandra.contact-points}")
    String contactPoints;

    @Value("${cassandra.port}")
    int port;

    @Override
    protected String getKeyspaceName() {
        return keyspaceName;
    }

    @Bean
    public CassandraClusterFactoryBean cluster() {
        CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
        cluster.setContactPoints(contactPoints);
        cluster.setPort(port);
        return cluster;
    }

    @Bean
    public CassandraMappingContext cassandraMapping() throws ClassNotFoundException {
        return new BasicCassandraMappingContext();
    }
}

