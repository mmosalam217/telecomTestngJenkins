package telecom.mobilfunk;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import telecom.intern.annotations.Chrome;
import telecom.intern.annotations.Edge;
import telecom.util.TestConfiguration;

public class BaseTest {

	protected WebDriver driver;
	private TestConfiguration config = new TestConfiguration();
	
	@BeforeClass()
	@Chrome()
	public void setup() {
		//ChromeOptions opts = new ChromeOptions();
		//opts.addArguments("--remote-allow-origins=*");
		//opts.addArguments("--headless");
		//opts.addArguments("window-size=1200,1100");
		//driver = new ChromeDriver(opts);
		driver.get(config.getApplicationUrl());
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(config.getDriverImplicitWait()));
		driver.manage().window().maximize();
	}
	
	@AfterClass()
	public void teardown() {
		if(driver != null) driver.quit();
	}
}
