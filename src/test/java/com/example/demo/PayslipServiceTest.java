package com.example.demo;




import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;


import com.example.demo.entity.Employee;
import com.example.demo.entity.Payslip;

import com.example.demo.util.TaxCalculator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flipkart.zjsonpatch.JsonDiff;


@ExtendWith(MockitoExtension.class)
public class PayslipServiceTest {

	private TaxCalculator taxCalculator;

	private ObjectMapper mapper = new ObjectMapper();

	private List<Employee> employees = new ArrayList<>();
	

	@DisplayName("JUnit test for prepare payslip method")
	@Test
	public void preparePaySlip_returnpayslips() throws Exception{
		
		 taxCalculator = new TaxCalculator();
		 taxCalculator.prepareThresholds();
		File file = new File("src/main/resources/employees.json");
		employees = mapper.readValue(file, new TypeReference<List<Employee>>() {});
		
		List<Payslip>payslips = new ArrayList<>();
		for ( Employee e: employees) {
			Payslip p = taxCalculator.preparePaySlip(e);
			
			payslips.add(p);
		}
		
		ClassLoader classLoader = getClass().getClassLoader();
		File desFile = new File("target/payslips.json");
		mapper.writeValue(desFile, payslips);
		JsonNode beforeNode = mapper.readTree(classLoader.getResourceAsStream("expected-payslips.json"));
		JsonNode afterNode = mapper.readTree(desFile);
	
		JsonNode patch = JsonDiff.asJson(beforeNode, afterNode);
		String diffs = patch.toString();
		assertEquals("No difference should be found", "[]", diffs);
		

	}
}
