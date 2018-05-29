package com.couchbase.framework.learn_query;

import com.couchbase.client.core.CouchbaseException;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.JsonLongDocument;
import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.N1qlParams;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;
import com.couchbase.client.java.query.ParameterizedN1qlQuery;
import com.couchbase.client.java.query.consistency.ScanConsistency;
import com.couchbase.framework.entity.Customer;
import com.couchbase.framework.entity.JsonConverter;
import com.couchbase.framework.repository.CouchbaseRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CounterLearn {
	private static final String[] COUCHBASE_NODES = {"localhost"};
	private static final String BUCKET_NAME = "customer360";
	private static final String BUCKET_PASSWORD = "password";

	private static final String COUNTER_NAME = "TEST_COUNTER";
	private Bucket bucket;

	@Before
	public void init() {
		Cluster cluster = CouchbaseRepository.createCluster(COUCHBASE_NODES);
		bucket = cluster.openBucket(BUCKET_NAME, BUCKET_PASSWORD);

	}
	@Test
	public void createCounter() {
		JsonLongDocument counterDocument = bucket.counter(COUNTER_NAME, 1, 100);
		counterDocument.content();
	}
	private JsonDocument getDocument(N1qlQueryRow row) {
		JsonObject jsonObject = row.value();
		return JsonDocument.create("", jsonObject);
	}
	private String getKey(Map<String, Object> map) {
		return (String)map.keySet().toArray()[0];
	}
	private JsonObject getValue(Map<String, Object> map) {
		return (JsonObject)map.values().toArray()[0];
	}


}
