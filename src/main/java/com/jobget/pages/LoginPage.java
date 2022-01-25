package com.jobget.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.jobget.base.Base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginPage extends Base {
	
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
	
	@FindBy(id ="com.jobget:id/tv_forgot_password")
	MobileElement forgotPaswordLink;
	
	@FindBy(id = "com.jobget:id/label_varified_email")
	MobileElement forgotPaswordLinkPageTitle;
	
	@FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.widget.EditText")
	MobileElement forgotPaswordEmailAddress;
	
	@FindBy(id = "com.jobget:id/tv_send")
	MobileElement sendButtonOnForgotPasswordPage;
	
	@FindBy(id = "com.jobget:id/label_varified_email")
	MobileElement emailSendVerificationText;
	
	@FindBy(id = "com.jobget:id/et_email_address")
	MobileElement otpField;
	
	@FindBy(id = "com.jobget:id/tv_send")
	MobileElement submitButtonOnEmailVerifiicationPage;
	
	@FindBy(id = "com.jobget:id/et_new_password")
	MobileElement newPassword;
	
	@FindBy(id = "com.jobget:id/et_confirm_password")
	MobileElement confirmPassword;
	
	@FindBy(id = "com.jobget:id/btn_reset_password")
	MobileElement resetButton;
	
	@FindBy(id = "com.jobget:id/tvTitle")
	MobileElement resetPasswordSuccessContent;
	
	@FindBy(id = "com.jobget:id/tv_login")
	MobileElement loginButtonPassreset;
	
	@FindBy(id = "com.jobget:id/tv_resend")
	MobileElement resendCodeLink;
	
	@FindBy(id = "com.jobget:id/tv_reset_link")
	MobileElement resendCodePoPUpText;
	
	@FindBy(id = "com.jobget:id/tv_country_code")
	MobileElement email;
	
	@FindBy(id = "com.jobget:id/btn_yes")
	MobileElement confirmYes;
	
	@FindBy(id = "com.jobget:id/btn_edit")
	MobileElement confirmEdit;
	
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
	
	@FindBy(id="com.jobget:id/tv_login_facebook")
	MobileElement loginWithFacebookButton;
	
	@FindBy(xpath="//android.view.View[@text='Log in to your Facebook account to connect to JobGet']")
	WebElement faceBookPageText;
	
	@FindBy(id="m_login_email")
	WebElement userNameFieldFaceBook;
	
	@FindBy(id="m_login_password")
	WebElement passwordFieldFaceBook;
	
	@FindBy(xpath="//android.widget.Button[@text='Log In']")
	WebElement logInButtonFaceBook;
	
	@FindBy(xpath="//android.widget.Button[@resource-id='u_0_7_v8']")
	WebElement continueAsEmailButton;
	
	public LoginPage(AppiumDriver<MobileElement> driver, String deviceName,String platformVersion, String UDID, String port) {
		super(driver, deviceName,platformVersion, UDID, port);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),this);
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
	
	public String validateEmail(String emailAddress) {
		emailAddressField.click();
		passwordField.click();
		return emailAddressErrorMessage.getText();
	}
	
	public String validateEmailOnForgotPasswordPage(String emailAddress) {
		forgotPaswordEmailAddress.click();
		if (emailAddressErrorMessage.isDisplayed()){
			return emailAddressErrorMessage.getText();
		}
		return null;
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
	
	public String getForgotPasswordPageText() {
		String title = forgotPaswordLinkPageTitle.getText();
		return title;
	}
	
	public void setEmailAddressforForgotPassword(String email) {
		forgotPaswordEmailAddress.sendKeys(email);
	}
	
	public void clickSendBtnOnForgotPasswordPage() {
		sendButtonOnForgotPasswordPage.click();
	}
	
	public String getEmailSendVerificationTextforForgotPassword() {
		String msg = emailSendVerificationText.getText();
		return msg;
	}
	
	
	public void inputOTP(String otp) {
		otpField.sendKeys(otp);
		submitButtonOnEmailVerifiicationPage.click();
	}
	
	public void resetPassword(String newPasswordValue, String confirmPasswordValue) {
		newPassword.sendKeys(newPasswordValue);
		confirmPassword.sendKeys(confirmPasswordValue);
		resetButton.click();
	}
	
	public String getResetPasswordSuccessContentText() {
		return resetPasswordSuccessContent.getText();
	}
	
	public void clickOnLoginButtonAfterPassReset() {
		loginButtonPassreset.click();
	}
	
	
	
	public void clickResendVerificationCodeLink() {
		resendCodeLink.click();
	}
	
	public String getPopUpTextForResendVerificationCode() {
		return resendCodePoPUpText.getText();
	}
	
	public String getEmailOnResendCodePopUp() {
		return email.getText();
	}
	
	public void clickYesOnResendCodePopUp() {
		confirmYes.click();
	}
	
	public void clickEditOnResendCodePopUp() {
		confirmEdit.click();
	}
	
	public void locationPermissionAccess(String action) {
		if (action.equalsIgnoreCase("While using the app"))
			allowWhileUsingTheAppBtn.click();
		else if (action.equalsIgnoreCase("Only this time"))
			allowWhileUsingTheAppBtn.click();
		else denyBtn.click();
	}
	
	public void selectCountry(String contact) {
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
	
	public void clickContinueWithFaceBookButton() {
	 loginWithFacebookButton.click();
	}
	
	public WebElement getFaceBookPageText() {
		return faceBookPageText;
	}
	
	public String facebookLogin(String username, String password) {
		userNameFieldFaceBook.sendKeys(username);
		passwordFieldFaceBook.sendKeys(password);
		logInButtonFaceBook.click();
		return continueAsEmailButton.getText();
	}
	
	public void clickContinueBuuton() {
		continueAsEmailButton.click();
	}
	
	
	

}
