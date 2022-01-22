package TestCases.AutomationTesting;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class assignment {
	WebDriver driver;
//	@Parameters("browser")
//	@BeforeTest
//	public void Launchbrowser(String browser) {
//		if(browser.equalsIgnoreCase("Chrome")) {
//		System.setProperty("webdriver.chrome.driver", "C:\\Users\\srinu\\eclipse-workspace\\AutomationTesting\\Drivers\\chromedriver.exe");
//		driver=new ChromeDriver();
//		driver.manage().window().maximize();	
//		}
//		else if(browser.equalsIgnoreCase("Firefox")) {
//			System.setProperty("webdriver.gecko.driver", "C:\\Users\\srinu\\eclipse-workspace\\AutomationTesting\\Drivers\\geckodriver.exe");
//			driver=new FirefoxDriver();
//			driver.manage().window().maximize();	
//		}
//		else if(browser.equalsIgnoreCase("ie")) {
//			System.setProperty("webdriver.chrome.driver", "C:\\Users\\srinu\\eclipse-workspace\\AutomationTesting\\Drivers\\IEDriverServerdriver.exe");
//			driver=new InternetExplorerDriver();
//			driver.manage().window().maximize();	
//		}
//	}
//	
	
	@BeforeTest
	public void Launchbrowser() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\srinu\\eclipse-workspace\\AutomationTesting\\Drivers\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	public String readdata(String key) throws IOException {
		Properties obj=new Properties();
		FileInputStream obj1=new FileInputStream("C:\\Users\\srinu\\eclipse-workspace\\AutomationTesting\\read.properties");
		obj.load(obj1);
		String value=obj.getProperty(key);
		return value;
	}
	                
	@Test       //   Handling Scrolling Operation      //
	public void testcase1() throws IOException, InterruptedException {
		driver.get(readdata("url1"));
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)", "");
		Thread.sleep(3000);
		js.executeScript("window.scrollTo(0, -document.body.scrollHeight)", "");
		
	}
	                 
	@Test      //   Handling Window Handles           //
	public void testcase2() throws IOException, InterruptedException {
		driver.get(readdata("url2"));
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[text()='Software & IT']")).click();
		String parentwindow=driver.getWindowHandle();
        Reporter.log("parent window ID is : "+parentwindow, true);
		Set<String> allwindows=driver.getWindowHandles();
		
		ArrayList<String> tabs= new ArrayList<String> (allwindows);
		for(int i=0;i<tabs.size();i++) {
			driver.switchTo().window(tabs.get(i));
			String a=(driver.getTitle());
			Reporter.log(a, true);
		}
			String b=driver.getTitle();
			String c=driver.getCurrentUrl();
			Reporter.log(c, true);
			Reporter.log(b, true);
			driver.switchTo().window(tabs.get(1));
			String d=driver.getTitle();
			String e=driver.getCurrentUrl();
			Reporter.log(d, true);
			Reporter.log(e, true);
		
	}
	                
	@Test       //   Handling DropDown Operation      //
	public void testcase3() throws IOException, InterruptedException {
		driver.navigate().to(readdata("url3"));
		driver.findElement(By.xpath("//a[text()='Create New Account']")).click();
		Thread.sleep(3000);
		WebElement ab=driver.findElement(By.id("day"));
				Select select=new Select(ab);
		        select.selectByIndex(15);
	    WebElement ab1=driver.findElement(By.id("month"));
				Select select1=new Select(ab1);
		        select1.selectByValue("2");
		        Thread.sleep(2000);
		WebElement ab2=driver.findElement(By.id("year"));
				Select select2=new Select(ab2);
		        select2.selectByVisibleText("1999");
		        SS(driver, "DropDown");
	}
	public void SS(WebDriver driver, String loop) throws IOException {
		TakesScreenshot obj2=(TakesScreenshot)driver;
        File srcFile=obj2.getScreenshotAs(OutputType.FILE);
        File destFile=new File("C:\\Users\\srinu\\eclipse-workspace\\AutomationTesting\\ScreenShot\\"+loop+".png");
        FileUtils.copyFile(srcFile,destFile);
	}
	                
	@Test      //       Handling Alerts    //
	public void testcase4() throws IOException, InterruptedException {
		
		driver.get(readdata("url4"));
		driver.findElement(By.id("alertButton")).click();
              Alert alert=driver.switchTo().alert();
              alert.accept();
		driver.findElement(By.id("timerAlertButton")).click();
		Thread.sleep(6000);
		      Alert alert2=driver.switchTo().alert();
              alert2.accept();

		driver.findElement(By.id("confirmButton")).click();
		      Alert alert3=driver.switchTo().alert();
              alert3.dismiss();
              String f=driver.findElement(By.id("confirmResult")).getText();
            Reporter.log(f, true);

		driver.findElement(By.id("promtButton")).click();
		      Alert alert4=driver.switchTo().alert();
		      alert4.sendKeys("Assignment Submit");
              alert4.accept();
              String g=driver.findElement(By.id("promptResult")).getText();
		    Reporter.log("Entered Values is : "+g,true);
	}
	@Test      //    Handling Title,Url,Pagesurce         //
	public void testcase5() throws IOException {
		driver.get(readdata("url5"));
		String h=driver.getTitle();
		String i=driver.getCurrentUrl();
		driver.getPageSource();
		Reporter.log(h,true);
		Reporter.log(i,true);
	}
	                
	@Test     //    Handling All Links          //
	public void testcase6() throws IOException {
		driver.navigate().to(readdata("url3"));
		List<WebElement> links=driver.findElements(By.tagName("a"));
		for(WebElement alllinks:links ) {
			System.out.println(alllinks.getText());
		}	
	}
	@Test   //    Handling  CheckBoxes And RadioButton     //
	public void testcase7() throws InterruptedException, IOException {
		driver.get(readdata("url6"));
		driver.findElement(By.xpath("//button[@title='Toggle']")).click();
		driver.findElement(By.xpath("(//button[@title='Toggle'])[3]")).click();
		driver.findElement(By.xpath("(//button[@title='Toggle'])[5]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='Public']")).click();
		TakesScreenshot obj3=(TakesScreenshot) driver;
		File srcFile=obj3.getScreenshotAs(OutputType.FILE);
		File destFile=new File("C:\\Users\\srinu\\eclipse-workspace\\AutomationTesting\\ScreenShot\\CheckBox.png");
		FileUtils.copyFile(srcFile, destFile);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='Radio Button']")).click();
		driver.findElement(By.xpath("//label[text()='Impressive']")).click();
		String text =driver.findElement(By.xpath("//span[@class=\"text-success\"]")).getText();
		Reporter.log("You Selected :: "+text, true);
		SS(driver, "RadioButton");
		
	}
	@Test   //    Handling  ActionsClass     //
	public void testcase8() throws IOException {
		//    MouseOver    //
		driver.get(readdata("url7"));
		WebElement ab=driver.findElement(By.id("product-menu-toggle"));
		Actions action= new Actions(driver);
		action.moveToElement(ab).build().perform();
		driver.findElement(By.xpath("(//div[text()=' Percy '])[1]")).click();
		//     DoubleClick    //
		driver.get(readdata("url8"));
		WebElement ab2=driver.findElement(By.id("doubleClickBtn"));
		Actions action2= new Actions(driver);
		action2.doubleClick(ab2).build().perform();
		String j  =driver.findElement(By.id("doubleClickMessage")).getText();
		Reporter.log(j, true);
		//      RightClick    //
		WebElement ab3=driver.findElement(By.id("rightClickBtn"));
		Actions action3= new Actions(driver);
		action3.contextClick(ab3).build().perform();
		String k  =driver.findElement(By.id("rightClickMessage")).getText();
		Reporter.log(k, true);
		driver.get(readdata("url9"));
		//     Drag & Drop     //
		WebElement source=driver.findElement(By.xpath("//a[text()=' BANK ']"));
		WebElement target=driver.findElement(By.id("bank"));
		
		WebElement source1=driver.findElement(By.xpath("//a[text()=' 5000']"));
		WebElement target1=driver.findElement(By.id("amt7"));
		
		Actions action4= new Actions(driver);
		action4.dragAndDrop(source, target).build().perform();
		Actions action5= new Actions(driver);
		action5.dragAndDrop(source1, target1).build().perform();
		SS(driver, "Drag&Drop");
		
	}
	@Test   //    Entering Data     //
	public void testcase9() throws IOException, InterruptedException {
		driver.get(readdata("url3"));
		driver.findElement(By.xpath("//a[text()='Create New Account']")).click();
		Thread.sleep(4000);
		driver.findElement(By.name("firstname")).sendKeys(readdata("first"));
		driver.findElement(By.name("lastname")).sendKeys(readdata("last"));
		driver.findElement(By.name("reg_email__")).sendKeys(readdata("email"));
		driver.findElement(By.name("reg_passwd__")).sendKeys("password");
		SS(driver, "Data");
		
	}

	@AfterTest
	public void Closebrowser() {
		driver.quit();
	}
	
	
	
	

}
