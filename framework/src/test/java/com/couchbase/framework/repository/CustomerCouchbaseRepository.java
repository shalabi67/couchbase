package com.couchbase.framework.repository;

import com.couchbase.client.java.Cluster;
import com.couchbase.framework.entity.Customer;

public class CustomerCouchbaseRepository extends CouchbaseRepository<Customer> {
    public CustomerCouchbaseRepository(String[] clusterNodes, String bucketName, String password) {
        super(clusterNodes, bucketName, password);
    }

    public CustomerCouchbaseRepository(Cluster couchbaseCluster, String bucketName, String password) {
        super(couchbaseCluster, bucketName, password);
    }
}
