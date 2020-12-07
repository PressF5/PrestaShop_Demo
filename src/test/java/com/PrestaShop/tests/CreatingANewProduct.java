package com.PrestaShop.tests;

import static com.PrestaShop.Report.Report.addAttachmentToReport;

import org.testng.annotations.Test;

import com.PrestaShop.Admin.*;
import com.PrestaShop.Asserts.Asserts;
import com.PrestaShop.DataResources.*;
import com.PrestaShop.InitialConfiguration.InitialConfiguration;
import com.PrestaShop.Store.*;

import io.qameta.allure.*;

@Epic("Тестовый набор 4.")
@Feature("Создание и проверка товара.")
public class CreatingANewProduct extends InitialConfiguration {
	
	private String productsName = GenerationProductData.generateNameProduct();
	private String productsPrice = GenerationProductData.generatePriceProduct();
	private String productsQuantity = GenerationProductData.generateQuantityProduct();

	@Story("Создание нового товара и проверка созданного товара на странице маганзина.")
	@Test(description = "Создание нового товара и проверка созданного товара на странице маганзина.", alwaysRun = true)
	public void createANewProduct() {

		CreateProduct createProduct = page.getInstance(LoginPage.class).inputLogin(loginAndPasword.login())
				.inputPassword(loginAndPasword.password()).clickOnLoginButton().selectCatalog().goToProduct()
				.clickOnNewProductButton().inputNameNewProduct(productsName).inputQuantityNewProduct(productsQuantity)
				.inputPriceNewProduct(productsPrice);

		addAttachmentToReport("Название нового продукта.", productsName);
		addAttachmentToReport("Цена нового продукта.", productsPrice);
		addAttachmentToReport("Количество нового продукта.", productsQuantity);

		createProduct.clickOnActivateANewProduct().closeSuccessfulUpdate().clickOnButtonSaveNewProduct()
				.closeSuccessfulUpdate();

		Product prod = page.getInstance(HomePage.class).clickOnAllProduct().clickOnProduct(productsName);

		String nameProductActual = prod.getNameProduct();
		String priceProductActual = prod.getPriceProduct();
		String quantityProduct = prod.getQuantityProduct();

		Asserts.assertVariable(nameProductActual, productsName, "Разное название товара.");
		Asserts.assertVariable(priceProductActual, productsPrice, "Разная цена товара.");
		Asserts.assertVariable(quantityProduct, quantityProduct, "Разное количество товара.");

		addAttachmentToReport("Название нового продукта на странице магазина.", productsName);
		addAttachmentToReport("Цена нового продукта на странице магазина.", productsPrice);
		addAttachmentToReport("Количество нового продукта на странице магазина.", quantityProduct);
	}
}