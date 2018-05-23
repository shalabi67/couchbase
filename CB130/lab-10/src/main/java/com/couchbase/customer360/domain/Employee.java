package com.couchbase.customer360.domain;

import java.util.Date;

public class Employee extends Person {
	private String businessEmail;
	private String businessPhone;
	private String ssn;
	private Date hired;

	public String getBusinessEmail() {
		return businessEmail;
	}

	public void setBusinessEmail(String businessEmail) {
		this.businessEmail = businessEmail;
	}

	public String getBusinessPhone() {
		return businessPhone;
	}

	public void setBusinessPhone(String businessPhone) {
		this.businessPhone = businessPhone;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public Date getHired() {
		return hired;
	}

	public void setHired(Date hired) {
		this.hired = hired;
	}
}