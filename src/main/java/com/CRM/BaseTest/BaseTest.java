package com.CRM.BaseTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.CRM.Utilities.CommonUtilities;
import com.CRM.Utilities.WebListeners;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.google.common.io.Files;

public class BaseTest {
	
	public static Properties properties;
	public ExtentReports reports;
	public ExtentTest test;
	public static WebDriver driver=null;
	public SoftAssert softassert=new SoftAssert();
    public static EventFiringWebDriver e_driver;
	@BeforeClass
	public void setUp() {
		try {
			ExtentHtmlReporter htmlreporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"\\ExtentReport\\CRMReport_"+CommonUtilities.getTimestamp()+".html");
			 htmlreporter.config().setDocumentTitle("Extent Automation");
			  htmlreporter.config().setReportName("ExtentReport demo");
			  reports=new ExtentReports();
			  reports.attachReporter(htmlreporter);
			  reports.setSystemInfo("user", System.getProperty("user.name"));
			  reports.setSystemInfo("OS", System.getProperty("os.name"));
			  reports.setSystemInfo("Environment", "E2");
			FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\Resources\\Config\\Env.properties");
			properties=new Properties();
			properties.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void InitilizeBrowser() {
		if(properties.getProperty("browser").equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Resources\\Drivers\\chromedriver.exe");
			driver=new ChromeDriver();
		}else if(properties.getProperty("browser").equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Resources\\Drivers\\geckodriver.exe");
			driver=new FirefoxDriver();
		}else if(properties.getProperty("browser").equalsIgnoreCase("ie")) {
			driver=new InternetExplorerDriver();
		}else if(properties.getProperty("browser").equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+"\\Resources\\Drivers\\msedgedriver.exe");
			driver=new EdgeDriver();
		}
		e_driver=new EventFiringWebDriver(driver);
		WebListeners listeners=new WebListeners();
		e_driver.register(listeners);
		driver=e_driver;  //we are replacing normal webdriver to EventFiringWebDriver inorder to listen the all listener methods 
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.get(properties.getProperty("URL"));
		driver.manage().window().maximize();
	}
	
	public static void takesscreenshotTest(String testName) throws IOException{
		File file=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Files.copy(file, new File(System.getProperty("user.dir")+"/Snapshots/"+testName+"_"+CommonUtilities.getTimestamp()+".png"));
	}
	
	@BeforeMethod
	public void registerMethod(Method method) {
		test=reports.createTest(method.getName());
		test.log(Status.INFO,"Test "+method.getName()+" started");
	}
	
	@AfterMethod
	public void getResult(ITestResult result) throws IOException {
		if(result.getStatus()==ITestResult.SUCCESS) {
			test.log(Status.PASS,"Test "+result.getName()+ "PASSED");
		}else if(result.getStatus()==ITestResult.FAILURE) {
			test.log(Status.FAIL,"Test "+result.getName()+ "FAILED");
			test.log(Status.FAIL,"Test "+result.getThrowable()+ "FAILED");
			takesscreenshotTest(result.getName());
		}
		
		//driver.quit();
	}
	
	
	@AfterClass
	public void tearDown() {
		reports.flush();
	}

	
}
