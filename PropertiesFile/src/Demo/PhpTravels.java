package Demo;



	import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

	import org.openqa.selenium.By;
	import org.openqa.selenium.Cookie;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.support.ui.Select;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.AfterSuite;
	import org.testng.annotations.AfterTest;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.BeforeSuite;
	import org.testng.annotations.BeforeTest;
	import org.testng.annotations.Test;
	import org.testng.annotations.DataProvider;


	public class PhpTravels {

		Properties pro=new Properties();
		WebDriver driver;
		@BeforeSuite
		public void openbrowser() throws IOException
		{
			System.setProperty("webdriver.chrome.driver","C:\\Users\\HP\\OneDrive\\Desktop\\ChromeDriver 111\\chromedriver_win32\\chromedriver.exe");
	             driver=new ChromeDriver();
	           FileInputStream file=new FileInputStream("C:\\Users\\HP\\OneDrive\\Desktop\\NewCjc\\Php.properties");  
	             pro.load(file);
		}
	   @BeforeTest
		public void getUrl()
		{
		     driver.get(pro.getProperty("url"));
		     
		}
	  @BeforeClass
	   public void maximize()
	   {
		   driver.manage().window().maximize();
	   }
	  @BeforeMethod
	  public void cookie()
	  {
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
			   
			   System.out.println("=====================================================");
			}
	  }
	    @Test
	    public void login()
	    {
	    	driver.findElement(By.id("inputFirstName")).sendKeys(pro.getProperty("fn"));
	    	driver.findElement(By.id("inputLastName")).sendKeys(pro.getProperty("ln"));
	    	driver.findElement(By.id("inputEmail")).sendKeys(pro.getProperty("em"));
	    	driver.findElement(By.id("inputPhone")).sendKeys(pro.getProperty("ph"));
	    	driver.findElement(By.id("inputCompanyName")).sendKeys(pro.getProperty("com"));
	    	driver.findElement(By.id("inputAddress1")).sendKeys(pro.getProperty("sta"));
	    	driver.findElement(By.name("address2")).sendKeys(pro.getProperty("sta1"));
	    	driver.findElement(By.id("inputCity")).sendKeys(pro.getProperty("ci"));
	    	driver.findElement(By.id("stateinput")).sendKeys(pro.getProperty("st"));
	    	driver.findElement(By.name("postcode")).sendKeys(pro.getProperty("po"));
	     WebElement country=driver.findElement(By.id("inputCountry"));
	            Select sel=new Select(country);
	            sel.selectByIndex(4);
	            driver.findElement(By.id("customfield2")).sendKeys(pro.getProperty("wn"));
	            driver.findElement(By.id("inputNewPassword1")).sendKeys(pro.getProperty("ps"));
	            driver.findElement(By.id("inputNewPassword2")).sendKeys(pro.getProperty("ps1"));
	            //.findElement(By.linkText("Generate Password")).click();   
	           
	                 
	    }
	    @AfterClass
	    public void deleteCookie()
	    {
	    	driver.manage().deleteAllCookies();
	    }

	    @AfterTest
	    public void after()
	    {
	    	System.out.println("afterTest");
	    }
	   @AfterSuite
	    public void aftersuite()
	    {
	    	System.out.println("After Suite");
	    	driver.close();
	    	 
	    }
	  
	
	   
	  
	  
	  
	  
	  
	  











	}


