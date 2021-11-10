package com.SeleniumSwagLabsDemo.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;

//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SwagLabsLogin {

	private static WebDriver driver;

	@BeforeAll
	public static void setUp() {
		// The "java.lang.System.setProperty()" method sets the system property indicated by the specified key
		driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com");
	}

	@Test
	@Order(1)
	public void login() {
		WebElement usernameBox = driver.findElement(By.id("user-name"));
		WebElement passwordBox = driver.findElement(By.id("password"));
		WebElement loginButton = driver.findElement(By.id("login-button"));

		usernameBox.sendKeys("performance_glitch_user");
		passwordBox.sendKeys("secret_sauce");

		loginButton.click();

		assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl(), "unsuccessfully redirected");
	}

	@Test
	@Order(2)
	public void menuButton() {
		WebElement burgerMenuButton = driver.findElement(By.id("react-burger-menu-btn"));

		burgerMenuButton.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl(), "unsuccessfully redirected");
	}

	@Test
	@Order(3)
	public void menuButtonAboutLink() {
		//WebElement menuButtonAboutLinkButton = driver.findElement(By.linkText("About"));
		WebElement menuButtonAboutLinkButton = driver.findElement(By.id("about_sidebar_link"));

		menuButtonAboutLinkButton.click();

		assertEquals("https://saucelabs.com/", driver.getCurrentUrl(), "unsuccessfully redirected");
	}

	@AfterAll
	public static void tearDown() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
	}
}
