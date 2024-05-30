package com.QA.TestCases;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;
import com.QA.BaseClass.BaseClass;
import com.QA.PageObject.*;
import com.QA.Utilities.*;

public class UserJoinTheCommunityTC extends BaseClass {
	@Test(dataProvider = "AllData", priority = 1)
	public void VerifyUserSignUpProcessTC(String UserEmailAddress, String UserPassword, String UserName, String UserDOB)
			throws InterruptedException, IOException {

		logger.info("***************TestCase Verify Signed Up starts*****************");
		JoinTheCommunityPageObject JoinTheCommunityPage_object = new JoinTheCommunityPageObject(driver);
		driver.get(baseURL);
		Thread.sleep(15000);
		driver.findElement(By.xpath("//a[normalize-space()='Sign up']")).click();
		Thread.sleep(1000);
		JoinTheCommunityPage_object.EnterDataInEmailAddress(UserEmailAddress);
		logger.info("Entered EmailAddress: " + UserEmailAddress);
		// Thread.sleep(1000);
		JoinTheCommunityPage_object.EnterDataInPassword(UserPassword);
		logger.info("Entered Password: " + UserPassword);
		JoinTheCommunityPage_object.EnterDataInUserName(UserName);
		logger.info("Entered UserName: " + UserName);

		// Select DOB from calender
		WebElement SelectDOBField = driver.findElement(By.xpath("//input[@placeholder='Select Date']"));
		SelectDOBField.click();
		Select DOBYear = new Select(driver.findElement(By.xpath("//select[@class='react-datepicker__year-select']")));
		DOBYear.selectByVisibleText("2000");
		WebElement DOBDate = driver.findElement(By.xpath("//div[@aria-label='Choose Sunday, May 7th, 2000']"));
		DOBDate.click();

		logger.info("Entered DOB");
		JoinTheCommunityPage_object.EnterDataInTCcheckbox();
		logger.info("Clicked on T&C checkbox");
		Thread.sleep(2000);
		JoinTheCommunityPage_object.clickOnLoginButton();
		logger.info("Clicked on Submit button");
		Thread.sleep(2000);
		// JavascriptExecutor jse = (JavascriptExecutor) driver;
		// jse.executeScript("document.body.style.zoom = '80%';");
		String NewURL = driver.getCurrentUrl();
		if (NewURL.equals("https://staging-platform.chancer.com/verifyEmail"))
			logger.info("User Signed Up sucessfully");
		else {
			logger.info("User failed to Signed Up");
			captureScreenShot(driver, "VerifyUserSignUpProcess");
		}

		logger.info("***************TestCase Verify Signed Up ends*****************");

	}

}
