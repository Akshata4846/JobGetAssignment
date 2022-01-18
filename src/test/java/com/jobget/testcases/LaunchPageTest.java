package com.jobget.testcases;

import com.jobget.helper.CSVHelper;
import com.jobget.pages.LaunchPage;
import com.jobget.util.Config;
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
	
	@AfterMethod
	public void tearDown() {
		launchPage.driver.quit();
	}
	
	/**
	 * This test case checks if JobGet logo is present on landing page
	 * @throws IOException 
	 */
	@Test (priority=1)
	public static void logoDisplayedTest() throws IOException {
		Util.takeScreenshot(launchPage.driver, new Object() {}
        .getClass()
        .getEnclosingMethod()
        .getName() );
		boolean displayed = launchPage.isLogoDisplayed();
		Assert.assertTrue(displayed, "Jobget logo is not displayed on landing page");
	}
	
	/**
	 * This test case checks if sign up button is present and enabled on landing page
	 * @throws IOException 
	 * @throws SecurityException 
	 */
	@Test (priority=2)
	public void signUpButtonDisplayedTest() throws SecurityException, IOException { 
		Util.takeScreenshot(launchPage.driver, new Object() {}
        .getClass()
        .getEnclosingMethod()
        .getName() );
	boolean enabled = launchPage.isSignUpButtonAvailable();
	Assert.assertTrue(enabled, "Sign up button is not available on landing page");
}
	
	/**
	 * This test case checks if login up button is present and enabled on landing page
	 * @throws IOException 
	 * @throws SecurityException 
	 */
	@Test (priority=3)
	public void loginButtonDisplayedTest() throws SecurityException, IOException {
		Util.takeScreenshot(launchPage.driver, new Object() {}
        .getClass()
        .getEnclosingMethod()
        .getName() );
		boolean enabled = launchPage.isLoginButtonAvailable();
		Assert.assertTrue(enabled, "Login button is not available on landing page");
	}
	
	

	

}
