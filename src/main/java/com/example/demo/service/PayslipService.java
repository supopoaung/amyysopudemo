package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Payslip;

public interface PayslipService {

	 Payslip preparePaySlip( Employee e);
	
	//abstract int calculateIncomeTax( int income);
}
