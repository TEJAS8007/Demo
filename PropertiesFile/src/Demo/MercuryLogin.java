package Demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MercuryLogin {

	WebDriver driver;
	Properties pro=new Properties(); 
	@BeforeSuite
	public void browser() throws IOException
	{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\HP\\OneDrive\\Desktop\\ChromeDriver 111\\chromedriver_win32\\chromedriver.exe");
		 
		    driver=new ChromeDriver();
		    FileInputStream file=new FileInputStream("C:\\Users\\HP\\OneDrive\\Desktop\\NewCjc\\DemoMercury.properties");
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


	@Test
	public void login()
	{
		driver.findElement(By.name("userName")).sendKeys(pro.getProperty("un"));
		driver.findElement(By.name("password")).sendKeys(pro.getProperty("ps"));
		driver.findElement(By.name("submit")).click();
	}
	
	@AfterMethod
	public void getScreen() throws IOException
	{
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("C:\\Users\\HP\\OneDrive\\Desktop\\NewCjc\\MerLogin.jpg"));
	}
	
	@AfterSuite
	public void close()
	{
		driver.close();
	}
}
