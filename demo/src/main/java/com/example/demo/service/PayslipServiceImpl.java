package com.example.demo.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Payslip;

@Service
public class PayslipServiceImpl implements PayslipService{

	@Override
	public Payslip preparePaySlip(Employee e) {
		
		int annualSalary = e.getAnnualSalary();
		double superRate = e.getSuperRate();
		
		int grossIncome = roundAmount( annualSalary/12);
		int incomeTax = calculateIncomeTax( annualSalary);
		int netIncome = grossIncome - incomeTax;
		int superannuation = roundAmount( grossIncome * superRate);
		int paymentMonth = e.getPaymentMonth();
		Date paymentDate = new Date();
		paymentDate.setDate(1);
		paymentDate.setMonth( paymentMonth );
		SimpleDateFormat simpleformat = new SimpleDateFormat("MMMM");
	    String strMonth= simpleformat.format(paymentDate);
	    paymentDate.setMonth( paymentMonth +1 );
	    paymentDate.setDate(0);
	    int endofMonth = paymentDate.getDate();
	    String fromDate = "01 " + strMonth;
	    String toDate = endofMonth + " " + strMonth;
		
		Payslip payslip = new Payslip( e, fromDate, toDate, grossIncome, incomeTax,
			 superannuation, netIncome);
		
		return payslip;
	}
	

	
	private int roundAmount(double amt) {
		int roundedAmount =  (int) Math.round(amt);
		return roundedAmount;
	}
	
	private int calculateIncomeTax( int salary) {
		double calculatedIncomeTax = 0;
		
		if ( salary >= 180001) {
			calculatedIncomeTax = (54232 + ( salary - 180000) * 0.45)/12;
		}
		else if ( salary >= 87001) {
			calculatedIncomeTax = (19822 + ( salary - 87000) * 0.37)/12;
		}
		else if ( salary >= 37001) {
			calculatedIncomeTax = (3572 + ( salary - 37000) * 0.325)/12;
		}
		else if ( salary >= 18201) {
			calculatedIncomeTax = (( salary - 18200) * 0.19)/12;
		}
		int finalIncomeTax = roundAmount( calculatedIncomeTax);
		return finalIncomeTax;
	} 

}
