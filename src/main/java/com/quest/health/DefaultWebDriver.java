package com.quest.health;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.quest.utilites.TestUtilites;


public class DefaultWebDriver {
	
    private WebDriver driver = null;

  ////  private static final Logger log = (Logger) LogManager.getLogger(DefaultWebDriver.class);

//    WebEventListener webEventListener = null;
//    EventFiringWebDriver eventFiringWebDriver = null;

    public DefaultWebDriver(WebDriver driver){
        this.driver = driver;
    }

    // reuse code will write in this page

    public void openUrl(String url){

        driver.get(url);
    }

    public String getTitleOfPage(){
        return driver.getTitle();
    }

    public String getCurrentUrlOfPage(){
        return driver.getCurrentUrl();

    }

    public String getPageSourceOfPage(){
        return driver.getPageSource();
    }

    public void launchApp(String url){
        driver.navigate().to(url);

    }

    public void back(){
        driver.navigate().back();
    }

    public void forward(){
        driver.navigate().forward();
    }

    public void refreshOfPage(){
        driver.navigate().refresh();
    }

    public void maximize(){
        driver.manage().window().maximize();
    }

    public String getTextValue(By by){
        String text = driver.findElement(by).getText();

        return text;
    }

    public String getAttributeValue(By by,String attributeName){
        String attrVal = driver.findElement(by).getAttribute(attributeName);

        return attrVal;
    }

    public void closeWindow(){
        driver.close();
    }

    public  void quitWindow(){
        driver.quit();
    }

    public boolean isEnabled(By by){
        return driver.findElement(by).isEnabled();
    }

    public boolean isSelected(By by){
        return driver.findElement(by).isSelected();
    }


    // DropDowns

    public void selectByIndex(By by, int index){
        WebElement element = driver.findElement(by);
        Select select = new Select(element);
        select.selectByIndex(index);

    }

    public void selectByVisibleText(By by, String text){
        WebElement element = driver.findElement(by);
        Select select = new Select(element);
        select.selectByVisibleText(text);

    }

    public void selectByValue(By by, String value){
        WebElement element = driver.findElement(by);
        Select select = new Select(element);
        select.selectByValue(value);

    }


    public List<WebElement> getAllOptionInDropDown(By by){
        WebElement element = driver.findElement(by);
        Select select = new Select(element);
        List<WebElement> options = select.getOptions();

        System.out.println(" the total no.of options are : "+options.size());

        return options;

    }

    public List<WebElement> getList(By by){
        return driver.findElements(by);
       
    }

    // Alerts

    public void acceptAlert(){
        driver.switchTo().alert().accept();
    }

    public void dismissAlert(){
        driver.switchTo().alert().dismiss();
    }

    public String getAlertText(){
        return driver.switchTo().alert().getText();
    }

    public void sendDataToAlert(String text){
        driver.switchTo().alert().sendKeys(text);
    }

    public void sendDataToTextBox(By by, String text){
        driver.findElement(by).sendKeys(text);
    }

    public void click(By by){
        driver.findElement(by).click();
    }

    public void clearTextTextBox(By by){
        driver.findElement(by).clear();
    }

    //frames

    public void switchToFrameByIndex(int index){
        driver.switchTo().frame(index);
    }

    public void switchToFrameByNameOrId(String frameName){
        driver.switchTo().frame(frameName);
    }

    public void switchToFrame(By by){
        WebElement element = driver.findElement(by);
        driver.switchTo().frame(element);
    }

    public void switchToParentFrame(){
        driver.switchTo().parentFrame();
    }

    public void switchToDefaultContent(){
        driver.switchTo().defaultContent();
    }

    // Action class Methods

    public void mouseHover(By by){
        WebElement element = driver.findElement(by);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public void actionClick( By by){
        WebElement element = driver.findElement(by);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();
    }

    public void rightClick(By by){
        WebElement element = driver.findElement(by);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).contextClick().build().perform();
    }

    public void doubleClick( By by){
        WebElement element = driver.findElement(by);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).doubleClick().build().perform();
    }

    public void dragAndDrop( By draggable, By droppable){
        WebElement source = driver.findElement(draggable);
        WebElement destination = driver.findElement(droppable);
        Actions actions = new Actions(driver);
        actions.dragAndDrop(source,destination).build().perform();
    }

    // Java Script Executor Methods

    public static void drawBorder(WebDriver driver, WebElement element){

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].style.border='3px solid red'",element);

    }

    public static void scrollDown(WebDriver driver){

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,1000)");

    }

    public static void scrollTo(WebDriver driver){

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollTo(0,1000)");

    }

    public static void scrollIntoView(WebDriver driver,WebElement element){

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView(true);",element);

    }

    public static void refreshPage(WebDriver driver){
        //log.info("refreshing the page ..........");
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("history.go(0)");

    }

    public static void jsClick(WebDriver driver,WebElement element){

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();",element);

    }

    public static String titleOfPage(WebDriver driver){

        JavascriptExecutor js = (JavascriptExecutor)driver;
        String title = js.executeScript("return document.title;").toString();
        return title;

    }

    public static void sendData(WebDriver driver,WebElement element, String value){

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].value='"+value+"'",element);

    }

    //Take a screenshot

    public void screenShot() {
        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destinationFile = new File(System.getProperty("user.dir") + "/screenshot");
            FileHandler.copy(srcFile, destinationFile);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //Explicit methods
    public void  isClickable(By by){
        WebElement ele = driver.findElement(by);
        WebDriverWait wait = new WebDriverWait(driver, TestUtilites.SMALL_WAIT);
        wait.until(ExpectedConditions.elementToBeClickable(ele));
    }

    public void titleIS(String title){

        WebDriverWait wait = new WebDriverWait(driver,TestUtilites.MEDIUM_WAIT);
        wait.until(ExpectedConditions.titleIs(title));
    }

    public void elementsToBeVisible(By by){
        WebElement ele = driver.findElement(by);
        WebDriverWait wait = new WebDriverWait(driver,TestUtilites.MEDIUM_WAIT);
        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    //14 methods of ExplicitWait , file upload , window handles methods .............

    public boolean isElementPresent(By by){
       // log.info(String.format("verify the Element is present in the DOM %s ::",by));
        boolean ret = false;
        try {
            if (driver.findElements(by).size() > 0) {
                ret = true;
            } else {
                ret = false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return ret;

    }

    public boolean isVisible(By by){
        return driver.findElement(by).isDisplayed();
    }

    public void waitForElementToBeVisible(By by){
        WebDriverWait wait = new WebDriverWait(driver, TestUtilites.MEDIUM_WAIT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));

    }

    public boolean isElementPresentAndVisible(By by){
        boolean isPresentAndVisible = false;

        if(isElementPresent(by) && isVisible(by)){

            isPresentAndVisible = true;
        }

        return isPresentAndVisible;

    }

    public boolean waitForElement(By by) {
        this.waitForElementToBeVisible(by);  //input[@id='email']
        int cnt = 0;
        boolean ret = true;

        while(! isElementPresent(by) || isVisible(by)){

            cnt++;
            ret = false;

            if(cnt==5){
                break;
            }
        }
        return  ret;
    }

    // safe methods ..........

    public void safeClick(By by){
        this.waitForElement(by);
        this.isElementPresentAndVisible(by);
        driver.findElement(by).click();
    }

    public void safeType(By by, String text){
        this.waitForElement(by);
        this.isElementPresentAndVisible(by);
        driver.findElement(by).sendKeys(text);
    }

    public void safeGetValue(By by,String attrName){
        this.waitForElement(by);
        isElementPresentAndVisible(by);
        driver.findElement(by).getAttribute(attrName);
    }

    public String safeGetText(By by){
        this.waitForElement(by);
        isElementPresentAndVisible(by);
        String text = driver.findElement(by).getText();
        return text;
    }

    public void safeSelectByValue(By by,String value){
        this.waitForElement(by);
        isElementPresentAndVisible(by);
        Select select = new Select(driver.findElement(by));
        select.selectByValue(value);

    }
    public void safeSelectByIndex(By by,int index){
        this.waitForElement(by);
        isElementPresentAndVisible(by);
        Select select = new Select(driver.findElement(by));
        select.selectByIndex(index);
    }

    public void safeSelectByVisibleText(By by,String text){
        this.waitForElement(by);
        isElementPresentAndVisible(by);
        Select select = new Select(driver.findElement(by));
        select.selectByVisibleText(text);
    }

    public void safeSwitchToFrameUsingWebElement(By by){
        this.waitForElement(by);
        isElementPresentAndVisible(by);
        driver.switchTo().frame(driver.findElement(by));
    }


    public void safeRightClick(By by){
        this.waitForElement(by);
        isElementPresentAndVisible(by);
        WebElement ele = driver.findElement(by);
        Actions actions = new Actions(driver);
        actions.moveToElement(ele).contextClick().build().perform();
    }

    public void safeDoubleClick(By by){
        this.waitForElement(by);
        isElementPresentAndVisible(by);
        WebElement ele = driver.findElement(by);
        Actions actions = new Actions(driver);
        actions.moveToElement(ele).doubleClick().build().perform();
    }

    public void safeDragAndDrop(By draggable,By droppable){
        this.waitForElement(draggable);
        isElementPresentAndVisible(droppable);
        WebElement source = driver.findElement(draggable);
        WebElement  target = driver.findElement(droppable);
        Actions actions = new Actions(driver);
        actions.dragAndDrop(source,target).build().perform();
    }

    public void safeActionsClick(By by){
        this.waitForElement(by);
        isElementPresentAndVisible(by);
        WebElement ele = driver.findElement(by);
        Actions actions = new Actions(driver);
        actions.moveToElement(ele).click().build().perform();
    }

    public void getScreenShotPath() throws IOException {
       // log.info("Screenshot called........");
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destinationFile = System.getProperty("user.dir") + "\\screenshots\\" + System.currentTimeMillis() + ".png";
        FileHandler.copy(source, new File(destinationFile));

    }

//    public void eventDriverRegister(){
//        eventFiringWebDriver = new EventFiringWebDriver(driver);
//        webEventListener = new WebEventListener(driver);
//
//        eventFiringWebDriver.register(webEventListener);
//
//        driver = eventFiringWebDriver;
    
    
    public int getWebElementSize(By by) {
    	
    int count = 0; 
    	       
    	        try {
    	           count =  driver.findElements(by).size() ;
    	           
    	        }catch (Exception e){
    	            e.printStackTrace();
    	        }

    	        return count;

    	    }
    
	public static String getscreenshot(DefaultWebDriver driver, String screenshotName) throws IOException {
		
		String dateName = new SimpleDateFormat("yyyyMMddhhss").format(new Date());
		TakesScreenshot ss = (TakesScreenshot) driver;
		
		File source = ss.getScreenshotAs(OutputType.FILE);
		
		String destination = System.getProperty("user.dir") +"/FailedTestsScreenshots/" 
		+ screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		
	
		return destination;
	}
    
    
    
    }



