package com.mart.billing.repo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity(name = "Bill")
@Table(name = "BILL")
public class BillDo {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "native")
	private Long id;
	
	//@JsonBackReference
	@OneToMany( mappedBy = "bill", fetch = FetchType.EAGER)
	private List<ItemDo> items = new ArrayList<>();
	
	private Double totalCost;
	private Double totalTax;
	private LocalDateTime creationTime = LocalDateTime.now();
	
	@Enumerated(EnumType.STRING)
	private BillStatus status;

	public void addToTotalCost(Double itemTotalCost) {
		if(this.totalCost == null){
			this.totalCost = 0.0;
		}
		if(itemTotalCost != null){
			this.totalCost = this.totalCost + itemTotalCost;
		}
	}

	public void addToTotalTax(Double itemTotalTax) {
		if(totalTax == null){
			totalTax = 0.0;
		}
		if(itemTotalTax != null){
			this.totalTax = this.totalTax + itemTotalTax;
		}
	}
}
