package com.mart.billing.repo.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity(name = "Item")
@Table(name = "ITEM")
public class ItemDo {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "native")
	private Long id;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "Bill_id", nullable = false)
	private BillDo bill;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Product_id", nullable = false)
	private ProductDo product;
	
	private Integer quantity;
	private Double totalCost;
	private Double totalTax;
	
	public static Double calculateTotalCost(ItemDo item){
		ProductDo prod = item.getProduct();
		if(prod != null && prod.getCost() != null){
			return item.getQuantity() * prod.getCost();
		}
		return 0.0;
	}
	
	public static Double calculateTotalTax(ItemDo item){
		ProductDo prod = item.getProduct();
		if(prod != null && prod.getCategory() != null){
			return item.getQuantity() * prod.getCost() * prod.getCategory().getTax();
		}
		return 0.0;
	}
}
