package com.mart.billing.service;

import java.util.Optional;

import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mart.billing.error.RecordCreationFailedException;
import com.mart.billing.error.RecordNotFoundException;
import com.mart.billing.model.Item;
import com.mart.billing.model.Product;
import com.mart.billing.repo.BillRepository;
import com.mart.billing.repo.ItemRepository;
import com.mart.billing.repo.ProductRepository;
import com.mart.billing.repo.mapper.ItemMapper;
import com.mart.billing.repo.mapper.ProductMapper;
import com.mart.billing.repo.model.BillDo;
import com.mart.billing.repo.model.BillStatus;
import com.mart.billing.repo.model.ItemDo;
import com.mart.billing.repo.model.ProductDo;
import com.mart.billing.validator.ItemValidator;

@Service
public class BillingServiceImpl implements BillingService {

	
	@Autowired
	private BillRepository billRepo;
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private ItemRepository itemRepo;
	@Autowired
	private ItemValidator itemValidator;
	@Autowired
	@Setter
	private ItemMapper itemMapper;
	@Autowired
	@Setter
	private ProductMapper productMapper;
	
	@Override
	public Item addBillItem(Item item) {
		itemValidator.validate(item);
		ItemDo itemDo = itemMapper.map(item);
		itemDo.setProduct(getProduct(item.getProductId()));
		itemDo.setTotalCost(ItemDo.calculateTotalCost(itemDo));
		itemDo.setTotalTax(ItemDo.calculateTotalTax(itemDo));
		itemDo.setBill(updateBillRecord(itemDo));
		return itemMapper.map(itemRepo.save(itemDo));
	}

	private BillDo updateBillRecord(ItemDo item) {
		BillDo bill = getBill(item.getBill().getId());
		bill.addToTotalCost(item.getTotalCost());
		bill.addToTotalTax(item.getTotalTax());
		return billRepo.save(bill);
	}

	@Override
	public Long createBill() {
		BillDo billDo = new BillDo();
		billDo.setStatus(BillStatus.IN_PROGRESS);
		BillDo bill = Optional.of(billRepo.save(billDo)).orElseThrow(
				() -> new RecordCreationFailedException("Failed To create Bill Record in DB"));
		return bill.getId();
	}
	
	@Override
	public BillDo printBill(Long id) {
		BillDo bill = getBill(id);
		if(bill != null){
			//Printer.print(bill);
			bill.setStatus(BillStatus.PRINTED);
			bill = billRepo.save(bill);
		}
		return bill;
	}
	
	private ProductDo getProduct(Long productId) {
		return productRepo.findById(productId).orElseThrow(() -> 
			new RecordNotFoundException("Product record not found for given Id:" + productId));
	}

	@Override
	public BillDo getBill(Long billId) {
		BillDo bill = billRepo.findById(billId).orElseThrow(() -> 
				new RecordNotFoundException("Bill record not found or its already printed."));
		//itemRepo.findAllById(arg0)
		return bill;
	}

	@Override
	public Page<BillDo> getBills(Integer pageNo, Integer pageSize, String sortBy){
		return billRepo.findAll(PageRequest.of(pageNo, pageSize, Sort.by(sortBy)));
	}

	@Override
	public Page<ProductDo> getProducts(Integer pageNo, Integer pageSize, String sortBy) {
		 return productRepo.findAll(PageRequest.of(pageNo, pageSize, Sort.by(sortBy)));
	}

	@Override
	public Product getProductDetails(Long productId) {
		ProductDo product = getProduct(productId);
		return productMapper.map(product);
	}

	@Override
	public BillDo getBillDetails(Long billId) {
		return getBill(billId);
	}
	
}
