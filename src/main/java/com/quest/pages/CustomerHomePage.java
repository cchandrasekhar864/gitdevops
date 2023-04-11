package com.quest.pages;

import java.lang.System.Logger;
import java.util.logging.LogManager;

import org.openqa.selenium.By;

import com.quest.health.BasePage;
import com.quest.health.DefaultWebDriver;

public class CustomerHomePage extends BasePage {

	public CustomerHomePage(DefaultWebDriver defaultWebDriver) {
		super(defaultWebDriver);
		// TODO Auto-generated constructor stub
	}

	// ****************************************xpath**********************************************

	public static final By selecttheDD = By.xpath("//select[@id='userSelect']");
	public static final By loginbttn = By.xpath("//button[text()='Login']");
	public static final String actual_val = "//span[text()='%s']";
	public static final By actual_value1 = By.xpath("//div[@class='center']//strong[@class='ng-binding' and text()='0']");
	public static final By actual_value2 = By.xpath("//strong[text()='Rupee']");
	public static final By clickOnTransactions = By.xpath("//div[@class='center']/button[@ng-class='btnClass1']");
	public static final By get_size = By.xpath("//table//tbody//tr11111111111111111");
	public static final By backbutton = By.xpath("//button[text()='Back']");
	public static final By depositbttn = By.xpath("//div[@class='center']/button[@ng-class='btnClass2']");
	public static final By sendthedatatotextfield = By.xpath("//input[@type='number']");
	public static final By depositbutton = By.xpath("//button[text()='Deposit']");
	public static final By actual_msg = By.xpath("//span[text()='Deposit Successful']");
	public static final By withdrawlbttn = By.xpath("//div[@class='center']/button[@ng-class='btnClass3']");
	public static final By sendthedatatotextfield2 = By.xpath("//input[@type='number']");
	public static final By withdrawlbutton = By.xpath("//button[text()='Withdraw']");
	public static final By actual_msg1 = By.xpath("//span[text()='Transaction successful']");
//		driver.findElements(By.xpath("//tr//td[text()='Credit']/preceding-sibling::td[1]"));
//	    List<WebElement> deposit_price_web=driver.findElements(By.xpath("//tr//td[text()='Debit']/preceding-sibling::td[1]"));
	public static final By LogoutButton = By.xpath("//button[text()='Logout']"); 
    public static final By  HomeButton= By.xpath("//button[text()='Home']"); 

	public void selectCustomerName(String fullName) {

		try {

			defaultWebDriver.selectByVisibleText(selecttheDD, fullName);
			defaultWebDriver.safeClick(loginbttn);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean verifyLoginDetails(String fullName, String Balance, String currency) {
		boolean isOpSucess = false;
		try {

			isOpSucess = defaultWebDriver.isElementPresentAndVisible(By.xpath(String.format(actual_val, fullName)));
			isOpSucess = isOpSucess && defaultWebDriver.isElementPresent(actual_value1);
			isOpSucess = isOpSucess && defaultWebDriver.isElementPresent(actual_value2);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return isOpSucess;
	}

	public void clickOntransactions() {

		try {
			Thread.sleep(4000);
			defaultWebDriver.safeClick(clickOnTransactions);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean validateEmptyTransacationList() {
		boolean isNewCustomer = false;
		try {
			
			int txnCount = defaultWebDriver.getWebElementSize(get_size);
			if(txnCount ==0 ) {
				isNewCustomer = true;
			}else {
				System.out.println("he is a old customer");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return isNewCustomer;
	}
	
	public void clickOnBackBtn() {
		
		defaultWebDriver.safeClick(backbutton);
	}
	
	public boolean depositAmount(String depositAmount, String txnMsg) {
		boolean isDeposit = false;
		try {
			
			defaultWebDriver.safeClick(depositbttn);
			defaultWebDriver.sendDataToTextBox(sendthedatatotextfield, depositAmount);
			defaultWebDriver.safeClick(depositbutton);

			Thread.sleep(3000);
			
			String actualDepositMsg = defaultWebDriver.safeGetText(actual_msg);
			if(actualDepositMsg.equalsIgnoreCase(txnMsg)) {
				
				isDeposit = true;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return isDeposit;
	}
	
	public boolean withDrawAmount(String withdrawAmount, String txnMsg1) {
		boolean isWithdraw = false;
		try {
			
			defaultWebDriver.safeClick(withdrawlbttn);
			Thread.sleep(5000);
			defaultWebDriver.sendDataToTextBox(sendthedatatotextfield2, withdrawAmount);
			Thread.sleep(5000);
			defaultWebDriver.safeClick(withdrawlbutton);

			Thread.sleep(5000);
			
			String actualWithdrawlMsg = defaultWebDriver.safeGetText(actual_msg1);
			Thread.sleep(10000);
			if(actualWithdrawlMsg.equalsIgnoreCase(txnMsg1)) {
				
				isWithdraw = true;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return isWithdraw;
	}
	
	
	
	public boolean validateTransacationList() {
		boolean isOldCustomer = false;
		try {
			
			int txnCount = defaultWebDriver.getWebElementSize(get_size);
			if(txnCount > 0 ) {
				isOldCustomer = true;
			}else {
				System.out.println("he is a new customer");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return isOldCustomer;
	}
	
	public void clickOnLogout() {
		
		defaultWebDriver.safeClick(LogoutButton);
	}
	
	
	public void clickOnHome() {
		
		defaultWebDriver.safeClick(HomeButton);
		System.out.println("Successfulluy clicked on Home Button");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
