package com.quest.pages;


import java.lang.System.Logger;
import java.util.logging.LogManager;

import org.openqa.selenium.By;

import com.quest.health.BasePage;
import com.quest.health.DefaultWebDriver;

public class LoginPage extends BasePage{

	public LoginPage(DefaultWebDriver defaultWebDriver) {
		super(defaultWebDriver);
		// TODO Auto-generated constructor stub
	}
	
	////private static final Logger log = (Logger) LogManager.getLogManager();
	
	//xpath 
	
	public static final By bankManagerLoginBtn = By.xpath("//button[text()='Bank Manager Login']"); 
	public static final By customerLoginBtn = By.xpath("//button[text()='Customer Login']"); 
	
	public static final By user_Name = By.xpath("//input[@name='user-name']");
	public static final By user_password = By.xpath("//input[@name='password']");
	public static final By Login_buttn = By.xpath("//input[@name='login-button']");
	
	public BankManagerHomePage clickOnBankManagerLoginButton() {
		
		try {
			defaultWebDriver.waitForElementToBeVisible(bankManagerLoginBtn);
			defaultWebDriver.safeClick(bankManagerLoginBtn);
		}catch(Exception e) {
		
			e.printStackTrace();
		}
		
		return new BankManagerHomePage(defaultWebDriver);
		
	}
	
	
	
	
	public CustomerHomePage clickOncustomerLoginBtn() {
		
		try {
			defaultWebDriver.waitForElementToBeVisible(customerLoginBtn);
			defaultWebDriver.safeClick(customerLoginBtn);
		}catch(Exception e) {
		
			e.printStackTrace();
		}
		
		return new CustomerHomePage(defaultWebDriver);
		
	}
	
	
	
	public SwagLabsPage login_Swag(String uname, String password) {
		
		try {
			defaultWebDriver.sendDataToTextBox(user_Name, uname);
			defaultWebDriver.sendDataToTextBox(user_password, password);
			defaultWebDriver.safeActionsClick(Login_buttn);
		}catch(Exception e) {
		
			e.printStackTrace();
		}
		
		return new SwagLabsPage(defaultWebDriver);
		
	}



}
