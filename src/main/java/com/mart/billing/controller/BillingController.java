package com.mart.billing.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mart.billing.model.Item;
import com.mart.billing.repo.model.BillDo;
import com.mart.billing.service.BillingService;


@RestController
@RequestMapping(path = "/mart/bills", produces = "application/json")
@Validated
public class BillingController {

	@Autowired
	private BillingService service;
	
	@PostMapping
	public ResponseEntity<Long> createBill() {
		return ResponseEntity.ok(service.createBill());
	}
	
	@PostMapping("/{billId}")
	public ResponseEntity<Item> addItem(@PathVariable Long billId, @Valid @RequestBody Item item){
		item.setBillId(billId);
		return ResponseEntity.ok(service.addBillItem(item));
	}
	
	@PostMapping("/{billId}/print")
	public ResponseEntity<BillDo> printBill(@PathVariable Long billId){
		return ResponseEntity.ok(service.printBill(billId));
	}
	
	@GetMapping
	@Profile("default")
	public ResponseEntity<Page<BillDo>> getBills(@RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy){
		return ResponseEntity.ok(service.getBills(pageNo, pageSize, sortBy));
	}
	
	@GetMapping("/{billId}")
	@Profile("default")
	public ResponseEntity<BillDo> getBillDetails(@PathVariable Long billId){
		return ResponseEntity.ok(service.getBillDetails(billId));
	}
	
}
