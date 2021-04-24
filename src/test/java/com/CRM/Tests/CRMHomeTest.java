package com.CRM.Tests;

import org.testng.Assert;
import org.testng.annotations.*;

import com.CRM.BaseTest.BaseTest;
import com.CRM.Pages.CRMHomePage;
import com.CRM.Pages.CRMLoginPage;
import com.CRM.Utilities.AutomationConstants;
import com.aventstack.extentreports.Status;

public class CRMHomeTest extends BaseTest{
	
	CRMLoginPage loginpage;
	CRMHomePage homePage;
	
	@BeforeMethod()
	public void invoke() {
		InitilizeBrowser();
		loginpage=new CRMLoginPage();
		homePage=new CRMHomePage();
	}
	
	
   @Test
   public void verifyHomePageFields() {
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
		boolean b1=homePage.verifyCRMLogo();
		softassert.assertTrue(b1);
		test.log(Status.INFO, "CRM Logo is appearing");
		boolean b2=homePage.verifyCRMUserName();
		softassert.assertTrue(b2);
		String user=homePage.getCRMUserName();
		boolean b3=user.toLowerCase().contains(properties.getProperty("user").toLowerCase());
		softassert.assertTrue(b3);
		test.log(Status.INFO, "CRM user is appearing as "+user+" which is expected one");
		softassert.assertAll();
   }

}
