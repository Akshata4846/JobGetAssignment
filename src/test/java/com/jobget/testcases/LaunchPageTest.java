package com.jobget.testcases;

import com.jobget.pages.LaunchPage;

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
	
	@Test
	public static void logoDisplayedTest() {
		boolean displayed = launchPage.isLogoDisplayed();
		Assert.assertTrue(displayed, "Jobget logo is not displayed on landing page");
		System.out.println("logoDisplayedTest -------");
	}
	
	@AfterMethod
	public void tearDown() {		
		//launchPage.driver.quit();
	}
	

}
