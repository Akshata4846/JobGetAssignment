package com.jobget.pages;

import com.jobget.base.Base;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LaunchPage extends Base {
	
	/*
	 * This is the logo of JobGet app
	 */
	@AndroidFindBy(id = "com.jobget:id/ivLogoJobget")
	MobileElement logo;
	
	@FindBy(id=	"com.jobget:id/tvSignUp")
    MobileElement signUpBtn;
	
	@FindBy(id = "com.jobget:id/tvLogin")
	MobileElement loginBtn;

	
	public LaunchPage(AppiumDriver<MobileElement> driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(this.getDriver()),this);
		}
				
	
	public boolean isLogoDisplayed() {
		return logo.isDisplayed();
	}
	
	public boolean isSignUpButtonAvailable() {
		return signUpBtn.isEnabled();
		
	}
	
	public boolean isLoginButtonAvailable() {
		return loginBtn.isDisplayed();
		
	}
	


}
