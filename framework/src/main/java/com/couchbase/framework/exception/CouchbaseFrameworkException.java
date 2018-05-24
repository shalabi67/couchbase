package com.couchbase.framework.exception;

public class CouchbaseFrameworkException extends RuntimeException {
    public CouchbaseFrameworkException() {
        super();
    }

    public CouchbaseFrameworkException(String message) {
        super(message);
    }

    public CouchbaseFrameworkException(String message, Throwable cause) {
        super(message, cause);
    }

    public CouchbaseFrameworkException(Throwable cause) {
        super(cause);
    }
}
