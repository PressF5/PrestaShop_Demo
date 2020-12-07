package com.PrestaShop.Store;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.PrestaShop.InitialConfiguration.PageGenerator;

import io.qameta.allure.Step;

public class PersonalOrderData extends PageGenerator {

	@FindBy(xpath = "//input[@name='firstname']")
	private WebElement fieldFirstName;

	@FindBy(xpath = "//input[@name='lastname']")
	private WebElement fieldLastName;

	@FindBy(xpath = "//div[@id='checkout-guest-form']//input[@name='email']")
	private WebElement fieldEmail;

	@FindBy(xpath = "//form[@id='customer-form']/footer/button")
	private WebElement buttonContinueInform;

	public PersonalOrderData(RemoteWebDriver driver) {
		super(driver);
	}

	@Step("Ввод имени.")
	public PersonalOrderData inputFirstName(String firstName) {

		fieldFirstName.clear();
		fieldFirstName.sendKeys(firstName);
		return this;
	}

	@Step("Ввод фамилии.")
	public PersonalOrderData inputLastName(String lastName) {

		fieldLastName.clear();
		fieldLastName.sendKeys(lastName);
		return this;
	}

	@Step("Ввод почты.")
	public PersonalOrderData inputEmail(String email) {

		fieldEmail.clear();
		fieldEmail.sendKeys(email);
		return this;
	}

	@Step("Продолжить заказ.")
	public DeliveryAddress clickOnButtonContinueInform() {

		buttonContinueInform.click();
		return getInstance(DeliveryAddress.class);
	}
}