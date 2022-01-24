package com.jobget.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.jobget.base.Base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * @author AVK2E5744
 *
 */
public class SignUpPage extends Base {
	public static final String s1 ="";

	@FindBy(id=	"com.jobget:id/tvSignUp")
	public static MobileElement signUpBtn;

	@FindBy(id=	"com.android.permissioncontroller:id/permission_allow_foreground_only_button")
	public static MobileElement allowWhileUsingTheAppBtn;

	@FindBy(id=	"com.android.permissioncontroller:id/permission_allow_one_time_button")
	public static MobileElement allowOnlyThisTimeBtn;

	@FindBy(id=	"com.android.permissioncontroller:id/permission_deny_button")
	public static MobileElement denyBtn;

	@FindBy(id=	"com.jobget:id/rlEmployer")
	public MobileElement employerSignUpType;

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
	
	@FindBy(id = "com.jobget:id/et_company_name")
	MobileElement companyNameField;
	
	@FindBy(id = "com.jobget:id/et_company_address")
	MobileElement companyWebsiteField;
	
	@FindBy(id = "com.jobget:id/tv_login")
	MobileElement continueButtononCompanyWebsitePage;
	
	@FindBy(id = "com.jobget:id/et_phone_number")
	MobileElement phoneNumberField;
	
	@FindBy(id = "com.jobget:id/tv_send")
	MobileElement continueButtonOnMobileNumberPage;
	
	@FindBy(id = "com.jobget:id/tv_country_code")
	MobileElement countryCode;
	
	@FindBy(id = "com.jobget:id/et_country_name")
	MobileElement countryName;
	
	
	@FindBy(id = "com.jobget:id/et_phone_number")
	MobileElement otpField;
	
	@FindBy(id = "com.jobget:id/tv_send")
	MobileElement finishButton;
	
	@FindBy(id = "com.jobget:id/tv_description") 
	MobileElement successContent;
	
	@FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout")
	MobileElement successContent1;
	
	@FindBy(id = "com.jobget:id/btn_ok")
	MobileElement okayButton;
	
	@FindBy(id = "com.jobget:id/tv_i_am_employer")
	MobileElement jobSeekerLink;
	
	@FindBy(id = "com.jobget:id/tv_title")
	MobileElement jobSeekerPageTile;
	
	@FindBy(id = "com.jobget:id/tv_description")
	MobileElement alreadyRegisteredUserPopUp;
	
	@FindBy(id = "com.jobget:id/btn_ok")
	MobileElement okBtnOnAlreadyRegisteredUserPopUp;
	
	@FindBy(id = "com.jobget:id/tv_title")
	MobileElement jobPostingsPageTitle;
	
	@FindBy(id = "com.jobget:id/tv_terms_condition")
	MobileElement termsPageLinkFromSignUpPage;

	@FindBy(id = "com.jobget:id/tv_toolbar_title")
	MobileElement termsPageTitleClickedFromSignUpPage;
	

	public SignUpPage(AppiumDriver<MobileElement> driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),this);
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

	public String getEmployerBtnText() {
		String btnText = signUpBtn.getText();
		return btnText;
	}

	public void clickSignUpForEmployerBtn() {
		signUpBtn.click();
	}

	public void setFirstName(String firstName) {
		firstNameField.sendKeys(firstName);
	}
	
	public boolean isFirstNameFieldDisplayed() {
		return firstNameField.isDisplayed();
	}

	public void setlastName(String lastName) {
		lastNameField.sendKeys(lastName);
	}
	
	public boolean isLastNameFieldDisplayed() {
		return lastNameField.isDisplayed();
	}

	public void setEmail(String emailAddress) {
		emailAddressField.sendKeys(emailAddress);
	}
	
	public boolean isEmailFieldDisplayed() {
		return emailAddressField.isDisplayed();
	}
	
	public String validateEmail() {
		emailAddressField.click();
		passwordField.click();
		return emailAddressErrorMessage.getText();
	}

	public void setPassword(String password) {
		passwordField.sendKeys(password);
	}
	
	public boolean isPasswordFieldDisplayed() {
		return passwordField.isDisplayed();
	}
	
	public String validatePassword() {
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
	
	public void selectCountry(String contact) {
		contactSearchBox.sendKeys(contact);
		contactSearchResultSelection.click();
	}
	
	
	public void clickJobSeekerLink() {
		jobSeekerLink.click();

	}
	
	public String getJobSeekerPageTitle() {
		return jobSeekerPageTile.getText();
		
	}
	
	public void setCompanyName(String companyName) {
		companyNameField.sendKeys(companyName);

	}
	
	public void setCompanyWebsite(String compnayWebsite) {
		companyWebsiteField.sendKeys(compnayWebsite);
		continueButtononCompanyWebsitePage.click();

	}
	
	public String getAlreadyRegisteredEmailPopUpText() {
		return alreadyRegisteredUserPopUp.getText();
		
	}
	
	public void clickOkBtnOnAlreadyRegisteredEmailPopUp() {
		okBtnOnAlreadyRegisteredUserPopUp.click();
		
	}
	
	public void clickOnPhoneNumberCountryCodeDropDown() {
		countryCode.click();
		
	}
	
	public void setCountryName(String CountryName) {
		countryName.sendKeys(CountryName);
		
	}
	
	public void selectCountryCode(String countryCode) {
		/**final String BeforeXpath="\"//android.widget.TextView[@text='\"";
		final String afterXpath= "\"']\"";
		final String xpath =  BeforeXpath  + countryCode + afterXpath; **/
		getDriver().findElement(By.xpath("//android.widget.TextView[@text='+91']")).click();
	}
	
	public void setPhoneNumber(String phoneNumber) {
		phoneNumberField.sendKeys(phoneNumber);
		continueButtonOnMobileNumberPage.click();
	}
	
	public void setOTP(String OTP) {
		otpField.sendKeys(OTP);
		finishButton.click();
	}
	
	public String getRegistrationSuccessContent() {
		return successContent.getText();
	}
	
	public void okayButtonOnSucessfulRegistration() {
		okayButton.click();
	}
	
	public String getJobPostingsPageTitle() {
		String title = jobPostingsPageTitle.getText();
		return title;
	}
	
	public void clickOnTermsLinkFromSignUpPage() {
		termsPageLinkFromSignUpPage.click();
	}
	
	public String getTermsPageTitle() {
		String title = termsPageTitleClickedFromSignUpPage.getText();
		return title;
	}
	
	
	






}
