package base;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.mongodb.MapReduceCommand.OutputType;

/**
 * Unit test for simple App.
 */

public class basePage {

	protected WebDriverWait wait;
	protected WebDriver driver;
	protected JavascriptExecutor js;
	protected Logger logger = Logger.getLogger(basePage.class);
	private static HashMap<String, String> dataList = new HashMap<String, String>();

	ExtentReports reports = new ExtentReports();
	ExtentTest test = reports.baseTest("getscreenshot");



	public basePage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		this.js = (JavascriptExecutor) driver;
	}

	public String getScreenshot() {
		test.pass("pass screenshot", MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
		test.fail("fail screenshot", MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
	}
	
	public void storeData(String key, String value) {
		dataList.put(key, value);
	}

	public String getData(String key) {
		return dataList.get(key);
	}

	protected void click(By by) {
		WebElement element = this.wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		try {
			element.click();
			logger.info(by.toString() + " elementine tiklandi.");
		} catch (Exception e) {
			logger.error(by.toString() + " elementine tiklanamadi.");
		}
	}

	protected void write(By by, String text) {
		WebElement element = this.wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		try {
			element.sendKeys(text);
			logger.info(by.toString() + " elementine " + text + " degeri yazildi..");
		} catch (Exception e) {
			logger.error(by.toString() + " elementine " + text + "degeri yazilmadi.");
		}
	}

	public void enter(By by) {
		WebElement element = this.wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		element.sendKeys(Keys.ENTER);
	}

	public void clear(By by) {
		WebElement element = this.wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		element.sendKeys(Keys.CLEAR);
	}

	public void tab(By by) {
		WebElement element = this.wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		element.sendKeys(Keys.TAB);
	}

	public void waitSeconds(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
			logger.info(seconds + " saniye bekleniyor.");
		} catch (Exception e) {
			logger.error("bekleme gerceklesmedi.");
		}
	}

	protected String getText(By by) {
		WebElement element = this.wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		return element.getText();
	}

	protected WebElement getElement(By by) {
		WebElement element = null;
		try {
			element = this.wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (ElementNotVisibleException e) {
			e.printStackTrace();
		}
		return element;
	}

	protected void clickWithOnFocus(By by) {
		WebElement element = this.wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		try {
			element.click();
			logger.info(by.toString() + " elementine onFocus ile tiklandi.");
		} catch (Exception e) {
			logger.error(by.toString() + " elementine onFocus ile tiklanamadi.");
		}
	}

	protected List<WebElement> getElementList(By by) {
		List<WebElement> elementList = null;
		try {
			elementList = this.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
			logger.info(by.toString() + " elementin listesi geldi.");
		} catch (Exception e) {
			logger.error(by.toString() + " element listesi gelmedi..");
		}
		return elementList;
	}

	public void hover(By by) {
		Actions actions = new Actions(driver);
		WebElement element = this.wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		actions.moveToElement(element).build().perform();
	}

	protected String getPageTitle() {
		return driver.getTitle();
	}

	public boolean isDisplayed(By by) {
		return this.wait.until(ExpectedConditions.visibilityOfElementLocated(by)).isDisplayed();
	}

	protected int getRandomNumber(int limit) {
		Random random = new Random();
		int randomNumber = random.nextInt(limit);
		return randomNumber;
	}

	public void changeWindow(int windowNumber) {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(windowNumber));
	}

	protected void doubleClick(By by) {
		Actions actions = new Actions(driver);
		WebElement element = this.wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		try {
			actions.doubleClick(element).perform();
			logger.info(by.toString() + " elementine tiklandi.");
		} catch (Exception e) {
			logger.error(by.toString() + " elementine tiklanamadi.");
		}
	}

}