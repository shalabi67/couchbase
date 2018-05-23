package com.couchbase.customer360.web;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.eclipse.jetty.http.HttpStatus.NOT_FOUND_404;
import static org.eclipse.jetty.http.HttpStatus.OK_200;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.couchbase.customer360.domain.Address;
import com.couchbase.customer360.domain.Customer;
import com.couchbase.customer360.json.JacksonConverter;
import com.couchbase.customer360.json.JsonConverter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class CustomerControllerTest extends BaseTest {
	private final JsonConverter converter = new JacksonConverter();
	private final static String PATH = BASE_PATH + "/customer";

	@Test
	public void createUpdateDeleteCustomer() {
		for(int i=0; i<RANDOM.nextInt(10); i++) {
			// Create the Customer and POST it
			Response response1 = 
				given()
					.contentType(ContentType.JSON)
					.body(converter.toJson(createSampleCustomer()))
					.post(PATH);
			assertNotNull("Response is null", response1);
			assertEquals("POST failed", response1.statusCode(), OK_200);
			Customer c1 = converter.fromJson(response1.asString(), Customer.class);
			assertNotNull("Customer is null", c1);
	
			// See if we can GET the Customer
			Response response2 = get(PATH + "/" + c1.getId());
			assertNotNull("Response is null", response2);
			assertEquals("GET failed", response2.statusCode(), OK_200);
			Customer c2 = converter.fromJson(response2.asString(), Customer.class);
			assertNotNull("Customer is null", c2);
			assertEquals("Customer objects are not equal", c1, c2);
	
			// Now update the Customer and PUT it
			c2.setFirstName(c2.getFirstName().concat(" - updated"));
			Response response3 = given()
				.contentType(ContentType.JSON)
				.body(converter.toJson(c2))
				.put(PATH + "/" + c1.getId());
			assertNotNull("Response is null", response3);
			assertEquals("PUT failed", response3.statusCode(), OK_200);
			Customer c3 = converter.fromJson(response3.asString(), Customer.class);
			assertNotNull("Customer is null", c3);
			assertTrue("firstName was not updated", 
				c3.getFirstName().endsWith("updated"));

			// See if we can GET the Customer again
			Response response4 = get(PATH + "/" + c1.getId());
			assertNotNull("Response is null", response4);
			assertEquals("GET failed", response4.statusCode(), OK_200);
			Customer c4 = converter.fromJson(response4.asString(), Customer.class);
			assertNotNull("Customer is null", c4);
			assertEquals("Customer objects are not equal", c3, c4);

			// See if we can force a CAS error
			c4.setFirstName(c2.getFirstName().concat(" - updated again"));
			long cas = c4.getCas();
			c4.setCas(RANDOM.nextLong());
			Response response5 = given()
				.contentType(ContentType.JSON)
				.body(converter.toJson(c4))
				.put(PATH + "/" + c1.getId());
			assertNotNull("Response is null", response5);
			assertNotEquals("PUT was successful", response5.statusCode(), OK_200);
			c4.setCas(cas);

			// Now DELETE the Customer
			Response response6 = given()
				.contentType(ContentType.JSON)
				.body(converter.toJson(c4))
				.delete(PATH + "/" + c1.getId());
			assertNotNull("Response is null", response6);
			assertEquals("DELETE failed", response6.statusCode(), OK_200);

			// See if we can GET the Customer one last time
			Response response7 = get(PATH + "/" + c1.getId());
			assertNotNull("Response is null", response7);
			assertEquals("GET failed", response7.statusCode(), NOT_FOUND_404);
		}
	}

	private static int customerNumber = 1;

	private Customer createSampleCustomer() {
		Customer c = new Customer();
		if(RANDOM.nextBoolean()) {
			c.setId(c.getClass().getSimpleName() + "_" + customerNumber);
		}
		c.setFirstName(randomFirstName());
		c.setLastName(randomLastName());
		c.setEmail(randomEmailAddress(c.getFirstName() + c.getLastName()));
		c.setPhoneNumber(randomPhoneNumber());
		String userName = c.getFirstName().charAt(0) + c.getLastName() + 
			String.valueOf(customerNumber++);
		c.setUserName(userName.toLowerCase());
		Address a = new Address();
		a.setLine1(randomStreetAddress());
		a.setCity(randomCity());
		a.setState(randomState());
		a.setPostalCode(randomNumberAsString(99999));
		a.setCountry("USA");
		c.setBillingAddress(a);
		c.setShippingAddress(a);
		return c;
	}
}