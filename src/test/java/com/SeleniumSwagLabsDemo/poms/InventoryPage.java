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

	public boolean selectProductSortContainer(String value, String equals) {
		Select selectObject = new Select(selectElement);
		selectObject.selectByValue(value);

		// waitHelper.ExplicitWaitForElement(5, "elementToBeClickable", selectElement);
		// waitHelper.ImplicitWaitForElement(5);

		//	List<WebElement> allSelectedOptions = selectObject.getAllSelectedOptions();
		List<WebElement> options = selectObject.getOptions();

		boolean optionExists = false;

		for (WebElement e: options) {
			if (e.getText().equals(equals)) {
				optionExists = true;
				break;
			}
		}
		return optionExists;
	}
}
