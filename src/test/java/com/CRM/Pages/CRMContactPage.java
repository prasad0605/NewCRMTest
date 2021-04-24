package com.CRM.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.CRM.BaseTest.BaseTest;

public class CRMContactPage extends BaseTest{
	
	@FindBy(xpath="//i[@class='users icon']")
	WebElement elmContactsIcon;
	
	@FindBy(xpath="//i[@class='users icon']/following::button[@class='ui mini basic icon button'][1]")
	WebElement btnContactsAdd;
	
	@FindBy(xpath="//*[text()='Create New Contact']")
	WebElement elmCreateNewContact;
	
	@FindBy(name="first_name")
	WebElement edtFirstname;
	
	@FindBy(name="last_name")
	WebElement edtLastname;
	
	@FindBy(xpath="//div[@name='category' and @role='listbox']")
	WebElement lstCategory;
    
	@FindBy(xpath="//div[@name='status' and @role='listbox']")
	WebElement lstStatus;
	
	@FindBy(xpath="//div[@name='source' and @role='listbox']")
	WebElement lstSource;

	@FindBy(xpath="//*[@class='ui linkedin button']")
	WebElement btnSave;
	
	@FindBy(xpath="//*[@class='header item']")
	WebElement imgCRMLogo;
	
	@FindBy(xpath="//*[@class='ui header item mb5 light-black']")
	WebElement elmUserheader;
	
	@FindBy(xpath="//*[text()='Category']/following::div[1]")
	WebElement elmCategory;

	@FindBy(xpath="//*[text()='Status']/following::div[1]")
	WebElement elmStatus;

	public CRMContactPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void ClickOnContacts() throws InterruptedException {
		Actions actions=new Actions(driver);
		actions.moveToElement(elmContactsIcon).build().perform();
		Thread.sleep(2000);
		btnContactsAdd.click();
	}
	
	public boolean getContactPage() {
		return elmCreateNewContact.isDisplayed();
	}
	
	public void enterContactFirstName(String firstname) {
		imgCRMLogo.click();
		edtFirstname.sendKeys(firstname);
	}
	
	public void enterContactLastName(String lastName) {
		edtLastname.sendKeys(lastName);
	}
	
	
	public void SelectCategory(String category) {
		lstCategory.click();
		driver.findElement(By.xpath("//*[text()='"+category+"']")).click();
	    
	}
	
	public void SelectStatus(String status) {
		lstStatus.click();
		driver.findElement(By.xpath("//*[text()='"+status+"']")).click();
	}
	
	
	public void SelectSource(String source) throws InterruptedException {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		//js.executeScript("arguments[0].scrollIntoView(true)", lstSource);
		lstSource.click();
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[text()='"+source+"']")));
		//Actions actions=new Actions(driver);
		//actions.click(driver.findElement(By.xpath("//*[text()='"+source+"']"))).build().perform();
		//driver.findElement(By.xpath("//*[text()='"+source+"']")).click();
	}
	
	public void ClickOnSavebtn() {
		btnSave.click();
	}
	
	public String getContactUsername() {
		return elmUserheader.getText();
	}
	
	public String getContactCategory() {
		return elmCategory.getText();
	}
	
	public String getContactStatus() {
		return elmStatus.getText();
	}


}
