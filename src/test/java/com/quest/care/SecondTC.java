package com.quest.care;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SecondTC {

	WebDriver driver;
	
	@Test
	@Parameters({"browser","url","username","password"})
	public void swagapp(String browser, String url, String username, String password  ) {
		
		System.setProperty("webdriver.chrome.driver", "C:/Users/Chejala Chandra/brower/chromedriver.exe");
		
		driver = new ChromeDriver();
		driver.get(url);
		
		driver.findElement(By.xpath("//input[@name='user-name']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@name='login-button']")).click();
		
		
		driver.quit();
		
		
	}

}
