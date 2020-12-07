package com.PrestaShop.Store;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.PrestaShop.InitialConfiguration.PageGenerator;

import io.qameta.allure.Step;

public class ConfirmationOfAnOrder extends PageGenerator {

	@FindBy(xpath = "//h3[@class='h1 card-title']")
	private WebElement titleOrderConfirmed;

	@FindBy(xpath = "//div[@class='col-xs-5 text-sm-right text-xs-left']")
	private WebElement price;

	@FindBy(xpath = "//div[@class='col-xs-2']")
	private WebElement quantity;

	@FindBy(xpath = "//div[@class='col-sm-4 col-xs-9 details']/span")
	private WebElement name;

	@FindBy(xpath = "//section/a")
	private WebElement allProducts;

	private ConfirmationOfAnOrder(RemoteWebDriver driver) {
		super(driver);
	}

	@Step("Взятие название товара из заказа.")
	public String getOrderNameProduct() {

		return name.getText().toUpperCase();
	}

	@Step("Взятие цены товара из заказа.")
	public String getOrderPriceOrder() {

		return price.getText().substring(0, price.getText().length() - 2).replace(',', '.');
	}

	@Step("Взятие количества товаров из заказа.")
	public String getOrderQuantityProduct() {

		return quantity.getText();
	}

	@Step("Взятие заголовка заказа.")
	public String getOrderTitle() {

		return titleOrderConfirmed.getText().substring(1, titleOrderConfirmed.getText().length());
	}

	@Step("Выбор всех товаров.")
	public AllProduct selectAllProduct() {

		allProducts.click();
		return getInstance(AllProduct.class);
	}
}