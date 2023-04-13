package New;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AllMix {

	static Logger log=Logger.getLogger(AllMix.class);
	WebDriver driver;
	@BeforeSuite
	public void browser()
	{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\HP\\OneDrive\\Desktop\\ChromeDriver 111\\chromedriver_win32\\chromedriver.exe");
              driver=new ChromeDriver();		
	}

	@BeforeTest
	public void getUrl()
	{
		driver.get("https://www.redbus.in/");
	}

	@BeforeClass
	public void maximize()
	{
		driver.manage().window().maximize();
	}

	@BeforeMethod
	public void getCookies()
	{
		Set<Cookie>set=driver.manage().getCookies();
		  System.out.println(set.size());
		  for (Cookie cookie : set) 
		{
		   System.out.println(cookie.getDomain());
		   System.out.println(cookie.getName());
		   System.out.println(cookie.getPath());
		   System.out.println(cookie.getValue());
		   System.out.println(cookie.getClass());
		   System.out.println(cookie.getExpiry());
		   
		   System.out.println(cookie.isHttpOnly());
		   System.out.println(cookie.isSecure());
           System.out.println("====================================");
		}
	
		   Cookie cokie=new Cookie("PUN","qqqqq");
		   driver.manage().addCookie(cokie);
		   
		  Set<Cookie>set1=driver.manage().getCookies();
		     System.out.println(set1.size());
		     
		    for(Cookie coki :set1)
		    {
		    	System.out.println(coki.getName());
		    	System.out.println(coki.getDomain());
		    	System.out.println(coki.getPath());
		    	System.out.println(coki.getValue());
		    	System.out.println(coki.getClass());
		    	System.out.println(coki.getExpiry());
		    	System.out.println(coki.isHttpOnly());
		    	System.out.println(coki.isSecure());
		    	
		    	System.out.println("=====================================");
		    	
		    }
		     
	}
	
	
	@Test(priority = 1)
	public void login() throws InterruptedException
	{
	    System.out.println("Login Method");	
	    
	    WebElement from=driver.findElement(By.id("src"));
	      from.sendKeys("Par");
	      
	      JavascriptExecutor js=(JavascriptExecutor) driver;
	      String city="return document.getElementById('src').value";
	      String cityname=(String)js.executeScript(city);
	      System.out.println(cityname);
	      
	      while(!cityname.equalsIgnoreCase("Paratwada"))
	      {
	    	  Thread.sleep(500);
	    	  System.out.println("From");
	    	  from.sendKeys(Keys.DOWN);
	    	  city="return document.getElementById('src').value";
		      cityname=(String)js.executeScript(city);
		      System.out.println(cityname);
	      }
	      from.sendKeys(Keys.ENTER);
	
	      WebElement to=driver.findElement(By.id("dest"));
	         to.sendKeys("na");
	         Thread.sleep(1000);
	         
	        JavascriptExecutor js1=(JavascriptExecutor) driver; 
	        String city1="return document.getElementById('dest').value";
	        String cityname1=(String)js1.executeScript(city1);
	        System.out.println(cityname1);
	        
	        while(!cityname1.equalsIgnoreCase("Nashik Trimbakeshwar Package"))
	        {
	        	Thread.sleep(500);
	        	System.out.println("Too");
	        	to.sendKeys(Keys.DOWN);
	        	city1="return document.getElementById('dest').value";
		        cityname1=(String)js1.executeScript(city1);
		        System.out.println(cityname1);
	        }
	
	        to.sendKeys(Keys.ENTER);
	
	        String month="Jun 2023";
	      driver.findElement(By.id("onward_cal")).click();
	      
	      while(true)
	      {
	    	  
	    	 String mon=driver.findElement(By.xpath("//*[@id=\"rb-calendar_onward_cal\"]/table/tbody/tr[1]/td[2]")).getText();
	    	    
	    	        if(mon.equals(month))
	    	        {
	    	        	break;
	    	        }
	    	        else
	    	        {
	    	        	driver.findElement(By.xpath("//*[@id=\"rb-calendar_onward_cal\"]/table/tbody/tr[1]/td[3]")).click();
	    	        }
	      }
	
	     List day=driver.findElements(By.cssSelector("td[class='wd day']"));
	              int num=day.size();
	              System.out.println(num);
	              
	            for(int a=0;a<num ;a++)
	            {
	            	String days=driver.findElements(By.cssSelector("td[class='wd day']")).get(a).getText();
	                 
	            	   if(days.equalsIgnoreCase("2"))
	            	   {
	            		   driver.findElements(By.cssSelector("td[class='wd day']")).get(a).click();
	            	   }
	            }
	           
	          driver.findElement(By.xpath("//*[@id=\"search_btn\"]")).click();  
	            
	         Layout lay=new PatternLayout();
	         
	         Appender ap=new ConsoleAppender(lay);
	         log.addAppender(ap);
	         
	          log.debug("Main Debug");
	          log.info("Main Info");
	          log.warn("Main Warn");
	          log.error("Main Error");
	          log.fatal("Main Fatal");
	         
	    }
	
	 @Test(priority = 2)
	 public void action()
	 {
	     Actions act=new Actions(driver); 
	     
		 driver.navigate().to("https://jqueryui.com/droppable/");
		 driver.switchTo().frame(0);
		 
		 WebElement source=driver.findElement(By.id("draggable"));
		 WebElement target=driver.findElement(By.id("droppable"));
		 
		 act.dragAndDrop(source, target).build().perform();
//	     act.clickAndHold(source).moveToElement(target).build().perform();
//	     act.clickAndHold(source).release(target).build().perform();
	 }
	
	 @Test(priority=3)
	 public void login2()
	 {
		 driver.navigate().to("https://rahulshettyacademy.com/AutomationPractice/");
		
		 List total=driver.findElements(By.tagName("a"));
		 System.out.println(total.size());
		 
		 WebElement footer=driver.findElement(By.xpath("//*[@id=\"gf-BIG\"]"));
		    List foot= footer.findElements(By.tagName("a"));
		    
		    System.out.println(foot.size());
		    
		WebElement about=driver.findElement(By.xpath("//*[@id=\"gf-BIG\"]/table/tbody/tr/td[1]/ul")); 
	            List abo=about.findElements(By.tagName("a"));
	            
	                   int num=abo.size();
	             System.out.println(num);
	             
	           for(int a=0 ;a<num ;a++)
	           {
	        	   String tab=Keys.chord(Keys.CONTROL,Keys.ENTER);
	        	   about.findElements(By.tagName("a")).get(a).sendKeys(tab);
	           }
	 
	          Set<String>set=driver.getWindowHandles();
	             System.out.println(set);
	             
	           ArrayList<String> array=new ArrayList<String>(set);
	           Iterator<String> itr=array.iterator();
	           while(itr.hasNext())
	           {
	        	   driver.switchTo().window(itr.next());
	        	   String title=driver.getTitle();
	        	   System.out.println(title);
	        	   String url=driver.getCurrentUrl();
	        	   System.out.println(url);
	           }
	 
	 
	 
	 }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	



}
