package playwrightTests;

import java.awt.print.Pageable;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import Utility.UtilityMethods;

public class Screenshots {
	
	
	@Test
	public void takeScreenshot()
	{
		
	Browser browser=Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
	Page page=browser.newPage();
	
	page.navigate("https://selectorshub.com/xpath-practice-page/");
	
//	//captures only the visible screen
//	page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("Screenshots/"+"Screenshot2.png")));
//	
//	//Capture the entire screen
//	page.screenshot(new Page.ScreenshotOptions().setFullPage(true).setPath(Path.of("Screenshots/"+"Screenshot3.png")));
	

	UtilityMethods.takePageScreenShot(page);
	
	//Capture particular element
	page.locator("div[data-id='45227f6']").screenshot(new Locator.ScreenshotOptions().setPath(Path.of("Screenshots/"+"Screenshot4.png")));
	
	}

}
