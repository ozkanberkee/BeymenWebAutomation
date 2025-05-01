package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasketPage extends BasePage {
	public BasketPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "quantitySelect0-key-0")
	WebElement selectShirtArea;
	@FindBy(id = "removeCartItemBtn0-key-0")
	WebElement removeBasketButton;
	@FindBy(xpath = "//strong[text()='Sepetinizde Ürün Bulunmamaktadır']")
	WebElement afterDeletedBasketArea;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	public void updateShirtNumber() {
		wait.until(ExpectedConditions.elementToBeClickable(selectShirtArea));
		Select select = new Select(selectShirtArea);
		select.selectByValue("2");
	}

	public void deleteBasket() {
		wait.until(ExpectedConditions.elementToBeClickable(removeBasketButton));
		removeBasketButton.click();

	}

	public String checkBasketIsEmpty() {
		WebElement messageElement = afterDeletedBasketArea;
		wait.until(ExpectedConditions.visibilityOf(messageElement));
		return messageElement.getText();
	}
}
