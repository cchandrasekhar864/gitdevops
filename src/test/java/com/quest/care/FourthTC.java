package com.quest.care;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.quest.health.BasePage;
import com.quest.health.DefaultWebDriver;
import com.quest.health.TestBase;
import com.quest.pages.LoginPage;
import com.quest.pages.SwagLabsPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class FourthTC extends TestBase {

	
	public WebDriver driver;
	public ExtentReports extent;
	public ExtentTest extentTest;
	
	
	public SwagLabsPage swaglabspage = new SwagLabsPage(defaultWebDriver);

	
	
	
	@BeforeTest
	public void setExtent(){
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReport.html", true);
		extent.addSystemInfo("User Name", "1039329");
		extent.addSystemInfo("Host Name", "INBLRTL4729");
		extent.addSystemInfo("Environment", "QA");
		
	}
	
	
	@AfterTest
	public void endReport(){
		extent.flush();
		extent.close();
	}
	
	
	

	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException{
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots"
		// under src folder
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
	 

	
	
	
	@BeforeMethod
	
	public void setup() {
		
		String expUrl = properties.getProperty("url1");
		String expUsername = properties.getProperty("swag_userName");
		String expPassword = properties.getProperty("swag_pwd");
		
		
		initialization(expUrl);
		LoginPage loginPage = new LoginPage(defaultWebDriver);
		loginPage.login_Swag(expUsername, expPassword);
	}
		

	@Test
	public void createorder() {
		
		extentTest = extent.startTest("createorder");
	
		
		String expectedFilter = properties.getProperty("filter");
		String expectedFN = properties.getProperty("firstName");
		String expectedLN = properties.getProperty("lastName");
		String expectedPC = properties.getProperty("postCode");
		

		String expectedIN1 = properties.getProperty("itemname1");
		String expectedIN2 = properties.getProperty("itemname2");
		String expectedIN3 = properties.getProperty("itemname3");
		String expectedTotal = properties.getProperty("totalamount");
		String expectedMsg = properties.getProperty("message");
		
	
		
		SwagLabsPage swaglabspage = new SwagLabsPage(defaultWebDriver);
		swaglabspage.selectSwagOptions(expectedFilter);
		swaglabspage.clickOnContinue();
		swaglabspage.checkOutItem(expectedFN, expectedLN, expectedPC);
		Assert.assertTrue(swaglabspage.verifyitemsnames(expectedIN1, expectedIN2, expectedIN3));
		Assert.assertTrue(swaglabspage.verifyItemsCost(expectedTotal));
		swaglabspage.clickOnFinishButton();
		
	
		Assert.assertTrue(swaglabspage.verifyTheMsg(expectedMsg));
		
		//swaglabspage.closeTheBrowser();
	}

		

	
	
	
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException{
		
		if(result.getStatus()==ITestResult.FAILURE){
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getName()); //to add name in extent report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getThrowable()); //to add error/exception in extent report
			
			String screenshotPath = FourthTC.getScreenshot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath)); //to add screenshot in extent report
			//extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath)); //to add screencast/video in extent report
		}
		else if(result.getStatus()==ITestResult.SKIP){
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		}
		else if(result.getStatus()==ITestResult.SUCCESS){
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS " + result.getName());

		}
		
		
		extent.endTest(extentTest); //ending test and ends the current test and prepare to create html report
		driver.quit();
	}
}

