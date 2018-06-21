package com.rightmove.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.rightmove.qa.base.TestBase;
import com.rightmove.qa.util.TestUtil;

public class HomePage extends TestBase {

	@FindBy(name = "searchLocation")
	WebElement searchBox;

	@FindBy(id = "buy")
	WebElement forSaleBtn;

	@FindBy(id = "rent")
	WebElement toRentBtn;
	
	@FindBy(xpath = "//h1[contains(text(), 'not found in the UK')]")
	WebElement searchNotFound;
	
	@FindBy(xpath = "//p[contains(text(), 'Waterloo')]")
	WebElement messageOnErrorPage;

	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	public String validateHomePageTitle() {
		return driver.getTitle();
	}

	public void clickOnForSaleBtn() {
		TestUtil.clickMethodJS(forSaleBtn);
	}

	public void clickOnToRentBtn() {
		TestUtil.clickMethodJS(toRentBtn);
	}
	
	public SearchPage forSaleSearch(String enterKeyword) {
		searchBox.sendKeys(enterKeyword);
		TestUtil.clickMethodJS(forSaleBtn);
		return new SearchPage();
	}
	
	public SearchPage toRentSearch(String enterKeyword) {
		searchBox.sendKeys(enterKeyword);
		TestUtil.clickMethodJS(toRentBtn);
		return new SearchPage();
	}
	
	public void invalidForSaleSearchOperation(String enterKeyword) {
		searchBox.sendKeys(enterKeyword);
		TestUtil.clickMethodJS(forSaleBtn);
	}
	
	public void invalidToRentSearchOperation(String enterKeyword) {
		searchBox.sendKeys(enterKeyword);
		TestUtil.clickMethodJS(toRentBtn);
	}
	
	public boolean errorMessage() {
		return messageOnErrorPage.isDisplayed();
	}

}
