package com.QA.TestCases;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import com.QA.BaseClass.BaseClass;
import com.QA.PageObject.*;

public class UserChangePasswordTC extends BaseClass {

	@Test
	public void VerifyChangePasswordProcessTC()
	{
		logger.info("***************TestCase Verify Change Password starts*****************");
		ChangePasswordPageObject ChangePasswordPage_object = new ChangePasswordPageObject(driver);
		//driver.get(baseURL);
		//Mail link fetch work is pending.
		ChangePasswordPage_object.EnterDataInNewPasswordField("Abc@12345");
		ChangePasswordPage_object.EnterDataInConfirmPasswordField("Abc@12345");
		ChangePasswordPage_object.clickOnSubmitButton();
        WebElement toastMessageElement = driver.findElement(By.className("go3958317564"));
        String toastMessage = toastMessageElement.getText();
        System.out.println("Toast Message: " + toastMessage);
		
        if(toastMessage.equals("Email sent successfully"))
            System.out.println("Message is correct");
        else
        	System.out.println("Message is not correct");
		logger.info("***************TestCase Verify Change Password ends*****************");
	}
}
