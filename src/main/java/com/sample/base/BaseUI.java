package com.sample.base;
import com.sample.utils.*;

import java.awt.AWTException;
import java.awt.Desktop.Action;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.sample.utils.FileIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseUI{
public static WebDriver driver;
public static Properties prop;
public static WebElement drop;
public static String browser_choice;
public static String TimeStamp=DateUtils.getTimeStamp();
public static ExtentReports report;
public static ExtentTest logger;
public BaseUI() {
	prop=FileIO.initProperties();
}
/*****Invoke Browser****/
public static WebDriver invokeBrowser() {
	browser_choice=prop.getProperty("browserName");
	System.out.println(browser_choice);
	try {
		if(browser_choice.equalsIgnoreCase("chrome")) {
			driver=BrowserSetup.getChromeDriver();
		}else if(browser_choice.equalsIgnoreCase("firefox")) {
			driver=BrowserSetup.getFireFoxDriver();}
		else if(browser_choice.equalsIgnoreCase("edge")) {
			driver=BrowserSetup.getEdgeDriver();}
		else {
			throw new Exception("Invalid broswer name provided in property file");
		}
		}
		catch(Exception e) {
		System.out.println(e.getMessage());	
		}
	return driver;
	}
/*****Navigate To Website URL****/
public static void navigateToUrl(String websiteURL) {
	try {
	driver.get(prop.getProperty(websiteURL));
	}catch(Exception e) {
		e.printStackTrace();
	}
}
/*****Is Element Present****/
public static boolean isElementPresent(By locator,Duration timeout) {
	try {
		new WebDriverWait(driver,timeout).until(ExpectedConditions.presenceOfElementLocated(locator));
		return true;
	}
	catch(Exception e) {
		return false;
	}
}
/*****Send Text****/
public static void sendText(By locator,String text) {
	try {
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.presenceOfElementLocated(locator));
		driver.findElement(locator).sendKeys(text);
	}
	catch(Exception e) {
		e.printStackTrace();
	}
}
/*****Click on****/
public static void clickOn(By locator,Duration timeout) {
	try {
		new WebDriverWait(driver,timeout).until(ExpectedConditions.elementToBeClickable(locator));
		driver.findElement(locator).click();
	}catch(Exception e) {
		e.printStackTrace();
	}
}
/*****Click and hold****/
public static void actionclickandhold(By locator,Duration timeout) {
	try {
		new WebDriverWait(driver,timeout).until(ExpectedConditions.elementToBeClickable(locator));
		Actions a=new Actions(driver);
		a.moveToElement(driver.findElement(locator)).build().perform();
		a.clickAndHold().build().perform();
		//driver.findElement(locator).click();
	}catch(Exception e) {
		e.printStackTrace();
	}
}
/*****Click and hold****/
public static void actionclickOn(By locator,Duration timeout) {
	try {
		new WebDriverWait(driver,timeout).until(ExpectedConditions.elementToBeClickable(locator));
		Actions a=new Actions(driver);
		a.moveToElement(driver.findElement(locator)).build().perform();
		a.click().build().perform();
		driver.findElement(locator).click();
	}catch(Exception e) {
		e.printStackTrace();
	}
}
/*****scrollonce****/
public static void scrollonce() {
	try {
		Actions a=new Actions(driver);
		a.scrollByAmount(3, 100);
			}catch(Exception e) {
		e.printStackTrace();
	}
}
public static void scroll(int count) {
	Robot robot;
	try {
		robot = new Robot();
		robot.mouseWheel(count);
		
	} catch (AWTException e) {
		e.printStackTrace();
	}
	
}
/*****Scroll Up to the bottom of the page*****/
public static void scrollUp() {
	Robot robot;
	try {
		robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_HOME);
		robot.keyRelease(KeyEvent.VK_HOME);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	} catch (AWTException e) {
		e.printStackTrace();
	}
	
}
/*****Press Enter Key*****/
public static void enterkey() {
	Robot robot;
	try {
		robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	} catch (AWTException e) {
		e.printStackTrace();
	}
	
}
/*****Select a particular option from drop down list*****/
public static void dropDownSelect(By locator,String visibleText) {
try {
		
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.presenceOfElementLocated(locator));
		drop=driver.findElement(locator);
		drop.click();
		Select drpDown= new Select(drop);
				drpDown.selectByVisibleText(visibleText);
		
		//drpDown.selectByIndex(6);
	}
catch(Exception e) {
	e.printStackTrace();
}
}
/****Automatically click on all options of drop down list one by one*****/
public static void automate_dropDownList(By clicklocator,By locator) {
	try {
	new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.presenceOfElementLocated(locator));
	
	List<WebElement> droplist=driver.findElements(locator);
	for (WebElement element: droplist) {
		drop=driver.findElement(clicklocator);
		drop.click();
		Select drpDown= new Select(drop);
		drpDown.selectByVisibleText(element.getText());
	}
	}catch(Exception e) {
		e.printStackTrace();
	}
}
public static void dropDownIndex(By locator,int index) {
try {
		
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.presenceOfElementLocated(locator));
		drop=driver.findElement(locator);
		drop.click();
		Select drpDown= new Select(drop);
		drpDown.selectByIndex(index);
	}
catch(Exception e) {
	e.printStackTrace();
}
}
/*****Get Locator Method****/
public static By getLocator(String locatorKey) {
	
	if(locatorKey.endsWith("_id")) {
		System.out.println("id"+prop.getProperty(locatorKey));
		return By.id(prop.getProperty(locatorKey));
		
		
	}
	if(locatorKey.endsWith("_name")) {
		return By.name(prop.getProperty(locatorKey));
	}
	if(locatorKey.endsWith("_className")) {
		return By.className(prop.getProperty(locatorKey));
	}
	if(locatorKey.endsWith("_xpath")) {
		System.out.println("xpath "+prop.getProperty(locatorKey));
		return By.xpath(prop.getProperty(locatorKey));
	}
	if(locatorKey.endsWith("_css")) {
		return By.cssSelector(prop.getProperty(locatorKey));
	}
	if(locatorKey.endsWith("_linkText")) {
		return By.linkText(prop.getProperty(locatorKey));
	}
	if(locatorKey.endsWith("_partialLinkText")) {
		return By.partialLinkText(prop.getProperty(locatorKey));
	}
	if(locatorKey.endsWith("_tagName")) {
		return By.tagName(prop.getProperty(locatorKey));
	}
	return null;
}
}