package com.mart.billing.validator;

import lombok.Data;

@Data
public class ValidationError {
	private String field;
	private String errorMessage;
}
