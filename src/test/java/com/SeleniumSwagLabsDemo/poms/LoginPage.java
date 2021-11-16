package com.SeleniumSwagLabsDemo.poms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.SeleniumSwagLabsDemo.utilities.WaitHelper;
import com.SeleniumSwagLabsDemo.utilities.StoreCookieInfo;

public class LoginPage {

	private WaitHelper waitHelper;
	private StoreCookieInfo storeCookieInfo;

	@FindBy(id = "user-name")
	private WebElement usernameBox;
	@FindBy(id = "password")
	private WebElement passwordBox;
	@FindBy(id = "login-button")
	private WebElement loginButton;

	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);

		waitHelper = new WaitHelper(driver);
		storeCookieInfo = new StoreCookieInfo(driver);
		waitHelper.ExplicitWaitForElement(5, "elementToBeClickable", usernameBox);
		waitHelper.FluentWaitForElement(5, 250, "visibilityOf", passwordBox);
	}

	public void login(String username, String password) {
		usernameBox.sendKeys(username);
		passwordBox.sendKeys(password);
		loginButton.click();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			storeCookieInfo.StoreCookie();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
