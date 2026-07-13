package playwrightTests;

import java.awt.print.Pageable;
import java.nio.file.Path;
import java.util.regex.Pattern;

import org.testng.annotations.Test;
import org.yaml.snakeyaml.LoaderOptions;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;
import com.github.javafaker.Faker;
import com.microsoft.playwright.APIRequest.NewContextOptions;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.WaitForSelectorState;

public class PlaywrightAssignment {
	
	
@Test
public void verifyTitle()
{
	Browser browser=Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
	Page page=browser.newPage();
	
	page.navigate("https://freelance-learn-automation.vercel.app/");
	
	PlaywrightAssertions.assertThat(page).hasTitle(Pattern.compile(".*Courses.*"));
}
	
@Test
public void verifyCourseFooterGrt0()
{
	Browser browser=Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
	Page page=browser.newPage();
	
	page.navigate("https://freelance-learn-automation.vercel.app/");
	
	Locator courses=page.locator("div.course-card.row");
	
	PlaywrightAssertions.assertThat(courses).hasCount(courses.count());
	PlaywrightAssertions.assertThat(courses.first()).isVisible();

	//Footer count 4
	PlaywrightAssertions.assertThat(page.locator(".social-btns a")).hasCount(4);
}

@Test
public void VerifySignIn()
{

	Browser browser=Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
	Page page=browser.newPage();
	
	page.navigate("https://freelance-learn-automation.vercel.app/");
	
	page.locator("div[class='navbar-menu-links']").click();
	
	page.getByText("Log in").click();
	
	PlaywrightAssertions.assertThat(page.locator(".header")).hasText("Sign In");
	
	page.locator("#email1").fill("admin@email.com");
	
	page.locator("#password1").fill("admin@123");
	
	page.locator(".submit-btn").click();
	
	PlaywrightAssertions.assertThat(page.locator("h4.welcomeMessage")).containsText(Pattern.compile("Welcome"));
	
	// Verify welcome message disappears after 10 seconds
	page.locator("h4.welcomeMessage").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN).setTimeout(10000));
	
	page.getByAltText("menu").click();
	
	page.getByText("Sign out").click();
	
	PlaywrightAssertions.assertThat(page.locator(".header")).hasText("Sign In");
	
	
	
}

@Test
public void userRegistration()
{
	Browser browser=null;
	
	BrowserContext context=null;
	
	Page page=null;
	try {
		
		Faker testData= new Faker();
		browser=Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
		
		context= browser.newContext();
		
		context.tracing().start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true).setSources(true));
		
		page=context.newPage();
		
		page.navigate("https://freelance-learn-automation.vercel.app/");
		
		page.locator("div[class='navbar-menu-links']").click();
		
		page.getByText("Log in").click();
		
		PlaywrightAssertions.assertThat(page.locator("a.subLink")).isEnabled();
		
		page.locator("a.subLink").click();
		
		PlaywrightAssertions.assertThat(page.locator("h2.header")).hasText("Sign Up");
		PlaywrightAssertions.assertThat(page.locator(".submit-btn")).isDisabled();
		
		page.getByPlaceholder("Name").fill(testData.name().name());
		
		page.locator("#email").fill(testData.name().firstName()+"@email.com");
		
		page.locator("#password").fill("admin@123");
		
		page.locator("//label[text()='Java']/preceding-sibling::div/input").click();

		page.locator("//label[text()='TestNG']/preceding-sibling::div/input").click();
		
		page.locator("//label[text()='Female']/preceding-sibling::input").click();
		
		page.locator("#state").selectOption("Andhra Pradesh");
		
		page.locator("#hobbies").selectOption(new String[] {"Reading","Playing"});
		
		PlaywrightAssertions.assertThat(page.locator(".submit-btn")).isEnabled();
		
		page.locator(".submit-btn").click();
		
		PlaywrightAssertions.assertThat(page.locator("div.Toastify__toast-body")).containsText(Pattern.compile(".*successfully.*"));

		
	} finally {
		// TODO: handle finally clause
		
		context.tracing().stop(new Tracing.StopOptions().setPath(Path.of("Tracing.zip")));
		
		page.close();
		
		browser.close();
	}
	
	
	
}

@Test
public void validateCourseCreation()
{
	Faker testData= new Faker();
	Browser browser=Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
	Page page=browser.newPage();
	
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
	
	
	page.locator("#thumbnail").setInputFiles(Path.of(System.getProperty("user.dir")+"/src/test/resources/FileData/playwright-seeklogo.png"));
	
	page.locator("#name").fill("Playwright with Java practice");
	
	page.locator("#description").fill("Learning and Practice");
	
	page.locator("#instructorNameId").fill("SelfLearning");
	
	page.locator("#price").fill("1000");
	
	page.locator("[name='startDate']").click();
	
	page.getByText("12", new Page.GetByTextOptions().setExact(true)).click();
	
	page.locator("[name='endDate']").click();
	
	page.getByLabel("Next Month").click();
	
	page.getByText("11", new Page.GetByTextOptions().setExact(true)).click();
	
	page.locator("#isPermanent").click();
	
	page.locator(".menu-btn").click();
	
	page.locator("//button[text()='Java']").click();
	
	page.locator("//button[@class='action-btn']").click();
	
	
	
}
	

}
