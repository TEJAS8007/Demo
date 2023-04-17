package Demo;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DemoWebShop {

	static Logger log=Logger.getLogger(DemoWebShop.class.getName());
	WebDriver driver;
	@BeforeSuite
	public void broweser()
	{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\HP\\OneDrive\\Desktop\\ChromeDriver 112\\chromedriver_win32\\chromedriver.exe");
	         driver=new ChromeDriver();
	         Layout lay=new PatternLayout();
	         Appender ap=new ConsoleAppender(lay);
	         log.addAppender(ap);
	}

	@BeforeTest
	public void getUrl() {
		driver.get("https://demowebshop.tricentis.com/register");
	}

	@BeforeClass
	public void maximize()
	{
		driver.manage().window().maximize();
	}

	@BeforeMethod
	public void cookies() 
	{
		Set<Cookie>set=driver.manage().getCookies();
		System.out.println(set.size());
		
		for (Cookie cookie : set)
		{
		   log.info(cookie.getName());
		   log.info(cookie.getDomain());
		   log.info(cookie.getClass());
		   log.info(cookie.getPath());
		   log.info(cookie.getValue());
		   log.info(cookie.getExpiry());
		   log.info(cookie.isHttpOnly());
		   log.info(cookie.isSecure());
		   System.out.println("========================================================================");
		}
	}

	@Test
	public void login()
	{
		log.info("Login Action");
		driver.findElement(By.name("Gender")).click();
		driver.findElement(By.name("FirstName")).sendKeys("Tejas");
		driver.findElement(By.name("LastName")).sendKeys("Aware");
		driver.findElement(By.name("Email")).sendKeys("Tejas@123gmail.com");
		driver.findElement(By.name("Password")).sendKeys("Pass@123");
		driver.findElement(By.name("ConfirmPassword")).sendKeys("Pass@123");
		driver.findElement(By.name("register-button")).click();
		log.info("Login Action Complete");
	}
	
	@AfterMethod
	public void getScreen() throws IOException
	{
		File img=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(img,new File("C:\\Users\\HP\\OneDrive\\Desktop\\ChromeDriver 112\\chromedriver_win32\\Demo.jpg"));
	}
	
	@AfterSuite
	public void close()
	{
		driver.close();
	}
	
	
	
	
	
	
	
	
	
	












}
