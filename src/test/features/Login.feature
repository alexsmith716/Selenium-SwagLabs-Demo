Feature: Login For Swag Labs

	Background: 
		Given A user is on the home login page

		Scenario: Logging Into Swag Labs with Correct Credentials
			When A user enters correct username and password "performance_glitch_user" "secret_sauce"
			Then A user is redirected to the inventoryPage "https://www.saucedemo.com/inventory.html"
