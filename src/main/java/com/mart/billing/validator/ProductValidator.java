package com.mart.billing.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mart.billing.error.RecordNotFoundException;
import com.mart.billing.repo.ProductRepository;
import com.mart.billing.repo.model.ProductDo;

@Component
public class ProductValidator implements Validator<Long> {

	@Autowired
	private ProductRepository productRepo;

	@Override
	public ValidationResult validate(Long id) {
		ProductDo productDo = productRepo.findById(id).orElseThrow(() -> 
				new RecordNotFoundException("Product record not found for given Id:" + id));
		return ValidationResult.builder().valid().withResult(productDo).build();
	}

}
