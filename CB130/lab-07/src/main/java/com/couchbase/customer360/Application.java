package com.couchbase.customer360;

import static java.lang.ClassLoader.getSystemResourceAsStream;

import java.io.IOException;
import java.util.Properties;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.customer360.data.CouchbaseRepository;
import com.couchbase.customer360.data.Repository;
import com.couchbase.customer360.web.CustomerController;

/**
 * Entry point for the Customer360 application that is part of Couchbase CB130J.
 * 
 * @author Tony Piazza
 */
public class Application {
	public static final String PROPERTIES_FILENAME = "application.properties";
	public static final String NODES_KEY = "couchbase.nodes";
	public static final String NODES_DEFAULT_VALUE = "127.0.0.1";
	public static final String USER_NAME_KEY = "couchbase.user_name";
	public static final String USER_PASS_KEY = "couchbase.user_pass";
	public static final String BASE_URL = "/customer360";

	public static void main(String[] args) {
		final Properties props = System.getProperties();
		try {
			props.load(getSystemResourceAsStream(PROPERTIES_FILENAME));
		} catch (IOException e) {
			System.err.println("Unable to open " + PROPERTIES_FILENAME);
		}
		String[] nodes = 
			props.getProperty(NODES_KEY, NODES_DEFAULT_VALUE).split(",");
		String username = props.getProperty(USER_NAME_KEY);
		String password = props.getProperty(USER_PASS_KEY);
		Cluster cluster = CouchbaseCluster.create(nodes);
		Repository repo = new CouchbaseRepository(cluster, username, password);
		new CustomerController(BASE_URL, repo);
	}
}