package com.PrestaShop.DataResources;

public class GetRandomPayment {
	
	public static int getRandomPayment() {

		int payment = (int) ((Math.random() * 2) + 1);

		return payment;
	}
}
