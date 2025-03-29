package AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjects.OrderPage;

public class AbstractComponents {
	
	
	WebDriver driver;
	
	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	
	}
	
	@FindBy (xpath = "//button[contains(@routerlink,'cart')]")
	WebElement cart;
	@FindBy(xpath="//button[contains(@routerlink,'myorders')]")
	WebElement OrderHeader;

	public void waitForElememtToAppear(By findBy) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	
	
	public void invisibilityOfWebElement(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(ele));
		
	}
	
public void waitForWebElementToAppear(WebElement findBy) { 
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOf(findBy));

	}
public OrderPage goToOrderPage() {
	
	OrderHeader.click();
	OrderPage orderpage = new OrderPage(driver);
	return orderpage;
}
	
	public void goToCartPage() {
		
		cart.click();
		
	}
	

}
