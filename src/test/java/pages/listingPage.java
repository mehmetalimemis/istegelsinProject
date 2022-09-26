package pages;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.basePage;

public class listingPage extends basePage {

	private final By productList = By.cssSelector("h3[data-test-id='product-card-name']");
	private final By sortingButton = By.xpath("/html[1]/body[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[2]/select[1]/option[1]");
	private final By sortLoverPriceButton = By.xpath("//option[contains(text(),'Fiyatı Artan')]");
	private final By addToCartLoverPriceProduct = By.xpath("//*[@id=\"productListDesk\"]/div/div[1]/a/div[3]/div/div/div[2]/div/img");
	
	public listingPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}

	public void selectRandomProduct() {
		List<WebElement> elementList = getElementList(productList);
		int randomNumber = getRandomNumber(elementList.size());
		WebElement selectedProduct = elementList.get(randomNumber);
		storeData("productList_selectedProductName", selectedProduct.getText());
		selectedProduct.click();
		getScreenshot();
	}
	
	public void sortLoverPrice() {

		try {
			click(sortingButton);
			click(sortLoverPriceButton);
			String sortingButtonText = getText(sortingButton);
			String loverPriceText = ("Fiyatı Artan");
			Assert.assertTrue(loverPriceText.equals(sortingButtonText));
			logger.info("Ürünler fiyat artan şekilde sıralanmıştır.");
			getScreenshot();
		} catch (Exception e) {
			logger.info("Ürünler fiyat artan şekilde sıralanmamıştır.");
			getScreenshot();
		}
	}
	
	public void addToCartLoverPriceProduct() {
		click(addToCartLoverPriceProduct);
		getScreenshot();
	}

}
