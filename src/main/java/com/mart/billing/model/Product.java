package com.mart.billing.model;

import lombok.Data;

@Data
public class Product {
	private String name;
	private Double cost;
	private ProductCategory category; 
}
