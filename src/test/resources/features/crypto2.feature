
Feature: Cryptocurrency Automation
Background: Opening Browser and webpage
		Given open browser
    And go to coinmarketcap.com
  @smoketest
  Scenario: CryptoCurrency HomePage Without Filters
   
    When Select Rows to show as 20
    Then Pull data from Webpage table to excel "Sheet9"
    Then Close the browser


    @smoketest
  Scenario: CryptoCurrency HomePage With Filters
		When Add Filters
    And Pull data from Webpage table to excel "Sheet10"
    Then Compare results
    Then Close the browser