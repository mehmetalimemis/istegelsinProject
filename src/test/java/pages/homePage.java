package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.basePage;

public class homePage extends basePage{
	
	private Logger logger = Logger.getLogger(homePage.class);
	
	private final By loginButton = By.cssSelector(".v3-desk.v3-header-desk .register-title");
	private final By myAccountUser = By.className("user-title");
	private final By searchTextBox = By.xpath("//*[@id=\"appLayout\"]/main/div[1]/div/div[1]/div[1]/div/input");
	
	
	public homePage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}
	

	public void isHomePageOpen() {
		Assert.assertTrue(getPageTitle().equals("istegelsin | taptaze süpermarket"));
		logger.info("Anasayfa başarılı şekilde açılmıştır.");
		getScreenshot();
	}

	public void gotoLoginPage() {
		click(loginButton);
		getScreenshot();
	}

	public void isUserLogin() {
		boolean isUserLogin = isDisplayed(myAccountUser);
		Assert.assertTrue(isUserLogin);
		logger.info("User başarılı şekilde login olmuştur.");
		getScreenshot();
	}

	public void search(String text) {
		write(searchTextBox, text);
		enter(searchTextBox);
		getScreenshot();
	}

}

