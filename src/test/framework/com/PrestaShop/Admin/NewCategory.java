package com.PrestaShop.Admin;

import static com.PrestaShop.Report.Report.addAttachmentToReport;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.PrestaShop.InitialConfiguration.PageGenerator;

import io.qameta.allure.Step;

public class NewCategory extends PageGenerator {

	@FindBy(xpath = "//input[@id = 'name_1']")
	private WebElement nameFieldCategories;

	@FindBy(xpath = "//button[@id = 'category_form_submit_btn']")
	private WebElement buttonSave;

	private NewCategory(RemoteWebDriver driver) {
		super(driver);
	}

	@Step("Сохранить категорию.")
	public Category clickSaveButton() {

		buttonSave.click();
		return getInstance(Category.class);
	}

	@Step("Установка названия новой категории.")
	public NewCategory setNameCategoty(String nameCategory) {

		nameFieldCategories.sendKeys(nameCategory);
		addAttachmentToReport("Название новой категории.", nameCategory);
		return this;
	}
}