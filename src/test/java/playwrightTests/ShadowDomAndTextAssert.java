package playwrightTests;

import java.awt.print.Pageable;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

public class ShadowDomAndTextAssert {
	
	
	@Test
	public void shadowDomTest()
	{
		
		Browser browser= Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
	    Page page= browser.newPage();
		
		page.navigate("https://selectorshub.com/xpath-practice-page/");
		
		Locator shadowDomLocator= page.locator("#userName");
		
		String textString=shadowDomLocator.getByText("Learning Hub").innerText();
		
		PlaywrightAssertions.assertThat(page.getByText("Learning Hub", new Page.GetByTextOptions().setExact(true))).containsText("Learning Hub");
		
		System.out.println(textString);
		
	}

}
