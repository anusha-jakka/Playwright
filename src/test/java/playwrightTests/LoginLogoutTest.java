package playwrightTests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

public class LoginLogoutTest {

	public static void main(String[] args) {
		
		Browser browser=null;
		Page page=null;
		
	try {
			browser=Playwright.create().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));	
			page=browser.newPage();
			
			page.navigate("https://www.saucedemo.com/");
			
			PlaywrightAssertions.assertThat(page).hasTitle("Swag Labs");
			
			//Enter username and password
			
			page.locator("#user-name").fill("standard_user");
			page.getByPlaceholder("Password").fill("secret_sauce");
			page.locator("css=input[type=\"submit\"]").click();
			
			PlaywrightAssertions.assertThat(page.locator(".shopping_cart_link")).isVisible();
			
			//Logout
			page.locator("#react-burger-menu-btn").click();
			page.getByText("Logout").click();
			
			PlaywrightAssertions.assertThat(page.locator("#login-button")).isVisible();
			
			
			
			
	}
	finally {
			
			page.close();
			browser.close();
	}

	}

}
