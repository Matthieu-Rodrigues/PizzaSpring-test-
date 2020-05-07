package WebDriverTest;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CommandePage {
	private WebDriver webDriver;

	public CommandePage(WebDriver webDriver) {
		this.webDriver = webDriver;
		assertTrue("Titre de page inattendu " + webDriver.getTitle(), webDriver.getTitle().endsWith("Pizza Spring"));
	}
	
	public CommandePage open() {
		webDriver.navigate().to("http://localhost:8081/pizza-spring/commande");
		assertTrue("Titre de page inattendu " + webDriver.getTitle(), webDriver.getTitle().startsWith("Pizza Spring"));
		return this;
	}
	
	public static CommandePage openWith(WebDriver webDriver) {
		CommandePage commandePage = new CommandePage(webDriver);
		commandePage.open();
		return commandePage;
	}

	public CommandePage entrerTexte(String id, String... words) {
		WebElement searchInput = webDriver.findElement(By.id(id));
		searchInput.sendKeys(String.join(" ", words));
		return this;
	}

	public CommandePage choisirPizza(String id, String pizza) {
		WebElement searchInput = webDriver.findElement(By.id(id));
		searchInput.findElement(By.xpath(pizza)).click();
		return this;
	}

	public RecapCommandePage validerCommande() {
		WebElement searchButton = webDriver.findElement(By.cssSelector("button"));
		searchButton.click();
		return new RecapCommandePage(webDriver);
	}
}
