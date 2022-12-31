package telecom.mobilfunk;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import telecom.pages.CartPage;
import telecom.pages.TarifAuswahlPage;
import telecom.pages.VFHomePage;
import telecom.pages.WarenkorbPage;

public class SmartphoneTest extends BaseTest {
	private VFHomePage homePage;
	
	@BeforeClass()
	public void init() {
		homePage = new VFHomePage(driver);
	}

	@Test(description="Test e2e smartphone order process")
	@Parameters({"model", "color", "capacity", "personFirstname", "personLastname", 
		"zip", "city", "street", "house", "birthday", "birthmonth", "birthyear", 
		"email", "phone", "nationality", "kennwort", "ident"})
	public void test_smartphone_order_e2e(String model, String color, String capacity, 
									String personFirstname, String personLastname, String zip, 
									String city, String street, String house, 
									String birthday, String birthmonth, String birthyear, 
									String email, String phone, String nationality, String kennwort, String ident) throws InterruptedException  {
		homePage.acceptCookies();
		var smartphonePage = homePage.navigateToSmartphone(model);
		Assert.assertTrue(smartphonePage.getDeviceNameFromHeader().contains(model));
		smartphonePage.pickColor(color);
		smartphonePage.pickCapacity(capacity);
		String offerPrice = smartphonePage.getOfferPrice();
		TarifAuswahlPage tarifauswahlPage = smartphonePage.toTarifAuswahl();
		
		String auswahl_header = tarifauswahlPage.getBasketHeaderText();
		Assert.assertEquals(auswahl_header, String.format("%s | %s | %s", model, color, capacity));
		String offeredTarif = tarifauswahlPage.getOfferedTarif();
		WarenkorbPage warenkorb = tarifauswahlPage.weiterZumAngebot();
		
		String displayedTarif = warenkorb.getDisplayedTarif();
		CartPage cart = warenkorb.toCart();
		Assert.assertTrue(cart.getProductName().contains(offeredTarif));
		cart.clickWeiterOhneLogin();
		cart.enterPersonData(personFirstname, personLastname, zip, city, street, house, phone, 
				birthday, birthmonth, birthyear, nationality, kennwort, email);
		cart.chooseIdentMethod(ident);
		cart.searchStore(zip);
	}

}
