package com.jobget.testcases;

import com.jobget.pages.LaunchPage;
import com.jobget.util.Util;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LaunchPageTest{
	static LaunchPage launchPage;
	
	
	@BeforeMethod
	public void setUp() {
		launchPage = new LaunchPage(null);
	}
	
	/**
	 * This test case checks if JobGet logo is present on landing page
	 * @throws IOException 
	 */
	@Test (priority=1)
	public static void logoDisplayedTest() throws IOException {
		String methodName = Util.getCurrentMethodName();
		Util.takeScreenshot(launchPage.driver, methodName );
		boolean displayed = launchPage.isLogoDisplayed();
		Assert.assertTrue(displayed, "Jobget logo is not displayed on landing page");
	}
	
	/**
	 * This test case checks if sign up button is present and enabled on landing page
	 */
	@Test (priority=2)
	public void signUpButtonDisplayedTest() { 
	boolean enabled = launchPage.isSignUpButtonAvailable();
	Assert.assertTrue(enabled, "Sign up button is not available on landing page");
}
	
	/**
	 * This test case checks if login up button is present and enabled on landing page
	 */
	@Test (priority=3)
	public void loginButtonDisplayedTest() {
		boolean enabled = launchPage.isLoginButtonAvailable();
		Assert.assertTrue(enabled, "Login button is not available on landing page");
	}
	
	

	

}
