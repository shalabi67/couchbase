package com.couchbase.framework.learn_query;

import com.couchbase.client.core.CouchbaseException;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.N1qlParams;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;
import com.couchbase.client.java.query.ParameterizedN1qlQuery;
import com.couchbase.client.java.query.consistency.ScanConsistency;
import com.couchbase.framework.entity.Converter;
import com.couchbase.framework.entity.Customer;
import com.couchbase.framework.entity.JsonConverter;
import com.couchbase.framework.repository.CouchbaseRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParametrizedQueryLearn {
	private static final String[] COUCHBASE_NODES = {"localhost"};
	private static final String BUCKET_NAME = "customer360";
	private static final String BUCKET_PASSWORD = "password";

	private Bucket bucket;

	@Before
	public void init() {
		Cluster cluster = CouchbaseRepository.createCluster(COUCHBASE_NODES);
		bucket = cluster.openBucket(BUCKET_NAME, BUCKET_PASSWORD);

	}
	@Test
	public void getNRows() {
		JsonDocument doc;
		List<Customer> customers = new ArrayList<Customer>();
		try {
			// doc = bucket.get(id);
			String statement = "SELECT customer360.* FROM customer360 limit $1";
			JsonArray values = JsonArray.empty().add(10);
			N1qlParams params =
					N1qlParams.build().consistency(ScanConsistency.REQUEST_PLUS);
			ParameterizedN1qlQuery query =
					ParameterizedN1qlQuery.parameterized(statement, values, params);
			N1qlQueryResult result = bucket.query(query);
			List<N1qlQueryRow> list = result.allRows();
			if (list.size() == 0) {
				doc = null;
			} else {
				for(N1qlQueryRow row : list) {
					JsonObject jsonObject = row.value();
					JsonConverter jsonConverter = new JsonConverter();
					Customer customer = jsonConverter.fromJson(jsonObject.toString(), Customer.class);
					customers.add(customer);
					//JsonDocument document = getDocument(row);
					//customers.add(Converter.fromJsonDocument(document, Customer.class));
				}
			}
		} catch (CouchbaseException e) {
			System.out.println(e.getStackTrace());
		}
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
