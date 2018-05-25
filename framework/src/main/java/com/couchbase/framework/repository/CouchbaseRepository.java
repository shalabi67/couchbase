package com.couchbase.framework.repository;

import com.couchbase.client.core.CouchbaseException;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.N1qlParams;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;
import com.couchbase.client.java.query.ParameterizedN1qlQuery;
import com.couchbase.client.java.query.consistency.ScanConsistency;
import com.couchbase.framework.entity.Converter;
import com.couchbase.framework.entity.Entity;
import com.couchbase.framework.entity.JsonConverter;
import com.couchbase.framework.exception.RepositoryException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CouchbaseRepository<T extends Entity> {
    private Bucket couchbaseBucket;
    private Class entityType;

    /**
     * create couchbase repository object. notice that the bucket user name should be identical to the bucket name.
     *
     * @param clusterNodes Couchbase Cluster Nodes
     * @param bucketName Name of the bucket to use.
     * @param  password Couchbase bucket password.
     */
    protected CouchbaseRepository(String[] clusterNodes, String bucketName, String password) {
        iniEntityType();
        Cluster couchbaseCluster = createCluster(clusterNodes);
        couchbaseBucket = createBucket(bucketName, password, couchbaseCluster);
    }

    /**
     * create couchbase repository object. notice that the bucket user name should be identical to the bucket name.
     *
     * @param couchbaseCluster Couchbase Cluster Nodes
     * @param bucketName Name of the bucket to use.
     * @param  password Couchbase bucket password.
     */
    protected CouchbaseRepository(Cluster couchbaseCluster, String bucketName, String password) {
        iniEntityType();
        couchbaseBucket = createBucket(bucketName, password, couchbaseCluster);
    }

    public static Cluster createCluster(String[] clusterNodes ){
        return CouchbaseCluster.create(clusterNodes);
    }


    private Bucket createBucket(String bucketName, String password, Cluster couchbaseCluster) {
        try {
            return couchbaseCluster.openBucket(bucketName, password);
        } catch (CouchbaseException e) {
            throw new RepositoryException(e);
        }
    }

    private void iniEntityType() {
        this.entityType = ((Class) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    /**
     * Get a list of items based on a based query
     * @param query the query to execute
     * @return List of items
     */
    public List<T> getItems(N1qlQuery query) {
        List<T> customers = new ArrayList<T>();
        try {
            N1qlQueryResult result = couchbaseBucket.query(query);
            List<N1qlQueryRow> list = result.allRows();

            for(N1qlQueryRow row : list) {
                JsonObject jsonObject = row.value();
                JsonConverter jsonConverter = new JsonConverter();
                T customer = (T)jsonConverter.fromJson(jsonObject.toString(), entityType);
                customers.add(customer);
            }
        } catch (CouchbaseException e) {
            throw new RepositoryException(e);
        }

        return customers;
    }

    /**
     * Find an entity by the specified ID and return a reference to
     * an instance of the specified type.
     *
     * @param entityId Unique ID of the entity
     * @return Reference to an instance of the specified type that
     * 	corresponds to the ID
     */
    public T findById(String entityId){
        JsonDocument couchbaseDocument = couchbaseBucket.get(entityId);

        if(couchbaseDocument == null) {
            return null;
        }

        Entity item = Converter.fromJsonDocument(couchbaseDocument, entityType);
        item.setId(entityId);

        return (T)item;
    }

    /**
     * Persist a new instance of the specified type in the repository.
     *
     * @param entity Source entity to be persisted
     * @return Reference to the entity that has been persisted
     */
    public T insert(T entity){
        JsonDocument inputDocument = Converter.toJsonDocument(entity);
        JsonDocument insertedDocument = couchbaseBucket.insert(inputDocument);
        return (T)Converter.fromJsonDocument(insertedDocument, entityType);
    }

    /**
     * Update an existing instance of the specified type in the repository.
     *
     * @param entity Source entity to be persisted
     * @return Reference to the entity that has been persisted
     */
    public T update(T entity){
        throw new NotImplementedException();
    }

    /**
     * Insert or update an instance of the specified type in the repository.
     *
     * @param entity Source entity to be persisted
     * @return Reference to the entity that has been persisted
     */
    public T upsert(T entity){
        throw new NotImplementedException();
    }

    /**
     * Delete the specified entity from the repository.
     *
     * @param entity Source entity to be deleted
     */
    public void delete(T entity){
        throw new NotImplementedException();
    }
}

