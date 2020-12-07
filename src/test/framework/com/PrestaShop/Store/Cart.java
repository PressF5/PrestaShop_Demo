package com.PrestaShop.Store;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.PrestaShop.InitialConfiguration.PageGenerator;

import io.qameta.allure.Step;

public class Cart extends PageGenerator {

	@FindBy(xpath = "//div[@class='product-line-info']/a")
	private WebElement nameProduct;

	@FindBy(xpath = "//div[@class='product-line-grid-body col-md-4 col-xs-8']/div[2]/span")
	private WebElement priceProduct;

	@FindBy(xpath = "//span[@class='label js-subtotal']")
	private WebElement quantityProduct;

	@FindBy(xpath = "//div[@class = 'text-xs-center']/a")
	private WebElement ordering;

	private Cart(RemoteWebDriver driver) {
		super(driver);
	}

	@Step("Взятие имени товара с корзины.")
	public String getNameProductInCart() {

		return nameProduct.getText().toUpperCase();
	}

	@Step("Взятие цены товара с корзины.")
	public String getPriceProductInCart() {

		return priceProduct.getText().substring(0, priceProduct.getText().length() - 2).replace(',', '.');
	}

	@Step("Взятие количества товара с корзины.")
	public String getQuantityProductInCart() {

		return quantityProduct.getText().substring(0, quantityProduct.getText().length() - 4);
	}

	@Step("Подтверждение заказа.")
	public PersonalOrderData clickOnPlaceOrder() {

		ordering.click();
		return getInstance(PersonalOrderData.class);
	}
}