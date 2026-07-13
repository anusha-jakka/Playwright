package playwrightTests;

import java.awt.print.Pageable;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class TablesValidationTest {
	
	
	@Test
	public void tableValidationTest()
	{
		// create a new context first, then create pages from that context
		Browser browser = Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
		BrowserContext context = browser.newContext();
		Page page = context.newPage();
		page.navigate("https://practicetestautomation.com/practice-test-table/");

		// locate the table row that contains the course text and click its View link
		// use context.waitForPage so the newly opened tab (page) is captured correctly
		// Use a row selector (tr) or a :has-text filter so you get the specific row,
		// and prefer Playwright text selectors instead of raw XPath for clarity.
		Page newTab = context.waitForPage(() -> {
			page.locator("#courses_table tr:has-text(\"Selenium with Java\")").locator("text=View").click();
		});
		newTab.bringToFront();
		System.out.println("New tab title"+newTab.url()); 
		
		newTab.close();
		page.close();
		browser.close();
	}

}
