package com.jobget.pages;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.jobget.base.TestBase;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginPage extends TestBase {
	
	@FindBy(id = "com.jobget:id/tvLogin")
	public static MobileElement loginBtn;
	
	@FindBy(id = "com.jobget:id/et_email_address")
	MobileElement emailAddressField;
	
	@FindBy(id = "com.jobget:id/et_password")
	MobileElement passwordField;
	
	@FindBy(id = "com.jobget:id/tv_login")
	MobileElement loginBtnOnLoginPage;
		

	public LoginPage() {
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);
	}

	public void clickSignUpBtn() {
		loginBtn.click();
	}
	
	public void setFirstName(String userName) {
		emailAddressField.sendKeys(userName);
	}
	
	public void setPassword(String password) {
		passwordField.sendKeys(password);
	}
	
	public void clickLoginBtnOnLoginPage() {
		loginBtnOnLoginPage.click();
	}

}
