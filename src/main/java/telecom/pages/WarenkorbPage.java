package telecom.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import telecom.util.TestConfiguration;

public class WarenkorbPage {
	private WebDriver driver;
	private WebDriverWait wait;
	private TestConfiguration config = new TestConfiguration();
	
	private By displayedTarifPrice = By.xpath("//tr[contains(@class, 'subscription')]/td[1]");
	private By toCartBtn = By.id("nsf-cost-overview-buttons-wrap");
	
	public WarenkorbPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(config.getDriverImplicitWait()));
	}
	
	
	public String getDisplayedTarif() {
		return wait.until(ExpectedConditions.visibilityOf(driver.findElement(displayedTarifPrice))).getText();
	}
	
	public CartPage toCart() {
		driver.findElement(toCartBtn).click();
		return new CartPage(driver);
	}
	
}
