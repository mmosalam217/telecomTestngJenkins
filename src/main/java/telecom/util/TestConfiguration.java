package telecom.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

public class TestConfiguration {
	
	private Properties props;
	
	public TestConfiguration() {
		props = new Properties();
		try {
			props.load(new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\test.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Long getDriverImplicitWait() {
		return Optional.of(Long.parseLong(props.getProperty("driver.wait.implicit")))
				.orElseThrow(()-> new RuntimeException("Cannot read or find property" + " driver.wait.implicit"));
	
	}
	
	public Long getDriverExplicitWait() {
		return Optional.of(Long.parseLong(props.getProperty("driver.wait.explicit")))
				.orElseThrow(()-> new RuntimeException("Cannot read or find property" + " driver.wait.explicit"));
	
	}
	
	public String getApplicationUrl() {
		return Optional.of(props.getProperty("application.url"))
				.orElseThrow(()-> new RuntimeException("Cannot read or find property" + " application.url"));
	}
	
}
