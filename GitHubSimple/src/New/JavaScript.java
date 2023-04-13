package New;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class JavaScript {

	public static void main(String[] args) throws InterruptedException 
	{
		 System.setProperty("webdriver.chrome.driver","C:\\Users\\HP\\OneDrive\\Desktop\\ChromeDriver 111\\chromedriver_win32\\chromedriver.exe");	
	       WebDriver driver=new ChromeDriver();
	        driver.get("https://www.redbus.in/");
	        driver.manage().window().maximize();  
	        
	     WebElement from=driver.findElement(By.id("src"));
	                     from.click();   
	                  from.sendKeys("Par");
	                  
	       JavascriptExecutor js=(JavascriptExecutor) driver;  
	        String city="return document.getElementById('src').value";
	        String cityname=(String) js.executeScript(city);
	        System.out.println(cityname);
	        while(!cityname.equalsIgnoreCase("Paratwada"))
	        {
	        	System.out.println("click");
	        	Thread.sleep(1000);
	        	from.sendKeys(Keys.DOWN);
	        	city="return document.getElementById('src').value";
		        cityname=(String) js.executeScript(city);
		        System.out.println(cityname);
	       
	        }
	         from.sendKeys(Keys.ENTER);
	       
	    WebElement to=driver.findElement(By.id("dest"));   
	              to.sendKeys("pu");
	              
	         JavascriptExecutor js1=(JavascriptExecutor) driver; 
	         String city1="return document.getElementById('dest').value";
	         String cityname1=(String)js1.executeScript(city1);
	         System.out.println(cityname1);
	       
	         while(!cityname1.equalsIgnoreCase("Sangamwadi, Pune"))
	         {
	        	 System.out.println("click");
	        	 Thread.sleep(1000);
	        	 to.sendKeys(Keys.DOWN);
	        	 city1="return document.getElementById('dest').value";
		         cityname1=(String)js1.executeScript(city1);
		         System.out.println(cityname1);
	        	 
	         }
	         
	         to.sendKeys(Keys.ENTER);
	         
	      driver.findElement(By.id("onward_cal")).click();
	      
	       while(!driver.findElement(By.cssSelector("  [class='rb-monthHeader']   [class='monthTitle']"))
	    		    .getText().contains("Apr"))
	       {
	    	   System.out.println("click");
	    	   driver.findElement(By.cssSelector("  [class='rb-monthHeader']   [class='next']")).click();
	    	   
	       }
	       
	      List days=driver.findElements(By.cssSelector("td[classm='we day']"));
	            int num=days.size();
	            System.out.println("Total Days="+num);
	            
	          for(int a=0 ;a<num ;a++)
	          {
	        	 String day=driver.findElements(By.cssSelector("td[class='we day']")).get(a).getText();
	                if(day.equalsIgnoreCase("1"))
	                {
	                	driver.findElements(By.cssSelector("td[class='we day']")).get(a).click();
	                }
	          }
	            
	         driver.findElement(By.id("search_btn")).click();
	         
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
			   
			   System.out.println(cookie.isHttpOnly());//boolean
			   System.out.println(cookie.isSecure());//boolean
			   System.out.println("=======================================================");
			} 
	         
	       Cookie cok=new Cookie("Un","Tejas");
	       Set<Cookie>st=driver.manage().getCookies();
	         System.out.println(st.size());
	         
	         for (Cookie cookie1 : st)
	        {
			  System.out.println(cookie1.getName());
			  System.out.println(cookie1.getDomain());
			  System.out.println(cookie1.getPath());
			  System.out.println(cookie1.getValue());
			  System.out.println(cookie1.getClass());
			  System.out.println(cookie1.getExpiry());
			  
			  System.out.println(cookie1.isHttpOnly());//boolean
			  System.out.println(cookie1.isSecure());//boolean
			  System.out.println("=============================================================");
			  
			}
	       
	       
	       driver.manage().deleteCookieNamed("cok");
	       Set<Cookie>st1=driver.manage().getCookies();
	        System.out.println(st1.size());
	       
	       
	       
	       
	       
	       
	       
	       
	}
	
}
