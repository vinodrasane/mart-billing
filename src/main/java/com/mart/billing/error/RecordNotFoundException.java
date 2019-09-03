package com.mart.billing.error;

public class RecordNotFoundException extends CustomException {
	private static final long serialVersionUID = 1L;

	public RecordNotFoundException(String msg){
		super(msg, "3");
	}
}
