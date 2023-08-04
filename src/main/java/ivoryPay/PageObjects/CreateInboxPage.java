package ivoryPay.PageObjects;

import java.util.UUID;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ivoryPay.AbstractComponents.AbstractComponents;



public class  CreateInboxPage extends AbstractComponents {
	
	WebDriver driver;
	
	public CreateInboxPage (WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		//Using PageFactory, you should add this syntax in the constructor so that PageFactory can have a knowledge of the driver
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='flex-row justify-center items-center text-center mt-16 mb-4 lg:mr-8 lg:ml-8']/button")
	WebElement getInboxButton;
	
	@FindBy(id ="username")
	WebElement emailUsernameEle;
	
	@FindBy(xpath="//button[text()='Add Inbox']")
	WebElement addInboxButton;
	
	
	@FindBy(xpath="//h1/span[2]")
	WebElement emailText;
	
	
	
	public static String generateString() 
	{
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.substring(0, Math.min(uuid.length(), 10));
        return uuid;
    } 
	
	public void navigateToDummyInboxCreationPage()
	{
		driver.get("https://inboxes.com/");
	}
	
	public void createDummyEmail(String emailUsername)
	{
		getInboxButton.click();
		emailUsernameEle.sendKeys(emailUsername);
		addInboxButton.click();
	}
	
	public String getEmailText() {
		String emailAddress = emailText.getText();
		return emailAddress;
	}

}
