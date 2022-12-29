package telecom.mobilfunk;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import telecom.util.TestConfiguration;

public class BaseTest {

	protected WebDriver driver;
	private TestConfiguration config = new TestConfiguration();
	
	@BeforeClass()
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get(config.getApplicationUrl());
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(config.getDriverImplicitWait()));
		driver.manage().window().maximize();
	}
	
	@AfterClass()
	public void teardown() {
		if(driver != null) driver.close();
	}
}
