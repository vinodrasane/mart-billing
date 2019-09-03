package com.mart.billing.error;


public class RecordCreationFailedException extends CustomException {
	private static final long serialVersionUID = 1L;

	public RecordCreationFailedException(String msg) {
		super(msg, "2");
	}

}
