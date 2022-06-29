package com.example.demo.entity;


public class TaxThreshold {

	int minAmount;
	int maxAmount;
	double taxRate;
	int fixedAmount;
	
	public int getMinAmount() {
		return minAmount;
	}
	public void setMinAmount(int minAmount) {
		this.minAmount = minAmount;
	}
	public int getMaxAmount() {
		return maxAmount;
	}
	public void setMaxAmount(int maxAmount) {
		this.maxAmount = maxAmount;
	}
	public double getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(double taxRate) {
		this.taxRate = taxRate;
	}
	
	
	public int getFixedAmount() {
		return fixedAmount;
	}
	public void setFixedAmount(int fixedAmount) {
		this.fixedAmount = fixedAmount;
	}
	@Override
	public String toString() {
		return "TaxThreshold [minAmount=" + minAmount + ", maxAmount=" + maxAmount + ", taxRate=" + taxRate
				+ ", fixedAmount=" + fixedAmount + "]";
	}
	
	
	
}
