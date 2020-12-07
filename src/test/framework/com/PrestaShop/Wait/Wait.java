package com.PrestaShop.Wait;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wait{
	
	private final static int explicitWait = 20;

	private static WebDriverWait waitFor(RemoteWebDriver driver) {
		return new WebDriverWait(driver, explicitWait);
	}

	public static WebElement waitingForElementToBeClickable(RemoteWebDriver driver, By element) {
		return waitFor(driver).until(ExpectedConditions.elementToBeClickable(element));
	}

	public static WebElement waitingForElementToBeClickable(RemoteWebDriver driver, WebElement element) {
		return waitFor(driver).until(ExpectedConditions.elementToBeClickable(element));
	}

	public static WebElement waitingForVisibilityOfElementLocated(RemoteWebDriver driver, By element) {
		return waitFor(driver).until(ExpectedConditions.visibilityOfElementLocated(element));
	}

	public static boolean waitingForInvisibilityOfElementLocated(RemoteWebDriver driver, By element) {
		return waitFor(driver).until(ExpectedConditions.invisibilityOfElementLocated(element));
	}
}