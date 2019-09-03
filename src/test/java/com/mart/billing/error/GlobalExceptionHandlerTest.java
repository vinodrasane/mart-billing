package com.mart.billing.error;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class GlobalExceptionHandlerTest {
	@Spy
	private GlobalExceptionHandler handler = new GlobalExceptionHandler();
	
	@Test
	public void testBusinessValidatinFailure() {
		RecordCreationFailedException exp = new RecordCreationFailedException("TEST");
		ResponseEntity<ErrorMessage> response = handler.handleCustomExceptions(exp);
		Assert.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
		Assert.assertEquals("2", response.getBody().getCode());
	}
	//TODO: add test case for other handlers.
	
}
