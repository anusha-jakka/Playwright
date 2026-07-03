package playwrightTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

public class LoginLogoutTestng {
	
	
	
	@Test
	public void LoginLogoutTest() {
		
		Browser browser=null;
		Page page=null;
		
	try {
			browser=Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));	
			page=browser.newPage();
			
			page.navigate("https://www.saucedemo.com/");
			
//			PlaywrightAssertions.assertThat(page).hasTitle("Swag Labs");
			Assert.assertTrue(page.title().equals("Swag Labs"), "Title is not matching");
			
			//Enter username and password
			
			page.locator("#user-name").fill("standard_user");
			page.getByPlaceholder("Password").fill("secret_sauce");
			page.locator("css=input[type=\"submit\"]").click();
			
			//PlaywrightAssertions.assertThat(page.locator(".shopping_cart_link")).isVisible();
			Assert.assertTrue(page.locator(".shopping_cart_link").isVisible(), "Shopping cart link is not visible");
			
			//Logout
			page.locator("#react-burger-menu-btn").click();
			page.getByText("Logout").click();
			
			//PlaywrightAssertions.assertThat(page.locator("#login-button")).isVisible();
			Assert.assertTrue(page.locator("#login-button").isVisible(), "Login button is not visible");
			
			
			
			
	}
	finally {
			
			page.close();
			browser.close();
	}

	}
	
	@Test
	public void LoginLogoutFailTest() {
		
		Browser browser=null;
		Page page=null;
		
	try {
			browser=Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));	
			page=browser.newPage();
			
			page.navigate("https://www.saucedemo.com/");
			
//			PlaywrightAssertions.assertThat(page).hasTitle("Swag Labs");
			Assert.assertTrue(page.title().equals("Swag Labs"), "Title is not matching");
			
			//Enter username and password
			
			page.locator("#user-name").fill("standard_user123");
			page.getByPlaceholder("Password").fill("secret_sauce");
			page.locator("css=input[type=\"submit\"]").click();
			
			//PlaywrightAssertions.assertThat(page.locator(".shopping_cart_link")).isVisible();
			Assert.assertTrue(page.locator("css=h3[data-test=\"error\"]").isVisible(), "Error message is not visible");
			Assert.assertEquals(page.locator("css=h3[data-test=\"error\"]").textContent(), "Epic sadface: Username and password do not match any user in this service", "Error message is not matching");
			
			
			
			
			
	}
	finally {
			
			page.close();
			browser.close();
	}

	}

}
