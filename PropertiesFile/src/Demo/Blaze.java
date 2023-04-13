package Demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
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

public class Blaze {

	static Logger log=Logger.getLogger(Blaze.class);
	WebDriver driver;
	Properties pro=new Properties();
	@BeforeSuite
	public void browser() throws IOException
	{
		  System.setProperty("webdriver.chrome.driver","C:\\Users\\HP\\OneDrive\\Desktop\\ChromeDriver 111\\chromedriver_win32\\chromedriver.exe");
          driver=new ChromeDriver(); 
       FileInputStream file=new FileInputStream("C:\\Users\\HP\\OneDrive\\Desktop\\NewCjc\\Blaze.properties");
       pro.load(file);
       Layout lay=new PatternLayout();
       Appender ap=new ConsoleAppender(lay);
       log.addAppender(ap);
	}

	@BeforeTest
	public void getUrl()
	{
		log.info("UrlPassed");
		driver.get(pro.getProperty("url"));
	}

	@BeforeClass
	public void maximize()
	{
		log.info("Window Maximized");
		driver.manage().window().maximize();
	}
	
	@BeforeMethod
	public void getCookies()
	{
		log.info("Get Cookies");
		Set<Cookie>set=driver.manage().getCookies();
		System.out.println(set.size());
		
		  for (Cookie cookie : set)
		{
		   System.out.println(cookie.getName());	
		   System.out.println(cookie.getDomain());
		   System.out.println(cookie.getPath());
		   System.out.println(cookie.getValue());
		   System.out.println(cookie.getClass());
		   System.out.println(cookie.getExpiry());
		   System.out.println(cookie.isHttpOnly());
		   System.out.println(cookie.isSecure());
		   
		   System.out.println("=========================");
		}
	}

	@Test
	public void login() throws InterruptedException
	{
		log.info("SignUp Code");
		driver.findElement(By.xpath("//*[@id=\"signin2\"]")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("sign-username")).sendKeys(pro.getProperty("un1"));
		driver.findElement(By.id("sign-password")).sendKeys(pro.getProperty("ps1"));
		driver.findElement(By.xpath("//*[@id=\"signInModal\"]/div/div/div[3]/button[2]")).click();
		Thread.sleep(2000);
		driver.switchTo().alert().accept();
		log.info("Login Code");
		driver.findElement(By.xpath("//*[@id=\"login2\"]")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("loginusername")).sendKeys(pro.getProperty("un2"));
		driver.findElement(By.id("loginpassword")).sendKeys(pro.getProperty("ps2"));
		driver.findElement(By.xpath("//*[@id=\"logInModal\"]/div/div/div[3]/button[2]")).click();
		Thread.sleep(2000);
		//driver.switchTo().alert().accept();
		log.info("Login Complete");
	}


	@AfterMethod
	public void getScreen() throws IOException
	{
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src,new File("C:\\Users\\HP\\OneDrive\\Desktop\\NewCjc\\New.jpg"));
	}

	@AfterSuite
	public void close()
	{
		log.info("close");
		driver.close();
	}













}
