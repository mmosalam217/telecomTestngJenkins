package telecom.mobilfunk;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import telecom.intern.annotations.Chrome;
import telecom.util.TestConfiguration;

public class BaseTest {

	protected WebDriver driver;
	private TestConfiguration config = new TestConfiguration();
	
	@BeforeClass()
	@Chrome(headless=true)
	public void setup() {
		driver.get(config.getApplicationUrl());
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(config.getDriverImplicitWait()));
		driver.manage().window().maximize();
	}
	
	@AfterClass()
	public void teardown() {
		if(driver != null) driver.quit();
	}
}
