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
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class MerRegister {

	static Logger log=Logger.getLogger(MerRegister.class);
	
	
	WebDriver driver;
	
	Properties pro=new Properties();
	@BeforeSuite
	public void openBrowser() throws IOException
	{
		log.info("Before Suite");
		System.setProperty("webdriver.chrome.driver","C:\\Users\\HP\\OneDrive\\Desktop\\chromdriver110\\chromedriver_win32\\chromedriver.exe");
             driver=new ChromeDriver();
          FileInputStream file=new FileInputStream("C:\\Users\\HP\\OneDrive\\Desktop\\NewCjc\\MerRegister.properties");   
	          pro.load(file);
	          
	}
	  @BeforeTest
     public void getUrl()
	{
		log.info("Before Test");
		driver.get(pro.getProperty("url"));
		System.out.println("url passed");


	}
     @BeforeClass
	  public void maximizeBrowser()
	  {
	    log.info("Before Class");
		driver.manage().window().maximize();
	
	  }
    @BeforeMethod
     public void getCookies()
     {
    	 log.info("Before Method");
    	  Set<Cookie> set= driver.manage().getCookies();
    	   System.out.println(set.size());
    	   for (Cookie cookie : set)
        {
		         System.out.println(cookie.getName());
		         System.out.println(cookie.getDomain());
		         System.out.println(cookie.getPath());
		         System.out.println(cookie.getClass());
		         System.out.println(cookie.getValue());
		         
		         System.out.println(cookie.isHttpOnly());
		         System.out.println(cookie.isSecure());
		         System.out.println("===============================================================");
		}
    	   
     }
     @Test
    public void login()
    {
    	 Layout lay=new PatternLayout();
    		Appender ap=new ConsoleAppender(lay);
    	  log.info("Test");
    	 driver.findElement(By.name("firstName")).sendKeys(pro.getProperty("fn"));
    	 driver.findElement(By.name("lastName")).sendKeys(pro.getProperty("ln"));
    	 driver.findElement(By.name("phone")).sendKeys(pro.getProperty("ph"));
    	 driver.findElement(By.xpath("//*[@id=\"userName\"]")).sendKeys(pro.getProperty("em"));
    	 driver.findElement(By.name("address1")).sendKeys(pro.getProperty("ad"));
    	 driver.findElement(By.cssSelector("input[name='city']")).sendKeys(pro.getProperty("ci"));
    	 driver.findElement(By.name("state")).sendKeys(pro.getProperty("st"));
    	 driver.findElement(By.name("postalCode")).sendKeys(pro.getProperty("po"));
    	 WebElement country=driver.findElement(By.name("country"));
    	         Select sel=new Select(country);
    	         sel.selectByValue("INDIA");
    	 driver.findElement(By.id("email")).sendKeys(pro.getProperty("un"));
    	 driver.findElement(By.name("password")).sendKeys(pro.getProperty("ps"));
    	 driver.findElement(By.name("confirmPassword")).sendKeys(pro.getProperty("ps1"));
    	 driver.findElement(By.name("submit")).click();
    	 
    	 log.info("Login Suceess");
    	 log.addAppender(ap);
    }
    @AfterMethod
     public void ScreenCapture() throws IOException
     {
    	 log.info("ScreenShot Captured");
    	File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    	FileUtils.copyFile(src ,new File("C:\\Users\\HP\\OneDrive\\Desktop\\hm\\New.jpg"));
        log.info("Screen Cpatured");
        
     }

    @AfterClass
    public void deleteCookies()
    {
    	driver.manage().deleteAllCookies();
    	log.info("Cookie Deleted");
    	
    }
     @AfterTest
    public void aftertest()
    {
    	log.info("After Test");
    	
    }
   @AfterSuite
   public void close()
   {
	   log.info("after suite");
	   driver.close();
	   
   }
     


}
