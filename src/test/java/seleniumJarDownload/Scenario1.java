/**
 * This Selenium java code snippet shows the way to ensure the file has downloaded.
 */
package seleniumJarDownload;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Scenario1 {

	@Test
	public  void test1() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		String downloadFilepath = System.getProperty("user.dir");
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("safebrowsing.enabled", "true"); 
		chromePrefs.put("download.default_directory", downloadFilepath);
		options.setExperimentalOption("prefs", chromePrefs);

		WebDriver driver=new ChromeDriver(options);
		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.get("https://www.selenium.dev/downloads/");
		driver.manage().window().maximize();
		WebElement downloadLink=driver.findElement(By.partialLinkText("4.9.1"));
		downloadLink.click();
		
		try {
			Thread.sleep(50000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		File file=new File(downloadFilepath);
		Assert.assertTrue(file.exists());
//		if(file.exists())
//		{
//			System.out.println("file downloaded successfully");
//		}
//		else
//		{
//			System.out.println("Error in downloading the file");
//		}
		
	}

}
