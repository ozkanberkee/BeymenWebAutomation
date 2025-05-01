package tests;

import org.apache.commons.io.input.CloseShieldReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import pageObjects.BasketPage;
import pageObjects.HomePage;
import pageObjects.ProductDetailsPage;
import pageObjects.SearchResultsPage;
import utils.ExcelUtils;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HomePageTest extends BaseTest {
	String expectedHomePageTitle = "Beymen.com – Türkiye’nin Tek Dijital Lüks Platformu";
	String expectedInfoMessage = "Ürün sepetinize eklenmiştir.";
	String expectedEmptyBasketMessage = "SEPETINIZDE ÜRÜN BULUNMAMAKTADIR";
	static String shirtPriceOnHomePage;
	static String shirtWear;
	static String shortWear;

	@Test
	@Order(1)
	public void userCanAcceptCookiesAndCloseGenderOption() {
		homePage.acceptCookies();
		homePage.selectMaleGenderOption();
	}

	@Test
	@Order(2)
	public void userIsOnCorrectHomePage() {
		String homePageTitle = homePage.getHomePageTitle();
		Assertions.assertEquals(expectedHomePageTitle, homePageTitle);
	}

	@Test
	@Order(3)
	public void userCanSearchForAShirt() {
		shortWear = homePage.dataReadFromExcel(1);
		shirtWear = homePage.dataReadFromExcel(2);
		homePage.typeSearchArea(shirtWear);
		homePage.typeEnter();
	}

	@Test
	@Order(4)
	public void userCanSelectRandomShirtAndValidatePrice() {
		searchResultsPage.selectRandomShirt();
		shirtPriceOnHomePage = searchResultsPage.pickShirtPrice();
		searchResultsPage.clickShirtDetail();
		String shirtPriceOnCartPage = searchResultsPage.textProductInfos();
		Assertions.assertEquals(shirtPriceOnCartPage, shirtPriceOnHomePage);
	}

	@Test
	@Order(5)
	public void userCanAddShirtToBasketAndSeeSuccessMessage() {
		productDetailsPage.addToBasket();
		String successMessage = productDetailsPage.returnSuccessMessage();
		Assertions.assertEquals(expectedInfoMessage, successMessage);
	}

	@Test
	@Order(6)
	public void userCanNavigateToBasketAndUpdateIt() {
		productDetailsPage.navigateToCart();
		basketPage.updateShirtNumber();
	}

	@Test
	@Order(7)
	public void userCanDeleteItemFromBasketAndSeeEmptyMessage() {
		basketPage.deleteBasket();
		String actualEmptyBasketMessage = basketPage.checkBasketIsEmpty();
		Assertions.assertEquals(expectedEmptyBasketMessage, actualEmptyBasketMessage);
	}

}
