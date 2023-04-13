package New;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DemoSeleniun {
   static Logger log=Logger.getLogger(DemoSeleniun.class);
	WebDriver driver;
	@BeforeSuite
	public void browser()
	{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\HP\\OneDrive\\Desktop\\ChromeDriver 111\\chromedriver_win32\\chromedriver.exe");
	          driver=new ChromeDriver();	
	          Layout lay=new PatternLayout();
	          Appender ap=new ConsoleAppender(lay);
	          log.addAppender(ap);
	}

	@BeforeTest
	public void url()
	{
		log.info("Url Passed");
		driver.get("https://demo.guru99.com/test/newtours/index.php");
	}

	@BeforeClass
	public void maximize()
	{
		log.info("Window Maximized");
		driver.manage().window().maximize();
	}

	@Test(dataProvider = "getData")
	public void login(String un ,String pas)
	{
		driver.findElement(By.name("userName")).sendKeys(un);
		driver.findElement(By.name("password")).sendKeys(pas);
		driver.findElement(By.name("submit")).click();
		
		driver.navigate().back();
		driver.findElement(By.name("userName")).clear();
		driver.findElement(By.name("password")).clear();
	}


	@AfterSuite
	public void close()
	{
		log.info("Driver Close");
		driver.close();
	}

	@DataProvider
	public Object[][] getData()
	{
      	Object[][] obj=new Object[2][2];
      	  obj[0][0]="QQQQ";
      	  obj[0][1]="qqqq";
      	  
      	  obj[1][0]="AAAA";
     	  obj[1][1]="aaaa";
     	  
		return obj;
		
	}
	











}
