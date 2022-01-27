package com.jobget.testcases;


import com.jobget.pages.LaunchPage;
import com.jobget.util.Util;
import com.relevantcodes.extentreports.LogStatus;

import net.bytebuddy.agent.builder.AgentBuilder.Identified.Extendable;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LaunchPageTest extends TestBase{
	static LaunchPage launchPage;
	
	
	@BeforeMethod
	@Parameters({"deviceName","platFormVersion", "UDID", "port"})
	public void setUp(String deviceName, String platFormVersion, String UDID, String port) {
		launchPage = new LaunchPage(null, deviceName, platFormVersion, UDID, port);
	}
	
	@AfterMethod
	public void tearDown(ITestResult iTestResult) {
		if (iTestResult.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, "Test case failed is: " + iTestResult.getName());
			test.log(LogStatus.FAIL, "Test case failed is: " + iTestResult.getThrowable());
			try {
				String screenshot = Util.takeScreenshot(launchPage.getDriver(), iTestResult.getName() + "_FAILED");
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
		launchPage.getDriver().quit();
	}
	
	
	/**
	 * This test case checks if JobGet logo is present on landing page
	 * @throws IOException 
	 */
	@Test (priority=1)
	public void logoDisplayedTest() throws IOException {
		test = extent.startTest("logoDisplayedTest");
		Util.takeScreenshot(launchPage.getDriver(), new Object() {}
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
	@Test (priority=2,enabled=true)
	public void signUpButtonDisplayedTest() throws SecurityException, IOException {
		test = extent.startTest("signUpButtonDisplayedTest");
		Util.takeScreenshot(launchPage.getDriver(), new Object() {}
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
	@Test (priority=3,enabled=true)
	public void loginButtonDisplayedTest() throws SecurityException, IOException {
		test = extent.startTest("loginButtonDisplayedTest");
		Util.takeScreenshot(launchPage.getDriver(), new Object() {}
        .getClass()
        .getEnclosingMethod()
        .getName() );
		boolean enabled = launchPage.isLoginButtonAvailable();
		Assert.assertTrue(enabled, "Login button is not available on landing page");
	}
	
	

	

}
