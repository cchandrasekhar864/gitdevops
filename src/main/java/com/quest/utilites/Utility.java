package com.quest.utilites;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utility {
	
	
	public static void  capturescreenshot(WebDriver driver , String screenshotName) 
	{
			
		try {
			String dateName = new SimpleDateFormat("yyyyMMddhhss").format(new Date());
			TakesScreenshot ss = (TakesScreenshot) driver;
			
			File source = ss.getScreenshotAs(OutputType.FILE);
			
			String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" 
			+ screenshotName + dateName + ".png";
			File finalDestination = new File(destination);
			FileUtils.copyFile(source, finalDestination);
			
			System.out.println("Screenshot taken!!!!!!!!!!!!!");
			
		}catch(Exception e) {
			System.out.println("Exception while taking screenshot" +e.getMessage());
			
			
		}
		

		
		
	}
	}

