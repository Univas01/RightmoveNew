package com.rightmove.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import java.io.FileNotFoundException;
import org.testng.Assert;
import com.rightmove.qa.base.TestBase;
import com.rightmove.qa.pages.SearchPage;
import com.rightmove.qa.pages.HomePage;
import com.rightmove.qa.util.TestUtil;

public class BuySearchPageTest extends TestBase {

	HomePage homePage;
	SearchPage buySearchPage;
	TestUtil testUtil;

	public BuySearchPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() throws FileNotFoundException {
		initialization();
		homePage = new HomePage();
		testUtil = new TestUtil();
		buySearchPage = homePage.forSaleSearch(prop.getProperty("buySearchBox"));
	}

	@Test(priority = 1, enabled = false)
	public void verifyBuyHeaderTitleTest() {
		/**
		 * Test to verify that 'Property for sale' page has HeaderTitle that contains
		 * 'Property for sale'. This is to indicate Application Under Test have now
		 * landed on 'Property for sale' search page.
		 */
		boolean flag = buySearchPage.verifyBuyHeaderTitle();
		Assert.assertTrue(flag);
	}

	@Test(priority = 2)
	public void findPropertiesTest() {
		/**
		 * Test to perform search operation on 'Property for sale' page. Select all
		 * required fields and then search. When result is displayed, the first
		 * non-featured property is selected which also include verification on
		 * non-featured property page.
		 */
		//buySearchPage.performSearch("radius", "Within 10 miles");
		buySearchPage.performBuyPropertySearch("Within 10 miles", "300,000", "900,000", 
				"1", "3", "Flats / Apartments", "Last 7 days");
		buySearchPage.clickOnSortOrderDDM().selectByVisibleText("Newest Listed");
		buySearchPage.clickOnFirstNonFeaturedProperty();
		String buyTtitle = buySearchPage.verifyFirstNonFeaturedPropertyTitlePage();

		if (buyTtitle.contains("for sale")) {
			System.out.println("First Non-Featured Property successfully loaded");
		} else {
			System.out.println("Unable to load Non-Featured Property");
		}
	}

	@AfterMethod(enabled = true)
	public void tearDown() {
		driver.quit();
	}

}
