package com.PrestaShop.Store;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.PrestaShop.InitialConfiguration.PageGenerator;

import io.qameta.allure.Step;

public class DeliveryMethod extends PageGenerator {

	@FindBy(xpath = "//form[@id='js-delivery']/button")
	private WebElement buttonContinueDelivery;

	private DeliveryMethod(RemoteWebDriver driver) {
		super(driver);
	}

	@Step("Продолжить заказ товара.")
	public PaymentOfAnOrder clickOnButtonContinueDelivery() {

		buttonContinueDelivery.click();
		return getInstance(PaymentOfAnOrder.class);
	}
}