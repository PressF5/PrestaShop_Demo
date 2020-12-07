package com.PrestaShop.tests;

import static com.PrestaShop.Report.Report.*;

import java.util.List;

import org.testng.annotations.Test;

import com.PrestaShop.Admin.*;
import com.PrestaShop.Asserts.Asserts;
import com.PrestaShop.InitialConfiguration.InitialConfiguration;

import io.qameta.allure.*;

@Epic("Тестовый набор 3.")
@Feature("Проверка титулов страниц сайта.")
public class AddNewCategory extends InitialConfiguration {

	private Category category;

	private String newCategory = "Jacket";
	private String buttonAlertExpected = "Создано";

	@Story("Создание и проверка новой категории.")
	@Test(description = "Создание и проверка новой категории.", alwaysRun = true)
	public void createCategory() {

		category = page.getInstance(LoginPage.class).inputLogin(loginAndPasword.login())
				.inputPassword(loginAndPasword.password()).clickOnLoginButton().selectCatalog().goToCategories();

		String buttonAlertActual = category.addNewCategory().setNameCategoty(newCategory).clickSaveButton()
				.getTextButtonAlert();
		Asserts.assertVariable(buttonAlertActual, buttonAlertExpected);

		category.clickOnCloseButtonAlert();

		List<String> list = category.setFilterByNameCategory(newCategory).filterSearch().getListCategories();
		Asserts.assertContainsVariable(list, newCategory);

		addAttachmentToReport("Новая категория.", newCategory);
	}
}