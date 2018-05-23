package com.couchbase.customer360.data;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.transcoder.JsonTranscoder;
import com.couchbase.customer360.domain.Entity;
import com.couchbase.customer360.exception.RepositoryException;
import com.couchbase.customer360.json.JacksonConverter;
import com.couchbase.customer360.json.JsonConverter;

/**
 * Implementation of the Repository interface that uses the synchronous API 
 * exposed by the Couchbase Java SDK.
 * 
 * @author Tony Piazza
 */
public class CouchbaseRepository implements Repository {
	private final JsonConverter converter = new JacksonConverter();
	private final JsonTranscoder transcoder = new JsonTranscoder();
	private Bucket bucket;

	/**
	 * Constructor
	 * 
	 * @param cluster Couchbase Cluster
	 * @param bucketName Name of the bucket to use
	 */
	public CouchbaseRepository(Cluster cluster, String bucketName) {
		/*
		 * TODO: Implement this method using the appropriate openBucket method 
		 *       of the cluster interface
		 */
		throw new UnsupportedOperationException("Not implemented");
	}

	/**
	 * Constructor
	 * 
	 * @param cluster Couchbase Cluster
	 * @param bucketName Name of the bucket to use
	 * @param bucketPassword Password for the specified bucket
	 */
	public CouchbaseRepository(Cluster cluster, String bucketName,
		String bucketPassword) {
		/*
		 * TODO: Implement this method using the appropriate open method 
		 *       of the cluster interface
		 */
		throw new UnsupportedOperationException("Not implemented");
	}

	/**
	  * @see Repository#findById(String, Class<? extends T>) findById
	  */
	@Override
	public <T extends Entity> T findById(String id, Class<? extends T> type) {
		throw new UnsupportedOperationException("Not implemented");
	}

	/**
	  * @see Repository#create(T, Class<? extends T>) create
	  */
	@Override
	public <T extends Entity> T create(T entity, Class<? extends T> type) {
		throw new UnsupportedOperationException("Not implemented");
	}

	/**
	  * @see Repository#update(T, Class<? extends T>) update
	  */
	@Override
	public <T extends Entity> T update(T entity, Class<? extends T> type) {
		throw new UnsupportedOperationException("Not implemented");
	}

	/**
	  * @see Repository#upsert(T, Class<? extends T>) upsert
	  */
	@Override
	public <T extends Entity> T upsert(T entity, Class<? extends T> type) {
		
		throw new UnsupportedOperationException("Not implemented");
	}

	/**
	  * @see Repository#delete(T) delete
	  */
	@Override
	public <T extends Entity> void delete(T entity) {
		throw new UnsupportedOperationException("Not implemented");
	}

	/**
	 * Converts a JsonDocument into an object of the specified type
	 * 
	 * @param doc JsonDocument to be converted
	 * @param type Class<T> that represents the type of this or a parent class
	 * @return Reference to an object of the specified type
	 */
	protected <T extends Entity> T fromJsonDocument(JsonDocument doc, Class<T> type) {
		if (doc == null) {
			throw new IllegalArgumentException("document is null");
		}
		JsonObject content = doc.content();
		if (content == null) {
			throw new IllegalStateException("document has no content");
		}
		if (type == null) {
			throw new IllegalArgumentException("type is null");
		}
		T result = converter.fromJson(content.toString(), type);
		return result;
	}

	/**
	 * Converts an object to a JsonDocument
	 * 
	 * @param source Object to be converted
	 * @return JsonDocument that represents the specified object
	 */
	protected <T extends Entity> JsonDocument toJsonDocument(T source) {
		if (source == null) {
			throw new IllegalArgumentException("entity is null");
		}
		String id = source.getId();
		if (id == null) {
			throw new IllegalStateException("entity ID is null");
		}
		try {
			JsonObject content = 
				transcoder.stringToJsonObject(converter.toJson(source));
			JsonDocument doc = JsonDocument.create(id, content);
			return doc;
		} catch (Exception e) {
			throw new RepositoryException(e);
		}
	}
}