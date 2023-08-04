package ivoryPay.TestComponents;

import org.testng.annotations.AfterMethod;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import ivoryPay.PageObjects.CreateInboxPage;

public class BaseTest {
	
	public WebDriver driver;

	
	
	public WebDriver initializeDriver() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ "//src//main//java//ivoryPay//Resources//GlobalData.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		
		//Then you can now use an "If-Else" condition to determine what should be triggered when the browser name is returned
		
		if (browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	@BeforeMethod(alwaysRun=true)
	public CreateInboxPage launchApplication() throws IOException {
		driver = initializeDriver();
		CreateInboxPage createInboxPage = new CreateInboxPage(driver);
		createInboxPage.navigateToDummyInboxCreationPage();
		return createInboxPage;
	}
	
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	

}
