package com.competency_filter.repository;

import com.competency_filter.entity.CompetencyFilter;
import com.couchbase.client.java.Cluster;
import com.couchbase.framework.repository.CouchbaseRepository;

public class CompetencyFilterRepository extends CouchbaseRepository<CompetencyFilter> {
    public CompetencyFilterRepository(String[] clusterNodes, String bucketName, String password) {
        super(clusterNodes, bucketName, password);
    }

    public CompetencyFilterRepository(Cluster couchbaseCluster, String bucketName, String password) {
        super(couchbaseCluster, bucketName, password);
    }
}
