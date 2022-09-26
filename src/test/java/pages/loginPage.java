package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.basePage;

public class loginPage extends basePage {

	private Logger logger = Logger.getLogger(loginPage.class);

	private final By phoneNumberTextBox = By.id("phone-input");
	private final By confirmationScreenText = By.xpath("//*[@id=\"appLayout\"]/div[2]/div/div[2]/div[2]");
	private final By continueButton = By.className("save-btn");
	private final By startShoppingButton = By.className("send-otp-button");
	private final By wrongPhoneNumberText = By.className("warn-text");
	private final By verificationText = By.xpath("//*[@id=\"appLayout\"]/div[2]/div/div[2]/div[1]");
	
	

	public loginPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		getScreenshot();
	}

	public void isVerificationScreenOpen() {
		String confirmationPopupText = getText(confirmationScreenText);
		String smsVerificationText = ("Alışverişe devam edebilmen veya aktif siparişinin takibini yapabilmek için SMS onayın gerekiyor.");
		Assert.assertTrue(confirmationPopupText.equals(smsVerificationText));
		logger.info("Login için sms doğrulama ekranı açılmıştır.");
		getScreenshot();
	}

	public void enterPhoneNumber(String phoneNumber) {
		try {
			click(phoneNumberTextBox);
			clear(phoneNumberTextBox);
			write(phoneNumberTextBox, phoneNumber);
			click(continueButton);
			String verificationPopupText = getText(verificationText);
			String smsVerificationScreenText = ("Doğrulama Kodunu Gir");
			Assert.assertTrue(smsVerificationScreenText.equals(verificationPopupText));
			logger.error("Geçerli telefon numarası girişi yapıldı.");
			getScreenshot();
		} catch (Exception e) {
			String warningText = getText(wrongPhoneNumberText);
			String smsVerificationText = ("Geçersiz telefon numarası.");
			Assert.assertTrue(warningText.equals(smsVerificationText));
			logger.error("Geçersiz telefon numarası girişi yapıldı.");
			getScreenshot();
		}
	}

	public void enterWrongPhoneNumber(String phoneNumber) {
		try {
			write(phoneNumberTextBox, phoneNumber);
			click(continueButton);
			String warningText = getText(wrongPhoneNumberText);
			String smsVerificationText = ("Geçersiz telefon numarası.");
			Assert.assertTrue(warningText.equals(smsVerificationText));
			logger.error("Geçersiz telefon numarası girişi yapıldı.");
			getScreenshot();
		} catch (Exception e) {
			logger.error("Geçerli telefon numarası girişi yapıldı.");
			getScreenshot();
		}
	}

	public void EntersmsVerificationCode() {
		waitSeconds(10);
		click(startShoppingButton);
		getScreenshot();

	}

}