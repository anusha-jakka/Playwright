package playwrightTests;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

public class frameTests {
	
	
	
@Test
public void frameTest() {
	
	Browser browser=Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
	Page page=browser.newPage();
	page.navigate("https://demoqa.com/frames");
	
	//Switch to frame using frame locator and verify the text in the frame
	PlaywrightAssertions.assertThat(page.frameLocator("#frame1").locator("#sampleHeading")).containsText("This is a sample page");
	
	PlaywrightAssertions.assertThat(page.frameLocator("#iframe2").locator("#sampleHeading")).containsText("This is a sample page");
	
}


@Test
public void nestedFrames()
{
	Browser browser=Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
	Page page=browser.newPage();
	
	page.navigate("https://demo.automationtesting.in/Frames.html");
	
	page.getByText("Iframe with in an Iframe").click();
	
	//Switch to first frame and verify the text
    
	// Use FrameLocator.frameLocator to target nested iframes reliably
	FrameLocator parent = page.frameLocator("css=iframe[src='MultipleFrames.html']");

	// Verify the heading inside the parent iframe
	PlaywrightAssertions.assertThat(parent.locator(".iframe-container h5")).containsText("Nested iFrame");

	// Target the child iframe inside the parent and verify the heading
	FrameLocator child = parent.frameLocator("css=iframe[src='SingleFrame.html']");
	PlaywrightAssertions.assertThat(child.locator(".container h5")).containsText("iFrame Demo");

	// Fill the input inside the child iframe
	child.locator("input[type='text']").fill("Test123");

}

}
