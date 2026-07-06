package playwrightTests;

import java.nio.file.Path;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

public class practiceFormTests {

	@Test
	public void practiceFormSubmitTest() {
		
		Browser browser=Playwright.create().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
		Page page=browser.newPage();
		page.navigate("https://demoqa.com/automation-practice-form");
		
		//Assert Form title
		PlaywrightAssertions.assertThat(page.locator(".text-center")).containsText("Practice Form");

		//Fill the form
		page.getByPlaceholder("First Name").fill(new Faker().name().firstName());
		
		page.locator("#lastName").fill(new Faker().name().lastName());
		
		page.locator("#userEmail").fill(new Faker().internet().emailAddress());
		
		page.locator("xpath=//input[@value='Female']").click();
		
		page.getByPlaceholder("Mobile Number").fill(new Faker().phoneNumber().subscriberNumber(10));
		
		//-->Automate calender field later

		page.locator("xpath=//label[text()='Sports']//preceding::input[1]").click();
		
		//File upload 
		//Single file-->
		page.locator("#uploadPicture").setInputFiles(Path.of(System.getProperty("user.dir")+"/src/test/resources/FileData/brochure_henley_woods.pdf"));
		
		//remove single file
		page.locator("#uploadPicture").setInputFiles(new Path[0]);
		
		//pass multiple file-->
/*		page.locator("#uploadPicture").setInputFiles(new Path[] {
				
				Path.of(System.getProperty("user.dir")+"/src/test/resources/FileData/brochure_henley_woods.pdf"),
				Path.of(System.getProperty("user.dir")+"/src/test/resources/FileData/Henley Available List.pdf")
				
		});*/
		
		
		page.getByPlaceholder("Current Address").fill(new Faker().address().fullAddress());
		
		page.locator("#submit").click();
		
		PlaywrightAssertions.assertThat(page.locator(".modal-content")).isVisible();
		PlaywrightAssertions.assertThat(page.locator("#example-modal-sizes-title-lg")).containsText("Thanks for");
		
		page.close();
		browser.close();
		
	}

}
