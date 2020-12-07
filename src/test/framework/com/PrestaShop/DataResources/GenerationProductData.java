package com.PrestaShop.DataResources;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class GenerationProductData {

	public static String generateNameProduct() {

		return ("Product " + System.currentTimeMillis()).toUpperCase();
	}

	public static String generateQuantityProduct() {

		return String.valueOf((int) ((Math.random() * 99) + 1));
	}

	public static String generatePriceProduct() {

		double priceProduct = (Math.random() * 99.9) + 0.1;
		return new BigDecimal(priceProduct).setScale(2, RoundingMode.UP).toString();
	}
}
