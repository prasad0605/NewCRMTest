package com.CRM.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.CRM.BaseTest.BaseTest;
import com.CRM.Utilities.WebDriverUtilities;

public class CRMHomePage extends BaseTest{
	
	@FindBy(xpath="//*[text()='Contacts activity stream12']")
	WebElement elmContactsactivity;
	
	@FindBy(css="span.user-display")
	WebElement elmUsername;
	
	@FindBy(xpath="//*[@class='header item']")
	WebElement imgCRMLogo;
	
	public CRMHomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyCRMLogo() {
		return imgCRMLogo.isDisplayed();
	}
	
	public boolean verifyCRMContactsActivity() {
		WebDriverUtilities.waitForElementToAppear(elmContactsactivity,30);
		return elmContactsactivity.isDisplayed();
	}
	
	public boolean verifyCRMUserName() {
		return elmUsername.isDisplayed();
	}
	
	public String getCRMUserName() {
		return elmUsername.getText();
	}

}
