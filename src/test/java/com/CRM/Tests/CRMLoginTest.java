package com.CRM.Tests;

import org.testng.Assert;
import org.testng.annotations.*;

import com.CRM.BaseTest.BaseTest;
import com.CRM.Pages.CRMHomePage;
import com.CRM.Pages.CRMLoginPage;
import com.CRM.Utilities.AutomationConstants;
import com.aventstack.extentreports.Status;

public class CRMLoginTest extends BaseTest{
	
	CRMLoginPage loginpage;
	CRMHomePage homePage;
	@BeforeMethod()
	public void invoke() {
		InitilizeBrowser();
		loginpage=new CRMLoginPage();
		homePage=new CRMHomePage();
	}
	
	@Test
	public void verifyCRMLogin() {
		String actTitle=loginpage.getCRLMLoginTitle();
		test.log(Status.INFO, "CRM Login title is "+actTitle);
		Assert.assertEquals(actTitle, AutomationConstants.CRLMLoginTitle);
		test.log(Status.INFO, "CRM Login title is "+actTitle+ " is appearing expected one");
		loginpage.EnterEmailId(properties.getProperty("username"));
		loginpage.EnterPassword(properties.getProperty("password"));
		loginpage.ClickLoginbtn();
		boolean b=homePage.verifyCRMContactsActivity();
		Assert.assertTrue(b);
		test.log(Status.INFO, "CRM Login is Success");
	}
	
	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}
	

}
