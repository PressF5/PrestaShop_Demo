package com.PrestaShop.tests;

import static com.PrestaShop.DataResources.GetRandomPayment.*;
import static com.PrestaShop.Report.Report.addAttachmentToReport;

import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.Test;

import com.PrestaShop.Asserts.Asserts;
import com.PrestaShop.DataResources.GetCustomerData;
import com.PrestaShop.InitialConfiguration.InitialConfiguration;
import com.PrestaShop.Store.*;

import io.qameta.allure.*;

@Epic("Тестовый набор 5.")
@Feature("Проверка покупки товара.")
public class Ordering extends InitialConfiguration {

	private GetCustomerData customerData = ConfigFactory.create(GetCustomerData.class);

	private String productsName;
	private String productsPrice;
	private String quantityAllProducts;
	private String productsQuantity = "1";
	private String confirmationOfAnOrder = "ВАШ ЗАКАЗ ПОДТВЕРЖДЁН";

	@Story("Проверка покупки добавленного товара в корзину.")
	@Test(description = "Проверка покупки добавленного товара в корзину.", alwaysRun = true)
	public void purchaseOfGood() {

		Product product = page.getInstance(HomePage.class).checkingTheVersionOfTheSite().clickOnAllProduct()
				.clickOnRandomProduct();

		productsName = product.getNameProduct();
		productsPrice = product.getPriceProduct();
		quantityAllProducts = product.getQuantityProduct();

		addAttachmentToReport("Имя товара на странице товара.", productsName);
		addAttachmentToReport("Цена товара на странице товара.", productsPrice);
		addAttachmentToReport("Количество товара на странице товара.", quantityAllProducts);

		Cart cart = product.addToCard().clearanceOfProduct();

		String cartProductsName = cart.getNameProductInCart();
		String cartProductsPrice = cart.getPriceProductInCart();
		String cartProductsQuantity = cart.getQuantityProductInCart();

		addAttachmentToReport("Имя товара в корзине.", cartProductsName);
		addAttachmentToReport("Цена товара в корзине.", cartProductsPrice);
		addAttachmentToReport("Количество товара в корзине.", cartProductsQuantity);

		Asserts.assertVariable(cartProductsName, productsName, "Разные названия товара.");
		Asserts.assertVariable(cartProductsPrice, productsPrice, "Разная цена на товар.");
		Asserts.assertVariable(cartProductsQuantity, productsQuantity, "Разное количество товара.");

		PersonalOrderData orderData = cart.clickOnPlaceOrder();

		DeliveryAddress deliveryAddress = orderData.inputFirstName(customerData.firstName())
				.inputLastName(customerData.lastName()).inputEmail(customerData.email()).clickOnButtonContinueInform();

		DeliveryMethod deliveryMethod = deliveryAddress.addAddress().inputAddress(customerData.address())
				.inputPostcode(customerData.postcode()).inputCity(customerData.city()).clickOnButtonContinueAddress();

		PaymentOfAnOrder payment = deliveryMethod.clickOnButtonContinueDelivery();

		ConfirmationOfAnOrder order = payment.paymentMethod(getRandomPayment()).termsOfAgreement().clickOnButtonOrder();

		Asserts.assertVariable(order.getOrderTitle(), confirmationOfAnOrder, "Разное сообщение о заказе.");
		Asserts.assertVariable(order.getOrderQuantityProduct(), productsQuantity, "Разное количество товара.");
		Asserts.assertContainsVariable(order.getOrderNameProduct(), productsName.toUpperCase(),
				"Разное название продукта.");
		Asserts.assertVariable(order.getOrderPriceOrder(), productsPrice, "Разная цена товара.");

		product = order.selectAllProduct().clickOnRandomProductAgain();

		Integer quantity = product.getQuantityProductInt();
		Integer balance = Integer.parseInt(quantityAllProducts) - Integer.parseInt(productsQuantity);

		Asserts.assertEqualVariable(quantity, balance,
				"Количество товаров не изменилось или изменилось более чем на указанное количество.");
	}
}