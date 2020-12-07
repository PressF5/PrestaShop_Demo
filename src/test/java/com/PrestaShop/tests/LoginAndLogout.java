package com.PrestaShop.tests;

import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.Test;

import com.PrestaShop.Admin.*;
import com.PrestaShop.Asserts.Asserts;
import com.PrestaShop.DataResources.GetLoginAndPassword;
import com.PrestaShop.InitialConfiguration.InitialConfiguration;

import io.qameta.allure.*;

@Epic("Тестовый набор 1.")
@Feature("Вход и выход с админ панель сайта.")
public class LoginAndLogout extends InitialConfiguration {

	private GetLoginAndPassword loginAndPasword = ConfigFactory.create(GetLoginAndPassword.class);

	@Story("Ввод логина и пароля и нажатие кнопки логин, выход из админ панели сайта.")
	@Test(description = "Проверка входа и выхода c админ панели сайта.", alwaysRun = true)
	public void loginAndLogout() {
		UserPage userPage = page.getInstance(LoginPage.class).inputLogin(loginAndPasword.login())
				.inputPassword(loginAndPasword.password()).clickOnLoginButton();
		Profile profile = userPage.clickOnImageProfile();

		String nameAndLastName = profile.getNameAndLastName();

		Asserts.assertContainsVariable(nameAndLastName, "Имя Фамилия");

		profile.clickOnLogOut();
	}
}
