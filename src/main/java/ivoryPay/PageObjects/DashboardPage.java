package ivoryPay.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ivoryPay.AbstractComponents.AbstractComponents;

public class DashboardPage extends AbstractComponents{
	
	WebDriver driver;
	
	public DashboardPage(WebDriver driver)
	{

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	
	//should contain businessName
	@FindBy(xpath = "//h3[@class='text-[#2C3489] capitalize text-xl md:text-2xl font-medium']")
	WebElement greetingsHeader;

	
	public String verifyBusinessNameOnDashboard() throws InterruptedException
	{
		waitForVisibilityOfWebElementByElement(greetingsHeader);
		String dashboardGreetingNameText = greetingsHeader.getText();
		return dashboardGreetingNameText;
	}

}
