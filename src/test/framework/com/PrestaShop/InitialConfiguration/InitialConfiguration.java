package com.PrestaShop.InitialConfiguration;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.*;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.PrestaShop.AllureAttach.ChangeAllureJson;
import com.PrestaShop.AllureAttach.ReadWriteProperty;
import com.PrestaShop.AllureXML.XML;
import com.PrestaShop.DataResources.GetLoginAndPassword;

public class InitialConfiguration {

	protected GetLoginAndPassword loginAndPasword = ConfigFactory.create(GetLoginAndPassword.class);

	private RemoteWebDriver driver;
	protected PageGenerator page;
	private String nameVideo;
	private static XML xml = new XML();
	private static ThreadLocal<String> nameVideoLocal = new ThreadLocal<>();

	@BeforeMethod
	@Parameters({ "browser" })
	public void beforeTest(@Optional("Chrome") String browser, ITestResult result, Method method) {

		nameVideo = browser + "_" + result.getTestClass().getName() + "_" + method.getName();
		nameVideoLocal.set(nameVideo);

		DesiredCapabilities cap = Browsers.valueOf(browser.toUpperCase()).getBrowser();
		cap.setCapability("enableVideo", true);
		cap.setCapability("enableVNC", true);
		cap.setCapability("videoName", nameVideo + ".mp4");

		try {
			driver = new RemoteWebDriver(new URL(System.getenv("urlGrid")), cap);
			driver.manage().window().maximize();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		page = new PageGenerator(getDriver());
	}

	@AfterMethod
	public void afterTest(ITestContext context) {

		if (getDriver() != null)
			getDriver().quit();

		String prop = ReadWriteProperty.read(nameVideo);
		
		File file = new File("target/allure-results/" + prop + "-result.json");
		File fls = new File("/video/" + nameVideo + ".mp4");

		ChangeAllureJson.changeAttachmentAllureJson(file, fls, "Test video.");
	}

	@AfterSuite
	public void afterSuite(ITestContext context) {

		xml.changeXMLDocument().addNewParametersToXML(context.getCurrentXmlTest().getParameter("browser"),
				getDriver().getCapabilities().getVersion()).writeToXMLFile();

	}

	public RemoteWebDriver getDriver() {

		return driver;
	}

	public static String getVideoName() {
		return nameVideoLocal.get();
	}
}