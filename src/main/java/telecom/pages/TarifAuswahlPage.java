package telecom.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import telecom.util.TestConfiguration;

public class TarifAuswahlPage {
	private WebDriver driver;
	private WebDriverWait wait;
	private TestConfiguration config = new TestConfiguration();
	
	private By headerText = By.xpath("//span[contains(@class, 'brix-basket-offer__headline')]");
	private By offeredTarif = By.xpath("//div[contains(@class, 'brix-offer-price__tariff-info')]/h3");
	private By weiterZumAngebotBtn = By.cssSelector("button[title='zum Warenkorb']");
	
	public TarifAuswahlPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(config.getDriverImplicitWait()));
	}
	
	public String getBasketHeaderText() {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(headerText)));
		return driver.findElement(headerText).getText();
	}
	
	public String getOfferedTarif() {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(offeredTarif)));
		return driver.findElement(offeredTarif).getText();
	}
	
	public WarenkorbPage weiterZumAngebot() {
		driver.findElement(weiterZumAngebotBtn).click();
		return new WarenkorbPage(driver);
	}
	
}
