package telecom.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;
import telecom.util.TestConfiguration;

public class VFHomePage {
	
	private WebDriver driver;
	private TestConfiguration config = new TestConfiguration();
	private WebDriverWait wait;
	
	private By accept_cookies_btn = By.xpath("//button[contains(@class, 'dip-consent-main-text-btn')]");
	private By main_nav_mobilfunk = By.linkText("Mobilfunk");
	private By sec_nav_mobilfunk = By.xpath("//ul[contains(@class, 'list-nav-sec')][1]");
	private By smartphone_submenu = By.xpath("//div[contains(@class, 'nav-secondary-sub nav-display')]");
	private By mobilfunk_smartphones = By.linkText("Smartphones");
	

	
	public VFHomePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(config.getDriverExplicitWait()));
	}
	
	@Step("Accept Cookies")
	public void acceptCookies() {
		var acceptBtn = driver.findElement(accept_cookies_btn);
		wait.until(ExpectedConditions.visibilityOf(acceptBtn));
		acceptBtn.click();
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
