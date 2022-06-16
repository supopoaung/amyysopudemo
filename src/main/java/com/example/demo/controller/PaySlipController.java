package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import com.example.demo.entity.*;
import com.example.demo.service.PayslipService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/demo")
public class PaySlipController {

	@Autowired
	private PayslipService payslipService;

	
	
	@RequestMapping(value = "/payslips", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Payslip>> getPayslips(@RequestBody List<Employee> employeeList) {
		List<Payslip> payslips = new ArrayList<Payslip>();
		for ( Employee e: employeeList) {
			Payslip p = payslipService.preparePaySlip(e);
			payslips.add(p);
		}
		
		return new ResponseEntity<List<Payslip>>(payslips, HttpStatus.OK);
	}
	
	

}
