package com.SeleniumSwagLabsDemo.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.SeleniumSwagLabsDemo.utilities.WaitHelper;
import com.SeleniumSwagLabsDemo.poms.LoginPage;
import com.SeleniumSwagLabsDemo.poms.InventoryPage;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SwagLabsLogin {

	private static WebDriver driver;
	private static WaitHelper waitHelper;

	@BeforeAll
	public static void setUp() {
		driver = new ChromeDriver();
		waitHelper = new WaitHelper(driver);
		driver.get("https://www.saucedemo.com");
		waitHelper.ImplicitWaitForElement(5);

		if (!driver.getCurrentUrl().contains("saucedemo.com")){
			throw new IllegalStateException("Current URL is NOT https://www.saucedemo.com: " + driver.getCurrentUrl());
		}
	}

	@Test
	@Order(1)
	public void login() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("performance_glitch_user", "secret_sauce");

		waitHelper.ImplicitWaitForElement(5);

		assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl(), "unsuccessfully redirected");
	}

	@Test
	@Order(2)
	public void selectProductSortContainer() {
		InventoryPage inventoryPage = new InventoryPage(driver);
		Boolean optionExists = inventoryPage.selectProductSortContainer("lohi");
		assertTrue(optionExists, "Value not matching with the select dropdown list");
	}

	@Test
	@Order(3)
	public void menuButton() throws InterruptedException {
		Thread.sleep(3000);
		WebElement burgerMenuButton = driver.findElement(By.id("react-burger-menu-btn"));

		burgerMenuButton.click();
		Thread.sleep(3000);

		assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl(), "unsuccessfully redirected");
	}

	@Test
	@Order(4)
	public void menuButtonAboutLink() throws InterruptedException {
		WebElement menuButtonAboutLinkButton = driver.findElement(By.xpath("//a[contains(text(),'About')]"));

		menuButtonAboutLinkButton.click();
		Thread.sleep(3000);

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
