package ivoryPay.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ivoryPay.AbstractComponents.AbstractComponents;

public class SuccessfulEmailVerificationPage extends AbstractComponents{
	
	WebDriver driver;
	
	public SuccessfulEmailVerificationPage(WebDriver driver)
	{

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	
	//Email Successfully Verified
	@FindBy(xpath = "//h2")
	WebElement emailVerificationSuccess;
	
	@FindBy(css = ".my-4 a")
	WebElement linkToDashboard;
	
	
	
	
	public void goToDashboard() throws InterruptedException
	{
		waitForVisibilityOfWebElementByElement(linkToDashboard);
		new Actions(driver).moveToElement(linkToDashboard).click().build().perform();
	}
	
	public String emailVerificationSuccessText() {
		String successfulVerificationText = emailVerificationSuccess.getText();
		return successfulVerificationText;
	}
	


}
