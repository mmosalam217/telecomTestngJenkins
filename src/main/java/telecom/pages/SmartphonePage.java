package telecom.pages;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import io.qameta.allure.Step;
import telecom.util.PageUtil;

public class SmartphonePage extends BasePage{

	
	private By deviceName = By.id("device-details-text-header");
	private By offer_price = By.xpath("//div[@data-offer-price-text='Angebotsprice']");
	private By zurTarifAuswhalBtn = By.linkText("Zur Tarifauswahl");
	
	public SmartphonePage(WebDriver driver) {
		super(driver);
		this.driver = driver;

	}
	
	@Step("Get device name from header")
	public String getDeviceNameFromHeader() throws Exception {
		String title =  this.findElement(deviceName).getText();
		PageUtil.takeScreenshot(driver);
		return title;
	}
	
	@Step("Choose color")
	public void pickColor(String choosen_color) throws Exception {
		String option_selector = "//div[@id='device-details-option-picker-color']//label[contains(.,'" + choosen_color + "')]";
		By color_option = By.xpath(option_selector);
		this.clickElement(color_option);		
	}
	
	@Step("Check Capacity")
	public void pickCapacity(String choosen_capacity) throws Exception {
		String capacity_selector = "//div[@id='device-details-option-picker-capacity']//label[contains(.,'" + choosen_capacity + "')]";
		By capacity_option = By.xpath(capacity_selector);
		this.clickElement(capacity_option);
	}
	
	@Step("Get offer price")
	public String getOfferPrice() throws Exception {
		return this.findElement(offer_price).getText();
	}
	
	@Step("Go to TarifAuswahl page")
	public TarifAuswahlPage toTarifAuswahl() throws Exception {
		this.clickElement(zurTarifAuswhalBtn);
		PageUtil.takeScreenshot(driver);
		return new TarifAuswahlPage(driver);
	}
	
}
