package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import AbstractComponents.AbstractComponents;

public class CheckOut extends AbstractComponents {

	WebDriver driver;

	public CheckOut(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement country;
	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement SelectCountry;
	@FindBy(css = ".btnn")
	WebElement e;
	By results = By.cssSelector(".ta-results");

	public void selectCountry(String countryName) {

		country.sendKeys(countryName);
		waitForElememtToAppear(results);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		js.executeScript("arguments[0].click(0);", SelectCountry);
		
		

		

	}
	
	public void submitOrder() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click(0);", e);
		
		
		
	}

}
