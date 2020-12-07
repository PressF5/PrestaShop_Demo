package com.PrestaShop.Store;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.PrestaShop.InitialConfiguration.PageGenerator;

import io.qameta.allure.Step;

import static com.PrestaShop.Wait.Wait.*;

public class Product extends PageGenerator {

	@FindBy(xpath = "//h1[@class = 'h1']")
	private WebElement nameProduct;

	@FindBy(xpath = "//ol/li[last()]//span")
	private WebElement nameProductTitle;

	@FindBy(xpath = "//span[@itemprop = 'price']")
	private WebElement priceProduct;

	@FindBy(xpath = "//a[@href = '#product-details']")
	private WebElement productDetails;

	@FindBy(xpath = "//button[@data-button-action = 'add-to-cart']")
	private WebElement addToCart;

	private By quantityProduct = By.xpath("//div[@class = 'product-quantities']/span");

	private Product(RemoteWebDriver driver) {
		super(driver);
	}

	private void productDetails() {

		productDetails.click();
	}

	private WebElement getWebElementQuantityProduct() {

		return waitingForVisibilityOfElementLocated(driver, this.quantityProduct);
	}

	@Step("Взять имя товара.")
	public String getNameProduct() {

		return this.nameProduct.getText().toUpperCase();
	}

	@Step("Взять цену товара.")
	public String getPriceProduct() {

		return this.priceProduct.getText().substring(0, priceProduct.getText().length() - 2).replace(',', '.');
	}

	@Step("Взять количество товара.")
	public String getQuantityProduct() {

		productDetails();
		WebElement quantityProductElement = getWebElementQuantityProduct();
		String quantity = quantityProductElement.getText().substring(0, quantityProductElement.getText().length() - 7);

		return quantity;
	}

	@Step("Взять количество товара.")
	public Integer getQuantityProductInt() {

		return Integer.parseInt(getQuantityProduct());
	}

	@Step("Добавить товар в корзину.")
	public ProductDesign addToCard() {

		addToCart.click();
		return getInstance(ProductDesign.class);
	}
}