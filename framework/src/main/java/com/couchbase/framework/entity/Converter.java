package com.couchbase.framework.entity;

import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.transcoder.JsonTranscoder;
import com.couchbase.framework.exception.ConversionException;

/**
 * Converts for couchbase document to an Entity object and vice versa.
 */
public class Converter {
    /**
     * Converts a JsonDocument into an object of the specified type
     *
     * @param couchbaseDocument JsonDocument (Couchbase Document) to be converted
     * @param entity Class<T> that represents the type of this or a parent class
     * @return Reference to an object of the specified type
     */
    public static <T extends Entity> T fromJsonDocument(JsonDocument couchbaseDocument, Class<T> entity) {
        if (couchbaseDocument == null) {
            throw new IllegalArgumentException("document should not be null.");
        }
        JsonObject content = couchbaseDocument.content();
        if (content == null) {
            throw new IllegalStateException("document content should not be null.");
        }
        if (entity == null) {
            throw new IllegalArgumentException("type should not be null.");
        }
        JsonConverter converter = new JsonConverter();
        T result = converter.fromJson(content.toString(), entity);
        result.setCas(couchbaseDocument.cas());
        return result;
    }

    /**
     * Converts an object to a JsonDocument (Couchbase Document)
     *
     * @param entity Object to be converted
     * @return JsonDocument (Couchbase Document) that represents the specified object
     */
    public static <T extends Entity> JsonDocument toJsonDocument(T entity) {
        if (entity == null) {
            throw new IllegalArgumentException("entity is null");
        }
        String id = entity.getId();
        if (id == null) {
            throw new IllegalStateException("entity ID is null");
        }
        try {
            JsonTranscoder transcoder = new JsonTranscoder();
            JsonConverter converter = new JsonConverter();
            JsonObject content =
                    transcoder.stringToJsonObject(converter.toJson(entity));
            JsonDocument couchbaseDocument = JsonDocument.create(id, content, entity.getCas());
            return couchbaseDocument;
        } catch (Exception e) {
            throw new ConversionException(e);
        }
    }
}