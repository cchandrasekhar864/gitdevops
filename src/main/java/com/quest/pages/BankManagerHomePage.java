package com.quest.pages;



import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.quest.health.BasePage;
import com.quest.health.DefaultWebDriver;

import java.lang.System.Logger;
import java.util.List;
import java.util.logging.LogManager;

public class BankManagerHomePage extends BasePage {

	public BankManagerHomePage(DefaultWebDriver defaultWebDriver) {
		super(defaultWebDriver);
		// TODO Auto-generated constructor stub
	}

	
	
	//xpath 
	
	public static final By clickOnAddCustomer = By.xpath("//div[@class='center']//button[@ng-class='btnClass1']");
	public static final By enterFirstName = By.xpath("//div[@class='form-group']//input[@placeholder='First Name']"); 
	public static final By enterLastName = By.xpath("//div[@class='form-group']//input[@placeholder='Last Name']");
	public static final By enterPostCode = By.xpath("//div[@class='form-group']//input[@placeholder='Post Code']");
	public static final By clickOnAddCustomerBttn = By.xpath("//button[text()='Add Customer']"); 
	public static final By clickOnOpenAccount = By.xpath("//div[@class='center']//button[@ng-class='btnClass2']");
	public static final By clickOncustomerdd = By.xpath("//select[@id='userSelect']"); 
	public static final By clickoncurrencydd = By.xpath("//select[@id='currency']");
	public static final By clickonprocess = By.xpath("//button[text()='Process']");
	public static final By clickOnCustomer = By.xpath("//div[@class='center']//button[@ng-class='btnClass3']");
	public static final String firstnamevalidate = "(//td[text()='%s']/parent::tr/td)[1]";
	public static final String lastnamevalidate = "(//td[text()='%s']/parent::tr/td)[2]";
	public static final String postcodevalidate = "(//td[text()='%s']/parent::tr/td)[3]";
	public static final By clickOnHome = By.xpath("//button[text()='Home']"); 
	public static final String delete_bttn= "//table//tbody//tr//td[text()='%s']/following::button";
	public static final By firstName_columns= By.xpath("//table//tbody//td[1]");
	public static final By actual_value3= By.xpath("//table//tbody//tr//td[text()='chandra']"); 
	
	
	public void addCustomer(String firstName, String lastName, String postCode) {
		
		try {
			
			defaultWebDriver.safeClick(clickOnAddCustomer);
			defaultWebDriver.sendDataToTextBox(enterFirstName, firstName);
			defaultWebDriver.sendDataToTextBox(enterLastName, lastName);
			defaultWebDriver.sendDataToTextBox(enterPostCode, postCode);
			defaultWebDriver.safeClick(clickOnAddCustomerBttn);
			defaultWebDriver.acceptAlert();
		}catch(Exception e) {
		
			e.printStackTrace();
		}
	}
		
	public boolean openAccount(String firstName, String lastName, String currencyName) {
		
		String fullName = firstName + " "+lastName;
		 boolean isDisplayed = false;
		try {
			defaultWebDriver.waitForElementToBeVisible(clickOnOpenAccount);

			defaultWebDriver.safeClick(clickOnOpenAccount);
			isDisplayed = verifyNamesListInDropDown(firstName , lastName);
			System.out.println("the value is ::"+isDisplayed);
			defaultWebDriver.selectByVisibleText(clickOncustomerdd,fullName);
			Thread.sleep(3000);
			defaultWebDriver.selectByValue(clickoncurrencydd, currencyName);
			defaultWebDriver.safeClick(clickonprocess);
			Thread.sleep(3000);
			defaultWebDriver.acceptAlert();
			System.out.println("the value is ::"+isDisplayed);
			   
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return isDisplayed; 
	}
	
	public boolean verifyNamesListInDropDown(String firstName, String lastName) {
		
		boolean isOpSuccess = false;
		
		String fullName = firstName + " "+lastName;
		
		List<WebElement>  lis = defaultWebDriver.getAllOptionInDropDown(clickOncustomerdd);
		
		for(int i=0; i<lis.size();i++) {
			
			if(lis.get(i).getText().equalsIgnoreCase(fullName)) {
				
				isOpSuccess = true;
				break;
				
			}
		}
		
		return isOpSuccess;
	}
	
	public boolean validateCustomers(String firstName, String lastName, String postCode) {
		boolean isOpSuccess = false;
		
		try {
			
			defaultWebDriver.safeClick(clickOnCustomer);
			
			
			isOpSuccess =  defaultWebDriver.isElementPresentAndVisible(By.xpath(String.format(firstnamevalidate, firstName)));
			isOpSuccess = isOpSuccess && defaultWebDriver.isElementPresentAndVisible(By.xpath(String.format(lastnamevalidate, lastName)));
			isOpSuccess = isOpSuccess && defaultWebDriver.isElementPresentAndVisible(By.xpath(String.format(postcodevalidate, postCode)));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return isOpSuccess;
	}
	
	public void clickOnHomeButton() {
		
		try {
			
			defaultWebDriver.safeClick(clickOnHome);
			
		}catch(Exception e) {
		
			e.printStackTrace();
		}
	}
	
	public void clickOnCustomersTab() {
		defaultWebDriver.safeClick(clickOnCustomer);
	}
	
	
	public void deleteUser(String firstName) {
		try {
			Thread.sleep(10000);
			defaultWebDriver.safeClick(By.xpath(String.format(delete_bttn, firstName)));
		} catch (Exception e) {
			e.printStackTrace();
			
	}
	
	}
	
	public boolean verifyDeleteUser(String firstname) {
		
			boolean isDeleted = false; 
		try {
			
			List<WebElement> col_Names_Lit = defaultWebDriver.getList(firstName_columns);
			
			for(int i=0;i<col_Names_Lit.size();i++) {
				if(col_Names_Lit.get(i).getText().equals(firstname)) {
					
					isDeleted = false;
				}else {
					
					isDeleted = true; 
				}
			}
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return isDeleted;
		
		
	}
	
	public void closeTheBrowser() {
		defaultWebDriver.closeWindow();
	}
		
	}
	
