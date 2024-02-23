package telecom.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;




public class TarifAuswahlPage extends BasePage{
	private By headerText = By.xpath("//div[@id='item-summary-card']//h4");
	private By offeredTarif = By.xpath("//div[@id=\"tariff-details-offer-price\"]//h3");
	private By weiterZumAngebotBtn = By.xpath("//div[@id=\"tariff-details-offer-price\"]//button");
	
	public TarifAuswahlPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	@Step("Get displayed header text in basket")
	public String getBasketHeaderText() throws Exception {
		return this.findElement(headerText).getText();
	}
	
	@Step("Get offered tariff name")
	public String getOfferedTarif() throws Exception {
		return this.findElement(offeredTarif).getText();
	}
	
	@Step("Click 'Weiter zum Angebot'")
	public TarifExtrasPage weiterZumAngebot() throws Exception {
		this.clickElement(weiterZumAngebotBtn);
		return new TarifExtrasPage(driver);
	}
	
}
