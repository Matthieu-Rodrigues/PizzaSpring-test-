package WebDriverTest;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RecapCommandePage {

	private WebDriver webDriver;

	public RecapCommandePage(WebDriver webDriver2) {
		this.webDriver = webDriver;
	}

	public boolean verifierId(String id) {
		return webDriver.findElement(By.id(id)).isDisplayed();
	}

	public String getValueId(String id) {
		return webDriver.findElement(By.id(id)).getText();
	}
}