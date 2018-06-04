package com.competence_filter.repository;

import com.competence_filter.entity.CompetenceFilter;
import com.couchbase.client.java.Cluster;
import com.couchbase.framework.repository.CouchbaseRepository;

public class CompetenceFilterRepository extends CouchbaseRepository<CompetenceFilter> {
    public CompetenceFilterRepository(String[] clusterNodes, String bucketName, String password) {
        super(clusterNodes, bucketName, password);
    }

    public CompetenceFilterRepository(Cluster couchbaseCluster, String bucketName, String password) {
        super(couchbaseCluster, bucketName, password);
    }
}
