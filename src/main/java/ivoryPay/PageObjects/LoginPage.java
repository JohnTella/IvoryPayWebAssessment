package ivoryPay.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ivoryPay.AbstractComponents.AbstractComponents;

public class LoginPage extends AbstractComponents{
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "email")
	WebElement emailField;
	
	@FindBy(id = "password")
	WebElement passwordField;
	
	@FindBy(css = "button[type='submit']")
	WebElement loginButton;

	public void loginToApp(String emailAddress, String password)
	{
		driver.get("https://qa.d1ainun5cjrnni.amplifyapp.com");
		emailField.sendKeys(emailAddress);
		passwordField.sendKeys(password);
		loginButton.click();
	}


}
