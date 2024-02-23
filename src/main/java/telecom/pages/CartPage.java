package telecom.pages;



import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.qameta.allure.Step;
import telecom.util.Log;

public class CartPage extends BasePage{
	private By cartProduct = By.cssSelector(".basket-card[data-propid=\"1\"] .brix-basket-offer__headline");
	private By einmalPriceInBasket = By.xpath("//div[contains(@class, 'basket-card') and @data-propid='1']//li[contains(@class, 'brix-basket-offer__price-block')][2]//span[contains(@class, 'brix-basket-offer__price-regular')]");
	//private By overlay = By.cssSelector(".mod.mod-mph-flex-anchor");
	private By weiterOhneLoginBtn = By.xpath("//button[contains(., 'Weiter ohne Login')]");
	private By salutation_man = By.xpath("//label[@for='SALUTATION-1']");
	//private By salutation_woman = By.xpath("//label[@for='SALUTATION-0']");
	private By firstname = By.name("firstname");
	private By lastname = By.name("lastname");
	private By zip = By.name("zip");
	private By city = By.name("city");
	private By street = By.name("street");
	private By houseNumber = By.name("housenumber");
	private By email = By.name("email");
	private By phone = By.name("phone");
	private By birth_day = By.name("day");
	private By birth_month = By.name("month");
	private By birth_year = By.name("year");
	//private By nationality = By.name("nationality");
	private By kundenkennwort = By.name("kundenkennwort");
	private By persondata_weiterBtn = By.xpath("//*[@id='formPersData']//button[contains(., 'weiter')]");
	private By identVideo = By.xpath("//label[@for='IDENTIFICATION-0']");
	private By identShop = By.xpath("//label[@for='IDENTIFICATION-1']");
	private By identdata_weiterBtn = By.cssSelector("#formIdentData button[name ='submit-1']");
	private By storeSearchInput = By.name("storeSearchLocation");
	private By storeSearchInputSpinner = By.xpath("//div[contains(@class, 'storeSearchInputContainer')]/div[@class='loading']");
	private By storeSearchInputMagnifier = By.xpath("//div[contains(@class, 'storeSearchInputContainer')]/a[contains(@class,'magnifier')]");
	private By storeListContainer = By.className("storeListContainer");
	//private By storeListItems = By.className("storeListItem");
	private By firstStoreSearchResult = By.xpath("//div[contains(@class, 'storeListItem ')][1]//label");
	private By shipdata_weiterBtn = By.cssSelector("#formShipData button[name ='submit-1']");
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	@Step("Get product name")
	public String getProductName() throws Exception {
		return this.findElement(cartProduct).getText();
	}
	
	@Step("Get price in basket")
	public String getEinmalPriceInBasket() throws Exception {
		return this.findElement(einmalPriceInBasket).getText();
	}
	
	@Step("Click 'Weiter ohne Login'")
	public void clickWeiterOhneLogin() throws Exception {
		this.clickElement(weiterOhneLoginBtn);
	}
	
	@Step("Choose nationality from dropdown")
	public void choose_nationality(String nationality) throws Exception {
		By nationality_field = By.xpath("//div[@nationality-dropdown]");
		By ddl_container = By.xpath("//div[@nationality-dropdown]//*[@class='select-options']");
		String opt_selector = String.format("//div[@nationality-dropdown]//*[@class='select-options']//a[contains(., '%s')]", nationality);
		By option = By.xpath(opt_selector);
		this.clickElement(nationality_field);
		this.wait.until(ExpectedConditions.visibilityOfElementLocated(ddl_container));
		this.clickElement(option);
		
	}
	
	@Step("Dismiss saving address alert if displayed")
	public boolean bypass_address_alert() {
		boolean alert_present = false;
		try {
			this.wait.until(ExpectedConditions.alertIsPresent());
			Alert address_alert = this.driver.switchTo().alert();
			address_alert.dismiss();
			alert_present = true;
		}catch(TimeoutException e){
			Log.debug("Address alert is not present.");
			alert_present = false;
		}
		return alert_present;
	}
	
	@Step("Fill person details")
	public void enterPersonData(String first_name, String last_name, 
			String zipcode, String city_name, String street_name, 
			String house_number, String phone_number, String birthday, 
			String birthmonth, String birthyear, String land, String password, String email_address) throws Exception {
		
		this.clickElement(salutation_man);
		this.findElement(firstname).sendKeys(first_name);
		this.findElement(lastname).sendKeys(last_name);
		this.findElement(zip).sendKeys(zipcode);
		this.findElement(city).sendKeys(city_name);
		this.findElement(street).sendKeys(street_name);
		this.findElement(houseNumber).sendKeys(house_number);
		this.findElement(email).sendKeys(email_address);
		this.clickElement(phone);
		this.findElement(phone).sendKeys(phone_number);
		this.findElement(birth_day).sendKeys(birthday);
		this.findElement(birth_month).sendKeys(birthmonth);
		this.findElement(birth_year).sendKeys(birthyear);
		this.choose_nationality(land);
		this.findElement(kundenkennwort).sendKeys(password);
		this.clickElement(persondata_weiterBtn);
		this.bypass_address_alert();
		this.clickElement(persondata_weiterBtn);
		

	}
	
	@Step("Choose identification method")
	public void chooseIdentMethod(String method) throws Exception {
		this.bypass_address_alert();
		if(method.equalsIgnoreCase("video")) {
			this.clickElement(this.identVideo);
		}else {
			this.clickElement(this.identShop);
		}
		this.clickElement(identdata_weiterBtn);
	}
	
	@Step("Search for stores nearby")
	public void searchStoreNearBy() throws Exception {
		this.bypass_address_alert();
		wait.until(ExpectedConditions.visibilityOfElementLocated(storeSearchInputMagnifier));
		this.clickElement(storeSearchInputMagnifier);
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(storeSearchInputSpinner)));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(storeListContainer)));
		this.clickElement(firstStoreSearchResult);
		this.clickElement(shipdata_weiterBtn);
		
	}
	
	@Step("Search for stores by zip")
	public void searchStoreByZip(String store_zip) throws Exception {
		this.bypass_address_alert();
		this.findElement(storeSearchInput).sendKeys(store_zip);
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(storeSearchInputSpinner)));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(storeListContainer)));
		this.clickElement(firstStoreSearchResult);
		this.clickElement(shipdata_weiterBtn);
		
	}
	
}
