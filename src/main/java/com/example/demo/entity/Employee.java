package com.example.demo.entity;


public class Employee {
	
	String firstName;
	String lastName;
	int annualSalary;
	int paymentMonth;
	double superRate;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAnnualSalary() {
		return annualSalary;
	}
	public void setAnnualSalary(int annualSalary) {
		this.annualSalary = annualSalary;
	}
	public int getPaymentMonth() {
		return paymentMonth;
	}
	public void setPaymentMonth(int paymentMonth) {
		this.paymentMonth = paymentMonth;
	}
	public double getSuperRate() {
		return superRate;
	}
	public void setSuperRate(double superRate) {
		this.superRate = superRate;
	}
	public Employee(String firstName, String lastName, int annualSalary, int paymentMonth, double superRate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.annualSalary = annualSalary;
		this.paymentMonth = paymentMonth;
		this.superRate = superRate;
	}
	
	
	public Employee() {
	}

}
