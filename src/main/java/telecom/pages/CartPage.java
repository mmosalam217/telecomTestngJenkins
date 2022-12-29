package telecom.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import telecom.util.TestConfiguration;

public class CartPage {
	private WebDriver driver;
	private WebDriverWait wait;
	private TestConfiguration config = new TestConfiguration();
	
	private By cartProduct = By.cssSelector(".basket-card[data-propid=\"1\"] .brix-basket-offer__headline");
	private By einmalPriceInBasket = By.xpath("//div[contains(@class, 'basket-card') and @data-propid='1']//li[contains(@class, 'brix-basket-offer__price-block')][2]//span[contains(@class, 'brix-basket-offer__price-regular')]");
	private By overlay = By.cssSelector(".mod.mod-mph-flex-anchor");
	private By weiterOhneLoginBtn = By.xpath("//button[contains(., 'Weiter ohne Login')]");
	private By salutation_man = By.xpath("//label[@for='SALUTATION-1']");
	private By salutation_woman = By.xpath("//label[@for='SALUTATION-0']");
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
	private By nationalityDDL = By.name("nationality");
	private By kundenkennwort = By.name("kundenkennwort");
	private By persondata_weiterBtn = By.cssSelector("#formPersData button[name ='submit-1']");
	private By identVideo = By.xpath("//label[@for='IDENTIFICATION-0']");
	private By identShop = By.xpath("//label[@for='IDENTIFICATION-1']");
	private By identdata_weiterBtn = By.cssSelector("#formIdentData button[name ='submit-1']");
	private By storeSearchInput = By.name("storeSearchLocation");
	private By storeSearchInputSpinner = By.xpath("//div[contains(@class, 'storeSearchInputContainer')]/div[@class='loading']");
	private By storeSearchInputMagnifier = By.xpath("//div[contains(@class, 'storeSearchInputContainer')]/a[contains(@class,'magnifier')]");
	private By storeSearchMap = By.cssSelector("#map iframe");
	private By storeListContainer = By.className("storeListContainer");
	private By storeListItems = By.className("storeListItem");
	private By firstStoreSearchResult = By.xpath("//div[contains(@class, 'storeListItem ')][1]//label");
	private By shipdata_weiterBtn = By.cssSelector("#formShipData button[name ='submit-1']");
	
	public CartPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(config.getDriverExplicitWait()));
	}
	
	public String getProductName() {
		return wait.until(ExpectedConditions.visibilityOf(driver.findElement(cartProduct))).getText();
	}
	
	public String getEinmalPriceInBasket() {
		return driver.findElement(einmalPriceInBasket).getText();
	}
	
	public void clickWeiterOhneLogin() throws InterruptedException {
		Thread.sleep(10000);;
		driver.findElement(weiterOhneLoginBtn).click();
	}
	
	public void enterPersonData(String first_name, String last_name, 
			String zipcode, String city_name, String street_name, 
			String house_number, String phone_number, String birthday, 
			String birthmonth, String birthyear, String land, String password, String email_address) {
		
		driver.findElement(salutation_man).click();
		driver.findElement(firstname).sendKeys(first_name);
		driver.findElement(lastname).sendKeys(last_name);
		driver.findElement(zip).sendKeys(zipcode);
		driver.findElement(city).sendKeys(city_name);
		driver.findElement(street).sendKeys(street_name);
		driver.findElement(houseNumber).sendKeys(house_number);
		driver.findElement(email).sendKeys(email_address);
		driver.findElement(phone).sendKeys(phone_number);
		driver.findElement(birth_day).sendKeys(birthday);
		driver.findElement(birth_month).sendKeys(birthmonth);
		driver.findElement(birth_year).sendKeys(birthyear);
		Select nationality = new Select(driver.findElement(nationalityDDL));
		nationality.selectByValue(land);
		driver.findElement(kundenkennwort).sendKeys(password);
		driver.findElement(persondata_weiterBtn).click();

	}
	
	public void chooseIdentMethod(String method) {
		if(method.equalsIgnoreCase("video")) {
			driver.findElement(identVideo).click();
		}else {
			driver.findElement(identShop).click();
		}
		driver.findElement(identdata_weiterBtn).click();
	}
	
	public void searchStore(String store_zip) {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(storeSearchMap)));
		driver.findElement(storeSearchInput).sendKeys(store_zip);
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(storeSearchInputSpinner)));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(storeSearchInputMagnifier));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(storeListContainer)));
		js.executeScript("arguments[0].click();", driver.findElement(firstStoreSearchResult));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(shipdata_weiterBtn)));
		driver.findElement(shipdata_weiterBtn).click();
		
	}
	
}
