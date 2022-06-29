package com.example.demo.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Payslip;
import com.example.demo.entity.TaxThreshold;
import com.example.demo.util.TaxThresholdConfig;

@Service
public class PayslipServiceImpl implements PayslipService{

	@Autowired
	TaxThresholdConfig taxThresholdConfig;
	
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
		List<TaxThreshold>taxThresholds = taxThresholdConfig.getTaxThresholds();
		for (TaxThreshold t: taxThresholds) { 
			if ( salary >= t.getMinAmount() && (salary <= t.getMaxAmount() || t.getMaxAmount()==0)) {  
				calculatedIncomeTax = ( t.getFixedAmount()+ ( salary - (t.getMinAmount()-1)) * t.getTaxRate() )/12;
				break;
			}
		}
		
		int finalIncomeTax = roundAmount( calculatedIncomeTax);
		return finalIncomeTax;
	} 
	
	

}
