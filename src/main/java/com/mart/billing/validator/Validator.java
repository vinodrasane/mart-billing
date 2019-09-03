package com.mart.billing.validator;

public interface Validator<T> {
	ValidationResult validate(T bean);
}
