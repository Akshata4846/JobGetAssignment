package com.jobget.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.jobget.base.TestBase;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginPage extends TestBase {
	
	@FindBy(id = "com.jobget:id/tvLogin")
	public static MobileElement loginBtn;
	
	@FindBy(id = "com.jobget:id/tv_welcome_back")
	public static MobileElement loginPageTitle;
	
	@FindBy(id = "com.jobget:id/et_email_address")
	MobileElement emailAddressField;
	
	@FindBy(id = "com.jobget:id/tv_error_email")
	MobileElement emailAddressErrorMessage;
	
	@FindBy(id = "com.jobget:id/et_password")
	MobileElement passwordField;
	
	@FindBy(id = "com.jobget:id/tv_error_password")
	MobileElement passwordErrorMessage;
	
	@FindBy(id = "com.jobget:id/tv_login")
	MobileElement loginBtnOnLoginPage;
	
	@FindBy(id = "com.jobget:id/tv_title")
	MobileElement jobPostingsPageTitle;
	
	@FindBy(id ="com.jobget:id/tv_forgot_password")
	MobileElement forgotPaswordLink;
	
	@FindBy(id = "com.jobget:id/tv_forgot_password")
	MobileElement forgotPaswordLinkPageTitle;
	
	@FindBy(id = "com.jobget:id/et_email_address")
	MobileElement forgotPaswordEmailAddress;
	
	@FindBy(id = "com.jobget:id/tv_send")
	MobileElement sendButtonOnForgotPasswordPage;
	
	@FindBy(id = "com.jobget:id/label_varified_email")
	MobileElement emailSendVerificationText;
	
	@FindBy(id = "com.jobget:id/tv_resend")
	MobileElement resendVerificationCodeButton;
	
	@FindBy(id=	"com.android.permissioncontroller:id/permission_allow_foreground_only_button")
	MobileElement allowWhileUsingTheAppBtn;

	@FindBy(id=	"com.android.permissioncontroller:id/permission_allow_one_time_button")
	MobileElement allowOnlyThisTimeBtn;
	
	@FindBy(id=	"com.android.permissioncontroller:id/permission_deny_button")
	MobileElement denyBtn;
	
	@FindBy(id=	"com.jobget:id/places_autocomplete_search_bar")
	MobileElement contactSearchBox;
	
	@FindBy(xpath=	"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.TextView[1]")
	MobileElement contactSearchResultSelection;
	
	@FindBy(id=	"com.jobget:id/rlEmployer")
	MobileElement employerType;
	
	@FindBy(id=	"com.jobget:id/tvSignUp")
	MobileElement employerButton;
	
	@FindBy(id=	"com.jobget:id/tv_signup")
	MobileElement signUpLinkOnLoginPage;
	
	@FindBy(id=	"com.jobget:id/tv_title")
	MobileElement signUpAsEmployerPageTitle;
	
	
	public LoginPage() {
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);
	}

	public void clickLoginUpBtn() {
		loginBtn.click();
	}
	
	public String getLoginPageTitle() {
		String title = loginPageTitle.getText();
		return title;
	}
	
	
	public boolean isUserNameFieldDisplayed() {
		return emailAddressField.isDisplayed();
	}
	
	public void setUserName(String userName) {
		emailAddressField.sendKeys(userName);
	}
	
	public boolean isPasswordFieldDisplayed() {
		return passwordField.isDisplayed();
	}
	
	public void setPassword(String password) {
		passwordField.sendKeys(password);
	}
	
	public void clickLoginBtnOnLoginPage() {
		loginBtnOnLoginPage.click();
	}
	
	public String getJobPostingsPageTitle() {
		String title = jobPostingsPageTitle.getText();
		return title;
	}
	
	public String validateEmail(String emailAddress) {
		emailAddressField.click();
		passwordField.click();
		return emailAddressErrorMessage.getText();
	}

	
	public String validatePassword(String emailAddress) {
		passwordField.click();
		emailAddressField.click();
		return passwordErrorMessage.getText();
	}
	
	public boolean isLoginButtonOnLoginPageEnabled() {
		return loginBtnOnLoginPage.isEnabled();

	}
	
	public void clickForgotPasswordLink() {
		forgotPaswordLink.click();

	}
	
	public boolean isForgotPasswordLinkDisplayed() {
		return forgotPaswordLink.isDisplayed();
	}
	
	public String getForgotPasswordPageTitle() {
		String title = forgotPaswordLinkPageTitle.getText();
		return title;
	}
	
	public void setEmailAddressforForgotPassword(String email) {
		try {
		forgotPaswordEmailAddress.sendKeys(email);
		}
		catch (Exception ex) {
	        ex.printStackTrace();
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].click();", forgotPaswordEmailAddress);
	    }
	}
	
	public void clickSendBtnOnForgotPasswordPage() {
		sendButtonOnForgotPasswordPage.click();
	}
	
	public String getEmailSendVerificationTextforForgotPassword() {
		String msg = emailSendVerificationText.getText();
		return msg;
	}
	
	public void clickResendVerificationCodeButton() {
		resendVerificationCodeButton.click();
	}
	
	public void locationPermissionAccess(String action) {
		if (action.equalsIgnoreCase("While using the app"))
			allowWhileUsingTheAppBtn.click();
		else if (action.equalsIgnoreCase("Only this time"))
			allowWhileUsingTheAppBtn.click();
		else denyBtn.click();
	}
	
	public void selectContacts(String contact) {
		contactSearchBox.sendKeys(contact);
		contactSearchResultSelection.click();
	}
	
	public boolean isEmployer() {
		employerType.click();
		return employerType.isEnabled();
	}
	
	public String getEmployerBtnText() {
		String btnText = employerButton.getText();
		return btnText;
	}
	
	public void clickEmployerBtn() {
		employerButton.click();
	}
	
	public void clickSignUpLinkOnLoginPage() {
		signUpLinkOnLoginPage.click();
	}
	
	public String getSignUpPageTitle() {
		String title = signUpAsEmployerPageTitle.getText();
		return title;
	}
	
	public boolean isSignUpLinkDisplayed() {
		return signUpLinkOnLoginPage.isDisplayed();
	}
	
	public void logOut() {
		
	}
	
	
	

}
