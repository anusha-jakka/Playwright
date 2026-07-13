package playwrightTests;

import org.testng.annotations.Test;
import org.xml.sax.ext.Locator2Impl;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.WaitForSelectorState;

public class DynamicWaits {
	
	
	
@Test
public void navigationWaitTest()
{
	Browser browser=Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
	Page page=browser.newPage();
	
	//Navigation timeout
	page.navigate("https://seleniumpractise.blogspot.com/2016/08/how-to-use-explicit-wait-in-selenium.html",new Page.NavigateOptions().setTimeout(15000));

}

@Test
public void defaultAndDynamicWait()
{
	Browser browser=Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
	Page page=browser.newPage();
	
	//Navigation timeout
	page.navigate("https://seleniumpractise.blogspot.com/2016/08/how-to-use-explicit-wait-in-selenium.html",new Page.NavigateOptions().setTimeout(15000));

	//change the default wait from 30sec to 15sec for all the actions
	
	page.setDefaultTimeout(20000);
	
	page.locator("//button[text()='Click me to start timer']").click();
	
	//Verify the text that appears after 15sec
	page.locator("//p[text()='WebDriver']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(16000));
	
	System.out.println("Webdriver text visible:"+page.locator("//p[text()='WebDriver']").isVisible());
}

}
