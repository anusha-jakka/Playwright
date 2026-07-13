package playwrightTests;

import java.nio.file.Path;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class VideoRecording {
	
	
	@Test
	public void videoRecordingTest()
	{
		
		Browser browser= Playwright.create().chromium().launch(new com.microsoft.playwright.BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
		BrowserContext context= browser.newContext(new Browser.NewContextOptions().setRecordVideoSize(1000, 800).setRecordVideoDir(Path.of("Videos/")));
		Page page=context.newPage();
		
		
		page.navigate("https://freelance-learn-automation.vercel.app/");
		
		page.getByAltText("menu").click();
		
		page.locator("button.nav-menu-item").click();
		
		page.locator("#email1").fill("admin@email.com");
		
		page.locator("#password1").fill("admin@123");
		
		page.locator(".submit-btn").click();
		
		page.locator("div.nav-menu-item-manage span").hover();
		
		page.getByText("Manage Courses", new Page.GetByTextOptions().setExact(true)).click();
		
		page.locator("div.manage-btns button").nth(3).click();
		
		page.onceDialog(dialog->{
			
			System.out.println(dialog.message());
			dialog.accept();
	   	});
		page.locator("#thumbnail").setInputFiles(Path.of(System.getProperty("user.dir")+"/src/test/resources/FileData/playwright-seeklogo.svg"));
		
		page.close();
		context.close();
		browser.close();
		
	}

}
