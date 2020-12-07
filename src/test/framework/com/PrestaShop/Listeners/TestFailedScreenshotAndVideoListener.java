package com.PrestaShop.Listeners;

import org.openqa.selenium.TakesScreenshot;
import java.io.ByteArrayInputStream;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import com.PrestaShop.AllureAttach.ReadWriteProperty;
import com.PrestaShop.InitialConfiguration.InitialConfiguration;
import io.qameta.allure.Allure;

public class TestFailedScreenshotAndVideoListener implements IInvokedMethodListener {

	private void takeScreenshot(ITestResult testResult) {

		Object currentClass = testResult.getInstance();
		RemoteWebDriver driver = ((InitialConfiguration) currentClass).getDriver();

		Allure.addAttachment("Screenshot.",
				new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {

		if (method.isTestMethod() && !testResult.isSuccess()) {

			takeScreenshot(testResult);
			
			ReadWriteProperty.write(InitialConfiguration.getVideoName(),
					Allure.getLifecycle().getCurrentTestCase().get());
		}
	}
}
