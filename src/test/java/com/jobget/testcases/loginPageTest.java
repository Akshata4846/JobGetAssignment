package com.jobget.testcases;

import java.io.IOException;
import java.util.Iterator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.jobget.pages.LoginPage;
import com.jobget.util.Config;
import com.jobget.util.EmailOtpValidator;
import com.jobget.util.Util;

import org.testng.Assert;

public class loginPageTest {
	LoginPage loginPage;
	private String pageTitle;
	final String SHEETNAME = "LoginDetails";


	@DataProvider
	public Iterator<String[]> getData() throws IOException {
		return Util.getLoginSheetData(SHEETNAME);
	}


	private void populateFormFields (String email, String password ) {
		loginPage.setUserName(email);
		loginPage.setPassword(password);
	}


	@BeforeMethod
	public void setUp() {
		loginPage = new LoginPage();
	}


	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 * @param country
	 * This test case checks if all mandatory input fields are present on login page
	 */
	@Test(priority =1, dataProvider = "getData")
	public void testElementsPresentOnPage(String email, String password, String country) {
		boolean result;
		loginPage.clickLoginUpBtn();
		Util.handleStartupPages(loginPage, country);
		if (Util.isEmployerLogin(loginPage)) {
			loginPage.clickEmployerBtn();
			String loginPageTitle = loginPage.getLoginPageTitle();
			Assert.assertEquals(loginPageTitle, "Login", "Login page not loaded correctly");
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

	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 * @param country
	 * This test case checks if user is redirected to Forgot password page after clicking
	 * on Forgot Password link on login page 
	 */
	@Test(priority =12, dataProvider = "getData")
	public void testForgotPasswordRedirection(String email, String password, String country) {
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
		}
	}

	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 * @param country
	 * This test case checks if user is redirected to Employer Sign Up page after clicking
	 * on Sign Up link on login page  
	 */
	@Test(priority =4, dataProvider = "getData")
	public void testSignUpRedirection(String email, String password, String country) {
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


	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 * @param country
	 * This test case checks if login is successful if user provides all valid values 
	 */
	@Test(priority =2, dataProvider = "getData")
	public void testValidLogin(String email, String password, String country) {
		loginPage.clickLoginUpBtn();
		Util.handleStartupPages(loginPage, country);
		if (Util.isEmployerLogin(loginPage)) {
			loginPage.clickEmployerBtn();
			String loginPageTitle = loginPage.getLoginPageTitle();
			Assert.assertEquals(loginPageTitle, "Login", "Login page not loaded correctly");
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

	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 * @param country
	 * This test case checks if Login button is disabled if all mandatory fields are not provided
	 */
	@Test(priority =11, dataProvider = "getData")
	public void testLoginButtonDisabled(String email, String password, String country) {
		loginPage.clickLoginUpBtn();
		Util.handleStartupPages(loginPage, country);
		if (Util.isEmployerLogin(loginPage)) {
			loginPage.clickEmployerBtn();
			populateFormFields(email,"");
			boolean isEnabled = loginPage.isLoginButtonOnLoginPageEnabled();
			Assert.assertFalse(isEnabled, "Login button was expected to be disabled since some fields don't have values, but the button is enabled.");
		}
	}

	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 * @param country
	 * This test case checks if Login button is enabled if all mandatory fields are not provided
	 */
	@Test(priority =5, dataProvider = "getData")
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

	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 * @param country
	 * This test case checks if email validation message is thrown when empty email address is used
	 */
	@Test(priority =6, dataProvider = "getData")
	public void testEmptyUserNameValidiation(String email, String password, String country) {
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

	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 * @param country
	 * This test case checks if password validation message is thrown when empty password is used
	 */
	@Test(priority =7, dataProvider = "getData")
	public void testEmptyPasswordValidiation(String email, String password, String country) {
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



	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 * @param country
	 * This test case checks if email validation message is thrown when incorrect email address is used
	 */
	@Test(priority =8, dataProvider = "getData")
	public void testInvalidEmailAddressValidation(String email, String password, String country) {
		loginPage.clickLoginUpBtn();
		Util.handleStartupPages(loginPage, country);
		if (Util.isEmployerLogin(loginPage)) {
			loginPage.clickEmployerBtn();
			populateFormFields("abc@",password);
			String emailValue = loginPage.validateEmail("abc@");
			Assert.assertEquals(emailValue, "Please enter a valid email address", "Email addresss validation is not working as expected.");
		}
	}

	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 * @param country
	 * This test case checks if password validation message is thrown when invalid password is used
	 */
	@Test(priority =9, dataProvider = "getData")
	public void testInvalidPasswordValidation(String email, String password, String country) {
		loginPage.clickLoginUpBtn();
		Util.handleStartupPages(loginPage, country);
		if (Util.isEmployerLogin(loginPage)) {
			loginPage.clickEmployerBtn();
			populateFormFields(email,"abc");
			String passwordValue = loginPage.validatePassword("abc");
			Assert.assertEquals(passwordValue, "Password must be atleast 6 characters", "Password validation is not working as expected.");
		}
	}


	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 * @param country
	 * This test case checks if user is able to reset password after clicking on Forgot Password link on Login page
	 * @throws IOException 
	 */
	@Test(priority =10, dataProvider = "getData")
	public void testForgotPassword(String email, String password, String country) throws IOException {
		String subjectKeyword = "Forgot Password";
		String emailPassword = Config.getProperty("EmailPassword");
		String fromEmail = "support@jobget.com";
		String bodySearchText = "Here is your 4 digit password reset verification code";
		loginPage.clickLoginUpBtn();
		Util.handleStartupPages(loginPage, country);
		if (Util.isEmployerLogin(loginPage)) {
			loginPage.clickEmployerBtn();
			String pageTitle = loginPage.getLoginPageTitle();
			Assert.assertEquals(pageTitle, "Login", "Login page not loaded correctly");
			loginPage.clickForgotPasswordLink();
			loginPage.setEmailAddressforForgotPassword(email);
			//String text = loginPage.validateEmailOnForgotPasswordPage(email);
			//if (text != null)
			//Assert.assertEquals(text, "Please enter a valid email address", "Email addresss validation is not working as expected.");
			loginPage.clickSendBtnOnForgotPasswordPage();
			String message = loginPage.getEmailSendVerificationTextforForgotPassword();
			if (message.contains(email +" to reset your password")) {
				EmailOtpValidator emailOtpValidator = new EmailOtpValidator();
				String otp = null;
				try {
					otp = emailOtpValidator.getVerificationCode
							(email, emailPassword, subjectKeyword, fromEmail, bodySearchText);
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("OTP ----> " + otp); //To be removed. Added only for debugging
				loginPage.inputOTP(otp);
				loginPage.resetPassword(Config.getProperty("NewPassword"), Config.getProperty("ConfirmPassword"));
				String successText = loginPage.getResetPasswordSuccessContentText();
				Assert.assertEquals(successText, "Password has been Reset", "Password was not reset correctly");
				loginPage.clickOnLoginButtonAfterPassReset();
				String loginPageTitle = loginPage.getLoginPageTitle();
				Assert.assertEquals(loginPageTitle, "Login", "Login page not loaded correctly");
			}

		}
	}


	/** Currently checking this case by validating if after clicking on login button, the user is still on login page. 
	 * The ideal way to check is to read the actual error message. However I was not able to find the id of error message using Appium Inspector
	 */
	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 * @param country
	 * This test case checks whether login fails if non registered email address is used
	 */
	@Test(priority =3, dataProvider = "getData")
	public void testNotRegisteredEmailUsedForlogin(String email, String password, String country) {
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
