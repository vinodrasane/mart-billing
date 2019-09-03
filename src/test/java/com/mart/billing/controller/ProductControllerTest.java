package com.mart.billing.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.mart.billing.service.BillingService;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {
	@Autowired
	private MockMvc mvc;

	@MockBean
	private BillingService service;

	// TODO: add test case for all Product controller endpoint
	@Test
	public void testProductEndpoint() {

	}
}
