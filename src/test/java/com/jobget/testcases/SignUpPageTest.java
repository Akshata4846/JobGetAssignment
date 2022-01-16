package com.jobget.testcases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jobget.helper.CSVHelper;
import com.jobget.pages.LaunchPage;
import com.jobget.pages.SignUpPage;
import com.jobget.util.Config;



public class SignUpPageTest {
	SignUpPage signUpPage;
	
	private static final String SIGN_UP_AS_EMPLOYER_STRING = "Sign up as an Employer";
	
	@DataProvider
	public Iterator<String[]> getData() throws IOException {
		final String SHEETNAME = "EmployerDetails";
		ArrayList<String[]> bodyData = CSVHelper.getSheetData(SHEETNAME);
		return bodyData.iterator();
	}
	
	@BeforeMethod
	public void setUp() {
		signUpPage = new SignUpPage();
	}
	
	private void handleStartupPages() {
		try {
			signUpPage.locationPermissionAccess(Config.getProperty("LocationPermissionAccess"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		signUpPage.selectContacts("India");
	}
	
	private void populateFormFields (String firstName
			, String lastName, String email, String password ) {
		signUpPage.setFirstName(firstName);
		signUpPage.setlastName(lastName);
		signUpPage.setEmail(email);
		signUpPage.setPassword(password);
	}
	
	//@Test(priority = 1, dataProvider = "getData")
	public void testEmployerSignUp(String firstName, String lastName, String email, String password) {
		signUpPage.clickSignUpBtn();
		handleStartupPages();
		String pageTitle = signUpPage.getSignUpTypePageTitle();
		Assert.assertEquals(pageTitle, "I am looking to...", "SignUp type selection page not loaded correctly");
		boolean  isEmployer = signUpPage.isEmployer();
		Assert.assertTrue(isEmployer, "Expected selected type was employer but it is not");
		String buttonText = signUpPage.getSignUpForEmployerBtnText();
		Assert.assertEquals(buttonText, "Sign up as an Employer"
				, "Expected text on sign up button was \"Sign up as Employer\", but it is not");
		signUpPage.clickSignUpForEmployerBtn();
		signUpPage.setFirstName(firstName);
		signUpPage.setlastName(lastName);
		signUpPage.setEmail(email);
		signUpPage.setPassword(password);
		signUpPage.clickSignUpBtnOnSignUpPage();
	}
	
	@Test (dataProvider = "getData")
	public void testSignUpButtonDisabled(String firstName, String lastName, String email, String password) {
		signUpPage.clickSignUpBtn();
		handleStartupPages();
		String pageTitle = signUpPage.getSignUpTypePageTitle();
		Assert.assertEquals(pageTitle, "I am looking to...", "SignUp type selection page not loaded correctly");
		boolean  isEmployer = signUpPage.isEmployer();
		Assert.assertTrue(isEmployer, "Expected selected type was employer but it is not");
		String buttonText = signUpPage.getSignUpForEmployerBtnText();
		Assert.assertEquals(buttonText, "Sign up as an Employer"
				, "Expected text on sign up button was \"Sign up as Employer\", but it is not");
		signUpPage.clickSignUpForEmployerBtn();
		populateFormFields(firstName, lastName, email, "");
		boolean isEnabled = signUpPage.isSignUpBtnOnSignUpPageEnabled();
		Assert.assertFalse(isEnabled, "Sign up button was expected to be disabled since some fields don't have values, but the button is enabled.");
	}
	
	@Test (dataProvider = "getData")
	public void testSignUpButtonEnabled(String firstName, String lastName, String email, String password) {
		signUpPage.clickSignUpBtn();
		handleStartupPages();
		String pageTitle = signUpPage.getSignUpTypePageTitle();
		Assert.assertEquals(pageTitle, "I am looking to...", "SignUp type selection page not loaded correctly");
		boolean  isEmployer = signUpPage.isEmployer();
		Assert.assertTrue(isEmployer, "Expected selected type was employer but it is not");
		String buttonText = signUpPage.getSignUpForEmployerBtnText();
		Assert.assertEquals(buttonText, "Sign up as an Employer"
				, "Expected text on sign up button was \"Sign up as Employer\", but it is not");
		signUpPage.clickSignUpForEmployerBtn();
		populateFormFields(firstName, lastName, email, password);
		boolean isEnabled = signUpPage.isSignUpBtnOnSignUpPageEnabled();
		Assert.assertTrue(isEnabled, "Sign up button was expected to be enabled since all fields have values, but the button is disabled.");
	}
	
	@Test (dataProvider = "getData")
	public void testEmailAddressValidation(String firstName, String lastName, String email, String password) {
		signUpPage.clickSignUpBtn();
		handleStartupPages();
		String pageTitle = signUpPage.getSignUpTypePageTitle();
		Assert.assertEquals(pageTitle, "I am looking to...", "SignUp type selection page not loaded correctly");
		boolean  isEmployer = signUpPage.isEmployer();
		Assert.assertTrue(isEmployer, "Expected selected type was employer but it is not");
		String buttonText = signUpPage.getSignUpForEmployerBtnText();
		Assert.assertEquals(buttonText, "Sign up as an Employer"
				, "Expected text on sign up button was \"Sign up as Employer\", but it is not");
		signUpPage.clickSignUpForEmployerBtn();
		populateFormFields(firstName, lastName, email, password);
		String emailValue = signUpPage.validateEmail(email);
		Assert.assertEquals(emailValue, "Please enter a valid email address", "Email addresss validation is not working as expected.");
	}
	
	@Test (dataProvider = "getData")
	public void testPasswordValidation(String firstName, String lastName, String email, String password) {
		signUpPage.clickSignUpBtn();
		handleStartupPages();
		String pageTitle = signUpPage.getSignUpTypePageTitle();
		Assert.assertEquals(pageTitle, "I am looking to...", "SignUp type selection page not loaded correctly");
		boolean  isEmployer = signUpPage.isEmployer();
		Assert.assertTrue(isEmployer, "Expected selected type was employer but it is not");
		String buttonText = signUpPage.getSignUpForEmployerBtnText();
		Assert.assertEquals(buttonText, "Sign up as an Employer"
				, "Expected text on sign up button was \"Sign up as Employer\", but it is not");
		signUpPage.clickSignUpForEmployerBtn();
		populateFormFields(firstName, lastName, email, password);
		String passwordValue = signUpPage.validatePassword(password);
		Assert.assertEquals(passwordValue, "Password must be atleast 6 characters", "Password must be atleast 6 characters");
	}

}
