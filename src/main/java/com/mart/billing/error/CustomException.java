package com.mart.billing.error;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CustomException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String code;
	public CustomException(String msg, String code){
		super(msg);
		this.code = code;
	}
}
