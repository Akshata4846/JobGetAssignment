package com.jobget.pages;

import com.jobget.base.TestBase;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LaunchPage extends TestBase {
	
	/*
	 * This is the logo of JobGet app
	 */
	@AndroidFindBy(id = "com.jobget:id/ivLogoJobget")
	MobileElement logo;

	
	public LaunchPage(AppiumDriver<MobileElement> driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);
		}
				
	
	//Check if JobGet logo is present on landing page
	public boolean isLogoDisplayed() {
		return logo.isDisplayed();
	}
	


}
