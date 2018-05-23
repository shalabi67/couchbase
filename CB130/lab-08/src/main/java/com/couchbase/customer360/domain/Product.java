package com.couchbase.customer360.domain;

import java.math.BigDecimal;

public class Product extends Entity {
	private String description;
	private BigDecimal price;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}