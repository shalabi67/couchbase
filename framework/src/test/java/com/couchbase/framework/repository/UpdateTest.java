package com.couchbase.framework.repository;


import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.framework.entity.Converter;
import com.couchbase.framework.entity.Customer;
import com.couchbase.framework.entity.CustomerFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UpdateTest {
    @Mock
    private Cluster cluster;

    @Mock
    private Bucket bucket;

    @Before
    public void initTest() {
        when(cluster.openBucket(anyString(), anyString())).thenReturn(bucket);
    }

    @Test
    public void testAddingNewItem() {
        JsonDocument inputCustomer = Converter.toJsonDocument(CustomerFactory.createDefault());
        when(bucket.insert(any(JsonDocument.class))).thenReturn(inputCustomer);

        CustomerCouchbaseRepository customerCouchbaseRepository = RepositoryFactory.createCustomerRepository(cluster);
        Customer insertedCustomer = customerCouchbaseRepository.insert(CustomerFactory.createDefault());

        Assert.assertTrue(CustomerFactory.createDefault().equals(insertedCustomer));
    }
}
