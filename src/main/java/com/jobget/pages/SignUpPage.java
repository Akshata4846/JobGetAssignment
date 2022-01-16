package com.jobget.pages;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.jobget.base.TestBase;
import com.jobget.util.Util;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * @author AVK2E5744
 *
 */
public class SignUpPage extends TestBase {

	@FindBy(id=	"com.jobget:id/tvSignUp")
	public static MobileElement signUpBtn;

	@FindBy(id=	"com.android.permissioncontroller:id/permission_allow_foreground_only_button")
	MobileElement allowWhileUsingTheAppBtn;

	@FindBy(id=	"com.android.permissioncontroller:id/permission_allow_one_time_button")
	MobileElement allowOnlyThisTimeBtn;

	@FindBy(id=	"com.android.permissioncontroller:id/permission_deny_button")
	MobileElement denyBtn;

	@FindBy(id=	"com.jobget:id/rlEmployer")
	MobileElement employerSignUpType;

	@FindBy(id=	"com.jobget:id/tv_continue")
	MobileElement signUpTypePageTitle;

	@FindBy(id=	"com.jobget:id/tv_employer")
	MobileElement employerSignUpTypeText;

	@FindBy(id=	"com.jobget:id/tv_title")
	MobileElement signUpPageTitle;
	
	@FindBy(id=	"com.jobget:id/places_autocomplete_search_bar")
	MobileElement contactSearchBox;
	
	@FindBy(xpath=	"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.TextView[1]")
	MobileElement contactSearchResultSelection;
	

	@FindBy(id = "com.jobget:id/et_first_name")
	MobileElement firstNameField;

	@FindBy(id = "com.jobget:id/et_last_name")
	MobileElement lastNameField;

	@FindBy(id = "com.jobget:id/et_email_address")
	MobileElement emailAddressField;
	
	@FindBy(id = "com.jobget:id/tv_error_email")
	MobileElement emailAddressErrorMessage;
	 
	@FindBy(id = "com.jobget:id/et_password")
	MobileElement passwordField;
	
	@FindBy(id = "com.jobget:id/tv_error_password")
	MobileElement passwordErrorMessage;

	@FindBy(id = "com.jobget:id/tv_signup")
	public static MobileElement signUpBtnOnSignUpPage;

	@FindBy(id = "com.jobget:id/tv_i_am_employer")
	MobileElement infoText;

	@FindBy(id = "com.jobget:id/iv_back")
	public static MobileElement backBtn;

	public SignUpPage() {
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);
	}

	/**
	 * Clicks on SignUp button on landing page
	 */
	public void clickSignUpBtn() {
		signUpBtn.click();
	}

	public String getSignUpTypePageTitle() {
		String title = signUpTypePageTitle.getText();
		return title;
	}

	public boolean isEmployer() {
		employerSignUpType.click();
		return employerSignUpType.isEnabled();
	}

	public String getSignUpForEmployerBtnText() {
		String btnText = signUpBtn.getText();
		return btnText;
	}

	public void clickSignUpForEmployerBtn() {
		signUpBtn.click();
	}

	public void setFirstName(String firstName) {
		firstNameField.sendKeys(firstName);
	}

	public void setlastName(String lastName) {
		lastNameField.sendKeys(lastName);
	}

	public void setEmail(String emailAddress) {
		emailAddressField.sendKeys(emailAddress);
	}
	
	public String validateEmail(String emailAddress) {
		emailAddressField.click();
		passwordField.click();
		return emailAddressErrorMessage.getText();
	}

	public void setPassword(String password) {
		passwordField.sendKeys(password);
	}
	
	public String validatePassword(String emailAddress) {
		passwordField.click();
		emailAddressField.click();
		return passwordErrorMessage.getText();
	}

	public void clickSignUpBtnOnSignUpPage() {
		signUpBtnOnSignUpPage.click();

	}
	
	public boolean isSignUpBtnOnSignUpPageEnabled() {
		return signUpBtnOnSignUpPage.isEnabled();

	}

	public void locationPermissionAccess(String action) {
		if (action.equalsIgnoreCase("While using the app"))
			allowWhileUsingTheAppBtn.click();
		else if (action.equalsIgnoreCase("Only this time"))
			allowOnlyThisTimeBtn.click();
		else denyBtn.click();
	}
	
	public void selectContacts(String contact) {
		contactSearchBox.sendKeys(contact);
		contactSearchResultSelection.click();
	}






}
