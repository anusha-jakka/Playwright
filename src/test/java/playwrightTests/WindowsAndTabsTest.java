package playwrightTests;

import java.util.List;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

public class WindowsAndTabsTest {
	
	
	@Test
	public void tabTest() {
		
	Browser browser=Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));	
	
	BrowserContext context= browser.newContext();
	Page page=context.newPage();
	
	page.navigate("https://demoqa.com/browser-windows");
	
	Page newTab=context.waitForPage(()->{
		
		page.locator("#tabButton").click();
	});
	
	PlaywrightAssertions.assertThat(newTab.locator("#sampleHeading")).containsText("This is a sample page");
		
	page.bringToFront();
	
	newTab.bringToFront();
	
	newTab.close();
	
	page.close();
	}
	
	@Test
	public void windowTest() {
		
	Browser browser=Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));	
	
	BrowserContext context= browser.newContext();
	Page page=context.newPage();
	
	page.navigate("https://demoqa.com/browser-windows");
	
	Page newWindow=context.waitForPage(()->{
		
		page.locator("#messageWindowButton").click();
	});
	
	PlaywrightAssertions.assertThat(newWindow.locator("body")).containsText("Knowledge increases by sharing");
		
	page.bringToFront();
	
	newWindow.bringToFront();
	
	newWindow.close();
	
	page.close();
	}

	
	@Test
	public void multiTabSwitchWithTitle()
	{
		
		Browser browser=Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(2000));
		BrowserContext context=browser.newContext();
		
		Page page=context.newPage();
		page.navigate("https://demo.automationtesting.in/Windows.html");
		
		Locator socials=page.locator("div.social a");
		
		
		for(int i=0;i<socials.count();i++)
		{
			socials.nth(i).click();
		}
		
		List<Page> openPages=context.pages();
		
		for(Page p:openPages)
		{
			String title=p.title();
			
			if(title.contains("Profile"))
			{
				p.bringToFront();
				break;
				
			}
			
		}
		
		page.bringToFront();
		context.close();
		
		
	}
}
