package com.SeleniumSwagLabsDemo.poms;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ISelect;
import org.openqa.selenium.support.ui.Select;

import com.SeleniumSwagLabsDemo.utilities.WaitHelper;

public class InventoryPage {

	private static WaitHelper waitHelper;

	@FindBy(xpath = "//select[@data-test='product_sort_container']")
	@CacheLookup
	private WebElement selectElement;

	public InventoryPage(WebDriver driver) {
		waitHelper = new WaitHelper(driver);
		PageFactory.initElements(driver, this);
	}

	public boolean selectProductSortContainer(String value) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		selectElement.click();

		waitHelper.ImplicitWaitForElement(2);
		Select selectObject = new Select(selectElement);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		boolean optionExists = false;

		try {
			selectObject.selectByValue(value);
			optionExists = true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		}

		return optionExists;
	}
}
