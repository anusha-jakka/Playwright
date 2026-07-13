package Utility;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

import java.util.Date;

import com.microsoft.playwright.Page;

public class UtilityMethods {
	
	
	
	public static byte[] takePageScreenShot(Page page)
	{
		SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
		Date date=new Date();
		String actualDate=format.format(date);
		
		//captures only the visible screen
		page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("Screenshots/"+"check123"+".png")));
		
		//Captures full screen
		page.screenshot(new Page.ScreenshotOptions().setFullPage(true).setPath(Path.of("Screenshots/"+actualDate+".png")));
		
		
		return null;
		
	}

}
