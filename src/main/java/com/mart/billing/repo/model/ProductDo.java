package com.mart.billing.repo.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity(name = "Product")
@Table(name = "PRODUCT")
public class ProductDo {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "native")
	private Long id;
	private String name;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Product_Category_id", nullable = false)
	private ProductCategoryDo category;
	private Double cost;

}
