package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductDetailsPage extends BasePage {
	public ProductDetailsPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//span[contains(@class, 'm-variation__item') and not(contains(@class, 'disable'))]")
	WebElement productSizeOption;
	@FindBy(id = "addBasket")
	WebElement addToBasketButton;
	@FindBy(className = "m-notification__message")
	WebElement addedInfoMessage;
	@FindBy(className = "m-notification__button")
	WebElement directToCartButton;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	public void addToBasket() {
		wait.until(ExpectedConditions.elementToBeClickable(productSizeOption));
		productSizeOption.click();
		wait.until(ExpectedConditions.elementToBeClickable(addToBasketButton));

		addToBasketButton.click();

	}

	public String returnSuccessMessage() {
		String ınfoMessage = wait.until(ExpectedConditions.visibilityOf(addedInfoMessage)).getText();
		return ınfoMessage;
	}

	public void navigateToCart() {
		wait.until(ExpectedConditions.elementToBeClickable(directToCartButton)).click();
	}
}
