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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class JpStore {

	WebDriver driver;
	Properties pro=new Properties();
	Logger log=Logger.getLogger(JpStore.class);
	
	@BeforeSuite
	public void browser() throws IOException
	{
      System.setProperty("webdriver.chrome.driver","C:\\Users\\HP\\OneDrive\\Desktop\\ChromeDriver 111\\chromedriver_win32\\chromedriver.exe");
              driver=new ChromeDriver(); 
           FileInputStream file=new FileInputStream("C:\\Users\\HP\\OneDrive\\Desktop\\NewCjc\\JpStore.properties");
           pro.load(file);
           Layout lay=new PatternLayout();
           Appender ap=new ConsoleAppender(lay);
           log.addAppender(ap);
	}

	@BeforeTest
	public void GetUrl()
	{
		log.info("Url Passed");
		driver.get(pro.getProperty("url"));
	}

	@BeforeClass
	public void maxi()
	{
		log.info("Maximized");
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
	public void login()
	{
		log.info("Login Method");
		driver.findElement(By.name("username")).sendKeys(pro.getProperty("uid"));
		driver.findElement(By.name("password")).sendKeys(pro.getProperty("ps"));
		driver.findElement(By.name("repeatedPassword")).sendKeys(pro.getProperty("ps1"));
		driver.findElement(By.name("firstName")).sendKeys(pro.getProperty("fn"));
		driver.findElement(By.name("lastName")).sendKeys(pro.getProperty("ln"));
		driver.findElement(By.name("email")).sendKeys(pro.getProperty("em"));
		driver.findElement(By.name("phone")).sendKeys(pro.getProperty("ph"));
		driver.findElement(By.name("address1")).sendKeys(pro.getProperty("ad1"));
		driver.findElement(By.name("address2")).sendKeys(pro.getProperty("ad2"));
		driver.findElement(By.name("city")).sendKeys(pro.getProperty("ci"));
		driver.findElement(By.name("state")).sendKeys(pro.getProperty("st"));
		driver.findElement(By.name("zip")).sendKeys(pro.getProperty("zp"));
		driver.findElement(By.name("country")).sendKeys(pro.getProperty("co"));
		
		WebElement lang=driver.findElement(By.name("languagePreference"));
		       Select sel=new Select(lang);
		          sel.selectByIndex(1);
		   
		WebElement pet=driver.findElement(By.name("favouriteCategoryId"));
     	       Select se=new Select(pet);
		          se.selectByIndex(1);          
		 driver.findElement(By.name("listOption")).click();         
		 driver.findElement(By.name("bannerOption")).click(); 
		 driver.findElement(By.className("button")).click();
		 log.info("Login Success");
	}

	@AfterMethod
	public void getScreen() throws IOException
	{
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src,new File("C:\\Users\\HP\\OneDrive\\Desktop\\ChromeDriver 111\\chromedriver_win32\\Nee.jpg"));
	    log.info("ScreenShot Captured");
	}

	@AfterSuite
	public void close()
	{
		log.info("Driver Close");
		driver.close();
	}


















}
