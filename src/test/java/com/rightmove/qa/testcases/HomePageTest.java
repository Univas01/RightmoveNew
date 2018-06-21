package com.rightmove.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import com.rightmove.qa.base.TestBase;
import com.rightmove.qa.pages.SearchPage;
import com.rightmove.qa.pages.HomePage;
import com.rightmove.qa.util.TestUtil;

public class HomePageTest extends TestBase {
	HomePage homePage;
	SearchPage buySearchPage;
	TestUtil testUtil;

	public HomePageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		testUtil = new TestUtil();
		homePage = new HomePage();
	}

	@Test(priority = 1)
	public void homePageTitleTest() {
		// Test to verify that Application Under Test is currently on Rightmove page.
		String title = homePage.validateHomePageTitle();
		Assert.assertEquals(title, "Rightmove - UK's number one property website for properties for sale and to rent");
	}

	@Test(priority = 2)
	public void forSaleAlertPopupTest() {
		// Test to verify that Alert Pop-up can be handled when user performs an empty search on 'For Sale' button.
		homePage.clickOnForSaleBtn();
		testUtil.switchToAlert();
		Assert.assertEquals(testUtil.validateAlertPopupText(), "Please enter an area, postcode or train station.");
	}

	@Test(priority = 3)
	public void toRentAlertPopupTest() {
		// Test to verify that Alert Pop-up can be handled when user performs an empty search on 'To Rent' button.
		homePage.clickOnToRentBtn();
		testUtil.switchToAlert();
		Assert.assertEquals(testUtil.validateAlertPopupText(), "Please enter an area, postcode or train station.");
		testUtil.acceptPopupAlert();
	}
	
	@Test(priority = 4)
	public void invalidForSaleSearchOperationTest() {
		// Test to verify user is re-directed to 'Page Not Found' when user clicks on 'For Sale' button with either incorrect location or post code.
		homePage.invalidForSaleSearchOperation(prop.getProperty("invalidSearch"));
		Assert.assertTrue(homePage.errorMessage());
	}
	
	@Test(priority = 4)
	public void invalidToRentSearchOperationTest() {
		// Test to verify user is re-directed to 'Page Not Found' when user clicks on 'To Rent' button with either incorrect location or post code.
		homePage.invalidToRentSearchOperation(prop.getProperty("invalidSearch"));
		Assert.assertTrue(homePage.errorMessage());
	}

	@AfterMethod(enabled = true)
	public void tearDown() {
		driver.quit();
	}

}
