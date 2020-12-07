package com.PrestaShop.Admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.PrestaShop.InitialConfiguration.PageGenerator;

import io.qameta.allure.Step;

import static com.PrestaShop.Wait.Wait.*;

public class Catalog extends PageGenerator {

	private Actions actions;

	private By products = By.xpath("//li[@data-submenu = '10']/a");

	private By categories = By.xpath("//li[@data-submenu = '11']/a");

	private Catalog(RemoteWebDriver driver, Actions actions) {
		super(driver);
		this.actions = actions;
	}

	@Step("Переход в катагории.")
	public Category goToCategories() {

		WebElement categories = waitingForElementToBeClickable(driver, driver.findElement(this.categories));
		actions.click(categories).perform();

		return getInstance(Category.class);
	}

	@Step("Переход к товарам.")
	public Products goToProduct() {

		WebElement products = waitingForElementToBeClickable(driver, driver.findElement(this.products));
		actions.click(products).build().perform();

		return getInstance(Products.class);
	}
}