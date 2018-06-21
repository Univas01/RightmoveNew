package com.rightmove.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.rightmove.qa.base.TestBase;
import com.rightmove.qa.pages.HomePage;
import com.rightmove.qa.pages.RentSearchPage;
import com.rightmove.qa.pages.SearchPage;
import com.rightmove.qa.util.TestUtil;

public class RentSearchPageTest extends TestBase {

	HomePage homePage;
	RentSearchPage rentSearchPage;
	TestUtil testUtil;
	String rentTitle;

	public RentSearchPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		homePage = new HomePage();
		testUtil = new TestUtil();
		rentSearchPage = homePage.toRentSearch(prop.getProperty("rentSearchBox"));
	}

	@Test(priority = 1)
	public void verifyRentHeaderTitleTest() {
		/**
		 * Test to verify that 'Property to rent' page has HeaderTitle that contains
		 * 'Property to rent'. This is to indicate that Application Under Test have now
		 * landed on 'Property to rent' search page.
		 */
		boolean flag = rentSearchPage.verifyRentHeaderTitle();
		Assert.assertTrue(flag);
	}

	@Test(priority = 2)
	public void findPropertiesTest() {
		/**
		 * Test to perform search operation on 'Property to rent' page. Select all
		 * required fields and then search. When result is displayed, the first
		 * non-featured property is selected which also include verification on
		 * non-featured property page.
		 */
		rentSearchPage.clickOnSearchRadiusDDM("radius", "Within 10 miles");
		rentSearchPage.clickOnMinPriceRangeDDM("minPrice", "1,000 PCM");
		rentSearchPage.clickOnMaxPriceRangeDDM("maxPrice", "2,500 PCM");
		rentSearchPage.clickOnMinBedroomsDDM("minBedrooms", "2");
		rentSearchPage.clickOnMaxBedroomsDDM("maxBedrooms", "4");
		rentSearchPage.clickOnSearchRadiusDDM("displayPropertyType", "Flats / Apartments");
		rentSearchPage.offerCheckBox();
		rentSearchPage.clickOnFindPropertiesBtn();
		rentSearchPage.clickOnSortOrderDDM().selectByVisibleText("Newest Listed");
		rentSearchPage.clickOnFirstNonFeaturedProperty();
		rentTitle = rentSearchPage.verifyFirstNonFeaturedPropertyTitlePage();
		if (rentTitle.contains("to rent")) {
			System.out.println("First Non-Featured Property successfully loaded");
		} else {
			System.out.println("Unable to load Non-Featured Property");
		}
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
