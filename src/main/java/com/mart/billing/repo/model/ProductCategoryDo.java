package com.mart.billing.repo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity(name = "ProductCategory")
@Table(name = "PRODUCT_CATEGORY")
public class ProductCategoryDo {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "native")
	private Long id;
	private String catType;
	
	@JsonIgnore
	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<ProductDo> products = new HashSet<>();
	private Double tax;
}
