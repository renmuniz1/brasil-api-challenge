package com.brasil.prev.challenge.controller.customer;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.ParseException;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.brasil.prev.challenge.dto.model.customer.CustumerDTO;
import com.brasil.prev.challenge.model.customer.Customer;
import com.brasil.prev.challenge.service.customer.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, MockitoTestExecutionListener.class })
public class CustomerControllerTest {
	
	static final String URL = "/customer/v1/";
	static final String CUSTOMER_NAME = "Renato Muniz";
	static final String CUSTOMER_CPF = "21231684089";
	static final String CUSTOMER_ADDRESS = "Rua Rodrigues Lobo 120";
	static final Long CUSTOMER_ID = 1L;
	
	
	
	HttpHeaders headers;
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	CustomerService customerService;
	
	
	@Test
	@Order(1)
	public void testSave() throws Exception {
		
		BDDMockito.given(customerService.save(Mockito.any(Customer.class))).willReturn(getMockCustomer());
		
		mockMvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayload(CUSTOMER_ID, CUSTOMER_NAME, CUSTOMER_CPF, CUSTOMER_ADDRESS))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").value(CUSTOMER_ID))
				.andExpect(jsonPath("$.name").value(CUSTOMER_NAME))
				.andExpect(jsonPath("$.cpf").value(CUSTOMER_CPF))
				.andExpect(jsonPath("$.address").value(CUSTOMER_ADDRESS));
	}
	
	
	private Customer getMockCustomer() throws ParseException {
		return new Customer(CUSTOMER_ID,CUSTOMER_NAME,CUSTOMER_CPF,CUSTOMER_ADDRESS);
	}
	
	private String getJsonPayload(Long id, String name, String cpf, String address) 
			throws JsonProcessingException {
		
		CustumerDTO dto = new CustumerDTO(id, name, cpf,address);
		return new ObjectMapper().writeValueAsString(dto);
	}

}
