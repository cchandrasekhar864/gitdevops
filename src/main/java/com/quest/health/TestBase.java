package com.quest.health;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase {
	
	   FileInputStream fileInputStream = null;
	    public Properties properties = null;
	    public DefaultWebDriver defaultWebDriver = null;
	    public ExtentReports extent;
		public ExtentTest extentTest;

	    public TestBase() {
	        try {
	            fileInputStream = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/quest/config/Config.Properties");
	            properties = new Properties();
	            properties.load(fileInputStream);
	        }catch (FileNotFoundException e){
	            e.printStackTrace();
	        }catch (IOException e){
	            e.printStackTrace();
	        }
	        catch (Exception e){
	            e.printStackTrace();
	        }
	    }

	    public void initialization(String url){
	        BrowserSetUp browserSetUp = new BrowserSetUp();
	        defaultWebDriver = browserSetUp.getWebDriver(properties.getProperty("browser"));
	        
	        if(url.contains("globalsqa")) {
	        defaultWebDriver.openUrl(properties.getProperty("url"));
	        }else {
	        	defaultWebDriver.openUrl(properties.getProperty("url1"));
	        }

	    }
	    
	    
	 
	    
	    
	    
//	    @AfterMethod
//		
//		public void tearDown(ITestResult result) throws IOException {
//			
//			if(result.getStatus()==ITestResult.FAILURE) {
//				extentTest.log(LogStatus.FAIL, "Test Case Failed is" +result.getName());
//				extentTest.log(LogStatus.FAIL, "Test Case Failed is" +result.getThrowable());
//				
//				String screenshotpath = DefaultWebDriver.getscreenshot(defaultWebDriver, result.getName());
//				System.out.println("screenshot name :" + screenshotpath);
//				extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotpath));	
//			}
//			
//			else if(result.getStatus()==ITestResult.SKIP) {
//				extentTest.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
//				
//			}
//			
//			else if(result.getStatus()==ITestResult.SUCCESS) {
//				extentTest.log(LogStatus.PASS, "Test Case Passed is " + result.getName());
//				
//			}
//			
//			extent.endTest(extentTest);
//			//driver.close();
//			
//			
//		}
}


