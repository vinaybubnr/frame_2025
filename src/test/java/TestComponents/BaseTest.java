package TestComponents;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import PageObjects.LandingPage;

public class BaseTest {

	public static WebDriver driver;
	public static LandingPage landingpage;

	public static WebDriver initilizeDriver() throws IOException {
		Properties pro = new Properties();
		FileInputStream input = new FileInputStream(
				"C:\\Users\\VinayUmesh\\OneDrive - QS QUACQUARELLISYMONDS LIMITED\\Desktop\\Java_eclipse\\Frame2025\\src\\main\\java\\Resources\\GlobalDataaa.properties");
		pro.load(input);
		String Browser = pro.getProperty("browser");

		if (Browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();

		}

		else if (Browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}

	@BeforeMethod(alwaysRun=true)
	public static LandingPage launchApp() throws IOException {

		driver = initilizeDriver();
		landingpage = new LandingPage(driver);
		landingpage.Goto();
		return landingpage;
	}

	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();
		
	}
	

		public List<HashMap<String, String>> getJsonData(String FilePath) throws IOException {
			// convert JSON to String

			String JsonContent = FileUtils.readFileToString(
					new File(FilePath),
					StandardCharsets.UTF_8);
			// Convert String To Hash MAP - JacksonDataBind ( Maven Repo )

			ObjectMapper mapper = new ObjectMapper();
			

			List<HashMap<String, String>> data = mapper.readValue(JsonContent,
					new TypeReference<List<HashMap<String, String>>>() {
					}); // Default IMP

			return data;

		}
		public String getScreenshot(String testcaseName, WebDriver driver) throws IOException {
			
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			File target = new File(System.getProperty("user.dir") + "\\Reports\\" +testcaseName +".png");
			FileUtils.copyFile(source, target);
			return System.getProperty("user.dir")+ "\\reports\\" +testcaseName +"\\.png";
			
			
			
		}
	
}
