package New;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DemoWebShop2 {

	static Logger log=Logger.getLogger(DemoWebShop2.class);
	Properties pro=new Properties();
	WebDriver driver;
	@BeforeSuite
	public void browser() throws IOException
	{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\HP\\OneDrive\\Desktop\\ChromeDriver 111\\chromedriver_win32\\chromedriver.exe");
        driver=new ChromeDriver(); 
     FileInputStream file=new FileInputStream("C:\\Users\\HP\\OneDrive\\Desktop\\NewCjc\\DemoWeb2.properties");
     pro.load(file);
     Layout lay=new PatternLayout();
     Appender ap=new ConsoleAppender(lay);
     log.addAppender(ap);
	}
	
	@BeforeTest
	public void getUrl()
	{
		log.info("Url passed");
		driver.get(pro.getProperty("url"));
	}
	
	@BeforeClass
	public void maximize()
	{
		log.info("Window Maximized");
		driver.manage().window().maximize();
	}
	
	@Test(priority = 1)
	public void login()
	{
		log.info("Login Check");
		driver.findElement(By.name("Gender")).click();
		driver.findElement(By.name("FirstName")).sendKeys(pro.getProperty("fn"));
		driver.findElement(By.name("LastName")).sendKeys(pro.getProperty("ln"));
		driver.findElement(By.name("Email")).sendKeys(pro.getProperty("em"));
		driver.findElement(By.name("Password")).sendKeys(pro.getProperty("ps"));
		driver.findElement(By.name("ConfirmPassword")).sendKeys(pro.getProperty("ps1"));
		driver.findElement(By.name("register-button")).click();
		log.info("Login Complete");
	}
	@Test(priority = 2)
	public void purchaseBook() throws InterruptedException
	{
		log.info("Book Purchase");
		driver.findElement(By.linkText("Books")).click();
		driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div[2]/div[2]/div[2]/div[3]/div[1]/div/div[1]/a/img")).click();
		driver.findElement(By.xpath("//*[@id=\"add-to-cart-button-13\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"topcartlink\"]/a")).click();
		WebElement country=driver.findElement(By.xpath("//*[@id=\"CountryId\"]"));
	           Select sel=new Select(country);
	           sel.selectByValue("41");
	    WebElement oth=driver.findElement(By.xpath("//*[@id=\"StateProvinceId\"]")); 
	            Select se=new Select(oth);
	            se.selectByValue("0");
	    driver.findElement(By.name("termsofservice")).click();
	    driver.findElement(By.id("checkout")).click();
	    driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div[2]/div/div[2]/div[1]/div[1]/div[3]/input[1]")).click();
	    driver.findElement(By.name("BillingNewAddress.FirstName")).sendKeys(pro.getProperty("fname"));
	    driver.findElement(By.name("BillingNewAddress.LastName")).sendKeys(pro.getProperty("lname"));
	    driver.findElement(By.name("BillingNewAddress.Email")).sendKeys(pro.getProperty("email"));
	    driver.findElement(By.name("BillingNewAddress.Company")).sendKeys(pro.getProperty("com"));
	    WebElement co=driver.findElement(By.name("BillingNewAddress.CountryId"));
	                Select sele=new Select(co);
	                   sele.selectByIndex(2);
	    WebElement st=driver.findElement(By.name("BillingNewAddress.StateProvinceId"));
	                 Select set=new Select(st);
	                  set.selectByIndex(0);
	    driver.findElement(By.name("BillingNewAddress.City")).sendKeys(pro.getProperty("ci"));
	    driver.findElement(By.name("BillingNewAddress.Address1")).sendKeys(pro.getProperty("ad1"));
	    driver.findElement(By.name("BillingNewAddress.Address2")).sendKeys(pro.getProperty("ad2"));
	    driver.findElement(By.name("BillingNewAddress.ZipPostalCode")).sendKeys(pro.getProperty("zp"));
	    driver.findElement(By.name("BillingNewAddress.PhoneNumber")).sendKeys(pro.getProperty("ph"));
	    driver.findElement(By.name("BillingNewAddress.FaxNumber")).sendKeys(pro.getProperty("fno"));
	    driver.findElement(By.xpath("//*[@id=\"billing-buttons-container\"]/input")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//*[@id=\"shipping-buttons-container\"]/input")).click();
        Thread.sleep(2000);	    
        driver.findElement(By.xpath("//*[@id=\"shipping-method-buttons-container\"]/input")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//*[@id=\"payment-method-buttons-container\"]/input")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//*[@id=\"payment-info-buttons-container\"]/input")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//*[@id=\"confirm-order-buttons-container\"]/input")).click();
	}
	@AfterMethod
	public void getScreen() throws IOException
	{
		log.info("ScreenShot Captured");
		File img=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(img, new File("C:\\Users\\HP\\OneDrive\\Desktop\\NewCjc\\bill.jpg"));
	}
	
	@AfterSuite
	public void close()
	{
		log.info("Program Complete");
		driver.close();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
