package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class ProductCatalogPage extends AbstractComponents {
	WebDriver driver;

	public ProductCatalogPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List <WebElement> products;
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".w-10");
	By toastmsg = By.cssSelector("#toast-container");
	
	@FindBy (css = ".ng-animating")
	WebElement spinner;
	
	public List<WebElement> getProductsList() {
		
		waitForElememtToAppear(productsBy);
		return products;
	}
	public WebElement getProductByName(String PName) {
		WebElement prod = getProductsList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(PName))
				.findFirst().orElse(null);
		return prod;
		
	}
	
	public void addProductToCart(String PName) {
		WebElement prod = getProductByName(PName);
		prod.findElement(addToCart).click();
		waitForElememtToAppear(toastmsg);
		invisibilityOfWebElement(spinner);
		
	}

	

}
