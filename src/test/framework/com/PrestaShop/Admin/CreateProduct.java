package com.PrestaShop.Admin;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.PrestaShop.InitialConfiguration.PageGenerator;
import com.PrestaShop.Wait.Wait;

import io.qameta.allure.Step;

import static com.PrestaShop.Wait.Wait.*;

public class CreateProduct extends PageGenerator {

	@FindBy(xpath = "//input[@id = 'form_step1_name_1']")
	private WebElement fieldNameNewProduct;

	@FindBy(xpath = "//input[@id = 'form_step1_qty_0_shortcut']")
	private WebElement fieldQuantityNewProduct;

	@FindBy(xpath = "//input[@id = 'form_step1_price_shortcut']")
	private WebElement fieldPriceNewProduct;

	@FindBy(xpath = "//div[@class = 'col-lg-5']/div")
	private WebElement activateNewProduct;

	@FindBy(xpath = "//div[contains(@class, 'btn-group')]/button[@type='submit']")
	private WebElement buttonSaveNewProduct;

	private By saveButton = By.xpath("//input[@id='submit']");

	private By buttonCloseSuccessfulUpdate = By.xpath("//div[@id = 'growls']//div[@class = 'growl-close']");

	private CreateProduct(RemoteWebDriver driver) {
		super(driver);
	}

	@Step("Ввод име товара.")
	public CreateProduct inputNameNewProduct(String nameNewProduct) {

		fieldNameNewProduct.clear();
		fieldNameNewProduct.sendKeys(nameNewProduct);
		return this;
	}

	@Step("Ввод количества товара.")
	public CreateProduct inputQuantityNewProduct(String quantityNewProduct) {

		fieldQuantityNewProduct.clear();
		fieldQuantityNewProduct.sendKeys(quantityNewProduct);
		return this;
	}

	@Step("Ввод цены товара.")
	public CreateProduct inputPriceNewProduct(String priceNewProduct) {

		fieldPriceNewProduct.clear();
		fieldPriceNewProduct.sendKeys(priceNewProduct);
		return this;
	}

	@Step("Активация нового товара.")
	public CreateProduct clickOnActivateANewProduct() {

		activateNewProduct.click();
		return this;
	}

	@Step("Сохранение нового товара.")
	public CreateProduct clickOnButtonSaveNewProduct() {

		try {
			Wait.waitingForVisibilityOfElementLocated(driver, saveButton).click();
		} catch (TimeoutException e) {
			buttonSaveNewProduct.click();
		}
		return this;
	}

	@Step("Закрытие алерта.")
	public CreateProduct closeSuccessfulUpdate() {

		waitingForVisibilityOfElementLocated(driver, buttonCloseSuccessfulUpdate).click();
		waitingForInvisibilityOfElementLocated(driver, buttonCloseSuccessfulUpdate);
		return this;
	}
}