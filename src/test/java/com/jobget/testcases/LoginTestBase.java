package com.jobget.testcases;

import org.testng.Assert;

import com.jobget.pages.LoginPage;
import com.jobget.util.Util;

public class LoginTestBase extends TestBase {
	LoginPage loginPage;
	
	/**
	 * @param email
	 * @param password
	 * Sets user name and password in login form
	 */
	public void populateFormFields (String email, String password) {
		loginPage.setUserName(email);
		loginPage.setPassword(password);
	}

	/**
	 * @param country
	 * prepares initial screens before login page
	 */
	public void prepareForLogin(String country) {
		loginPage.clickLoginUpBtn();
		Util.handleStartupPages(loginPage, country);
		if (Util.isEmployerLogin(loginPage))  {
			loginPage.clickEmployerBtn();
			String pageTitle = loginPage.getLoginPageTitle();
			Assert.assertEquals(pageTitle, "Login", "Login page not loaded correctly");
		}
	}

}
