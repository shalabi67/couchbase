package com.couchbase.customer360.web;

import java.util.Arrays;
import java.util.List;

import org.eclipse.jetty.http.HttpStatus;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import spark.route.HttpMethod;

import com.couchbase.customer360.data.Repository;
import com.couchbase.customer360.domain.Entity;
import com.couchbase.customer360.json.JacksonConverter;
import com.couchbase.customer360.json.JsonConverter;

/**
 * Base class for Controllers that provides useful methods for implementing
 * request handling.
 * 
 * @author Tony Piazza
 */
public abstract class BaseController {
	private final JsonConverter converter = new JacksonConverter();
	private Repository repo;

	/**
	 * Constructor
	 * 
	 * @param repo Reference to Repository instance
	 */
	protected BaseController(Repository repo) {
		this.repo = repo;
	}

	/**
	 * Method used to map standard RESTful API to corresponding Repository
	 * interface methods.
	 * 
	 * @param baseURL Base URL for mapping requests
	 * @param type Specific type of the entities being handled
	 * @param methods Specific HTTP methods that should not be mapped
	 */
	protected <T extends Entity> void mapRoutes(final String baseURL, 
		final Class<T> type, HttpMethod... methods) {
		List<HttpMethod> methodList = Arrays.asList(methods); 
		if(!methodList.contains(HttpMethod.get)) {
			Spark.get(baseURL + "/:id", new Route() {
				@Override
				public Object handle(Request request, Response response) {
					T entity = repo.findById(request.params(":id"), type);
					if(entity == null) {
						return error(response, HttpStatus.NOT_FOUND_404);
					} else {
						contentLocation(response, baseURL + "/" + entity.getId());
						return ok(response, entity);
					}
				}
			});
		}
		if(!methodList.contains(HttpMethod.post)) {
			Spark.post(baseURL, new Route() {
				@Override
				public Object handle(Request request, Response response) {
					T entity = converter.fromJson(request.body(), type);
					contentLocation(response, baseURL + "/" + entity.getId());
					T result = repo.create(entity, type);
					return ok(response, result);
				}
			});
		}
		if(!methodList.contains(HttpMethod.put)) {
			Spark.put(baseURL + "/:id", new Route() {
				@Override
				public Object handle(Request request, Response response) {
					T entity = converter.fromJson(request.body(), type);
					if(!request.params(":id").equals(entity.getId())) {
						response.body("Invalid ID specified");
						return error(response, HttpStatus.BAD_REQUEST_400);
					}
					contentLocation(response, baseURL + "/" + entity.getId());
					T result = repo.update(entity, type);
					return ok(response, result);
				}
			});
		}
		if(!methodList.contains(HttpMethod.delete)) {
			Spark.delete(baseURL + "/:id", new Route() {
				@Override
				public Object handle(Request request, Response response) {
					T entity = converter.fromJson(request.body(), type);
					if(!request.params(":id").equals(entity.getId())) {
						response.body("Invalid ID specified");
						return error(response, HttpStatus.BAD_REQUEST_400);
					}
					repo.delete(entity);
					return ok(response);
				}
			});
		}
	}

	/**
	 * Set the Content-Location header on the response with the location of a
	 * specific resource.
	 * 
	 * @param response Reference to the response
	 * @param location URL of the entity being referenced
	 */
	protected void contentLocation(Response response, String location) {
		response.header("Content-Location", location);
	}

	/**
	 * Method invoked to set HTTP status code to OK and serialize the 
	 * result to JSON.
	 * 
	 * @param response Reference to the response
	 * @param result Reference to the entity being serialized
	 * @return JSON String representing the serialized entity
	 */
	protected <T> String ok(Response response, T entity) {
		response.type("application/json");
		response.status(HttpStatus.OK_200);
		return converter.toJson(entity);
	}

	/**
	 * Method invoked to set HTTP status code to OK and return simple
	 * JSON payload with the specified message.
	 * 
	 * @param response Reference to the response
	 * @param msg String message to include in response payload
	 * @return JSON String with specified method included
	 */
	protected String ok(Response response, String msg) {
		response.type("application/json");
		response.status(HttpStatus.OK_200);
		return "{\"message\":\"" + msg + "\"}";
	}

	/**
	 * Method invoked to set HTTP status code to OK and return simple
	 * JSON payload with SUCCESS message.
	 * 
	 * @param response Reference to the response
	 * @return JSON String with standard SUCCESS message
	 */
	protected String ok(Response response) {
		response.type("application/json");
		response.status(HttpStatus.OK_200);
		return "{\"message\":\"SUCCESS\"}";
	}

	/**
	 * Method invoked to set HTTP status code to specified value and return
	 * either the existing body content as payload or an appropriate message.
	 * 
	 * @param response Reference to the response
	 * @param status Standard HTTP status code
	 * @return JSON String with appropriate message included
	 */
	protected String error(Response response, int status) {
		response.type("application/json");
		response.status(status);
		String msg = response.body();
		return "{\"message\":\"" + 
			(msg == null ? HttpStatus.getMessage(status) : msg) + "\"}";
	}
}