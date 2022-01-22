package TestCases.AutomationTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class FileUpload {
	WebDriver driver;
	@Test
	public void name() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32\\chromedriver.exe");
		 driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demo.guru99.com/test/upload/");
		Thread.sleep(9000);
		driver.findElement(By.name("uploadfile_0")).sendKeys("C:\\Users\\srinu\\Desktop\\uploadfile.txt");
		driver.findElement(By.name("terms")).click();
		driver.findElement(By.id("submitbutton")).click();
		driver.quit();
//		
		
	}

	
	
	
	
	
}
