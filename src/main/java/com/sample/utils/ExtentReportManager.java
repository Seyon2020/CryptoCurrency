package com.sample.utils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.sample.base.*;
public class ExtentReportManager extends BaseUI{
	public static ExtentReports report;
	public static ExtentSparkReporter htmlReporter;
	public static ExtentReports getReportInstance() {
		String repName="TestReport"+BaseUI.TimeStamp+".html";
		htmlReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"/TestOutput/"+repName);
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("CryptoCurrency-coinmarketcap Report");
		htmlReporter.config().setTheme(Theme.DARK);
		report=new ExtentReports();
		report.attachReporter(htmlReporter);
		return report;
	}
}
