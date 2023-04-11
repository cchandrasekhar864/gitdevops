package com.quest.health;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class BasePage {
	
	public WebDriver driver;
	
    public DefaultWebDriver defaultWebDriver = null;

    public BasePage(DefaultWebDriver defaultWebDriver){
        this.defaultWebDriver = defaultWebDriver;
    }

    

	
	  public static String getscreenshot(WebDriver driver, String screenshotName)
	  throws IOException {
	  
	  String dateName = new SimpleDateFormat("yyyyMMddhhss").format(new Date());
	  //TakesScreenshot ss = (TakesScreenshot) driver;
	  
	  //File source = ss.getScreenshotAs(OutputType.FILE);
	  
	  File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  org.apache.commons.io.FileUtils.copyFile(source, new File("C:\\tmp\\screenshot.png"));
	  String destination = System.getProperty("user.dir")
	  +"/FailedTestsScreenshots/" + screenshotName + dateName + ".png"; File
	  finalDestination = new File(destination); FileUtils.copyFile(source,
	  finalDestination);
	  
	  
	  return destination; }
	 

}
