package com.quest.health;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.quest.utilites.TestUtilites;

public class BrowserSetUp extends TestBase {
		
	    public BrowserSetUp(){
	        super();
	    }

	    public DefaultWebDriver defaultWebDriver = null;
	    public WebDriver driver = null;

	    public DefaultWebDriver getWebDriver(String browserName){
	        if(browserName.equals("chrome")){
	            System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/src/main/java/com/quest/drivers/chromedriver.exe");
	            driver = new ChromeDriver();
	        }else if(browserName.equals("firefox")){
	            System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"/src/main/java/com/quest/drivers/geckodriver.exe");
	            driver = new FirefoxDriver();
	        }else if(browserName.equals("ie")){
	            System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"/src/main/java/com/quest/drivers/internetExplorerdriver.exe");
	            driver = new InternetExplorerDriver();
	        }

	        driver.manage().window().maximize();
	        driver.manage().deleteAllCookies();
	        driver.manage().timeouts().implicitlyWait(TestUtilites.MEDIUM_WAIT, TimeUnit.SECONDS);
	        driver.manage().timeouts().pageLoadTimeout(TestUtilites.LONG_WAIT,TimeUnit.SECONDS); 

	        defaultWebDriver = new DefaultWebDriver(driver);

	        return defaultWebDriver;

	    }

}

