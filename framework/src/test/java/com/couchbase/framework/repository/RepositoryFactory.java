package com.couchbase.framework.repository;

import com.couchbase.client.java.Cluster;

public class RepositoryFactory {
    public static CustomerCouchbaseRepository createCustomerRepository(Cluster cluster) {
        return new CustomerCouchbaseRepository(cluster, "", "");
    }
}
