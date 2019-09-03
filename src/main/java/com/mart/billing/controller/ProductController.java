package com.mart.billing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mart.billing.model.Product;
import com.mart.billing.repo.model.ProductDo;
import com.mart.billing.service.BillingService;

@RestController
@RequestMapping(path = "/mart/products", produces = "application/json")
public class ProductController {
	@Autowired
	private BillingService service;
	
	@GetMapping
	@Profile("default")
	public ResponseEntity<Page<ProductDo>> getProducts(@RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy){
		return ResponseEntity.ok(service.getProducts(pageNo, pageSize, sortBy));
	}
	
	@GetMapping("/{productId}")
	@Profile("default")
	public ResponseEntity<Product> getProdctDetails(@PathVariable Long productId){
		return ResponseEntity.ok(service.getProductDetails(productId));
	}
}
