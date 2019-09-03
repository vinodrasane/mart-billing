package com.mart.billing.validator;

import java.util.List;

import lombok.Data;

@Data
public class ValidationResult {
	private Object returnResult;
	private boolean valid;
	private List<ValidationError> problems;
	
	public static Builder builder(){
		return new Builder();
	}
	
	public static class Builder {
		private ValidationResult instance;
		public Builder(){
			instance = new ValidationResult();
		}
		public Builder valid(){
			instance.setValid(true);
			return this;
		}
		public Builder inValid(){
			instance.setValid(false);
			return this;
		}
		public Builder withResult(Object returnResult){
			instance.setReturnResult(returnResult);
			return this;
		}
		public ValidationResult build(){
			return instance;
		}
	}
}
