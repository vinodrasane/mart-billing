package com.mart.billing.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.mart.billing.error.RecordCreationFailedException;
import com.mart.billing.model.Item;
import com.mart.billing.model.Product;
import com.mart.billing.model.ProductCategory;
import com.mart.billing.repo.BillRepository;
import com.mart.billing.repo.ItemRepository;
import com.mart.billing.repo.ProductRepository;
import com.mart.billing.repo.mapper.ItemMapper;
import com.mart.billing.repo.mapper.ProductMapper;
import com.mart.billing.repo.model.BillDo;
import com.mart.billing.repo.model.BillStatus;
import com.mart.billing.repo.model.ItemDo;
import com.mart.billing.repo.model.ProductCategoryDo;
import com.mart.billing.repo.model.ProductDo;
import com.mart.billing.validator.ItemValidator;
import com.mart.billing.validator.ValidationResult;

@RunWith(MockitoJUnitRunner.class)
public class BillingServiceImplTest {

	private static final Long ITEM_ID = 1L;
	private static final Long PRODUCT_ID = 1L;
	private static final Long BILL_ID = 1L;
	private static final Long PRODUCT_CAT_ID = 1L;
	@Mock
	private BillRepository billRepo;
	@Mock
	private ProductRepository productRepo;
	@Mock
	private ItemRepository itemRepo;
	@Mock
	private ItemValidator itemValidator;

	private ItemMapper itemMapper = Mappers.getMapper(ItemMapper.class);;
	private ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

	@Spy
	@InjectMocks
	private BillingServiceImpl service;

	Item item = new Item();
	ItemDo itemDo = new ItemDo();
	Product product = new Product();
	ProductDo productDo = new ProductDo();
	ProductCategory pcat = new ProductCategory();
	ProductCategoryDo pcatDo = new ProductCategoryDo();
	private BillDo billDo = new BillDo();

	@Before
	public void setUp() {
		service.setItemMapper(itemMapper);
		service.setProductMapper(productMapper);

		billDo.setId(BILL_ID);
		billDo.setStatus(BillStatus.IN_PROGRESS);
		billDo.setTotalCost(10.0);
		billDo.setTotalTax(1.0);

		item.setBillId(BILL_ID);
		item.setProductId(PRODUCT_ID);
		item.setQuantity(1);

		itemDo.setId(ITEM_ID);
		itemDo.setProduct(productDo);
		itemDo.setTotalCost(10.0);
		itemDo.setTotalTax(1.0);
		itemDo.setBill(billDo);

		productDo.setCategory(pcatDo);
		productDo.setId(PRODUCT_ID);
		productDo.setName("Pen");
		productDo.setCost(10.0);

		pcatDo.setId(PRODUCT_CAT_ID);
		pcatDo.setTax(0.1);
		pcatDo.setCatType("A_TYPE");

	}

	@Test
	public void testAddBillItem_Success() {
		Mockito.doReturn(Mockito.spy(ValidationResult.class)).when(itemValidator).validate(Mockito.any(Item.class));
		Mockito.doReturn(Mockito.spy(BillDo.class)).when(billRepo).save(Mockito.any(BillDo.class));
		Mockito.doReturn(itemDo).when(itemRepo).save(Mockito.any(ItemDo.class));
		Mockito.doReturn(Optional.of(productDo)).when(productRepo).findById(PRODUCT_ID);
		Mockito.doReturn(Optional.of(billDo)).when(billRepo).findById(BILL_ID);

		Item savedItem = service.addBillItem(item);
		assertEquals(ITEM_ID.toString(), savedItem.getId());
		assertEquals(item.getProductId(), savedItem.getProductId());
		assertEquals(item.getBillId(), savedItem.getBillId());
	}

	@Test
	public void testCreateBill_Success() {
		Mockito.doReturn(billDo).when(billRepo).save(Mockito.any(BillDo.class));
		Long newId = service.createBill();

		assertEquals(billDo.getId(), newId);
		Mockito.verify(billRepo, Mockito.times(1)).save(Mockito.any(BillDo.class));
	}

	@Test(expected = RecordCreationFailedException.class)
	public void testCreateBill_Failure() {
		Mockito.doThrow(RecordCreationFailedException.class).when(billRepo).save(Mockito.any(BillDo.class));
		service.createBill();
	}

	@Test
	public void testPrintBill_Success() {
		Mockito.doReturn(Optional.of(billDo)).when(billRepo).findById(BILL_ID);
		billDo.setStatus(BillStatus.PRINTED);
		Mockito.doReturn(billDo).when(billRepo).save(Mockito.any(BillDo.class));

		BillDo printedBill = service.printBill(BILL_ID);

		assertNotNull(printedBill);
		assertEquals(BILL_ID, printedBill.getId());
		assertEquals(BillStatus.PRINTED, printedBill.getStatus());

		Mockito.verify(billRepo, Mockito.times(1)).findById(BILL_ID);
		Mockito.verify(billRepo, Mockito.times(1)).save(Mockito.any(BillDo.class));
	}
	// TODO: testPrintBill_Failure expect Exception.
	
	@Test
	public void testGetBill(){
		Mockito.doReturn(Optional.of(billDo)).when(billRepo).findById(BILL_ID);
		BillDo bill = service.getBill(BILL_ID);
		assertNotNull(bill);
		assertEquals(BILL_ID, bill.getId());
		Mockito.verify(billRepo, Mockito.times(1)).findById(BILL_ID);
	}
	
	@Test
	public void testGetBills_Success(){
		Integer pageNo = 1;
		Integer pageSize = 10;
		String sortBy = "id";
		Page page = Mockito.spy(Page.class);
		Mockito.doReturn(page).when(billRepo).findAll(PageRequest.of(pageNo, pageSize, Sort.by(sortBy)));
		Page<BillDo> bills = service.getBills(pageNo, pageSize , sortBy);
		assertNotNull(bills);
		assertEquals(page, bills);
	}
	
	//TODO: Same test as for bills
	@Test
	public void testGetProducts_Success(){
	}
	
	@Test
	public void testGetProductDetails_Success(){
		Mockito.doReturn(Optional.of(productDo)).when(productRepo).findById(PRODUCT_ID);
		Product productDetails = service.getProductDetails(PRODUCT_ID);
		assertNotNull(productDetails);
	}
	
	@Test
	public void testGetBillDetails_Success(){
		Mockito.doReturn(Optional.of(billDo)).when(billRepo).findById(BILL_ID);
		BillDo billDetails = service.getBillDetails(BILL_ID);
		assertNotNull(billDetails);
		assertEquals(BILL_ID, billDetails.getId());
	}

}
