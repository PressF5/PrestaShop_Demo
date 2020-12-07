package com.PrestaShop.Store;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.PrestaShop.InitialConfiguration.PageGenerator;

import io.qameta.allure.Step;

public class DeliveryAddress extends PageGenerator {

	@FindBy(xpath = "//p[@class='add-address']/a")
	private WebElement addAddress;

	@FindBy(xpath = "//input[@name='address1']")
	private WebElement fieldAddress;

	@FindBy(xpath = "//input[@name='postcode']")
	private WebElement fieldPostcode;

	@FindBy(xpath = "//input[@name='city']")
	private WebElement fieldCity;

	@FindBy(xpath = "//div[@id='delivery-address']//button")
	private WebElement buttonContinueAddress;

	private DeliveryAddress(RemoteWebDriver driver) {
		super(driver);
	}

	@Step("Добавить адрес.")
	public DeliveryAddress addAddress() {

		addAddress.click();
		return this;
	}

	@Step("Ввести адрес.")
	public DeliveryAddress inputAddress(String address) {

		fieldAddress.clear();
		fieldAddress.sendKeys(address);
		return this;
	}

	@Step("Ввести почтовый индекс.")
	public DeliveryAddress inputPostcode(String postcode) {

		fieldPostcode.clear();
		fieldPostcode.sendKeys(postcode);
		return this;
	}

	@Step("Ввести город.")
	public DeliveryAddress inputCity(String city) {

		fieldCity.clear();
		fieldCity.sendKeys(city);
		return this;
	}

	@Step("Продолжить.")
	public DeliveryMethod clickOnButtonContinueAddress() {

		buttonContinueAddress.click();
		return getInstance(DeliveryMethod.class);
	}
}