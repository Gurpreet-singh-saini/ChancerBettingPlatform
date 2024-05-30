package com.QA.TestCases;

import org.testng.annotations.Test;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import com.QA.BaseClass.BaseClass;
import com.QA.PageObject.*;
import com.QA.PageObject.LoginPageObject;

public class UserForgotPasswordTC extends BaseClass {

	@Test(dataProvider = "AllData")
	public void VerifyForgotPasswordProcessTC(String UserEmailAddress, String UserPassword, String UserName, String UserDOB) throws InterruptedException
	{
		logger.info("***************TestCase Verify Forgot Password starts*****************");
		ForgotPasswordPageObject ForgotPasswordPage_object = new ForgotPasswordPageObject(driver);
		driver.get(baseURL);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[normalize-space()='Forgot password?']")).click();
		Thread.sleep(1000);
		ForgotPasswordPage_object.EnterDataInEmailAddress(UserEmailAddress);
		ForgotPasswordPage_object.clickOnSubmitButton();
				
        WebElement toastMessageElement = driver.findElement(By.className("go3958317564"));
        String toastMessage = toastMessageElement.getText();
        System.out.println("Toast Message: " + toastMessage);
		
        if(toastMessage.equals("Email sent successfully"))
            System.out.println("Message is correct");
        else
        	System.out.println("Message is not correct");
		logger.info("***************TestCase Verify Forgot Password ends*****************");
	}
}
