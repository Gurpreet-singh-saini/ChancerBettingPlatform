package com.QA.TestCases;

import java.io.IOException;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import com.QA.BaseClass.BaseClass;
import com.QA.PageObject.*;

public class UserLoginTC extends BaseClass {
	@Test(dataProvider = "AllData")
	public void VerifyUserLoginProcessTC(String UserEmailAddress, String UserPassword, String UserName, String UserDOB)
			throws InterruptedException, IOException {
		logger.info("***************TestCase Verify Login starts*****************");
		LoginPageObject UserLoginPage_object = new LoginPageObject(driver);
		driver.get(baseURL);
		Thread.sleep(5000);
		// logger.info("AdminURL opened");
		UserLoginPage_object.EnterDataInEmailAddress(UserEmailAddress);
		logger.info("Entered EmailAddress");
		UserLoginPage_object.EnterDataInPassword(UserPassword);
		logger.info("Entered Password");
		UserLoginPage_object.EnterDataInTCcheckbox();
		// input[@id='checkbox']
		UserLoginPage_object.clickOnLoginButton();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(1000);
		// JavascriptExecutor jse = (JavascriptExecutor) driver;
		// jse.executeScript("document.body.style.zoom = '80%';");
		String NewURL = driver.getCurrentUrl();
		if (NewURL.equals("https://staging-platform.chancer.com/main"))
			logger.info("Admin logged In sucessfully");
		else {
			logger.info("Admin failed to logged In");
			captureScreenShot(driver, "VerifyAdminLogin");
		}

		logger.info("***************TestCase Verify Login ends*****************");
	}
}
