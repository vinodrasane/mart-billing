package com.mart.billing.model;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ItemTest {
	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();
	
	Item item = new Item();
	
	@Before
	public void setUp(){
		item.setQuantity(1);
		item.setBillId(1L);
	}
	
	@Test
	public void testQuantity_PositiveNumber_Success(){
		validationShouldSucceed(item);
	}
	
	@Test
	public void testQuantity_MaxBoundryNumber_Success(){
		item.setQuantity(50);
		validationShouldSucceed(item);
	}
	
	@Test
	public void testQuantity_MinBoundryNumber_Success(){
		item.setQuantity(1);
		validationShouldSucceed(item);
	}
	
	@Test
	public void testQuantity_NegativeNumber_Failure(){
		item.setQuantity(-1);
		validationShouldFail(item);
	}
	
	@Test
	public void testQuantity_OutOfRange_Failure(){
		item.setQuantity(51);
		validationShouldFail(item);
	}
	
	@Test
	public void testBillId_Null_Failure(){
		item.setBillId(null);
		validationShouldFail(item);
	}
	
	@Test
	public void testBillId_Valid_Success(){
		item.setBillId(1L);
		validationShouldSucceed(item);
	}
	
	public <T> void validationShouldSucceed(T t){
		Set<ConstraintViolation<T>> violations = validator.validate(t);
		Assert.assertTrue(violations.isEmpty());
	}
	
	public <T> void validationShouldFail(T t){
		Set<ConstraintViolation<T>> violations = validator.validate(t);
		Assert.assertFalse(violations.isEmpty());
	}
}
