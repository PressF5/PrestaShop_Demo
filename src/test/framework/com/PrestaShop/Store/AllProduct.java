package com.PrestaShop.Store;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.PrestaShop.DataResources.GetProduct;
import com.PrestaShop.InitialConfiguration.PageGenerator;

import io.qameta.allure.Step;

import static com.PrestaShop.Wait.Wait.*;

import java.util.List;

public class AllProduct extends PageGenerator {

	private int numberProduct;

	@FindBy(xpath = "//div[@class='products row']//div[@class='product-description']//a")
	private List<WebElement> allProducts;

	private By next = By.xpath("//a[@rel = 'next']");

	private AllProduct(RemoteWebDriver driver) {
		super(driver);
		numberProduct = GetProduct.getNumberProduct().get(driver.getCapabilities().getBrowserName());
	}

	private WebElement clickOnNext() {

		return waitingForElementToBeClickable(driver, next);
	}

	@Step("Переход на страницу случайного товара.")
	public Product clickOnRandomProduct() {

		allProducts.get(numberProduct).click();
		return getInstance(Product.class);
	}

	private WebElement selectProduct(String nameProduct) {

		By selectProduct = By.linkText(nameProduct);
		WebElement element = null;

		boolean flag = true;

		while (flag) {
			try {
				element = waitingForElementToBeClickable(driver, selectProduct);
				flag = false;
			} catch (TimeoutException e) {
				clickOnNext().click();
			}
		}

		return element;
	}

	@Step("Переход на страницу созданного товара.")
	public Product clickOnProduct(String nameProduct) {

		selectProduct(nameProduct).click();
		return getInstance(Product.class);
	}

	@Step("Переход на страницу случайно выбранного товара снова.")
	public Product clickOnRandomProductAgain() {

		allProducts.get(numberProduct).click();
		return getInstance(Product.class);
	}
}