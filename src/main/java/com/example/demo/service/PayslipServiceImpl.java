package com.example.demo.service;


import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Payslip;
import com.example.demo.util.TaxCalculator;

@Service
public class PayslipServiceImpl implements PayslipService{

	
	@Override
	public Payslip preparePaySlip(Employee e) {
		TaxCalculator  taxCalculator = new TaxCalculator();
		 try {
			taxCalculator.prepareThresholds();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		 Payslip payslip = taxCalculator.preparePaySlip(e);
	
		return payslip;
	}
	

}
