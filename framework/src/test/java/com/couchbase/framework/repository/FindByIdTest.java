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
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FindByIdTest {
    @Mock
    private Cluster cluster;

    @Mock
    private Bucket bucket;

    @Before
    public void initTest() {
        when(cluster.openBucket(anyString(), anyString())).thenReturn(bucket);
    }

    @Test
    public void testExiting() {
        JsonDocument customerDocument = Converter.toJsonDocument(CustomerFactory.createDefault());
        when(bucket.get(anyString())).thenReturn(customerDocument);

        CustomerCouchbaseRepository couchbaseRepository = RepositoryFactory.createCustomerRepository(cluster);
        Customer customer = couchbaseRepository.findById(CustomerFactory.ID);

        Assert.assertTrue(CustomerFactory.createDefault().equals(customer));

        /*
        Assert.assertEquals(CustomerFactory.EMAIL, customer.getEmail());
        Assert.assertEquals(CustomerFactory.FIRST_NAME, customer.getFirstName());
        Assert.assertEquals(CustomerFactory.LAST_NAME, customer.getLastName());
        Assert.assertEquals(CustomerFactory.PHONE_NUMBER, customer.getPhoneNumber());
        Assert.assertEquals(CustomerFactory.USER_NAME, customer.getUserName());
        Assert.assertEquals(CustomerFactory.ID, customer.getId());
        Assert.assertEquals(Customer.class.getName(), customer.getType());
        */
    }

    @Test
    public void testWithNulls() {
        customersHavingNull[0].setPhoneNumber(null);
        customersHavingNull[1].setUserName(null);
        customersHavingNull[2].setLastName(null);
        customersHavingNull[3].setFirstName(null);
        customersHavingNull[4].setEmail(null);
        for(Customer customerHavingNull : customersHavingNull) {
            JsonDocument customerDocument = Converter.toJsonDocument(customerHavingNull);
            when(bucket.get(anyString())).thenReturn(customerDocument);

            CustomerCouchbaseRepository couchbaseRepository = RepositoryFactory.createCustomerRepository(cluster);
            Customer customer = couchbaseRepository.findById(CustomerFactory.ID);

            Assert.assertTrue(customerHavingNull.equals(customer));
        }
    }

    private Customer[] customersHavingNull = {
            CustomerFactory.createDefault(),
            CustomerFactory.createDefault(),
            CustomerFactory.createDefault(),
            CustomerFactory.createDefault(),
            CustomerFactory.createDefault()
    };


}
