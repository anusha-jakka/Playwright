package playwrightTests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class StartWithPlaywright {

	public static void main(String[] args) {
		
		Playwright pw=Playwright.create();
		BrowserType browserType=pw.chromium();
		Browser browser=browserType.launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome"));
		Page pg=browser.newPage();
		pg.navigate("https://www.google.com");
		System.out.println(pg.title());
		pg.close();
		browser.close();
		pw.close();
		
	}

}
