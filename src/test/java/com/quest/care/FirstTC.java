package com.quest.care;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.quest.health.TestBase;
import com.quest.pages.BankManagerHomePage;
import com.quest.pages.CustomerHomePage;
import com.quest.pages.LoginPage;
import com.quest.pages.SwagLabsPage;
import com.quest.reports.ExtentReporterNG;
import com.quest.utilites.Utility;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class FirstTC  extends TestBase{
	
   public WebDriver driver; 
   public ExtentReports extent;
   public ExtentTest extentTest;
   
   
   
	@BeforeTest
	
	public void setUp() {
		extent = new ExtentReports(System.getProperty("user.dir")+"\\reports\\"+ File.separator
				+System.currentTimeMillis() +"ExtentReport.html", true);
		extent.addSystemInfo("User Name", "chejala chandra");
		extent.addSystemInfo("Host Name", "INBLRTL4729");
		extent.addSystemInfo("Environment", "QA");
		
	}
   
   
   @AfterTest
   
   public void endreport() {
	   
	  extent.flush();
	  //extent.close();
   }
   
   
   
	public static String getscreenshot(WebDriver driver, String screenshotName) throws IOException {
	
	String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	TakesScreenshot ss = (TakesScreenshot) driver;
	
	File source = ss.getScreenshotAs(OutputType.FILE);
	
	String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" 
	+ screenshotName + dateName + ".png";
	File finalDestination = new File(destination);
	FileUtils.copyFile(source, finalDestination);
	
	
	return destination;
	
	
}
   
   
   
   
   
//   @BeforeMethod
//   
//   public void setup() {
//	   
//	   String exprUrl = properties.getProperty("url");
//	   
//	   initialization(exprUrl);
//   }
   
   
   	

	@Test
	public void addnewcustomer() {
		
		extentTest = extent.startTest("addnewcustomer");
		
		String exprUrl = properties.getProperty("url");
		
		String expectedFirstName = properties.getProperty("firstName");
		String expectedlastName = properties.getProperty("lastName");
		String expectedPostCode = properties.getProperty("postCode");
		String expectedCurrency = properties.getProperty("currency");
		
		initialization(exprUrl);
		
		LoginPage loginPage = new LoginPage(defaultWebDriver);
		BankManagerHomePage bankManagerHomePage = loginPage.clickOnBankManagerLoginButton();
		
		
		
		bankManagerHomePage.addCustomer(expectedFirstName, expectedlastName, expectedPostCode);
		
		
		Assert.assertTrue(bankManagerHomePage.openAccount(expectedFirstName, expectedlastName, expectedCurrency));
		
		Assert.assertTrue(bankManagerHomePage.verifyNamesListInDropDown(expectedFirstName, expectedlastName));
		Assert.assertTrue(bankManagerHomePage.validateCustomers(expectedFirstName, expectedlastName, expectedPostCode));
		
		bankManagerHomePage.clickOnHomeButton();
		
		
		
	}
	

	
	@Test
	public void banktranscation() {
		
		extentTest = extent.startTest("banktranscation");
		
	
		
		String expectedFullName = properties.getProperty("fullName");
		String expectedBalance = properties.getProperty("balance");
		String expectedCurrency = properties.getProperty("currency");
		String expectedDeposAmount = properties.getProperty("depositAmount");
		String expectedDepoMsg = properties.getProperty("txnMsg");
		String expectedWithDrawAmount = properties.getProperty("withdrawAmount");
		String expectedWithDrawMsg = properties.getProperty("txnMsg1");

		
		
		
		LoginPage loginPage = new LoginPage(defaultWebDriver);
		CustomerHomePage customerHomePage = loginPage.clickOncustomerLoginBtn();
		
		customerHomePage.selectCustomerName(expectedFullName);
		Assert.assertTrue(customerHomePage.verifyLoginDetails(expectedFullName, expectedBalance, expectedCurrency));
		customerHomePage.clickOntransactions();
		Assert.assertTrue(customerHomePage.validateEmptyTransacationList());
		customerHomePage.clickOnBackBtn();
		Assert.assertTrue(customerHomePage.depositAmount(expectedDeposAmount, expectedDepoMsg));
		Assert.assertTrue(customerHomePage.withDrawAmount(expectedWithDrawAmount, expectedWithDrawMsg));
		customerHomePage.clickOntransactions();
		Assert.assertTrue(customerHomePage.validateTransacationList());
		customerHomePage.clickOnLogout();
		customerHomePage.clickOnHome();
		
	}
	
	
	@Test
	public void deletecustomer() {
		

		extentTest = extent.startTest("deletecustomer");
	
		
		String expectedFirstName = properties.getProperty("firstName");
		
		LoginPage loginPage = new LoginPage(defaultWebDriver);
		BankManagerHomePage bankManagerHomePage = loginPage.clickOnBankManagerLoginButton();
		
		
		bankManagerHomePage.clickOnCustomersTab();
		bankManagerHomePage.deleteUser(expectedFirstName);
		Assert.assertTrue(bankManagerHomePage.verifyDeleteUser(expectedFirstName));
		
		bankManagerHomePage.closeTheBrowser();
		
	}
	
	
	
	
	
	
	
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		
		if(result.getStatus()==ITestResult.FAILURE) {
		
			extentTest.log(LogStatus.FAIL, "Test Case Failed is " +result.getName());//to add name to ER
			extentTest.log(LogStatus.FAIL, "Test Case Failed is " +result.getThrowable());//to add error/exception in ER
			
			String screenshotpath = FirstTC.getscreenshot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotpath));//To add ss in ER	
		}
		
		else if(result.getStatus()==ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
			
		}
		
		else if(result.getStatus()==ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "Test Case Passed is " + result.getName());
			
		}
		
		extent.endTest(extentTest); //ENDING TEST AND PREAPRE TO CREATE HTML REPORT
		//driver.quit();
		
		
	}

	
	
		

	

}