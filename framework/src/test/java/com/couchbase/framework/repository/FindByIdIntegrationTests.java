package com.couchbase.framework.repository;

import com.couchbase.framework.entity.Customer;
import org.junit.Assert;
import org.junit.Test;

/**
 * tzo run these tests a bucket should be exits with the name customer360
 */
public class FindByIdIntegrationTests {
    private static final String[] COUCHBASE_NODES = {"localhost"};
    private static final String BUCKET_NAME = "customer360";
    private static final String BUCKET_PASSWORD = "password";

    @Test
    public void getExistingRecord() {
        CouchbaseRepository<Customer> couchbaseRepository = new CustomerCouchbaseRepository(COUCHBASE_NODES, BUCKET_NAME, BUCKET_PASSWORD);
        Customer customer = couchbaseRepository.findById("customer::bblue22");
        Assert.assertNotNull(customer);

    }
}
