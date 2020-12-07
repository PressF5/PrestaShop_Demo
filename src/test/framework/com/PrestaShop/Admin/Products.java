package com.PrestaShop.Admin;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.PrestaShop.InitialConfiguration.PageGenerator;

import io.qameta.allure.Step;

public class Products extends PageGenerator{
	
	@FindBy(xpath = "//a[@id = 'page-header-desc-configuration-add']")
	private WebElement buttonNewProduct;

	private Products(RemoteWebDriver driver) {
		super(driver);
	}
	
	@Step("Нажатие на кнопку создать новый товар.")
	public CreateProduct clickOnNewProductButton() {
	
		buttonNewProduct.click();
		return getInstance(CreateProduct.class);
	}
}