package org.sample.stepdefinitions;

import java.io.IOException;



import com.sample.base.BaseUI;
import com.sample.pages.HomePageCrypto;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Crypto extends BaseUI{

	@Given("open browser")
	public void open_browser() {
		driver=BaseUI.invokeBrowser();
	}

	@Given("go to coinmarketcap.com")
	public void go_to_coinmarketcap_com() {
		navigateToUrl("websiteURL");
	}
	@When("Select Rows to show as {int}")
	public void select_rows_to_show_as(Integer int1) {
	  HomePageCrypto homepage=new HomePageCrypto(driver,logger);
	  homepage.selectRows();
	}
	@Then("Pull data from Webpage table to excel {string}")
	public void pull_data_from_webpage_table_to_excel_sheet(String string) throws IOException, InterruptedException {
		HomePageCrypto homepage=new HomePageCrypto(driver,logger);
		homepage.pullDatafromWebtable(string);
	}

	@Then("Close the browser")
	public void close_the_browser() {
	driver.close();
	}

	@Then("Compare results")
	public void compare_results() {
		HomePageCrypto homepage=new HomePageCrypto(driver,logger);
		homepage.compare_NonFiltered_and_Filterd_tables();
	}

	@When("Add Filters")
	public void add_filters() {
		HomePageCrypto homepage=new HomePageCrypto(driver,logger);
		homepage.applyFilter();
	}

}
