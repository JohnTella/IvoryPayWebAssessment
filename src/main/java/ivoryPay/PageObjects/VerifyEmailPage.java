package ivoryPay.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ivoryPay.AbstractComponents.AbstractComponents;

public class VerifyEmailPage extends AbstractComponents{
	
	WebDriver driver;
	
	public VerifyEmailPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath = "//button[@title='Reload']")
	WebElement reloadButton;
	
	@FindBy(xpath = "//tr/td[2]")
	WebElement openEmail;
	
	@FindBy(xpath = "//button[contains(text(),'Verify your email address')]")
	WebElement verifyEmail;
	
	
	
	
	
	public void verifyEmail()
	{
		reloadButton.click();
		openEmail.click();
		verifyEmail.click();
	}


}
