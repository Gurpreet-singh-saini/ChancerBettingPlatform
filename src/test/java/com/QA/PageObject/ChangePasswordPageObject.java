package com.QA.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChangePasswordPageObject {

	WebDriver ldriver;

	//constructor
	public ChangePasswordPageObject(WebDriver rdriver)
	{
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	//identify webelements
	@FindBy(xpath = "//input[@name='newPassword']") 
	@CacheLookup
	WebElement UserNewPassword;
	
	//identify webelements
	@FindBy(xpath = "//input[@name='confirmPassword']") 
	@CacheLookup
	WebElement UserConfirmPassword;
	
	@FindBy(xpath = "//button[@type='submit']") 
	@CacheLookup
	WebElement SubmitButton;
	
	
	public void EnterDataInNewPasswordField(String emailaddress)
	{
		UserNewPassword.sendKeys(emailaddress);
	}
	
	public void EnterDataInConfirmPasswordField(String emailaddress)
	{
		UserConfirmPassword.sendKeys(emailaddress);
	}
		
	public void clickOnSubmitButton()
	{
		SubmitButton.click();
	}
	
}
