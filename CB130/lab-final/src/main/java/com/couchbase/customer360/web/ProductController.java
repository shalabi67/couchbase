package com.couchbase.customer360.web;

import spark.route.HttpMethod;

import com.couchbase.customer360.data.Repository;
import com.couchbase.customer360.domain.Product;

/**
 * Provides RESTful API for Product data 
 * 
 * @author Tony Piazza
 */
public class ProductController extends BaseController {
	public ProductController(String baseURL, Repository repo) {
		super(repo);

		mapRoutes(baseURL + "/product", Product.class, HttpMethod.delete);
	}
}