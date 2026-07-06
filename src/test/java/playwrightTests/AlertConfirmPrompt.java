package playwrightTests;

import org.testng.Assert;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Dialog;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class AlertConfirmPrompt {
	public Browser browser=null;
	public Page page=null;
	
	
	@BeforeMethod
	public void launchURL()
	{
		browser=Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		page=browser.newPage();
		
		page.navigate("https://demoqa.com/alerts");
	}
	
	@AfterMethod
	public void tearDown()
	{
		if(page != null)
			page.close();
		if(browser != null)
			browser.close();
	}
	
	@Test
	public void testAlert()
	{
		page.onDialog(dialog->{
			
			System.out.println(dialog.message());
			Assert.assertTrue(dialog.message().contains("You clicked a button"));
			
			dialog.accept();
			
		});
		
		page.locator("#alertButton").click();
		
	}
	
	@Test
	public void delayAlertTest()
	{
		// Wait for the dialog event while triggering the action that causes it.
		// Create a future to wait for the dialog handler to run.
		java.util.concurrent.CompletableFuture<Dialog> dialogFuture = new java.util.concurrent.CompletableFuture<>();

		System.out.println("Registering dialog handler for delayed alert...");
		page.onceDialog(dialog->{
			System.out.println("Dialog received: " + dialog.message());
			Assert.assertTrue(dialog.message().contains("This alert appeared after 5 seconds"));
			dialog.accept();
			dialogFuture.complete(dialog);
		});

		
		page.locator("#timerAlertButton").click();

		try {
			// Wait up to 15 seconds for the dialog to appear and be handled. The alert is triggered after 5s
			// so use a larger timeout to allow for slight delays.
			dialogFuture.get(15, java.util.concurrent.TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Timed out waiting for delayed alert: " + e);
		}
		
		
	}
	
	@Test
	public void promptTest()
	{
		page.onDialog(dialog->{
			System.out.println(dialog.message());
			Assert.assertTrue(dialog.message().contains("Please enter your name"));
			dialog.accept("Anusha");
		});
		
		page.locator("#promtButton").click();
	}
	
	

}
