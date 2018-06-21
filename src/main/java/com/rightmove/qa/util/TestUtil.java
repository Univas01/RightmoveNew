package com.rightmove.qa.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.rightmove.qa.base.TestBase;

public class TestUtil extends TestBase {

	public static long PAGE_LOAD_TIMEOUT = 60;
	public static long IMPLICIT_WAIT = 60;

	public void switchToAlert() {
		driver.switchTo().alert();
	}

	public String validateAlertPopupText() {
		return driver.switchTo().alert().getText();
	}

	public void acceptPopupAlert() {
		driver.switchTo().alert().accept();
	}

	public static void scrollIntoView(WebElement element, WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
    	js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public static void clickMethodJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
    	js.executeScript("arguments[0].click();", element);
	}
}
