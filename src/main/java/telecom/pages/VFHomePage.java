package telecom.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.qameta.allure.Step;

public class VFHomePage extends BasePage{
	private By cookies_dialog = By.id("dip-consent");
	private By accept_cookies_btn = By.xpath("//button[contains(@class, 'dip-consent-main-text-btn')]");
	private By decline_cookies_btn = By.id("dip-consent-summary-reject-all");
	private By main_nav_mobilfunk = By.linkText("Mobilfunk");
	private By sec_nav_mobilfunk = By.xpath("//ul[contains(@class, 'list-nav-sec')][1]");
	private By smartphone_submenu = By.xpath("//div[contains(@class, 'nav-secondary-sub nav-display')]");
	private By mobilfunk_smartphones = By.linkText("Handy mit Vertrag");
	
	
	public VFHomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	@Step("Accept Cookies")
	public void acceptCookies() throws Exception {
		this.handleCookies(cookies_dialog, accept_cookies_btn, decline_cookies_btn, true);
	}
	
	@Step("Navigate to smartphone page")
	public SmartphonePage navigateToSmartphone(String smartphone_model) {
		WebElement mobilfunk = driver.findElement( main_nav_mobilfunk);
		Actions actions = new Actions(driver);
		actions.moveToElement(mobilfunk).perform();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(sec_nav_mobilfunk)));
		actions.moveToElement(driver.findElement(mobilfunk_smartphones)).perform();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(smartphone_submenu)));
		actions.moveToElement(driver.findElement(By.linkText(smartphone_model)))
				.click()
				.perform();
		return new SmartphonePage(driver);
	}
	


}
