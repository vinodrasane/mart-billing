package com.mart.billing.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class Item {
	
	//TODO: Create separate Models for Request and response one without Id other with.
	private String id;
	
	private Long productId;
	//TODO: Create separate Models for Request and response one with JsonIgnore BillId other without Json ignore.
	@NotNull
	private Long billId;
	@Min(1)
	@Max(50)
	private Integer quantity;
	
	private Double totalCost;
	private Double totalTax;
}