package com.PrestaShop.Admin;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.PrestaShop.DataResources.GetUrl;
import com.PrestaShop.InitialConfiguration.PageGenerator;

import io.qameta.allure.Step;

public class LoginPage extends PageGenerator {

	private GetUrl url = ConfigFactory.create(GetUrl.class);

	@FindBy(xpath = "//input[@id = 'email']")
	private WebElement loginField;

	@FindBy(xpath = "//input[@id = 'passwd']")
	private WebElement passwordField;

	@FindBy(xpath = "//button[@name = 'submitLogin']")
	private WebElement buttonLogin;

	private LoginPage(RemoteWebDriver driver) {
		super(driver);
		setURL(url.urlAdmin());
	}

	@Step("Ввод логина.")
	public LoginPage inputLogin(String login) {

		loginField.clear();
		loginField.sendKeys(login);
		return this;

	}

	@Step("Ввод пароля.")
	public LoginPage inputPassword(String password) {

		passwordField.clear();
		passwordField.sendKeys(password);
		return this;

	}

	@Step("Нажатие кнопки логин.")
	public UserPage clickOnLoginButton() {

		buttonLogin.click();
		return getInstance(UserPage.class);
	}
}