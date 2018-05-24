package com.couchbase.framework.entity;

public class CustomerFactory {
    public static final String EMAIL = "customer.a.com";
    public static final String USER_NAME = "user name";
    public static final String FIRST_NAME = "first name";
    public static final String LAST_NAME= "last name";
    public static final String PHONE_NUMBER= "phone number";
    public static final String ID = "customer::id";
   // public static final String = ;
    public static Customer createDefault() {
        return create(EMAIL, USER_NAME, FIRST_NAME,
                LAST_NAME, PHONE_NUMBER);

    }
    public static Customer create(String email,
                                  String userName,
                                  String firstName,
                                  String lastName,
                                  String phoneNumber) {
        Customer customer = new Customer();
        customer.setEmail(email);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setUserName(userName);
        customer.setPhoneNumber(phoneNumber);
        customer.setId(ID);

        return customer;
    }
}
