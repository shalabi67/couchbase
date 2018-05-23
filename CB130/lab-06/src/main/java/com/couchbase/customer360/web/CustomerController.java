package com.couchbase.customer360.web;

import com.couchbase.customer360.data.Repository;
import com.couchbase.customer360.domain.Customer;

/**
 * Provides RESTful API for Customer data 
 * 
 * @author Tony Piazza
 */
public class CustomerController extends BaseController {
	public final static String PATH = "/customer";

	public CustomerController(String baseURL, Repository repo) {
		super(repo);

		mapRoutes(baseURL + "/customer", Customer.class);
	}
}