package com.mart.billing.validator;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.mart.billing.error.BillStatusNotValidException;
import com.mart.billing.error.RecordNotFoundException;
import com.mart.billing.repo.BillRepository;
import com.mart.billing.repo.model.BillDo;
import com.mart.billing.repo.model.BillStatus;

@RunWith(MockitoJUnitRunner.class)
public class BillValidatorTest {
	private static final long BILL_ID = 1L;
	@Spy
	@InjectMocks
	BillValidator validator = new BillValidator();
	
	@Mock
	BillRepository billRepo;
	
	BillDo billDo = new BillDo();
	
	@Before
	public void setup(){
		billDo.setId(BILL_ID);
		billDo.setStatus(BillStatus.IN_PROGRESS);
		billDo.setTotalCost(10.0);
		billDo.setTotalTax(1.0);
	}
	
	@Test(expected = RecordNotFoundException.class)
	public void testValidateBill_RecordNotExists_Fail(){
		Mockito.doThrow(RecordNotFoundException.class).when(billRepo).findById(BILL_ID);
		validator.validate(BILL_ID);
	}
	
	@Test
	public void testValidateBill_RecordExists_Success(){
		Mockito.doReturn(Optional.of(billDo)).when(billRepo).findById(BILL_ID);
		validator.validate(BILL_ID);
	}
	
	@Test(expected = BillStatusNotValidException.class)
	public void testValidateBill_BillStatusNotValid_Fail(){
		Mockito.doThrow(BillStatusNotValidException.class).when(billRepo).findById(BILL_ID);
		validator.validate(BILL_ID);
	}
}
