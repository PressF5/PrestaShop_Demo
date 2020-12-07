package com.PrestaShop.tests;

import org.testng.annotations.Test;

import com.PrestaShop.Admin.LoginPage;
import com.PrestaShop.Admin.UserPage;
import com.PrestaShop.Asserts.Asserts;
import com.PrestaShop.InitialConfiguration.InitialConfiguration;

import io.qameta.allure.*;

@Epic("Тестовый набор 2.")
@Feature("Проверка титулов страниц сайта.")
public class Titles extends InitialConfiguration {
	
	private UserPage userPage;

	@Story("Проверка титулов страниц пунктов меню.")
	@Test(description = "Проверка титулов страниц пунктов меню после обновления страницы.", alwaysRun = true)
	public void checkTitleDashboard() {

		userPage = page.getInstance(LoginPage.class).inputLogin(loginAndPasword.login())
				.inputPassword(loginAndPasword.password()).clickOnLoginButton();
		
		title(userPage.clickOnDashboard());
		title(userPage.clickOnOrders());
		title(userPage.clickOnCatalog());
		title(userPage.clickOnCustomer());
		title(userPage.clickOnCustomerService());
		title(userPage.clickOnStatistics());
		title(userPage.clickOnModules());
		title(userPage.clickOnDesign());
		title(userPage.clickOnDelivery());
		title(userPage.clickOnPayment());
		title(userPage.clickOnInternational());
		title(userPage.clickOnShopParameters());
		title(userPage.clickOnAdvancedParameters());
	}
	
	private void title(UserPage param) {
		
		String title = param.getTitle();
		String titleRefresh = userPage.refreshPage().getTitle();

		Asserts.assertVariable(title, titleRefresh);
	}
}