package com.SeleniumSwagLabsDemo.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;

import org.openqa.selenium.support.ui.Select;
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
	}

	@Test
	@Order(1)
	public void login() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("performance_glitch_user", "secret_sauce");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl(), "unsuccessfully redirected");
	}

	//  @Test
	//  @Order(2)
	//  public void selectProductSortContainer() {
	//    InventoryPage inventoryPage = new InventoryPage(driver);
	//    Boolean optionExists = inventoryPage.selectProductSortContainer("lohi", "Price (low to high)");
	//    assertTrue(optionExists, "Value not matching with the select dropdown list");
	//  }

	@Test
	@Order(2)
	public void selectProductSortContainer() {
		WebElement selectElement = driver.findElement(By.xpath("//select[@data-test='product_sort_container']"));

		Select selectObject = new Select(selectElement);

		selectObject.selectByValue("lohi");

		WebElement selectElementA = driver.findElement(By.xpath("//select[@data-test='product_sort_container']"));
		Select selectObjectA = new Select(selectElementA);

		List<WebElement> options = selectObjectA.getOptions();

		boolean optionExists = false;

		for (WebElement e: options) {
			if (e.getText().equals("Price (low to high)")) {
				optionExists = true;
				break;
			}
		}

		assertTrue(optionExists, "Value not matching with the select dropdown list");
	}

	@Test
	@Order(3)
	public void menuButton() {
		WebElement burgerMenuButton = driver.findElement(By.id("react-burger-menu-btn"));

		burgerMenuButton.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl(), "unsuccessfully redirected");
	}

	@Test
	@Order(4)
	public void menuButtonAboutLink() {
		//WebElement menuButtonAboutLinkButton = driver.findElement(By.id("about_sidebar_link"));
		WebElement menuButtonAboutLinkButton = driver.findElement(By.xpath("//a[contains(text(),'About')]"));

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
