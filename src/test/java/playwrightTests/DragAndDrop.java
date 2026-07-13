package playwrightTests;

import java.nio.file.Path;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;

public class DragAndDrop {
	
	
	@Test
	public void dragAndDropTest()
	{
		
		Browser browser=null;
		BrowserContext  context=null;
		Page page=null;
		
 try {
	 	browser=Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
		
		context= browser.newContext();
		context.tracing().start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true).setSources(true));
		
		page= context.newPage();
		
		page.navigate("https://demoqa.com/droppable");

//		page.dragAndDrop("#draggable", "#droppable");

//		Locator source= page.locator("div#draggable");
//		Locator target=	page.locator("div#droppable").first();
//		source.dragTo(target);

		page.locator("#draggable").hover();
		page.mouse().down();
		
		page.locator("#droppable").first().hover();
		page.mouse().down();
		
	
} finally {
		// TODO: handle finally clause
		
		context.tracing().stop(new Tracing.StopOptions().setPath(Path.of("Tracing.zip")));
		
		page.close();
		context.close();
		browser.close();
	
}
		
	
	
	
	}

}
