package com.PrestaShop.Store;

import static com.PrestaShop.Report.Report.*;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.*;

import com.PrestaShop.DataResources.GetUrl;
import com.PrestaShop.InitialConfiguration.PageGenerator;

import io.qameta.allure.Step;

public class HomePage extends PageGenerator {

	private GetUrl url = ConfigFactory.create(GetUrl.class);

	@FindBy(xpath = "//div[@id = '_mobile_logo']/a")
	private WebElement mobileLogo;

	@FindBy(xpath = "//section[@id = 'content']/section/a")
	private WebElement allProduct;

	private HomePage(RemoteWebDriver driver) {
		super(driver);
		setURL(url.urlSite());
	}

	@Step("Версия сайта.")
	public HomePage checkingTheVersionOfTheSite() {

		try {

			if (mobileLogo.isDisplayed() == true) {
				addAttachmentToReport("Version: ", "Mobile version.");
			} else {
				addAttachmentToReport("Version: ", "Error version.");
			}

		} catch (NoSuchElementException e) {
			addAttachmentToReport("Version: ", "Desktop version.");
		}

		return this;
	}

	@Step("Выбор всех товаров.")
	public AllProduct clickOnAllProduct() {

		allProduct.click();
		return getInstance(AllProduct.class);
	}
}