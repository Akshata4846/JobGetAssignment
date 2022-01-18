package com.jobget.testcases;

import java.io.IOException;
import java.util.Iterator;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.jobget.pages.SignUpPage;
import com.jobget.util.Config;
import com.jobget.util.MobileOTPValidator;
import com.jobget.util.Util;



public class SignUpPageTest {
	SignUpPage signUpPage;
	final String SHEETNAME = "EmployerDetails";


	@DataProvider
	public Iterator<String[]> getData() throws IOException {
		return Util.getSignUpSheetData(SHEETNAME);
	}

	@BeforeMethod
	public void setUp() {
		signUpPage = new SignUpPage();
	}

	@AfterMethod
	public void tearDown() {
		signUpPage.driver.quit();
	}


	private void populateFormFields (String firstName, String lastName, String email, String password ) {
		signUpPage.setFirstName(firstName);
		signUpPage.setlastName(lastName);
		signUpPage.setEmail(email);
		signUpPage.setPassword(password);
	}


	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 * @param country
	 * This test case is used to validate the presence of mandatory elements on Sign Up page
	 */
	@Test(priority = 1, dataProvider = "getData")
	public void testElementsPresentOnPage(String firstName, String lastName, String email, 
			String password, String country, String companyName, String companyWebsite
			, String countryCode, String mobileNumber) { 
		boolean result;
		signUpPage.clickSignUpBtn();
		Util.handleStartupPages(signUpPage, country);
		if (Util.isEmployerSignUp(signUpPage)) {
			String pageTitle = signUpPage.getSignUpTypePageTitle();
			Assert.assertEquals(pageTitle, "I am looking to...", "SignUp type selection page not loaded correctly");
			if (true) {
				signUpPage.clickSignUpForEmployerBtn();
				//loginPage.clickEmployerBtn();
				result = signUpPage.isFirstNameFieldDisplayed();
				Assert.assertTrue(result, "FisrtName mandatory field on sign up page not present");

				result = signUpPage.isLastNameFieldDisplayed();
				Assert.assertTrue(result, "Last name mandatory field on sign up page not present");

				result = signUpPage.isEmailFieldDisplayed();
				Assert.assertTrue(result, "Email mandatory field on sign up page not present");

				result = signUpPage.isPasswordFieldDisplayed();
				Assert.assertTrue(result, "Password mandatory field on sign up page not present");
			}
		}

	}

	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 * @param country
	 * This test case checks if user is redirected to Job Seekers Sign Up page on 
	 * clicking "Oops, I'm a Job Seeker" from registration page
	 */
	@Test(priority = 4, dataProvider = "getData")
	public void testJobSeekerRedirection(String firstName, String lastName, String email, 
			String password, String country, String companyName, String companyWebsite
			, String countryCode, String mobileNumber) {
		String pageTitle;
		signUpPage.clickSignUpBtn();
		Util.handleStartupPages(signUpPage, country);
		if (Util.isEmployerSignUp(signUpPage)) {
			pageTitle = signUpPage.getSignUpTypePageTitle();
			Assert.assertEquals(pageTitle, "I am looking to...", "SignUp type selection page not loaded correctly");
			if (true) {
				signUpPage.clickSignUpForEmployerBtn();
				signUpPage.clickJobSeekerLink();
				pageTitle = signUpPage.getJobSeekerPageTitle();
				Assert.assertEquals(pageTitle, "Sign Up as a Job Seeker", "JobSeeker Sign Up page not loaded correctly");

			}
		}
	}



	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 * @param country
	 * This test case validates that already registered email address should not be allowed to register again
	 */
	@Test(priority = 3, dataProvider = "getData")
	public void testAlreadyRegisteredEmail(String firstName, String lastName, String email, 
			String password, String country, String companyName, String companyWebsite
			, String countryCode, String mobileNumber) { 
		String pageTitle;
		String text;
		signUpPage.clickSignUpBtn();
		Util.handleStartupPages(signUpPage, country);
		if (Util.isEmployerSignUp(signUpPage)) {
			pageTitle = signUpPage.getSignUpTypePageTitle();
			Assert.assertEquals(pageTitle, "I am looking to...", "SignUp type selection page not loaded correctly");
			if (true) {
				signUpPage.clickSignUpForEmployerBtn();
				signUpPage.setFirstName(firstName);
				signUpPage.setlastName(lastName);
				signUpPage.setEmail("test@abc.com"); //hardcoded for already registered user. TODO:handle from csv file
				signUpPage.setPassword(password);
				signUpPage.clickSignUpBtnOnSignUpPage();
				text = signUpPage.getAlreadyRegisteredEmailPopUpText();
				Assert.assertEquals(text, "This email already exists. Try logging in or resetting your password", "Already existed email allowed to registered again");
				signUpPage.clickOkBtnOnAlreadyRegisteredEmailPopUp();
			}
		}
	}

	public String getOTPFromTwilioNumber() throws IOException {
		String otpNumber = MobileOTPValidator.setOTP();
		return otpNumber;
	}



	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 * @param country
	 * @throws IOException
	 * This test case checks a successful registration scenario when all input fields are valid
	 */
	@Test(priority = 2, dataProvider = "getData")
	public void testValidEmployerSignUp(String firstName, String lastName, String email, 
			String password, String country, String companyName, String companyWebsite
			, String countryCode, String mobileNumber)  throws IOException {
		email="test3@ab.com";
		signUpPage.clickSignUpBtn();
		Util.handleStartupPages(signUpPage, country);
		if (Util.isEmployerSignUp(signUpPage)) {
			String pageTitle = signUpPage.getSignUpTypePageTitle();
			Assert.assertEquals(pageTitle, "I am looking to...", "SignUp type selection page not loaded correctly");
			if (true) {
				signUpPage.clickSignUpForEmployerBtn();
				signUpPage.setFirstName(firstName);
				signUpPage.setlastName(lastName);
				signUpPage.setEmail(email);
				signUpPage.setPassword(password);
				signUpPage.clickSignUpBtnOnSignUpPage();
				signUpPage.setCompanyName(companyName);
				signUpPage.setCompanyWebsite("https://" +companyWebsite+ ".com");
				signUpPage.clickOnPhoneNumberCountryCodeDropDown();
				signUpPage.setCountryName(country);
				signUpPage.selectCountryCode("+91");
				signUpPage.setPhoneNumber(Config.getProperty("PhoneNumber"));
				
				/**I was also trying to use Twilio api to test this case using mock number for OTP automation testing. However I was not able to pass the phone number provided by Twilio in the JobGet app.  
				 * When I enter this number in the Phone number field I get a popup saying "Please enter a VOIP number". This same number works when I tried registering 
				 * in jobget app from my actual mobile phone. Also I tried registering with the same number on other portal like amazon and it works. 
				 * Was not sure why AVD was not taking the same number. The code to input the OTP number is available under Util package. However I was not able to actually test the commented section because of the number restriction.
				 * If I use the password(8579904918) provided to me during the assignment I get a message saying "database error" **/
				
//				String otp = getOTPFromTwilioNumber();
//				signUpPage.setOTP("1234");  //currently hardcoded as twilio generated number is not being accepted on app hence my code for reading OTP automatically is not used here.
//				String successContent = signUpPage.getRegistrationSuccessContent();
//				Assert.assertEquals(successContent, "You have successfully verified your number.", "Mobile Number verification not working as expected");
//				signUpPage.okayButtonOnSucessfulRegistration();
//				signUpPage.locationPermissionAccess(Config.getProperty("LocationPermissionAccess"));
//				String title = signUpPage.getJobPostingsPageTitle();
//				Assert.assertEquals(title, "My Job Postings"
//						, "New Registration was not successful");		
			}

		}
	}



	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 * @param country
	 * This method checks if Sign Up button is disabled if all mandatory fields are not provided
	 */
	@Test(priority = 5, dataProvider = "getData")
	public void testSignUpButtonDisabled(String firstName, String lastName, String email, 
			String password, String country, String companyName, String companyWebsite
			, String countryCode, String mobileNumber) {
		signUpPage.clickSignUpBtn();
		Util.handleStartupPages(signUpPage, country);
		if (Util.isEmployerSignUp(signUpPage)) {
			String pageTitle = signUpPage.getSignUpTypePageTitle();
			Assert.assertEquals(pageTitle, "I am looking to...", "SignUp type selection page not loaded correctly");
			if (true) {
				signUpPage.clickSignUpForEmployerBtn();
				populateFormFields(firstName, lastName, email, "");
				boolean isEnabled = signUpPage.isSignUpBtnOnSignUpPageEnabled();
				Assert.assertFalse(isEnabled, "Sign up button was expected to be disabled since some fields don't have values, but the button is enabled.");
			}
		}
	}



	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 * @param country
	 * This method checks if Sign Up button is enabled if all mandatory fields are provided
	 */
	@Test(priority = 6, dataProvider = "getData")
	public void testSignUpButtonEnabled(String firstName, String lastName, String email, 
			String password, String country, String companyName, String companyWebsite
			, String countryCode, String mobileNumber) {
		signUpPage.clickSignUpBtn();
		Util.handleStartupPages(signUpPage, country);
		if (Util.isEmployerSignUp(signUpPage)) {
			String pageTitle = signUpPage.getSignUpTypePageTitle();
			Assert.assertEquals(pageTitle, "I am looking to...", "SignUp type selection page not loaded correctly");
			if (true) {
				signUpPage.clickSignUpForEmployerBtn();
				populateFormFields(firstName, lastName, email, password);
				boolean isEnabled = signUpPage.isSignUpBtnOnSignUpPageEnabled();
				Assert.assertTrue(isEnabled, "Sign up button was expected to be enabled since all fields have values, but the button is disabled.");
			}
		}
	}


	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 * @param country
	 * This method checks if email validation message is thrown when incorrect email address is used
	 */
	@Test(priority = 7, dataProvider = "getData")
	public void testEmailAddressValidation(String firstName, String lastName, String email, 
			String password, String country, String companyName, String companyWebsite
			, String countryCode, String mobileNumber) {
		signUpPage.clickSignUpBtn();
		Util.handleStartupPages(signUpPage, country);
		if (Util.isEmployerSignUp(signUpPage)) {
			String pageTitle = signUpPage.getSignUpTypePageTitle();
			Assert.assertEquals(pageTitle, "I am looking to...", "SignUp type selection page not loaded correctly");
			if (true) {
				signUpPage.clickSignUpForEmployerBtn();
				populateFormFields(firstName, lastName, "testemail@", password);
				String emailValue = signUpPage.validateEmail();
				Assert.assertEquals(emailValue, "Please enter a valid email address", "Email addresss validation is not working as expected.");
			}
		}
	}

	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 * @param country
	 * This method checks if password validation message is thrown when incorrect password is used
	 */
	@Test(priority = 8, dataProvider = "getData")
	public void testPasswordValidation(String firstName, String lastName, String email, 
			String password, String country, String companyName, String companyWebsite
			, String countryCode, String mobileNumber) {
		signUpPage.clickSignUpBtn();
		Util.handleStartupPages(signUpPage, country);
		if (Util.isEmployerSignUp(signUpPage)) {
			String pageTitle = signUpPage.getSignUpTypePageTitle();
			Assert.assertEquals(pageTitle, "I am looking to...", "SignUp type selection page not loaded correctly");
			if (true) {
				signUpPage.clickSignUpForEmployerBtn();
				populateFormFields(firstName, lastName, email, "test");
				String passwordValue = signUpPage.validatePassword();
				Assert.assertEquals(passwordValue, "Password must be atleast 6 characters", "Password must be atleast 6 characters");
			}
		}

	}

	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 * @param country
	 * This method checks if password validation message is thrown when incorrect password is used
	 */
	@Test(priority = 9, dataProvider = "getData")
	public void testTermsPageRedirectiron(String firstName, String lastName, String email, 
			String password, String country, String companyName, String companyWebsite
			, String countryCode, String mobileNumber) {
		signUpPage.clickSignUpBtn();
		Util.handleStartupPages(signUpPage, country);
		if (Util.isEmployerSignUp(signUpPage)) {
			String pageTitle = signUpPage.getSignUpTypePageTitle();
			Assert.assertEquals(pageTitle, "I am looking to...", "SignUp type selection page not loaded correctly");
			if (true) {
				signUpPage.clickSignUpForEmployerBtn();
				signUpPage.clickOnTermsLinkFromSignUpPage();
				String title = signUpPage.getTermsPageTitle();
				Assert.assertEquals(title, "Terms & Conditions", "Terms page from sign up not redirected correctly");
			}
		}

	}
}
