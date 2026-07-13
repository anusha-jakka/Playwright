package playwrightTests;

import java.awt.print.Pageable;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class DropDownTest {
	
	
	@Test
	public void SelectTest()
	{
		
		Browser browser=Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
		Page page=browser.newPage();
		
		page.navigate("https://demoqa.com/select-menu");
		
		page.locator("input#react-select-2-input").click();
		page.locator("div#react-select-2-option-0-0").click();
		
		
		page.locator("#react-select-3-input").click();
		page.getByText("Mrs.").click();
		
		page.locator("#react-select-4-input").click();
		page.locator("#react-select-4-option-0").click();
		page.locator("#react-select-4-option-1").click();
		
		
		page.locator("#oldSelectMenu").selectOption("Red");
		
		page.locator("#cars").selectOption(new String[] {"Volvo","Audi"});
		
		page.close();
		browser.close();
	
	}

}
