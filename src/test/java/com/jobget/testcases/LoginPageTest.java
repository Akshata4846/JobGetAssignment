package com.jobget.testcases;

import java.io.IOException;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Iterator;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.jobget.helper.CSVHelper;
import com.jobget.pages.HomePage;
import com.jobget.pages.LoginPage;
import com.jobget.util.Config;
import com.jobget.util.EmailOtpValidator;
import com.jobget.util.Util;
import com.relevantcodes.extentreports.LogStatus;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;

//@Listeners(com.jobget.util.Listener.class)



public class LoginPageTest extends LoginTestBase {

	HomePage homePage;
	private String pageTitle;
	final String SHEETNAME = "LoginDetails";


	@DataProvider
	public Iterator<String[]> getData() throws IOException {
		return Util.getLoginSheetData(SHEETNAME);
	}

	@BeforeMethod
	@Parameters({"deviceName","platFormVersion", "UDID"})
	public void setUp(String deviceName, String platformVersion, String UDID, String port) {
		loginPage = new LoginPage(null, deviceName, platformVersion, UDID, port);
		homePage = new HomePage(loginPage.getDriver(), deviceName, platformVersion, UDID, port);
	}

	@AfterMethod
	public void tearDown(ITestResult iTestResult) {
		if (iTestResult.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, "Test case failed is: " + iTestResult.getName());
			test.log(LogStatus.FAIL, "Test case failed is: " + iTestResult.getThrowable());
			try {
				String screenshot = Util.takeScreenshot(loginPage.getDriver(), iTestResult.getName() + "_FAILED");
				test.addScreenCapture(screenshot);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if (iTestResult.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, "Test case skipped is: " + iTestResult.getName());
		}
		else if (iTestResult.getStatus() == ITestResult.SUCCESS) {
			test.log(LogStatus.PASS, "Test case passed is: " + iTestResult.getName());
		}
		loginPage.getDriver().quit();
	}



	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 * @param country
	 * This test case checks if all mandatory input fields are present on login page
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	@Test (priority =1, dataProvider = "getData")
	public void testElementsPresentOnPage(String email, String password, String country) throws IOException, InterruptedException {
		boolean result;
		test = extent.startTest("testElementsPresentOnPage");
		prepareForLogin(country);
		result = loginPage.isUserNameFieldDisplayed();
		Assert.assertTrue(result, "UserName mandatory field on login page not present");

		result = loginPage.isPasswordFieldDisplayed();
		Assert.assertTrue(result, "Password mandatory field on login page not present");

		result = loginPage.isForgotPasswordLinkDisplayed();
		Assert.assertTrue(result, "Forgot Password link on login page not present");

		result = loginPage.isSignUpLinkDisplayed();
		Assert.assertTrue(result, "SignUp button on login page not present");	

	}

	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 * @param country
	 * This test case checks if user is redirected to Forgot password page after clicking
	 * on Forgot Password link on login page 
	 * @throws InterruptedException 
	 * @throws IOException 
	 * @throws SecurityException 
	 */
	@Test (priority =11, dataProvider = "getData")
	public void testForgotPasswordRedirection(String email, String password, String country) throws InterruptedException, SecurityException, IOException {
		String pageText;
		test = extent.startTest("testForgotPasswordRedirection");
		prepareForLogin(country);
		loginPage.clickForgotPasswordLink();
		Util.takeScreenshot(loginPage.getDriver(), new Object() {}
		.getClass()
		.getEnclosingMethod()
		.getName());
		Thread.sleep(5000);
		pageText = loginPage.getForgotPasswordPageText();
		Assert.assertEquals(pageText, "Please enter your registered email address. We will send you a verification code to reset your password.", "Forgot Password page not loaded correctly.");
	}


	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 * @param country
	 * This test case checks if user is redirected to Employer Sign Up page after clicking
	 * on Sign Up link on login page  
	 * @throws IOException 
	 * @throws SecurityException 
	 */
	@Test (priority =4, dataProvider = "getData")
	public void testSignUpRedirection(String email, String password, String country) throws SecurityException, IOException {
		loginPage.clickLoginUpBtn();
		test = extent.startTest("testSignUpRedirection");
		prepareForLogin(country);
		loginPage.clickSignUpLinkOnLoginPage();
		Util.takeScreenshot(loginPage.getDriver(), new Object() {}
		.getClass()
		.getEnclosingMethod()
		.getName());
		String title = loginPage.getSignUpPageTitle();
		Assert.assertEquals(title, "Sign Up as an Employer ", "Sign Up page not loaded correctly.");
	}



	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 * @param country
	 * This test case checks if login is successful if user provides all valid values 
	 * @throws IOException 
	 * @throws SecurityException 
	 */
	@Test (priority =2, dataProvider = "getData")
	public void testValidLogin(String email, String password, String country) throws SecurityException, IOException {
		test = extent.startTest("testValidLogin");
		prepareForLogin(country);
		populateFormFields(email,password);
		loginPage.clickLoginBtnOnLoginPage();
		try {
			loginPage.locationPermissionAccess(Config.getProperty("LocationPermissionAccess"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Util.takeScreenshot(loginPage.getDriver(), new Object() {}
		.getClass()
		.getEnclosingMethod()
		.getName());
		String title = homePage.getJobPostingsPageTitle();
		Assert.assertEquals(title, "My Job Postings"
				, "Login was not successfull");

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
	 * @throws IOException 
	 * @throws SecurityException 
	 */
	@Test (priority =3, dataProvider = "getData")
	public void testNotRegisteredEmailUsedForlogin(String email, String password, String country) throws SecurityException, IOException {
		test = extent.startTest("testNotRegisteredEmailUsedForlogin");
		prepareForLogin(country);
		populateFormFields("automation9878@automation.com",password);//Hard coded a non registered email id
		loginPage.clickLoginBtnOnLoginPage();
		Util.takeScreenshot(loginPage.getDriver(), new Object() {}
		.getClass()
		.getEnclosingMethod()
		.getName());
		pageTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(pageTitle, "Login", "Non Registered Email validation for login is not working as expected.");
	}





	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 * @param country
	 * This test case checks if Login button is disabled if all mandatory fields are not provided
	 * @throws IOException 
	 * @throws SecurityException 
	 */
	@Test (priority =10, dataProvider = "getData")
	public void testLoginButtonDisabled(String email, String password, String country) throws SecurityException, IOException {
		test = extent.startTest("testLoginButtonDisabled");
		prepareForLogin(country);
		populateFormFields(email,"");
		boolean isEnabled = loginPage.isLoginButtonOnLoginPageEnabled();
		Util.takeScreenshot(loginPage.getDriver(), new Object() {}
		.getClass()
		.getEnclosingMethod()
		.getName());
		Assert.assertFalse(isEnabled, "Login button was expected to be disabled since some fields don't have values, but the button is enabled.");
	}


	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 * @param country
	 * This test case checks if Login button is enabled if all mandatory fields are provided
	 * @throws IOException 
	 * @throws SecurityException 
	 */
	@Test (priority =5, dataProvider = "getData")
	public void testLoginUpButtonEnabled(String email, String password, String country) throws SecurityException, IOException {
		test = extent.startTest("testLoginUpButtonEnabled");
		prepareForLogin(country);
		populateFormFields(email,password);
		boolean isEnabled = loginPage.isLoginButtonOnLoginPageEnabled();
		Util.takeScreenshot(loginPage.getDriver(), new Object() {}
		.getClass()
		.getEnclosingMethod()
		.getName());
		Assert.assertTrue(isEnabled, "Login button was expected to be enabled since all fields have values, but the button is disabled.");
	}


	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 * @param country
	 * This test case checks if email validation message is thrown when empty email address is used
	 * @throws IOException 
	 * @throws SecurityException 
	 */
	@Test (priority =6, dataProvider = "getData")
	public void testEmptyUserNameValidiation(String email, String password, String country) throws SecurityException, IOException {
		test = extent.startTest("testEmptyUserNameValidiation");
		prepareForLogin(country);
		populateFormFields("",password);
		String emailValue = loginPage.validateEmail("");
		Assert.assertEquals(emailValue, "Please enter email an address", "Email addresss validation is not working as expected.");
		Util.takeScreenshot(loginPage.getDriver(), new Object() {}
		.getClass()
		.getEnclosingMethod()
		.getName());
		boolean isEnabled = loginPage.isLoginButtonOnLoginPageEnabled();
		Assert.assertFalse(isEnabled, "Login button was expected to be disabled since username field does not have value, but the button is enabled.");
	}


	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 * @param country
	 * This test case checks if password validation message is thrown when empty password is used
	 * @throws IOException 
	 * @throws SecurityException 
	 */
	@Test (priority =7, dataProvider = "getData")
	public void testEmptyPasswordValidiation(String email, String password, String country) throws SecurityException, IOException {
		test = extent.startTest("testEmptyPasswordValidiation");
		prepareForLogin(country);
		populateFormFields(email,"");
		String passwordValue = loginPage.validatePassword("");
		Assert.assertEquals(passwordValue, "Please enter password", "Password validation is not working as expected.");
		Util.takeScreenshot(loginPage.getDriver(), new Object() {}
		.getClass()
		.getEnclosingMethod()
		.getName());
		boolean isEnabled = loginPage.isLoginButtonOnLoginPageEnabled();
		Assert.assertFalse(isEnabled, "Login button was expected to be disabled since password field does not have value, but the button is enabled.");
	}




	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 * @param country
	 * This test case checks if email validation message is thrown when incorrect email address is used
	 * @throws IOException 
	 * @throws SecurityException 
	 */
	@Test (priority =8, dataProvider = "getData")
	public void testInvalidEmailAddressValidation(String email, String password, String country) throws SecurityException, IOException {
		test = extent.startTest("testInvalidEmailAddressValidation");
		loginPage.clickLoginUpBtn();
		Util.handleStartupPages(loginPage, country);
		if (Util.isEmployerLogin(loginPage)) {
			loginPage.clickEmployerBtn();
			populateFormFields("abc@",password);
			String emailValue = loginPage.validateEmail("abc@");
			Util.takeScreenshot(loginPage.getDriver(), new Object() {}
			.getClass()
			.getEnclosingMethod()
			.getName());
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
	@Test (priority =9, dataProvider = "getData")
	public void testInvalidPasswordValidation(String email, String password, String country) {
		test = extent.startTest("testInvalidPasswordValidation");
		prepareForLogin(country);
		populateFormFields(email,"abc");
		String passwordValue = loginPage.validatePassword("abc");
		Assert.assertEquals(passwordValue, "Password must be atleast 6 characters", "Password validation is not working as expected.");
	}



	/**
	 * @param email
	 * @throws InterruptedException
	 * This method resets user password after taking in email address from user and provding the otp
	 * @throws IOException 
	 * @throws SecurityException 
	 */
	public void resetPassword(String email) throws InterruptedException, SecurityException, IOException {
		String newPassword = Util.generateRandomPassword();
		String confirmPassword = newPassword;
		String subjectKeyword = "Forgot Password";
		String emailPassword = null;
		try {
			emailPassword = Config.getProperty("EmailPassword");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String fromEmail = "support@jobget.com";
		String bodySearchText = "Here is your 4 digit password reset verification code";
		//String text = loginPage.validateEmailOnForgotPasswordPage(email);
		//if (text != null)
		//Assert.assertEquals(text, "Please enter a valid email address", "Email addresss validation is not working as expected.");
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
			Thread.sleep(9000);
			loginPage.inputOTP(otp);
			loginPage.resetPassword(newPassword, confirmPassword);
			Util.takeScreenshot(loginPage.getDriver(), new Object() {}
			.getClass()
			.getEnclosingMethod()
			.getName());
			String successText = loginPage.getResetPasswordSuccessContentText();
			Assert.assertEquals(successText, "Password has been Reset", "Password was not reset correctly");
			loginPage.clickOnLoginButtonAfterPassReset();
			String loginPageTitle = loginPage.getLoginPageTitle();
			Assert.assertEquals(loginPageTitle, "Login", "Login page not loaded correctly");
			try {
				CSVHelper.setExcelCellData(SHEETNAME, newPassword);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * @param email
	 * @param password
	 * @param country
	 * This test case tests the verification code resend functionality where if user selects edit
	 * button on confirmation prompt then the user should be redirected to Forgot password page again
	 * @throws InterruptedException 
	 * @throws IOException 
	 * @throws SecurityException 
	 */
	@Test (priority =13, dataProvider = "getData")
	public void testPasswordResetResendCodeEditAction(String email, String password, String country) throws InterruptedException, SecurityException, IOException {
		test = extent.startTest("testPasswordResetResendCodeEditAction");
		prepareForLogin(country);
		loginPage.clickForgotPasswordLink();
		loginPage.setEmailAddressforForgotPassword(email);
		loginPage.clickSendBtnOnForgotPasswordPage();
		loginPage.clickResendVerificationCodeLink();
		String title = loginPage.getPopUpTextForResendVerificationCode();
		Assert.assertEquals(title, "EMAIL CONFIRMATION", "Resend button not working as expected or perhaps not clicked properly");
		String emailAddress = loginPage.getEmailOnResendCodePopUp();
		Assert.assertEquals(email.toUpperCase(), emailAddress, "Email displayed on code verification popup incorrect");
		loginPage.clickEditOnResendCodePopUp();
		//Redirect to forgot password page
		Util.takeScreenshot(loginPage.getDriver(), new Object() {}
		.getClass()
		.getEnclosingMethod()
		.getName());
		String pageText = loginPage.getForgotPasswordPageText();
		Assert.assertEquals(pageText, "Please enter your registered email address. We will send you a verification code to reset your password.", "Forgot Password page not loaded correctly.");
		loginPage.setEmailAddressforForgotPassword(email);
		loginPage.clickSendBtnOnForgotPasswordPage();
		Thread.sleep(2000);
		//Reset password
		try {
			resetPassword(email);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}



	/**
	 * @param email
	 * @param password
	 * @param country
	 * This test case tests the verification code resend functionality where if user selects Yes
	 * button on confirmation prompt then the user should be redirected to Forgot password page again
	 * @throws IOException 
	 * @throws SecurityException 
	 */
	@Test (priority =14, dataProvider = "getData")
	public void testPasswordResetResendCodeYesAction(String email, String password, String country) throws SecurityException, IOException {
		test = extent.startTest("testPasswordResetResendCodeYesAction");
		prepareForLogin(country);
		loginPage.clickForgotPasswordLink();
		loginPage.setEmailAddressforForgotPassword(email);
		loginPage.clickSendBtnOnForgotPasswordPage();
		loginPage.clickResendVerificationCodeLink();
		String title = loginPage.getPopUpTextForResendVerificationCode();
		Assert.assertEquals(title, "EMAIL CONFIRMATION", "Resend button not working as expected or perhaps not clicked properly");
		String emailAddress = loginPage.getEmailOnResendCodePopUp();
		Assert.assertEquals(email.toUpperCase(), emailAddress, "Email displayed on code verification popup incorrect");
		loginPage.clickYesOnResendCodePopUp();
		//Reset password
		try {
			resetPassword(email);
		} catch (InterruptedException e) {
			e.printStackTrace();
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
	 * @throws InterruptedException 
	 */
	@Test (priority =12, dataProvider = "getData")
	public void testForgotPassword(String email, String password, String country) throws IOException, InterruptedException {
		test = extent.startTest("testForgotPassword");
		prepareForLogin(country);
		loginPage.clickForgotPasswordLink();
		loginPage.setEmailAddressforForgotPassword(email);
		loginPage.clickSendBtnOnForgotPasswordPage();
		Thread.sleep(2000);
		//Reset Password
		resetPassword(email);
	}


	@Test (priority =15, dataProvider = "getData")
	public void testLoginWithFaceBook(String email, String password, String country) throws Exception {
		test = extent.startTest("testLoginWithFaceBook");
		prepareForLogin(country);
		loginPage.clickContinueWithFaceBookButton();
		WebDriverWait wait = new WebDriverWait(loginPage.getDriver(),30);
		wait.until(ExpectedConditions.visibilityOf(loginPage.getFaceBookPageText()));
		String continueButtonText = loginPage.facebookLogin(email, Config.getProperty("FaceBookPassword"));
		try {
			if (continueButtonText.contains(Config.getProperty("FaceBookUserName")) ) {
				loginPage.clickContinueBuuton();
				String title = homePage.getJobPostingsPageTitle();
				Assert.assertEquals(title, "My Job Postings"
						, "Login was not successfull");		
			}
			else {
				throw new Exception("Username not matching the required value on continue button");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}









}
