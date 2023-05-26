package com.sample.pages;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.sample.base.BaseUI;
import com.sample.utils.*;

import com.sample.utils.WebTableHandling;

import io.netty.channel.AbstractEventLoopGroup;

public class HomePageCrypto extends BaseUI{
	public WebDriver driver;
	public ExtentTest logger;
	public static By filter=getLocator("filter_xpath");
	public static By algorithm=getLocator("algorithm_xpath");
	public static By algdrop=getLocator("algdrop_xpath");
	public static By pow=getLocator("pow_xpath");
	public static By addfilter=getLocator("addfilter_xpath");
	public static By popupoverlay=getLocator("popupoverlay_xpath");
	public static By popupcontent=getLocator("popupcontent_xpath");
	public static By Allcrypto=getLocator("Allcrypto_xpath");
	public static By coins=getLocator("coins_xpath");
	public static By price=getLocator("price_xpath");
	public static By pricetext1=getLocator("pricetext1_xpath");
	public static By pricetext2=getLocator("pricetext2_xpath");
	public static By pricerange=getLocator("pricerange_xpath");
	public static By mineablecheckbox=getLocator("mineablecheckbox_xpath");
	public static By showresults=getLocator("showresults_xpath");
	public static By table=getLocator("table_xpath");
	public static By cookiex=getLocator("cookiex_xpath");
	public static By thx=getLocator("thx_xpath");
	public static By trx=getLocator("trx_xpath");
	public static String tdx1=prop.getProperty("tdx1");
	public static String tdx2=prop.getProperty("tdx2");
	public static By applyfilter=getLocator("applyfilter_xpath");
	public static By numOfRows=getLocator("numOfRows_xpath");
	public static By num20=getLocator("num20_xpath");
	List<String> table1,table2;
	public static int tab1size,tab2size;
	public HomePageCrypto(WebDriver driver,ExtentTest logger) {
	 this.driver=driver;
	 this.logger=logger;
	}
	/**
	 * selectRows() method select number of rows to show in the table available in the Home Page of coinmarket.com website.
	 * By default We are choosing 20 rows out of 3 available options:100,50,20.
	 * */
	public void selectRows() {
		if(isElementPresent(cookiex, Duration.ofSeconds(30)))
			clickOn(cookiex, Duration.ofSeconds(30));
		
		if(isElementPresent(numOfRows, Duration.ofSeconds(30))) 
			clickOn(numOfRows,Duration.ofSeconds(30));
		if(isElementPresent(num20, Duration.ofSeconds(30))) {
		WebElement rowoption=driver.findElement(num20);
		JavascriptExecutor js1=(JavascriptExecutor)driver;
		js1.executeScript("arguments[0].click();", rowoption);
		}
	}
	/**
	 * applyFiler() method select Filters to filter the results from the available table in HomePage.
	 * First Select Algorithm as PoW
	 * Then we are applying Following 3 Filters from Add Filters Option
	 * 1.All CryptoCurrency as Coins Only
	 * 2.Price Range from 10-10000, Apply Filter
	 * 3.Enable Mineable Option
	 * After clicking Show Results in Filter Page the table in the Home page will be updated 
	 * */
	public void applyFilter() {
		driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
		if(isElementPresent(cookiex, Duration.ofSeconds(30)))
			clickOn(cookiex, Duration.ofSeconds(30));

		if(isElementPresent(filter, Duration.ofSeconds(30)))
		clickOn(filter, Duration.ofSeconds(30));
		actionclickandhold(filter, Duration.ofSeconds(30));
		WebElement alg=driver.findElement(algorithm);
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(alg));
		if(isElementPresent(algorithm, Duration.ofSeconds(30))) {
			actionclickandhold(algorithm, Duration.ofSeconds(30));
			enterkey();
			WebElement algoption=driver.findElement(pow);
			new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(algoption));
			if(isElementPresent(pow, Duration.ofSeconds(30))) {
			JavascriptExecutor js3=(JavascriptExecutor)driver;
			js3.executeScript("arguments[0].click();",algoption);
			}
			}
			
		if(isElementPresent(addfilter, Duration.ofSeconds(30))) {
			clickOn(addfilter, Duration.ofSeconds(30));
			if(isElementPresent(popupcontent, Duration.ofSeconds(30))){
			if(isElementPresent(Allcrypto,Duration.ofSeconds(30))) {
				clickOn(Allcrypto,Duration.ofSeconds(30));
				if(isElementPresent(coins, Duration.ofSeconds(30)))
					clickOn(coins, Duration.ofSeconds(30));
				}
			if(isElementPresent(mineablecheckbox,Duration.ofSeconds(30))) {
				WebElement min=driver.findElement(mineablecheckbox);
				JavascriptExecutor js3=(JavascriptExecutor)driver;
				js3.executeScript("arguments[0].click();",min);
				actionclickandhold(mineablecheckbox, Duration.ofSeconds(30));
			}
				if(isElementPresent(price,Duration.ofSeconds(30))) {
					actionclickandhold(price, Duration.ofSeconds(30));
					clickOn(price,Duration.ofSeconds(30));
					if(isElementPresent(pricetext1, Duration.ofSeconds(30)))
					sendText(pricetext1, "10");
					sendText(pricetext2, "10000");
					clickOn(applyfilter, Duration.ofSeconds(30));
			}
				
				
				if(isElementPresent(showresults, Duration.ofSeconds(30)))
					clickOn(showresults, Duration.ofSeconds(30));
			}
			scrollUp();
		}
	}
	/**
	 * pullDatafromWebtable(String Sheetname) method pulls data from given WebTable in to a List<String>
	 * then send the data,sheetname,rowcount,colcount to the WebTableHandling.webDataToExcel() method
	 * to save data in the corresponding excel sheet.
	 * */
	public List<String> pullDatafromWebtable(String Sheetname) throws IOException, InterruptedException {
		int rowcount,colcount;
		driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
		List<WebElement> tr=driver.findElements(trx);
		rowcount=tr.size();
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(driver.findElement(thx)));
		List<WebElement> th=driver.findElements(thx);
		colcount=th.size();
		List<String> column_values = new ArrayList<>();
		for (int h = 0; h < th.size(); h++) {
			column_values.add(th.get(h).getText());
		}
		for(int i=0;i<rowcount;i++) {
			new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(tdx1+(i+1)+"]"))));
			List<WebElement> td=driver.findElements(By.xpath(tdx1+(i+1)+tdx2));
			for (int k = 0; k < td.size(); k++) {
				column_values.add(td.get(k).getText());
			}	
		}
		String time=BaseUI.TimeStamp;
		WebTableHandling.webDataToExcel(time,column_values,rowcount,colcount,Sheetname);
		if(Sheetname.equalsIgnoreCase("Sheet9")) {
			setTable1(column_values);
		}
		else if (Sheetname.equalsIgnoreCase("Sheet10")) {
			setTable2(column_values);
		}
		return column_values;
		
	}
	/**Sets Table 1 List*/
	public void setTable1(List<String> table1) {
		this.table1=table1;
		tab1size=table1.size();
	}
	/**Sets Table 2 List*/
	public void setTable2(List<String> table2) {
		this.table2=table2;
		tab2size=table2.size();
	}
	/**Compare Non-Filtered Table elements with Filtered Table Elements*/
	public void compare_NonFiltered_and_Filterd_tables() {
		System.out.println("Table without Filters contains "+tab1size+" elements");
		System.out.println("Table with Filters contains "+tab2size+" elements");
	}
}


