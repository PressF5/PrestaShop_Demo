package com.PrestaShop.Admin;

import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.PrestaShop.InitialConfiguration.PageGenerator;

import io.qameta.allure.Step;

public class Category extends PageGenerator {

	@FindBy(xpath = "//input[@name='categoryFilter_name']")
	private WebElement categoryFilterName;

	@FindBy(xpath = "//td[@class='pointer' and position() = 3]")
	private List<WebElement> categoryNames;

	@FindBy(xpath = "//button[@name='submitFilter']")
	private WebElement searchFilter;

	@FindBy(xpath = "//div[@class='alert alert-success']/button[@class='close']")
	private WebElement closeButtonAlertSuccess;

	@FindBy(xpath = "//a[@id = 'page-header-desc-category-new_category']")
	private WebElement addCategories;

	private Category(RemoteWebDriver driver) {
		super(driver);
	}

	@Step("Добавить новую категорию.")
	public NewCategory addNewCategory() {

		addCategories.click();
		return getInstance(NewCategory.class);
	}

	@Step("Надпись с кнопки алерта.")
	public String getTextButtonAlert() {

		WebElement parentElement = closeButtonAlertSuccess.findElement(By.xpath("./.."));

		String text = parentElement.getText();
		return text.substring(1, text.length()).trim();
	}

	@Step("Закрытие алерта.")
	public Category clickOnCloseButtonAlert() {

		closeButtonAlertSuccess.click();
		return this;
	}

	@Step("Установка фильтра по имени категории.")
	public Category setFilterByNameCategory(String nameCategory) {

		categoryFilterName.sendKeys(nameCategory);
		return this;
	}

	@Step("Поиск по фильтру.")
	public Category filterSearch() {

		searchFilter.click();
		return this;
	}

	@Step("Список категорий.")
	public List<String> getListCategories() {

		List<String> listNames = new LinkedList<>();
		for (WebElement element : categoryNames)
			listNames.add(element.getText());

		return listNames;
	}
}