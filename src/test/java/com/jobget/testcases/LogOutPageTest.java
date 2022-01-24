package com.jobget.testcases;

import java.io.IOException;
import java.util.Iterator;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jobget.pages.HomePage;
import com.jobget.pages.LaunchPage;
import com.jobget.pages.LogOutPage;
import com.jobget.pages.LoginPage;
import com.jobget.util.Config;
import com.jobget.util.Util;
import com.relevantcodes.extentreports.LogStatus;

public class LogOutPageTest extends LoginTestBase {
	
	LogOutPage logoutPage;
	HomePage homePage;
	final String SHEETNAME = "LoginDetails";

	@DataProvider
	public Iterator<String[]> getTestData() throws IOException {	
		return Util.getLoginSheetData(SHEETNAME);
	}


	@BeforeMethod
	public void setUp() {
		loginPage = new LoginPage(null);
		homePage = new HomePage(loginPage.getDriver());
		logoutPage = new LogOutPage(loginPage.getDriver());
	}
	
	@AfterMethod
	public void tearDown(ITestResult iTestResult) {
		if (iTestResult.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, "Test case failed is: " + iTestResult.getName());
			test.log(LogStatus.FAIL, "Test case failed is: " + iTestResult.getThrowable());
			try {
				String screenshot = Util.takeScreenshot(logoutPage.getDriver(), iTestResult.getName() + "_FAILED");
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
		logoutPage.getDriver().quit();
	}


	public boolean userLogin(String email, String password, String country) {
		prepareForLogin(country);
			populateFormFields(email,password);
			loginPage.clickLoginBtnOnLoginPage();
			try {
				loginPage.locationPermissionAccess(Config.getProperty("LocationPermissionAccess"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		String title = homePage.getJobPostingsPageTitle();
		Assert.assertEquals(title, "My Job Postings"
				, "Login was not successfull");
		return true;
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
	@Test(priority=1, dataProvider = "getTestData", enabled=true)
	public void testLogoutConfirmationTrue(String email, String password, String country) throws InterruptedException { 
		test = extent.startTest("testLogoutConfirmationTrue");
		if (userLogin(email, password, country)) {
			logoutPage.clickProfile();
			Thread.sleep(5000);
			logoutPage.scrollPage();
			logoutPage.clickSettings();
			logoutPage.clickLogoutBtn();
			logoutPage.logOutConfirmation("Yes");
			Thread.sleep(5000);
			LaunchPage launchPage = new LaunchPage(logoutPage.getDriver());
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
		test = extent.startTest("testLogoutConfirmationFalse");
		if (userLogin(email, password, country)) {
		logoutPage.clickProfile();
		Thread.sleep(5000);
		logoutPage.scrollPage();
		logoutPage.clickSettings();
		logoutPage.clickLogoutBtn();
		logoutPage.logOutConfirmation("No");
		Thread.sleep(8000);
		String title = logoutPage.getSettingsPageText(email);
		Assert.assertEquals(title, "ettings"
				, "User is logged out even after selecting No in confirmation prompt");		
	}

}
}


