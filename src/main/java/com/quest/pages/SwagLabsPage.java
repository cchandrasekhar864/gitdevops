package com.quest.pages;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import com.quest.health.BasePage;
import com.quest.health.DefaultWebDriver;

public class SwagLabsPage extends BasePage{
	
		public SwagLabsPage(DefaultWebDriver defaultWebDriver) {
			super(defaultWebDriver);
			
		}
		
		   public  String getscreenshot(String screenshotName)
		    		  throws IOException {
		    		  
		    		  String dateName = new SimpleDateFormat("yyyyMMddhhss").format(new Date());
		    		  TakesScreenshot ss = (TakesScreenshot) defaultWebDriver;
		    		  
		    		  File source = ss.getScreenshotAs(OutputType.FILE);
		    		  
		    		  String destination = System.getProperty("user.dir")
		    		  +"/FailedTestsScreenshots/" + screenshotName + dateName + ".png"; File
		    		  finalDestination = new File(destination); FileUtils.copyFile(source,
		    		  finalDestination);
		    		  
		    		  
		    		  return destination; }
		    		 


		//*************************************Xpaths*************************************************

		public static final By selecttheindex = By.xpath("//select[@class='product_sort_container']"); 
		
		public static final By lowtohigh = By.xpath("//div[@class='inventory_item_price']//following::button");
		public static final String clickoncart ="//a[@class='shopping_cart_link' ]//span[contains(text(),'%s')]"; 
		public static final By clickoncart1 = By.xpath("//a[@class='shopping_cart_link' ]");
		public static final By clickoncheckout = By.xpath("//button[@id='checkout']"); 
		public static final By sendthedatatofirst_name = By.xpath("//input[@id='first-name']"); 
		public static final By sendthedatatolast_name = By.xpath("//input[@id='last-name']"); 
		public static final By sendthedatatopostal_code = By.xpath("//input[@id='postal-code']"); 
		public static final By continue_button = By.xpath("//input[@id='continue']"); 
		public static final String  actual_value1 ="//div[text()='%s']"; 
		public static final String  actual_value2 = "//div[text()='%s']"; 
		public static final String actual_value3 = "//div[text()='%s']"; 		
		public static final By actual_value4 = By.xpath("//div[@class='summary_subtotal_label']"); 
		public static final By finishbutton = By.xpath("//button[text()='Finish']"); 
		public static final String actual_msg1 = "//h2[text()='%s']";
		public static final By item_price_list = By.cssSelector(".inventory_item_price1111&&&&&&1111");
		
		
		
		public void selectSwagOptions(String option) {
			
			try 
			{
				defaultWebDriver.selectByVisibleText(selecttheindex, option);
				List<WebElement> items = defaultWebDriver.getList(lowtohigh);

				for (int i = items.size()-1; i>=3; i--) {

					items.get(i).click();
				}
				
				
			}
			catch(Exception e) {
				
				e.printStackTrace();
				
			}
		}
		
		
		
		
		
		public void clickOnContinue() {
			try {
			defaultWebDriver.waitForElementToBeVisible(clickoncart1);
			defaultWebDriver.safeClick(clickoncart1 );
			defaultWebDriver.waitForElementToBeVisible(clickoncheckout);
			defaultWebDriver.safeClick(clickoncheckout);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
		
		
		
		
		
		
		
		
		
		public void checkOutItem(String firstname, String lastname, String postCode) {
			
			try {
				
				
				Thread.sleep(5000);
				defaultWebDriver.sendDataToTextBox(sendthedatatofirst_name, firstname);
				defaultWebDriver.sendDataToTextBox(sendthedatatolast_name, lastname);
				defaultWebDriver.sendDataToTextBox(sendthedatatopostal_code, postCode);
				defaultWebDriver.safeClick(continue_button);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		public boolean verifyitemsnames(String itemname1,String itemname2, String itemname3) {
			
			boolean isVerified = false;
			
			try {
				
				String item1  = defaultWebDriver.safeGetText(By.xpath(String.format(actual_value1, itemname1)));
				if(item1.equals(itemname1)) {
					isVerified = true;
				}
				String item2  = defaultWebDriver.safeGetText(By.xpath(String.format(actual_value2, itemname2)));
				if(item2.equals(itemname2)) {
					isVerified = true;
				}
				String item3  = defaultWebDriver.safeGetText(By.xpath(String.format(actual_value3, itemname3)));
				if(item3.equals(itemname3)) {
					isVerified = true;
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			System.out.println("the is Verified "+ isVerified);
			
			return isVerified;
		}
		
		
		
		public boolean verifyItemsCost(String totalAmount) {
			
			System.out.println("the method is started - verifyCost");
			ArrayList<String> l = new ArrayList<>();
			double sum =0;
			boolean isOpSuccess = false; 
			
			try {
				
				List<WebElement> itemcosts = defaultWebDriver.getList(item_price_list);
				
				for(int i=0;i<itemcosts.size();i++) {
					l.add(itemcosts.get(i).getText().replace("$", ""));
				}
				
				System.out.println(l);
					for(int k =0; k<l.size();k++) {
						sum = sum + Double.parseDouble(l.get(k));
					}
					
					System.out.println(" the sum is ::"+sum);
					
					
					if(sum == Double.parseDouble(totalAmount)) {
						System.out.println("the cost of the items cost is same");
						isOpSuccess = true;
					}
					
					
				
			}catch(Exception e) {
				
			}
			return isOpSuccess;
		}
		
		
		public void clickOnFinishButton() {
			
		try {
			defaultWebDriver.safeClick(finishbutton);
	
		}catch(Exception e) {
			e.printStackTrace();
		}
		}
		
		
		
		

		
		public boolean verifyTheMsg(String message) {
			
			boolean isVerified = false;
			
			try {
				String expe_msg =  defaultWebDriver.safeGetText(By.xpath(String.format(actual_msg1, message)));
				
				if(expe_msg.equals(message)) {
					isVerified = true;
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			return isVerified;
		}
		
		
		
		public void closeTheBrowser() {
			defaultWebDriver.closeWindow();
		}
		
		
		
		
		
}

