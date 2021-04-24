package com.CRM.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.CRM.BaseTest.BaseTest;
import com.CRM.Utilities.WebDriverUtilities;

public class CRMLoginPage extends BaseTest{
	
	@FindBy(name="email")
	WebElement edtEmail;
	
	@FindBy(name="password")
	WebElement edtPassword;
	
	@FindBy(xpath="//*[text()='Login']")
	WebElement btnLogin;
	
	public CRMLoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String getCRLMLoginTitle() {
		return driver.getTitle();
		
	}
	
	public void EnterEmailId(String email) {
		WebDriverUtilities.waitForElementToAppear(edtEmail,60);
		edtEmail.sendKeys(email);
	}
	
	public void EnterPassword(String pwd) {
		edtPassword.sendKeys(pwd);
	}
	
	public void ClickLoginbtn() {
		btnLogin.click();
	}
	

}
