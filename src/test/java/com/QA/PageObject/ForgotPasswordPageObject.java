package com.QA.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgotPasswordPageObject {

	WebDriver ldriver;

	//constructor
	public ForgotPasswordPageObject(WebDriver rdriver)
	{
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	//identify webelements
	@FindBy(xpath = "//input[@name='mail']") 
	@CacheLookup
	WebElement UserEmail;
	
	@FindBy(xpath = "//button[@type='submit']") 
	@CacheLookup
	WebElement SubmitButton;
	
	
	public void EnterDataInEmailAddress(String emailaddress)
	{
		UserEmail.sendKeys(emailaddress);
	}
	
	
	public void clickOnSubmitButton()
	{
		SubmitButton.click();
	}
	
}
