package com.jobget.pages;

import java.time.Duration;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;

import com.jobget.base.Base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class LogOutPage extends Base {
	
	@AndroidFindBy(id = "com.jobget:id/iv_profile_pic")
	MobileElement userProfile;
	
	@FindBy(id = "com.jobget:id/tv_settings")
	MobileElement settingsButton;
	
	@FindBy(id = "com.jobget:id/tv_logout")
	MobileElement logoutButton;
	
	@FindBy(id = "com.jobget:id/btn_yes")
	MobileElement logoutConfirmationYes;
	
	@FindBy(id = "com.jobget:id/btn_no")
	MobileElement logoutConfirmationNo;
	
	@FindBy(id = "com.jobget:id/tv_toolbar_title")
	MobileElement settingsPageTitle;
	
	
	public LogOutPage(AppiumDriver<MobileElement> driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);
	}

	public void clickProfile() {
		userProfile.click();
	}
	
	public void clickSettings() {
		settingsButton.click();
	}
	
	public void clickLogoutBtn() {
		logoutButton.click();
	}
	
	public void logOutConfirmation(String Confirmation) {
		if (Confirmation.equalsIgnoreCase("Yes"))
		logoutConfirmationYes.click();
		else logoutConfirmationNo.click();
			
	}
	
	public String getSettingsPageText(String emailAddress) {
		return settingsPageTitle.getText();
	}
	
	public void scrollPage() {
		TouchAction action = new TouchAction(getDriver());
		action.press(PointOption.point(103, 1800))
		.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
		.moveTo(PointOption.point(103,1550)).release().perform();
	}
	


}
