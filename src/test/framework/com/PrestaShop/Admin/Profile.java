package com.PrestaShop.Admin;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.PrestaShop.InitialConfiguration.PageGenerator;

import io.qameta.allure.Step;

public class Profile extends PageGenerator{
	
	@FindBy(xpath = "//a[@id = 'header_logout']")
	private WebElement logOut;
	
	@FindBy(xpath = "//li[@class='text-center text-nowrap']")
	private WebElement nameAndLastName;
	

	private Profile(RemoteWebDriver driver) {
		super(driver);
	}
	
	@Step("Выход из профиля.")
	public String getNameAndLastName() {
		return nameAndLastName.getText();
	}

	@Step("Выход из профиля.")
	public void clickOnLogOut() {
		logOut.click();
	}
}