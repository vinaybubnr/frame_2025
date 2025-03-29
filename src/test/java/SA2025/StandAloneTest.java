package SA2025;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjects.CartPage;
import PageObjects.CheckOut;
import PageObjects.ConfirmationPage;
import PageObjects.OrderPage;
import PageObjects.ProductCatalogPage;
import TestComponents.BaseTest;

public class StandAloneTest extends BaseTest {
	String productName = "ADIDAS ORIGINAL";
	@Test (dataProvider = "DataProvider" , groups = {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException {
		

		

		ProductCatalogPage PCP = landingpage.loginApplication(input.get("email"), input.get("pass"));

		
		List<WebElement> products = PCP.getProductsList();
		PCP.addProductToCart(productName);
		PCP.goToCartPage();

		CartPage cartpage = new CartPage(driver);
		boolean match = cartpage.verifyProductDisplay(productName);

		Assert.assertTrue(match);
		cartpage.goToCheckout();

		CheckOut checkout = new CheckOut(driver);
		checkout.selectCountry("india");
		checkout.submitOrder();

		ConfirmationPage cp = new ConfirmationPage(driver);
		String message = cp.getConfirmationMessage();
		Assert.assertEquals(message, "THANKYOU FOR THE ORDER.");
		
		
	}
	@DataProvider(name = "DataProvider")
	
	public Object[][] GetData() throws IOException {
		
	/*	HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", "vinaykumarbu@gmail.com");
		map.put("pass", "Vinay@490");
		HashMap<Object, Object> map1 = new HashMap<Object, Object>();
		map1.put("email", "vinaybubgsit@gmail.com");
		map1.put("pass", "Vinay@490"); */
		
		List<HashMap<String, String>> values = getJsonData(System.getProperty("user.dir") + "\\src\\test\\java\\Data\\purchaseOrder.json");
		
		
		Object data [] [] = {{values.get(0)},{values.get(1)}};
		return data;
		
		
		
		
	}
	
	
	// Extent report
	
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void orderHistoryTest() {
		ProductCatalogPage productcatalog = landingpage.loginApplication("vinaykumarbu@gmail.com", "Vinay@490");
		OrderPage orderpage = productcatalog.goToOrderPage();
		Assert.assertTrue(orderpage.verifyOrderDisplay(productName));
	}
}
