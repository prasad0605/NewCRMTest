package com.CRM.Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.CRM.BaseTest.BaseTest;

public class WebDriverUtilities extends BaseTest{
	
	public static void waitForElementToAppear(WebElement element,long timeout) {
		WebDriverWait wait=new WebDriverWait(driver,timeout);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	

}
