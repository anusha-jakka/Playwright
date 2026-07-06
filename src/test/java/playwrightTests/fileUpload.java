package playwrightTests;

import java.nio.file.Path;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.FileChooser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class fileUpload {
	
	//File upload without the input tag
	//With input tag is added in practiceFormTests.java
	@Test
	public void fileUploadTest()
	{
		Browser browser=Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		Page page=browser.newPage();
		page.navigate("https://www.dropzone.dev/");
		
		FileChooser fileChooser=page.waitForFileChooser(()->{
			page.locator(".dz-clickable").click();
		});
		
//		fileChooser.setFiles(Path.of(System.getProperty("user.dir")+"/src/test/resources/FileData/brochure_henley_woods.pdf"));
		fileChooser.setFiles(new Path[] {Path.of(System.getProperty("user.dir")+"/src/test/resources/FileData/brochure_henley_woods.pdf"),
				Path.of(System.getProperty("user.dir")+"/src/test/resources/FileData/Henley Available List.pdf")});
		
	}

}
