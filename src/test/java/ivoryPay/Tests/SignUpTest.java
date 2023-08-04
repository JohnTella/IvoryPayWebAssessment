package ivoryPay.Tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import ivoryPay.PageObjects.CreateInboxPage;
import ivoryPay.PageObjects.DashboardPage;
import ivoryPay.PageObjects.LoginPage;
import ivoryPay.PageObjects.SignUpPage;
import ivoryPay.PageObjects.SuccessfulEmailVerificationPage;
import ivoryPay.PageObjects.VerifyEmailPage;
import ivoryPay.TestComponents.BaseTest;
import ivoryPay.TestComponents.Retry;

public class SignUpTest extends BaseTest{

	@Test(retryAnalyzer=Retry.class)
	public void SignUp() throws IOException, InterruptedException {
			
			String password = "Password10$";
			String businessName = "Jon Doe LLC";
			String country = "Nigeria";
			
			CreateInboxPage createInboxPage = launchApplication();
			createInboxPage.navigateToDummyInboxCreationPage();
			createInboxPage.createDummyEmail(generateString());
			String emailAddress = createInboxPage.getEmailText();
			String inboxTab = driver.getWindowHandle();
			
			//Switch to IvoryPay tab after creating a dummy email inbox
			switchToIvoryPayTab(driver);
			
			SignUpPage signUpPage = new SignUpPage(driver);
			signUpPage.navigateToLandingPage();
			signUpPage.createAccount("Jon", "Doe", emailAddress, businessName, country, phoneNumber(), password, password);
			String verificationLinkSentText = signUpPage.getVerificationText();
			AssertJUnit.assertTrue(verificationLinkSentText.contains("A verification Link has been sent to your email"));
			signUpPage.closeThisWindow();

			//Switch back to parent tab (dummy email inbox tab)
			driver.switchTo().window(inboxTab);
			
			VerifyEmailPage verifyEmailPage = new VerifyEmailPage(driver);
			verifyEmailPage.verifyEmail();
			
			//Switch to email verification tab
			switchToEmailVerificationTab(driver);
			
			SuccessfulEmailVerificationPage verPage = new SuccessfulEmailVerificationPage (driver);
			String successfulVerificationText = verPage.emailVerificationSuccessText();
			AssertJUnit.assertEquals(successfulVerificationText, "Email Successfully Verified");
			verPage.goToDashboard();
			
			LoginPage LoginPage = new LoginPage(driver);
			LoginPage.loginToApp(emailAddress, password);

			DashboardPage dashboardPage = new DashboardPage(driver);
			String dashboardGreetingNameText = dashboardPage.verifyBusinessNameOnDashboard();
			Assert.assertTrue(dashboardGreetingNameText.contains(businessName));

	}
	
	private static String generateString() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.substring(0, Math.min(uuid.length(), 10));
        return uuid;
    } 
	
	
	private static String phoneNumber()
	{
		String randomNumbers = RandomStringUtils.randomNumeric(9);
		String phoneNo = "80"+randomNumbers;
		return phoneNo;
		
	}
	
	private static void switchToIvoryPayTab(WebDriver driver) {
		driver.switchTo().newWindow(WindowType.TAB);
		
		//Get the window handles of all the opened windows/tabs
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> it = handles.iterator();
		String inboxTab = it.next();
		String ivoryPayAppTab = it.next();
		
		//Switch to child window
		driver.switchTo().window(ivoryPayAppTab);
	}
	
	private static void switchToEmailVerificationTab(WebDriver driver) {
		new WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions.numberOfWindowsToBe(2));
		Set<String> allHandles = driver.getWindowHandles();
		Iterator<String> iterator = allHandles.iterator();
		String inboxTab2 = iterator.next();
		String emailVerificationTab = iterator.next();
		driver.switchTo().window(emailVerificationTab);
	}

}
