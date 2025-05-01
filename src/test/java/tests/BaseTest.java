package tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;

import pageObjects.BasketPage;
import pageObjects.HomePage;
import pageObjects.ProductDetailsPage;
import pageObjects.SearchResultsPage;
import utils.DriverFactory;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {

	protected WebDriver driver;
	protected HomePage homePage;
	protected BasketPage basketPage;
	protected ProductDetailsPage productDetailsPage;
	protected SearchResultsPage searchResultsPage;

	@BeforeAll
	public void setUp() {
		String browser = System.getProperty("browser", "chrome");
		driver = new DriverFactory().init_Driver(browser);
		homePage = new HomePage(driver);
		basketPage = new BasketPage(driver);
		productDetailsPage = new ProductDetailsPage(driver);
		searchResultsPage = new SearchResultsPage(driver);
		driver.manage().window().maximize();
		driver.get("https://beymen.com");
	}

	@AfterAll
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
