package tests;

import org.junit.Test;
import base.baseTest;
import pages.homePage;
import pages.listingPage;
import pages.loginPage;




public class addToCart extends baseTest{
	
	@Test
	
	public void loverPriceProductAddToCart() {

		homePage homePage = new homePage(driver, wait);
		loginPage loginPage = new loginPage(driver, wait);
		listingPage productListPage = new listingPage(driver, wait);
		homePage.isHomePageOpen();
		homePage.gotoLoginPage();
		loginPage.isVerificationScreenOpen();
		loginPage.enterWrongPhoneNumber("12345");
		loginPage.enterPhoneNumber("5417737125");
		loginPage.EntersmsVerificationCode();
		homePage.isUserLogin();
		homePage.search("elma");
		productListPage.sortLoverPrice();
		productListPage.addToCartLoverPriceProduct();
		
	}

}


