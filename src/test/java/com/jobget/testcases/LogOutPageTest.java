package com.jobget.testcases;

import java.io.IOException;
import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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
		return Util.getData(SHEETNAME);
	}

	private void populateFormFields (String email, String password) {
		loginPage.setUserName(email);
		loginPage.setPassword(password);
	}


	@BeforeMethod
	public void setUp() {
		loginPage = new LoginPage();
		logoutPage = new LogOutPage();
	}


	public boolean userLogin(String firstName, String lastName, String email, String password, String country) {
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



	@Test(dataProvider = "getTestData")
	public void testLogout(String firstName, String lastName, String email, String password, String country) {
		if (userLogin(firstName, lastName, email, password, country)) {
			logoutPage.clickProfile();
			logoutPage.clickSettings();
			logoutPage.clickLogoutBtn();
			LaunchPageTest.logoDisplayedTest();
		}

	}
}


