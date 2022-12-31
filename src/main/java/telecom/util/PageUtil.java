package telecom.util;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Attachment;

public class PageUtil {
	
	@Attachment(value = "Page screenshot", type = "image/png")
	public static byte[] takeScreenshot(WebDriver driver) {
		byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		return screenshot;
		
		
	}

}
