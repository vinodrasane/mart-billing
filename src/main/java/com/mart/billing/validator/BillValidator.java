package com.mart.billing.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mart.billing.error.BillStatusNotValidException;
import com.mart.billing.error.RecordNotFoundException;
import com.mart.billing.repo.BillRepository;
import com.mart.billing.repo.model.BillDo;
import com.mart.billing.repo.model.BillStatus;

@Component
public class BillValidator implements Validator<Long>{
	@Autowired
	private BillRepository billRepo;

	@Override
	public ValidationResult validate(Long id) {
		BillDo billDo = checkIfRecordExists(id);
		checkBillStatus(billDo);
		return ValidationResult.builder().valid().withResult(billDo).build();
	}

	private BillDo checkIfRecordExists(Long id) {
		return billRepo.findById(id).orElseThrow(() -> 
				new RecordNotFoundException("Bill record not found for given Id:" + id));
	}

	private void checkBillStatus(BillDo BillDo) {
		if(!BillStatus.IN_PROGRESS.equals(BillDo.getStatus())){
			throw new BillStatusNotValidException("Bill Status is not valid, id:"+BillDo.getId());
		}
	}
}
