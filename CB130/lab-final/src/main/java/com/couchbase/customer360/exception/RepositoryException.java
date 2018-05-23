package com.couchbase.customer360.exception;

public class RepositoryException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public RepositoryException(String message) {
		super(message);
	}

	public RepositoryException(Throwable t) {
		super(t);
	}

	public RepositoryException(String message, Throwable t) {
		super(message, t);
	}
}