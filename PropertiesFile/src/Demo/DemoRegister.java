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
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DemoRegister {

	static Logger log=Logger.getLogger(DemoRegister.class);
	    
	Properties pro=new Properties();
	
	WebDriver driver;
    @BeforeSuite
	public void openBrowser() throws IOException
	{
    	 Layout lay=new PatternLayout();
         Appender ap=new ConsoleAppender(lay);
         log.addAppender(ap);
    	log.debug("Before Suite");
		System.setProperty("webdriver.chrome.driver","C:\\Users\\HP\\OneDrive\\Desktop\\chromdriver110\\chromedriver_win32\\chromedriver.exe");
             driver=new ChromeDriver();	
             
         FileInputStream file=new FileInputStream("C:\\Users\\HP\\OneDrive\\Desktop\\NewCjc\\DemoWebShop.properties");  
         pro.load(file);         
             
	}
    @BeforeTest
     public void getUrl()
     {
    	driver.get(pro.getProperty("url"));
    	log.info("Url passed");
     }
  @BeforeClass
    public void maximizeBrowser()
    {
    	driver.manage().window().maximize();
        log.info("page maximised");
   
    }
    @BeforeMethod
    public void getCookies()
    {
    	 Set<Cookie> set=driver.manage().getCookies();
    	 System.out.println(set.size());
    	 for (Cookie cookie : set) 
    	{
		   System.out.println(cookie.getName());
		   System.out.println(cookie.getValue());
		   System.out.println(cookie.getPath());
		   System.out.println(cookie.getClass());
		   System.out.println(cookie.getDomain());
		   System.out.println(cookie.isSecure());
		   System.out.println(cookie.isHttpOnly());
	System.out.println("===================================================================");	   
		}
    }
    @Test
     public void Login()
     {
    	 log.info("Test Anotation");
    	 
    	 driver.findElement(By.name("Gender")).click();
    	 driver.findElement(By.name("FirstName")).sendKeys(pro.getProperty("fn"));
    	 driver.findElement(By.name("LastName")).sendKeys(pro.getProperty("ln"));
    	 driver.findElement(By.id("Email")).sendKeys(pro.getProperty("em"));
    	 driver.findElement(By.xpath("//*[@id=\"Password\"]")).sendKeys(pro.getProperty("ps"));
    	 driver.findElement(By.id("ConfirmPassword")).sendKeys(pro.getProperty("ps1"));
    	 driver.findElement(By.name("register-button")).click();
    	 log.info("Login SUCCESS");
    	 
     }
    @AfterMethod
     public void Scren() throws IOException
     {
    	 log.info("Screen CPture");
    	 File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    	 FileUtils.copyFile(src,new File("C:\\Users\\HP\\OneDrive\\Desktop\\hm\\new.jpg"));
     }
   @AfterClass
    public void delete()
    {
    	driver.manage().deleteAllCookies();
    	log.info("delete all cookies");
    }
   @AfterTest
   public void afterTest()
   {
	   log.info("Test After");
   }
   @AfterSuite
   public void afterSuite()
   {
	  log.info("After Suite");
	   driver.close();
   }










}
