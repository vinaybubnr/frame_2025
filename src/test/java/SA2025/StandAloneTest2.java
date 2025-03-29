package SA2025;

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

import PageObjects.LandingPage;



public class StandAloneTest2 {

	public static void main(String[] args) {
		String productName = "ADIDAS ORIGINAL";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		
	
		driver.findElement(By.cssSelector("#userEmail")).sendKeys("vinaykumarbu@gmail.com");
		driver.findElement(By.cssSelector("#userPassword")).sendKeys("Vinay@490");
		driver.findElement(By.cssSelector("#login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);
		prod.findElement(By.cssSelector(".w-10")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.xpath("//button[contains(@routerlink,'cart')]")).click();
		List<WebElement> CartProducts = driver.findElements(By.cssSelector(".cartSection h3"));

		boolean Match = CartProducts.stream()
				.anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(Match);

		driver.findElement(By.cssSelector(".totalRow button")).click();

		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("india");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement f = driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]"));
		js.executeScript("arguments[0].click(0);", f);
		WebElement e = driver.findElement(By.cssSelector(".btnn"));

		js.executeScript("arguments[0].click(0);", e);
		String message = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertEquals(message, "THANKYOU FOR THE ORDER.");
		driver.quit();

	}

}
