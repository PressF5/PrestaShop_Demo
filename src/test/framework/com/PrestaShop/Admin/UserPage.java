package com.PrestaShop.Admin;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.PrestaShop.InitialConfiguration.PageGenerator;

import io.qameta.allure.Step;

import static com.PrestaShop.Wait.Wait.*;
import static com.PrestaShop.Report.Report.*;

public class UserPage extends PageGenerator {

	private By loading = By.xpath("//span[@id='ajax_running']");

	@FindBy(xpath = "//span[@class = 'employee_avatar_small']/img")
	private WebElement imageProfile;

	@FindBy(xpath = "	//div[@class='img-circle person']/i")
	private WebElement imageProfileSmall;

	@FindBy(xpath = "//li[@data-submenu = '1']/a")
	private WebElement dashboard;

	@FindBy(xpath = "//li[@data-submenu = '3']/a")
	private WebElement orders;

	@FindBy(xpath = "//li[@data-submenu = '9']/a")
	protected WebElement catalog;

	@FindBy(xpath = "//li[@data-submenu = '23']/a")
	private WebElement customer;

	@FindBy(xpath = "//li[@data-submenu = '27']/a")
	private WebElement customerService;

	@FindBy(xpath = "//li[@data-submenu = '31']/a")
	private WebElement statistics;

	@FindBy(xpath = "//li[@data-submenu = '42']/a")
	private WebElement modules;

	@FindBy(xpath = "//li[@data-submenu = '46']/a")
	private WebElement design;

	@FindBy(xpath = "//li[@data-submenu = '52']/a")
	private WebElement delivery;

	@FindBy(xpath = "//li[@data-submenu = '55']/a")
	private WebElement payment;

	@FindBy(xpath = "//li[@data-submenu = '58']/a")
	private WebElement international;

	@FindBy(xpath = "//li[@data-submenu = '73']/a")
	private WebElement shopParameters;

	@FindBy(xpath = "//li[@data-submenu = '95']/a")
	private WebElement advancedParameters;

	private UserPage(RemoteWebDriver driver) {
		super(driver);
	}

	private void waitingLoading() {

		if (!driver.getCapabilities().getBrowserName().equals("Chrome")) {

			waitingForVisibilityOfElementLocated(driver, loading);
			waitingForInvisibilityOfElementLocated(driver, loading);
		}
	}

	@Step("Нажатие на аватарку профиля.")
	public Profile clickOnImageProfile() {

		try {
			imageProfile.click();
		} catch (NoSuchElementException e) {
			imageProfileSmall.click();
		}

		return getInstance(Profile.class);

	}

	private Actions moveToParagraph(WebElement element) {

		waitingLoading();
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();

		return actions;

	}

	@Step("Выбор каталога.")
	public Catalog selectCatalog() {

		return getInstance(Catalog.class, moveToParagraph(catalog));
	}

	private void clickOnParagraph(WebElement element) {
		element.click();
	}

	@Step("Взятие заголовка страницы.")
	public String getTitle() {

		String title = driver.getTitle();
		addAttachmentToReport("Заголовок страницы.", title);
		return title;
	}

	@Step("Обновление страницы.")
	public UserPage refreshPage() {

		driver.navigate().refresh();
		return this;
	}

	@Step("Переход в Dashboard.")
	public UserPage clickOnDashboard() {

		clickOnParagraph(dashboard);
		return this;
	}

	@Step("Переход в Orders.")
	public UserPage clickOnOrders() {

		clickOnParagraph(orders);
		return this;
	}

	@Step("Переход в Catalog.")
	public UserPage clickOnCatalog() {

		clickOnParagraph(catalog);
		return this;
	}

	@Step("Переход в Customer.")
	public UserPage clickOnCustomer() {

		clickOnParagraph(customer);
		return this;
	}

	@Step("Переход в CustomerService.")
	public UserPage clickOnCustomerService() {

		clickOnParagraph(customerService);
		return this;
	}

	@Step("Переход в Statistics.")
	public UserPage clickOnStatistics() {

		clickOnParagraph(statistics);
		return this;
	}

	@Step("Переход в Modules.")
	public UserPage clickOnModules() {

		clickOnParagraph(modules);
		return this;
	}

	@Step("Переход в Design.")
	public UserPage clickOnDesign() {

		clickOnParagraph(design);
		return this;
	}

	@Step("Переход в Delivery.")
	public UserPage clickOnDelivery() {

		clickOnParagraph(delivery);
		return this;
	}

	@Step("Переход в Payment.")
	public UserPage clickOnPayment() {

		clickOnParagraph(payment);
		return this;
	}

	@Step("Переход в International.")
	public UserPage clickOnInternational() {

		clickOnParagraph(international);
		return this;
	}

	@Step("Переход в ShopParameters.")
	public UserPage clickOnShopParameters() {

		clickOnParagraph(shopParameters);
		return this;
	}

	@Step("Переход в AdvancedParameters.")
	public UserPage clickOnAdvancedParameters() {

		clickOnParagraph(advancedParameters);
		return this;
	}
}