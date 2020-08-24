package com.java.assignment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IncomeDataTester {
	@Test
	public void test() {
		IncomeData id=new IncomeData();
		id.setAmount("132.00");
		id.setCurrency("INR");
		Assertions.assertEquals(2.00,Double.parseDouble(id.avgIncomeInDollars()),0.0001);
	}
}