package com.CRM.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import com.CRM.BaseTest.BaseTest;
import com.CRM.Pages.CRMContactPage;
import com.CRM.Pages.CRMHomePage;
import com.CRM.Pages.CRMLoginPage;
import com.CRM.Utilities.AutomationConstants;
import com.CRM.Utilities.ExcelUtilities;
import com.aventstack.extentreports.Status;

public class CRMContactTest extends BaseTest{
	
	CRMLoginPage loginpage;
	CRMHomePage homePage;
	CRMContactPage contactPage;
	SoftAssert softassert;
	@BeforeMethod()
	public void invoke() {
		InitilizeBrowser();
		loginpage=new CRMLoginPage();
		homePage=new CRMHomePage();
		contactPage=new CRMContactPage();
	}
	
	@Test
	public void verifyContactPage() throws InterruptedException, IOException {
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
		contactPage.ClickOnContacts();
		boolean b2=contactPage.getContactPage();
		Assert.assertTrue(b2);
		test.log(Status.INFO, "CRM New Contact page is invoked Successfully");
		String excelPath=System.getProperty("user.dir")+"\\Resources\\Testdata\\CRM.xlsx";
		ExcelUtilities excel=new ExcelUtilities(excelPath,"Sheet1");
		String fname=excel.getCelldata("FirstName", 2);
		String lname=excel.getCelldata("LastName", 2);
		String category=excel.getCelldata("Category", 2);
		String status=excel.getCelldata("Status", 2);
		String source=excel.getCelldata("Source", 2);
		contactPage.enterContactFirstName(fname);
		contactPage.enterContactLastName(lname);
		contactPage.SelectCategory(category);
		Thread.sleep(2000);
		contactPage.SelectStatus(status);
		Thread.sleep(2000);
		contactPage.SelectSource(source);
		Thread.sleep(2000);
		contactPage.ClickOnSavebtn();
		Thread.sleep(2000);
		String actContactuser=contactPage.getContactUsername();
		softassert=new SoftAssert();
		softassert.assertEquals(actContactuser, fname+" "+lname);
		String actcategory=contactPage.getContactCategory();
		softassert.assertEquals(actcategory,category);
		String actstatus=contactPage.getContactStatus();
		softassert.assertEquals(actstatus,status);
		softassert.assertAll();
		
	}
	
	
}
