package telecom.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;
import telecom.util.Log;

public class BasePage {
	protected WebDriver driver;
	protected WebDriverWait wait;
	
	BasePage(WebDriver driver){
		this.driver = driver;
		this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(20));
	}
	
	public WebElement findElement(By by) throws Exception {
		int max_retries = 3 ;
		int retries = 0;
		WebElement el = null;
		while(retries < max_retries) {
			try {
				this.wait.until(ExpectedConditions.presenceOfElementLocated(by));
				el = this.driver.findElement(by);
				break;
			}catch(StaleElementReferenceException e) {
				retries++;
				if(retries > max_retries) {
					throw e;
				}
				Log.debug("Stale element, retry again");
			
			}
		}

		return el;
	}
	
	public Boolean isElementVisible(By by) {
		Boolean isVisible = false;
		try {
			this.wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			isVisible = true;
			Log.debug("Element is visible: " + by.toString());
		}catch(TimeoutException e) {
			// Log exception message here
			Log.debug("Element is not visible: " + by.toString());
		}
		return isVisible;
		
	}
	
	public Boolean isElementClickable(By by) {
		Boolean isClickable = false;
		try {
			this.wait.until(ExpectedConditions.elementToBeClickable(by));
			isClickable = true;
			Log.debug("Element is clickable: " + by.toString());
		}catch(TimeoutException e) {
			// Log exception message here
			Log.debug("Element is not clickable: " + by.toString());
		}
		return isClickable;
	}
	
	public WebElement moveToElement(By by) throws Exception {
		WebElement el = this.findElement(by);
		Actions actions = new Actions(this.driver);
		actions
		.moveToElement(el)
		.build()
		.perform();
		return el;
	}
	
	public WebElement clickElement(By by) throws Exception {
		WebElement el = null;
		try {
			Log.info("Click Element Natively: " + by.toString());
			el = this.findElement(by);
			el.click();
		
		}catch(StaleElementReferenceException e) {
			this.findElement(by).click();
		}catch(ElementClickInterceptedException e) {
			Log.debug("Clicking Element using a click action failed. Trying with javascript");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", el);
			js.executeScript("arguments[0].click();", el);
		}catch(Exception e) {
			Log.error(e.getMessage());
			throw new Exception("Could not click element with error: " + e.getMessage());
		}
		return el;

	}
	
	@Step("Handle cookies")
	public void handleCookies(By cookieLocator, By acceptLocator, By declineLocator, Boolean shouldAccept) throws Exception {
		// First check if the cookie dialog is displayed
		if(this.isElementVisible(cookieLocator)) {
			// Accept or decline cookie based on the shouldAccept flag
			Log.info("Cookie dialog intercepted..");
			if(shouldAccept) {
				Log.info("Accepting cookies..");
				this.clickElement(acceptLocator);
			}else {
				Log.info("Decline cookiees");
				this.clickElement(declineLocator);
			}
			// Wait until the dialog is done before proceeding to another action
			this.wait.until(ExpectedConditions.invisibilityOfElementLocated(cookieLocator));
			Log.info("Cookies handeled successfully");
		}else {
			// log cookie state
			Log.info("No cookie dialog interecepted, moving forward..");
		}
	}
	
	public WebElement scrollToMe(By by) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement el = this.findElement(by);
		js.executeScript("arguments[0].scrollIntoView(true);", el);
		return el;
	}
	
	public Boolean elementHasClass(WebElement el, String clsName) {
		String classList = el.getAttribute("class");
		for(String cls: classList.split(" ")) {
			if(cls.equals(clsName)) return true;
		}
		return false;
	}

}
