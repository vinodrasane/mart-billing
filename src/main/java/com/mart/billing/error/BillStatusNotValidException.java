package com.mart.billing.error;

public class BillStatusNotValidException extends CustomException {
	private static final long serialVersionUID = 1L;
	
	public BillStatusNotValidException(String msg){
		super(msg, "1");
	}
}
