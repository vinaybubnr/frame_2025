package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "#userEmail")
	WebElement username;

	@FindBy(css = "#userPassword")
	WebElement password;

	@FindBy(css = "#login")
	WebElement submit;
	
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMsg;

	public ProductCatalogPage loginApplication(String email, String pass) {

		username.sendKeys(email);
		password.sendKeys(pass);
		submit.click();
		ProductCatalogPage PCP = new ProductCatalogPage(driver);
		return PCP;

	}
	
	public void Goto() {
		driver.get("https://rahulshettyacademy.com/client");
		
	}
	public String getErrorMsg() {
		waitForWebElementToAppear(errorMsg);
		return errorMsg.getText();
	}

}
