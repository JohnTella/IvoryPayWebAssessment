package ivoryPay.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ivoryPay.AbstractComponents.AbstractComponents;

public class SignUpPage extends AbstractComponents{
	
	WebDriver driver;
	
	public SignUpPage (WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(linkText ="Sign up")
	WebElement signUpLink;
	
	@FindBy(id ="register_firstName")
	WebElement firstNameField;
	
	@FindBy(id ="register_lastName")
	WebElement lastNameField;
	
	@FindBy(id ="register_email")
	WebElement emailField;
	
	@FindBy(id ="register_businessName")
	WebElement businessNameField;
	
	@FindBy(xpath ="//input[@type='search']")
	WebElement countryField;
	
	@FindBy(css =".rc-virtual-list-holder-inner")
	WebElement countryList;
	
	@FindBy(css =".rc-virtual-list-holder-inner:last-of-type")
	WebElement countrySelection;
	
	@FindBy(xpath ="//span[@class='ant-input-wrapper ant-input-group']/input[@class='ant-input']")
	WebElement phoneNumberField;
	
	@FindBy(id ="register_password")
	WebElement passwordField;
	
	@FindBy(id ="register_confirmPassword")
	WebElement confirmPasswordField;
	
	@FindBy(xpath ="(//label[@class='ant-radio-wrapper ant-radio-wrapper-in-form-item'])[2]//span[@class='ant-radio-inner']")
	WebElement registeredBusinessRadioButton;
	
	@FindBy(xpath ="(//label[@class='ant-radio-wrapper ant-radio-wrapper-in-form-item'])[4]//span[@class='ant-radio-inner']")
	WebElement notDeveloperRadioButton2;
	
	@FindBy(xpath ="(//span[@class='ant-radio'])[3]")
	WebElement notDeveloperRadioButton;
	
	@FindBy(id ="register_developerId")
	WebElement developerIdField;
			
	@FindBy(xpath ="(//input[@type='checkbox'])[1]")
	WebElement informationCheckbox;
	
	@FindBy(xpath ="(//input[@type='checkbox'])[2]")
	WebElement termsConditionsCheckbox;
	
	@FindBy(xpath ="//button[@type='submit']")
	WebElement submitButton;
	
	@FindBy(css =".mb-4")
	WebElement verificationLinkSent;
	
	
	
	
	public void navigateToLandingPage()
	{
		driver.get("https://qa.d1ainun5cjrnni.amplifyapp.com");
	}
	
	public void createAccount(String firstName, String lastName, String emailAddress, String businessName, String country, String phoneNumber,
			String password, String confirmPassword) throws InterruptedException 
	{
		signUpLink.click();
		firstNameField.sendKeys(firstName);
		lastNameField.sendKeys(lastName);
		emailField.sendKeys(emailAddress);
		businessNameField.sendKeys(businessName);
		countryField.sendKeys(country);
		waitForVisibilityOfWebElementByElement(countryList);
		countrySelection.click();
		phoneNumberField.sendKeys(phoneNumber);
		passwordField.sendKeys(password);
		confirmPasswordField.sendKeys(confirmPassword);
		scrollIntoView(registeredBusinessRadioButton);
		new Actions(driver).moveToElement(registeredBusinessRadioButton).click().build().perform();
		waitForVisibilityOfWebElementByElement(notDeveloperRadioButton);
		notDeveloperRadioButton.click();
		informationCheckbox.click();
		termsConditionsCheckbox.click();
		submitButton.click();
	
	}
	
	public String getVerificationText() {
		String verificationText = verificationLinkSent.getText();
		return verificationText;
	}
	
	public void closeThisWindow() {
		driver.close();
	}

}
