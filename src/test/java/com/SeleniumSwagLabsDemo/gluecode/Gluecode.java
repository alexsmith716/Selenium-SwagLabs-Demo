package com.SeleniumSwagLabsDemo.gluecode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import com.SeleniumSwagLabsDemo.poms.LoginPage;

public class Gluecode {

	private static WebDriver driver;
	private static LoginPage loginPage;

	@Before
	public static void setUp() {
		// The "java.lang.System.setProperty()" method sets the system property indicated by the specified key
		driver = new ChromeDriver();
		loginPage = new LoginPage(driver);
	}

	@After
	public static void tearDown() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
	}

	@Given("A user is on the home login page")
	public void a_user_is_on_the_home_login_page() {
		driver.get("https://www.saucedemo.com/");
	}

	@When("A user enters correct username and password {string} {string}")
	public void a_user_enters_correct_username_and_password(String username, String password) {
		loginPage.login(username, password);
	}

	@Then("A user is redirected to the inventoryPage {string}")
	public void a_user_is_redirected_to_the_inventoryPage(String inventoryPage) {
		assertEquals(inventoryPage, driver.getCurrentUrl(), "unsuccessfully redirected");
	}
}
