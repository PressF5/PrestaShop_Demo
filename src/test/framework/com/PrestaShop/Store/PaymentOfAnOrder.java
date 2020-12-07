package com.PrestaShop.Store;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.PrestaShop.InitialConfiguration.PageGenerator;

import io.qameta.allure.Step;

import static com.PrestaShop.Wait.Wait.*;

public class PaymentOfAnOrder extends PageGenerator {

	@FindBy(xpath = "//input[@id='payment-option-1']")
	private WebElement paymentByCheck;

	@FindBy(xpath = "//input[@id='payment-option-2']")
	private WebElement paymentByCard;

	@FindBy(xpath = "//input[@id='conditions_to_approve[terms-and-conditions]']")
	private WebElement termsOfAgreement;

	private By buttonOrder = By.xpath("//div[@id='payment-confirmation']//button");

	private PaymentOfAnOrder(RemoteWebDriver driver) {
		super(driver);
	}

	@Step("Выбор метода оплаты.")
	public PaymentOfAnOrder paymentMethod(int paymentMethodOneOrTwo) {

		switch (paymentMethodOneOrTwo) {
		case 1:
		default: {
			paymentByCheck.click();
			break;
		}
		case 2: {
			paymentByCard.click();
			break;
		}
		}

		return this;
	}

	@Step("Подтвердить соглашение.")
	public PaymentOfAnOrder termsOfAgreement() {

		termsOfAgreement.click();
		return this;
	}

	@Step("Подтвердить метод оплаты.")
	public ConfirmationOfAnOrder clickOnButtonOrder() {

		waitingForElementToBeClickable(driver, buttonOrder).click();
		return getInstance(ConfirmationOfAnOrder.class);
	}
}