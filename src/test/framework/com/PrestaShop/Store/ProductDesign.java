package com.PrestaShop.Store;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.PrestaShop.InitialConfiguration.PageGenerator;

import io.qameta.allure.Step;

import static com.PrestaShop.Wait.Wait.*;

public class ProductDesign extends PageGenerator {

	private By clearanceOfProduct = By.xpath("//div[@class='cart-content']/a");

	private ProductDesign(RemoteWebDriver driver) {
		super(driver);
	}

	@Step("Оформление продукта.")
	public Cart clearanceOfProduct() {

		waitingForElementToBeClickable(driver, clearanceOfProduct).click();
		return getInstance(Cart.class);
	}
}