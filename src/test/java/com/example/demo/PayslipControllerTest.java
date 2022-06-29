package com.example.demo;

import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;

import com.example.demo.controller.PaySlipController;
import com.example.demo.service.PayslipService;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PaySlipController.class)
class PayslipControllerTest {

	@Autowired
	PaySlipController paySlipController;

	@MockBean
	PayslipService PayslipService;

	@Autowired
	MockMvc mvc;

	@Test
	public void createRecord_success() throws Exception {

		File file = new File("src/main/resources/employees.json");
		ObjectMapper mapper = new ObjectMapper();
		JsonNode beforeNode = mapper.readTree(file);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/demo/payslips")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(beforeNode.toString());

		mvc.perform(mockRequest).andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue())).andReturn();
		
	}

}
