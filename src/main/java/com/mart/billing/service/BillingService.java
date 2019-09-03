package com.mart.billing.service;

import org.springframework.data.domain.Page;

import com.mart.billing.model.Item;
import com.mart.billing.model.Product;
import com.mart.billing.repo.model.BillDo;
import com.mart.billing.repo.model.ProductDo;

public interface BillingService {

	BillDo printBill(Long id);

	Long createBill();

	Item addBillItem(Item item);

	BillDo getBill(Long billId);

	Page<BillDo> getBills(Integer pageNo, Integer pageSize, String sortBy);

	Page<ProductDo> getProducts(Integer pageNo, Integer pageSize, String sortBy);

	Product getProductDetails(Long productId);

	BillDo getBillDetails(Long billId);
}
