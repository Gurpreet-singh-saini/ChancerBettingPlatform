package com.QA.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObject {

	WebDriver ldriver;

	//constructor
	public LoginPageObject(WebDriver rdriver)
	{
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	//identify webelements
	@FindBy(xpath = "//input[@name='mail']") 
	@CacheLookup
	WebElement UserEmail;
	
	@FindBy(xpath = "//input[@name='password']") 
	@CacheLookup
	WebElement UserPassword;
	
	@FindBy(xpath = "//input[@id='checkbox']") 
	@CacheLookup
	WebElement TCcheckbox;
	
	@FindBy(xpath = "//button[@type='submit']") 
	@CacheLookup
	WebElement LoginButton;
	
	
	public void EnterDataInEmailAddress(String emailaddress)
	{
		UserEmail.sendKeys(emailaddress);
	}
	
	public void EnterDataInPassword(String password)
	{
		UserPassword.sendKeys(password);
	}
	
	public void EnterDataInTCcheckbox()
	{
		TCcheckbox.click();
	}
	
	public void clickOnLoginButton()
	{
		LoginButton.click();
	}
	
}
