package com.mart.billing.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.mart.billing.service.BillingService;

@RunWith(SpringRunner.class)
@WebMvcTest(BillingController.class)
public class BillingControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private BillingService service;
	
	@Test
	public void testGetBill() throws Exception{
	  mvc.perform( MockMvcRequestBuilders
		      .get("/mart/bills")
		      .accept(MediaType.APPLICATION_JSON))
		      .andExpect(MockMvcResultMatchers.status().isOk());
	}
	//TODO: add test mvc test cases for other endpoints as well.
}
