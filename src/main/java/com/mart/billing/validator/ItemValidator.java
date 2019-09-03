package com.mart.billing.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mart.billing.model.Item;

@Component
public class ItemValidator implements Validator<Item> {

	@Autowired
	private ProductValidator productIdValidator;

	@Autowired
	private BillValidator billIdValidator;

	@Override
	public ValidationResult validate(Item bean) {
		productIdValidator.validate(bean.getProductId());
		billIdValidator.validate(bean.getBillId());
		return ValidationResult.builder().valid().build();
	}

}
