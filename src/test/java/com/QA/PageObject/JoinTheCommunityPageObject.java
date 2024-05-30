package com.QA.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JoinTheCommunityPageObject {

	WebDriver ldriver;

	//constructor
	public JoinTheCommunityPageObject(WebDriver rdriver)
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
	
	@FindBy(xpath = "//input[@name='username']") 
	@CacheLookup
	WebElement UserName;
	
	@FindBy(xpath = "//input[@placeholder='Select Date']") 
	@CacheLookup
	WebElement UserDOB;
	
	@FindBy(xpath = "//input[@id='checkbox']") 
	@CacheLookup
	WebElement TCcheckbox;
	
	@FindBy(xpath = "//button[@type='submit']") 
	@CacheLookup
	WebElement SubmitButton;
	
	
	public void EnterDataInEmailAddress(String emailaddress)
	{
		UserEmail.sendKeys(emailaddress);
	}
	
	public void EnterDataInPassword(String password)
	{
		UserPassword.sendKeys(password);
	}
	
	public void EnterDataInUserName(String password)
	{
		UserName.sendKeys(password);
	}
	
	public void EnterDataInDOB(String password)
	{
		UserDOB.sendKeys(password);
	}
	
	public void EnterDataInTCcheckbox()
	{
		TCcheckbox.click();
	}
	
	public void clickOnLoginButton()
	{
		SubmitButton.click();
	}
	
}
