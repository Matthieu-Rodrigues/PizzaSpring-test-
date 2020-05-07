package WebDriverTest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PizzaSpringIntegrationTest {

	private WebDriver webDriver;

	@Before
	public void createWebDriver() {
		webDriver = new ChromeDriver();
	}

	@After
	public void closeWebDriver() {
		webDriver.quit();
	}

	@Test 
	public void commanderPizzaSuccess() throws Exception {
		CommandePage commandePage = HomePage.openWith(webDriver).clickCommande();

		RecapCommandePage recapcommandePage = CommandePage.openWith(webDriver).choisirPizza("pizzaId", "//option[. = 'Margherita']")
				.entrerTexte("nom", "Matthieu").entrerTexte("telephone", "0606060606").validerCommande();

		assertTrue(recapcommandePage.verifierId("recap-commande"));	
	}

	@Test 
	public void commanderPizzaSansPizza() throws Exception {
		CommandePage commandePage = HomePage.openWith(webDriver).clickCommande();
		
		RecapCommandePage recapcommandePage = commandePage.openWith(webDriver).entrerTexte("nom", "Matthieu")
				.entrerTexte("telephone", "0102030405").validerCommande();

		assertTrue(recapcommandePage.verifierId("pizzaId.errors"));
		assertEquals("Vous devez prendre une pizza", recapcommandePage.getValueId("pizzaId.errors"));
	}
	
	@Test 
	public void commanderPizzaSansTelephone() throws Exception {
		CommandePage commandePage = HomePage.openWith(webDriver).clickCommande();
		
		RecapCommandePage recapcommandePage = commandePage.openWith(webDriver).choisirPizza("pizzaId", "//option[. = 'Margherita']")
				.entrerTexte("nom", "Matthieu").validerCommande();
		
		assertTrue(recapcommandePage.verifierId("telephone.errors"));
		assertEquals("ne peut pas être vide, veuillez fournir votre telephone", recapcommandePage.getValueId("telephone.errors"));
	}
}
