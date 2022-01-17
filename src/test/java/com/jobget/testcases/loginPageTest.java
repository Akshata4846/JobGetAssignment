package com.jobget.testcases;

import java.io.IOException;
import java.util.Iterator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.jobget.pages.LoginPage;
import com.jobget.util.Config;
import com.jobget.util.Util;

import org.testng.Assert;

public class loginPageTest {
	LoginPage loginPage;
	private String pageTitle;
	final String SHEETNAME = "LoginDetails";


	@DataProvider
	public Iterator<String[]> getData() throws IOException {
		return Util.getData(SHEETNAME);
	}


	private void populateFormFields (String email, String password ) {
		loginPage.setUserName(email);
		loginPage.setPassword(password);
	}


	@BeforeMethod
	public void setUp() {
		loginPage = new LoginPage();
	}


	@Test(dataProvider = "getData")
	public void testElementsPresentOnPage(String firstName, String lastName, String email, String password, String country) {
		boolean result;
		loginPage.clickLoginUpBtn();
		Util.handleStartupPages(loginPage, country);
		if (Util.isEmployerLogin(loginPage)) {
			loginPage.clickEmployerBtn();
			result = loginPage.isUserNameFieldDisplayed();
			Assert.assertTrue(result, "UserName mandatory field on login page not present");

			result = loginPage.isPasswordFieldDisplayed();
			Assert.assertTrue(result, "Password mandatory field on login page not present");

			result = loginPage.isForgotPasswordLinkDisplayed();
			Assert.assertTrue(result, "Forgot Password link on login page not present");

			result = loginPage.isSignUpLinkDisplayed();
			Assert.assertTrue(result, "SignUp button on login page not present");	
		}

	}

	@Test(dataProvider = "getData")
	public void testRedirections(String firstName, String lastName, String email, String password, String country) {
		String title;
		loginPage.clickLoginUpBtn();
		Util.handleStartupPages(loginPage, country);
		if (Util.isEmployerLogin(loginPage)) {
			loginPage.clickEmployerBtn();
			String pageTitle = loginPage.getLoginPageTitle();
			Assert.assertEquals(pageTitle, "Login", "Login page not loaded correctly");
			
			loginPage.clickForgotPasswordLink();
			title = loginPage.getForgotPasswordPageTitle();
			Assert.assertEquals(title, "Forgot Password ", "Forgot Password page not loaded correctly.");
			
			loginPage.clickSignUpLinkOnLoginPage();
			title = loginPage.getSignUpPageTitle();
			Assert.assertEquals(title, "Sign Up as an Employer ", "Sign Up page not loaded correctly.");
		}
	}
	
	@Test(dataProvider = "getData")
	public void testSignUpRedirection(String firstName, String lastName, String email, String password, String country) {
		loginPage.clickLoginUpBtn();
		Util.handleStartupPages(loginPage, country);
		if (Util.isEmployerLogin(loginPage)) {
			loginPage.clickEmployerBtn();
			pageTitle = loginPage.getLoginPageTitle();
			Assert.assertEquals(pageTitle, "Login", "Login page not loaded correctly");
			loginPage.clickSignUpLinkOnLoginPage();
			String title = loginPage.getSignUpPageTitle();
			Assert.assertEquals(title, "Sign Up as an Employer ", "Sign Up page not loaded correctly.");
		}
	}


	@Test(dataProvider = "getData")
	public void testValidLogin(String firstName, String lastName, String email, String password, String country) {
		loginPage.clickLoginUpBtn();
		Util.handleStartupPages(loginPage, country);
		if (Util.isEmployerLogin(loginPage)) {
			loginPage.clickEmployerBtn();
			populateFormFields(email,password);
			loginPage.clickLoginBtnOnLoginPage();
			try {
				loginPage.locationPermissionAccess(Config.getProperty("LocationPermissionAccess"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String title = loginPage.getJobPostingsPageTitle();
		Assert.assertEquals(title, "My Job Postings"
				, "Login was not successfull");

	}

	@Test(dataProvider = "getData")
	public void testLoginButtonDisabled(String firstName, String lastName, String email, String password, String country) {
		loginPage.clickLoginUpBtn();
		Util.handleStartupPages(loginPage, country);
		if (Util.isEmployerLogin(loginPage)) {
			loginPage.clickEmployerBtn();
			populateFormFields(email,"");
			boolean isEnabled = loginPage.isLoginButtonOnLoginPageEnabled();
			Assert.assertFalse(isEnabled, "Login button was expected to be disabled since some fields don't have values, but the button is enabled.");
		}
	}

	@Test(dataProvider = "getData")
	public void testSignUpButtonEnabled(String firstName, String lastName, String email, String password, String country) {
		loginPage.clickLoginUpBtn();
		Util.handleStartupPages(loginPage, country);
		if (Util.isEmployerLogin(loginPage)) {
			loginPage.clickEmployerBtn();
			populateFormFields(email,password);
			boolean isEnabled = loginPage.isLoginButtonOnLoginPageEnabled();
			Assert.assertTrue(isEnabled, "Login button was expected to be enabled since all fields have values, but the button is disabled.");
		}
	}
	
	@Test(dataProvider = "getData")
	public void testEmptyUserNameValidiation(String firstName, String lastName, String email, String password, String country) {
		loginPage.clickLoginUpBtn();
		Util.handleStartupPages(loginPage, country);
		if (Util.isEmployerLogin(loginPage)) {
			loginPage.clickEmployerBtn();
			populateFormFields("",password);
			String emailValue = loginPage.validateEmail("");
			Assert.assertEquals(emailValue, "Please enter a valid email address", "Email addresss validation is not working as expected.");
			
			boolean isEnabled = loginPage.isLoginButtonOnLoginPageEnabled();
			Assert.assertFalse(isEnabled, "Login button was expected to be disabled since username field does not have value, but the button is enabled.");
		}
	}
	
	@Test(dataProvider = "getData")
	public void testEmptyPasswordValidiation(String firstName, String lastName, String email, String password, String country) {
		loginPage.clickLoginUpBtn();
		Util.handleStartupPages(loginPage, country);
		if (Util.isEmployerLogin(loginPage)) {
			loginPage.clickEmployerBtn();
			populateFormFields(email,"");
			String passwordValue = loginPage.validatePassword("");
			Assert.assertEquals(passwordValue, "Password must be atleast 6 characters", "Password validation is not working as expected.");
			
			boolean isEnabled = loginPage.isLoginButtonOnLoginPageEnabled();
			Assert.assertFalse(isEnabled, "Login button was expected to be disabled since password field does not have value, but the button is enabled.");
		}
	}
	
	

	@Test(dataProvider = "getData")
	public void testInvalidEmailAddressValidation(String firstName, String lastName, String email, String password, String country) {
		loginPage.clickLoginUpBtn();
		Util.handleStartupPages(loginPage, country);
		if (Util.isEmployerLogin(loginPage)) {
			loginPage.clickEmployerBtn();
			populateFormFields("abc@",password);
			String emailValue = loginPage.validateEmail("abc@");
			Assert.assertEquals(emailValue, "Please enter a valid email address", "Email addresss validation is not working as expected.");
		}
	}

	@Test(dataProvider = "getData")
	public void testInvalidPasswordValidation(String firstName, String lastName, String email, String password, String country) {
		loginPage.clickLoginUpBtn();
		Util.handleStartupPages(loginPage, country);
		if (Util.isEmployerLogin(loginPage)) {
			loginPage.clickEmployerBtn();
			populateFormFields(email,"abc");
			String passwordValue = loginPage.validatePassword("abc");
			Assert.assertEquals(passwordValue, "Password must be atleast 6 characters", "Password validation is not working as expected.");
		}
	}


	@Test(dataProvider = "getData")
	public void testForgotPassword(String firstName, String lastName, String email, String password, String country) {
		loginPage.clickLoginUpBtn();
		Util.handleStartupPages(loginPage, country);
		if (Util.isEmployerLogin(loginPage)) {
			loginPage.clickEmployerBtn();
			String pageTitle = loginPage.getLoginPageTitle();
			Assert.assertEquals(pageTitle, "Login", "Login page not loaded correctly");
			loginPage.clickForgotPasswordLink();
			loginPage.setEmailAddressforForgotPassword(email);
			String emailValue = loginPage.validateEmail(email);
			if (emailValue != null) 
				Assert.assertEquals(emailValue, "Please enter a valid email address", "Email addresss validation is not working as expected.");
			loginPage.clickSendBtnOnForgotPasswordPage();
			String message = loginPage.getEmailSendVerificationTextforForgotPassword();
			Assert.assertEquals(message, "We have sent a verification code to your email, " +email+" to reset your password. Please enter your 4-digit verification code below"
					, "Sending Verfication code to specified email failed ");

		}
	}


	/** Currently checking this case by validating if after clicking on login button, the user is still on login page. 
	 * The ideal way to check is to read the actual error message. However I was not able to find the id of error message using Appium Inspector
	 */
	@Test(dataProvider = "getData")
	public void testNotRegisteredEmailUsedForlogin(String firstName, String lastName, String email, String password, String country) {
		loginPage.clickLoginUpBtn();
		Util.handleStartupPages(loginPage, country);
		if (Util.isEmployerLogin(loginPage))  {
			loginPage.clickEmployerBtn();
			pageTitle = loginPage.getLoginPageTitle();
			Assert.assertEquals(pageTitle, "Login", "Login page not loaded correctly");
			populateFormFields("nbbb@nn.com",password);
			loginPage.clickLoginBtnOnLoginPage();
			pageTitle = loginPage.getLoginPageTitle();
			Assert.assertEquals(pageTitle, "Login", "Non Registered Email validation for login is not working as expected.");
		}

	}

	



}
