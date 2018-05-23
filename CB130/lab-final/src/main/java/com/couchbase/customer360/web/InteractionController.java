package com.couchbase.customer360.web;

import spark.route.HttpMethod;

import com.couchbase.customer360.data.Repository;
import com.couchbase.customer360.domain.Interaction;

/**
 * Provides RESTful API for Interaction data 
 * 
 * @author Tony Piazza
 */
public class InteractionController extends BaseController {
	public InteractionController(String baseURL, Repository repo) {
		super(repo);

		mapRoutes(baseURL + "/interaction", Interaction.class, HttpMethod.get);
	}
}