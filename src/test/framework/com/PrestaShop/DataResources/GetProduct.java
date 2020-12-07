package com.PrestaShop.DataResources;

import java.util.HashMap;
import java.util.Map;

public class GetProduct {

	private static Map<String, Integer> numberProduct = new HashMap<>();

	public static Map<String, Integer> getNumberProduct() {

		numberProduct.put("chrome", 0);
		numberProduct.put("firefox", 1);

		return numberProduct;
	}
}
