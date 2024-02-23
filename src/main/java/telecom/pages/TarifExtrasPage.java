package telecom.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;



public class TarifExtrasPage extends BasePage{	
	private By displayedTarifPrice = By.xpath("//tr[contains(@class, 'subscription')]/td[1]");
	private By toCartBtn = By.xpath("//div[@id='connect-more-header']//div[@class='ws10-text-header__button']");
	
	public TarifExtrasPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	@Step("Get displayed tarif name")
	public String getDisplayedTarif() throws Exception {
		return this.findElement(displayedTarifPrice).getText();
	}
	
	@Step("Navigate to cart")
	public CartPage toCart() throws Exception {
		this.clickElement(toCartBtn);
		return new CartPage(driver);
	}
	
}
