package pageObjects;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ExcelUtils;
import utils.FileUtils;

public class HomePage extends BasePage {
	@FindBy(id = "genderManButton")
	WebElement maleGenderOptionButton;

	@FindBy(id = "onetrust-accept-btn-handler")
	WebElement acceptCookieButton;

	@FindBy(xpath = "(//input[contains(@class, 'o-header__search--input')])[1]")
	WebElement searchArea;
	@FindBy(id = "o-searchSuggestion__input")
	WebElement searchArea2;

	@FindBy(xpath = "//button[normalize-space()='Sil']")
	WebElement deleteSearchedButton;

	@FindBy(className = "m-productCard__newPrice")
	List<WebElement> discountedOrNotDiscountedCampaignedProductPriceLabelOnHome;

	String projectDir = System.getProperty("user.dir");
	String filePath = projectDir + "/src/test/resources/testdata.xlsx";

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	public HomePage(WebDriver driver) {
		super(driver);

	}

	public void acceptCookies() {

		wait.until(ExpectedConditions.elementToBeClickable(acceptCookieButton));
		acceptCookieButton.click();
	}

	public void selectMaleGenderOption() {
		wait.until(ExpectedConditions.elementToBeClickable(maleGenderOptionButton));
		maleGenderOptionButton.click();
	}

	public String getHomePageTitle() {
		return driver.getTitle();
	}

	public String dataReadFromExcel(int index) {

		ExcelUtils excel = new ExcelUtils(filePath);
		String data = excel.getCellValue(0, index - 1);
		excel.closeWorkbook();

		return data;
	}

	public void clickSearchArea() {
		wait.until(ExpectedConditions.elementToBeClickable(searchArea)).click();
	}

	public void typeSearchArea(String text) {

		searchArea.sendKeys(text);
	}

	public void clearSearchArea() {
		deleteSearchedButton.click();
	}

	public void typeEnter() {
		searchArea2.sendKeys(Keys.ENTER);

	}

}
