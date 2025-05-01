package pageObjects;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.FileUtils;

public class SearchResultsPage extends BasePage {
	public SearchResultsPage(WebDriver driver) {
		super(driver);

	}

	private int randomIndex;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	@FindBy(className = "m-productCard__detail")
	List<WebElement> campaignedProductDetailLabelOnHome;
	@FindBy(className = "m-productCard__desc")
	List<WebElement> linkRandomProduct;

	@FindBy(className = "o-productDetail__description")
	WebElement searchedProductName;
	@FindBy(className = "m-price__campaignDesc")
	List<WebElement> campaignDescOnCart;
	@FindBy(className = "m-price__lastPrice")
	List<WebElement> lastProductPriceLabelOnCart;

	@FindBy(className = "m-price__new")
	List<WebElement> newDiscountedProductPriceLabelOnCart;
	@FindBy(className = "m-price__campaignPrice")
	WebElement CampaignedProductPriceLabelOnCart;

	public Integer selectRandomShirt() {
		wait.until(ExpectedConditions.visibilityOfAllElements(campaignedProductDetailLabelOnHome));
		Random random = new Random();
		randomIndex = random.nextInt(campaignedProductDetailLabelOnHome.size());
		System.out.println(randomIndex);

		return randomIndex;
	}

	public String pickShirtPrice() {

		WebElement randomShirt = campaignedProductDetailLabelOnHome.get(randomIndex);
		wait.until(ExpectedConditions.visibilityOf(randomShirt));
		List<WebElement> campaignPrices = randomShirt
				.findElements(By.xpath(".//span[@class='m-productCard__campaignPrice']"));
		List<WebElement> campaignDescs = randomShirt
				.findElements(By.xpath(".//span[@class='m-productCard__campaignDesc']"));
		if (!campaignDescs.isEmpty() && campaignDescs.get(0).getText().equals("Sepette %70")
				&& !campaignPrices.isEmpty()) {
			return campaignPrices.get(0).getText();
		}
		List<WebElement> newPrices = randomShirt.findElements(By.xpath(".//span[@class='m-productCard__newPrice']"));
		if (!newPrices.isEmpty()) {
			return newPrices.get(0).getText();
		}
		List<WebElement> lastPrices = randomShirt.findElements(By.xpath(".//span[@class='m-productCard__lastPrice']"));
		if (!lastPrices.isEmpty()) {
			return lastPrices.get(0).getText();
		}
		return null;

	}

	public void clickShirtDetail() {
		linkRandomProduct.get(randomIndex).click();

	}

	public String textProductInfos() {
		wait.until(ExpectedConditions.visibilityOf(searchedProductName));
		String productName = searchedProductName.getText();
		String productPrice = "";
		if (!campaignDescOnCart.isEmpty() && campaignDescOnCart.get(0).getText().equals("Sepette %70")) {
			productPrice = CampaignedProductPriceLabelOnCart.getText();
		} else if (!lastProductPriceLabelOnCart.isEmpty()) {
			productPrice = lastProductPriceLabelOnCart.get(0).getText();
		} else if (!newDiscountedProductPriceLabelOnCart.isEmpty()) {
			productPrice = newDiscountedProductPriceLabelOnCart.get(0).getText();
		}

		String productDetails = productName + " - " + productPrice;
		FileUtils.writeToFile(productDetails);
		return productPrice;
	}
}
