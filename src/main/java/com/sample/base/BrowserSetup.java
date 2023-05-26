package com.sample.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


public class BrowserSetup {
	public static WebDriver driver;
	public static WebDriver getChromeDriver() {
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--disable-notifications*");
	
		driver=new ChromeDriver(options);
		driver.manage().window().maximize();
		//driver.get("https://www.google.com");
		return driver;
	}
	public static WebDriver getFireFoxDriver() {
		return driver;
	}
	public static WebDriver getEdgeDriver() {
		driver=new EdgeDriver();
		driver.manage().window().maximize();
		return driver;
	}
}
