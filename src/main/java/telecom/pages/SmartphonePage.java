package telecom.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import telecom.util.PageUtil;
import telecom.util.TestConfiguration;

public class SmartphonePage {

	private WebDriver driver;
	private WebDriverWait wait;
	private TestConfiguration config = new TestConfiguration();
	
	private By deviceName = By.id("hardware-details__headline");
	private By capacity_options = By.xpath("//div[@id='capacity-picker']//div[contains(@class, 'brix-option-picker__item')]");
	private By offer_price = By.xpath("//div[contains(@class, 'brix-offer-price__price')]//div[@aria-hidden='true']");
	private By zurTarifAuswhalBtn = By.linkText("Zur Tarifauswahl");
	
	public SmartphonePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(config.getDriverExplicitWait()));

	}
	
	@Step("Get device name from header")
	public String getDeviceNameFromHeader() {
		String title =  driver.findElement(deviceName).getText();
		PageUtil.takeScreenshot(driver);
		return title;
	}
	
	@Step("Choose color")
	public void pickColor(String choosen_color) {
		String option_selector = "//label[@for='option-picker--color-picker--item--" + choosen_color + "']";
		By color_option = By.xpath(option_selector);
		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(color_option)));
		WebElement color = driver.findElement(color_option);
		color.click();
		
	}
	
	@Step("Check Capacity")
	public void pickCapacity(String choosen_capacity) {
		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(capacity_options)));
		List<WebElement> capacities = driver.findElements(capacity_options);
		for(WebElement capacity: capacities) {
			if(capacity.getText().contains(choosen_capacity)) {
				PageUtil.takeScreenshot(this.driver);
				capacity.click();
				break;
			}
		}
	}
	
	@Step("Get offer price")
	public String getOfferPrice() {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(offer_price)));
		return driver.findElement(offer_price).getText();
	}
	
	@Step("Go to TarifAuswahl page")
	public TarifAuswahlPage toTarifAuswahl() {
		driver.findElement(zurTarifAuswhalBtn).click();
		PageUtil.takeScreenshot(driver);
		return new TarifAuswahlPage(driver);
	}
	
}
