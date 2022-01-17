package com.jobget.testcases;

import java.io.IOException;
import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jobget.pages.LaunchPage;
import com.jobget.pages.LogOutPage;
import com.jobget.pages.LoginPage;
import com.jobget.util.Config;
import com.jobget.util.Util;

public class LogOutPageTest {

	LoginPage loginPage;
	LogOutPage logoutPage;
	final String SHEETNAME = "LoginDetails";

	@DataProvider
	public Iterator<String[]> getTestData() throws IOException {	
		return Util.getLoginSheetData(SHEETNAME);
	}

	private void populateFormFields (String email, String password) {
		loginPage.setUserName(email);
		loginPage.setPassword(password);
	}


	@BeforeMethod
	public void setUp() {
		loginPage = new LoginPage();
		logoutPage = new LogOutPage(loginPage.driver);
	}


	public boolean userLogin(String email, String password, String country) {
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
		String title = loginPage.getJobPostingsPageTitle();
		Assert.assertEquals(title, "My Job Postings"
				, "Login was not successfull");
		return true;
		}
		return false;
	}



	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 * @param country
	 * @throws InterruptedException
	 * This test cases checks the logout functionality when user selects Yes on logout confirmation prompt 
	 * and after logout user should be redirected to landing page
	 */
	@Test(priority=1, dataProvider = "getTestData")
	public void testLogoutConfirmationTrue(String email, String password, String country) throws InterruptedException { 
		if (userLogin(email, password, country)) {
			logoutPage.clickProfile();
			Thread.sleep(5000);
			logoutPage.scrollPage();
			logoutPage.clickSettings();
			logoutPage.clickLogoutBtn();
			logoutPage.logOutConfirmation("Yes");
			Thread.sleep(5000);
			LaunchPage launchPage = new LaunchPage(logoutPage.driver);
			boolean displayed = launchPage.isLogoDisplayed();
			Assert.assertTrue(displayed, "Logout not successful");
		}

	}
	
	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 * @param country
	 * @throws InterruptedException
	 * This test case checks the logout functionality when user selects No on logout confirmation prompt 
	 * and user should still remain on existing page
	 */
	@Test(priority=2, dataProvider = "getTestData")
	public void testLogoutConfirmationFalse(String email, String password, String country) throws InterruptedException { 
		if (userLogin(email, password, country)) {
		logoutPage.clickProfile();
		Thread.sleep(5000);
		logoutPage.scrollPage();
		logoutPage.clickSettings();
		logoutPage.clickLogoutBtn();
		logoutPage.logOutConfirmation("No");
		Thread.sleep(8000);
		String title = logoutPage.getSettingsPageText(email);
		Assert.assertEquals(title, "Settings"
				, "User is logged out even after selecting No in confirmation prompt");		
	}

}
}


