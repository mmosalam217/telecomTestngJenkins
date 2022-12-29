package telecom.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import telecom.util.TestConfiguration;

public class SmartphonePage {

	private WebDriver driver;
	private WebDriverWait wait;
	private TestConfiguration config = new TestConfiguration();
	
	private By deviceName = By.id("hardware-details__headline");
	private By color_options = By.xpath("//div[@id='color-picker']//div[contains(@class, 'brix-option-picker__item')]");
	private By capacity_options = By.xpath("//div[@id='capacity-picker']//div[contains(@class, 'brix-option-picker__item')]");
	private By offer_price = By.xpath("//div[contains(@class, 'brix-offer-price__price')]//div[@aria-hidden='true']");
	private By zurTarifAuswhalBtn = By.linkText("Zur Tarifauswahl");
	
	public SmartphonePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(config.getDriverExplicitWait()));

	}
	
	
	public String getDeviceNameFromHeader() {
		return driver.findElement(deviceName).getText();
	}
	
	public void pickColor(String choosen_color) {
		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(color_options)));
		List<WebElement> colors = driver.findElements(color_options);
		for(WebElement color: colors) {
			if(color.getText().contains(choosen_color)) {
				color.click();
				break;
			}
		}
	}
	
	public void pickCapacity(String choosen_capacity) {
		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(capacity_options)));
		List<WebElement> capacities = driver.findElements(capacity_options);
		for(WebElement capacity: capacities) {
			if(capacity.getText().contains(choosen_capacity)) {
				capacity.click();
				break;
			}
		}
	}
	
	public String getOfferPrice() {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(offer_price)));
		return driver.findElement(offer_price).getText();
	}
	
	public TarifAuswahlPage toTarifAuswahl() {
		driver.findElement(zurTarifAuswhalBtn).click();
		return new TarifAuswahlPage(driver);
	}
	
}
