package SA2025;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import PageObjects.CartPage;
import PageObjects.CheckOut;
import PageObjects.ConfirmationPage;
import PageObjects.LandingPage;
import PageObjects.ProductCatalogPage;
import TestComponents.BaseTest;

public class ErrorValidation extends BaseTest {
	@Test (groups={"ErrorHandling"}, retryAnalyzer=TestComponents.Retry.class)
	public void LoginErrorValidation() throws IOException {

		landingpage.loginApplication("vinaykumarbu@gmail.com", "Vinay@4900");
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMsg());

	}
	
	@Test (priority = 2)
	public void ProductErrorValidation() throws IOException {
		String productName = "ADIDAS ORIGINAL";

		

		ProductCatalogPage PCP = landingpage.loginApplication("vinaybubgsit@gmail.com", "Vinay@490");

		
		List<WebElement> products = PCP.getProductsList();
		PCP.addProductToCart(productName);
		PCP.goToCartPage();

		CartPage cartpage = new CartPage(driver);
		boolean match = cartpage.verifyProductDisplay(productName);

		Assert.assertTrue(match);
	
		
		
	}

}
