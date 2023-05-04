package EndtoEndvalidation;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.pages.EndtoEndvalidationpage;

import utility.Library;

public class EndtoEndvalidation  extends Library{

	
	
		
		@Test(priority =1)
		public void VerifyTitle() {
			
			driver.get(objProperties.getProperty("URL"));
			PageLoadTimeOut();
			String Title = driver.getTitle();
			System.out.println("Title:"+Title);
			Assert.assertEquals(Title,objProperties.getProperty("Title") );
				
		}
		@Test(priority=2)
		
			public void EndToEnd()
			{
				
				driver.findElement(EndtoEndvalidationpage.search).sendKeys("bike");
				driver.findElement(EndtoEndvalidationpage.searchbtn).click();
				List<WebElement> products = driver.findElements(EndtoEndvalidationpage.listofproduct);
				System.out.println("product is"+products.get(1).getText());
				String firstprd=products.get(1).getText();
				driver.findElement(By.xpath("/html[1]/body[1]/div[5]/div[4]/div[2]/div[1]/div[2]/ul[1]/li[2]/div[1]/div[1]/div[1]/a[1]/div[1]")).click();
		
			    
			    Set<String> allWindowHandles = driver.getWindowHandles();
				 String mainwindow = driver.getWindowHandle();
			        Set<String> s1 = driver.getWindowHandles();
			        Iterator<String> i1 = s1.iterator();
			        
			        while (i1.hasNext()) {
			            String ChildWindow = i1.next();
			                if (!mainwindow.equalsIgnoreCase(ChildWindow)) {
			                driver.switchTo().window(ChildWindow);
			                }
			        }
			        Select se = new Select(driver.findElement(By.xpath("//*[@id='x-msku__select-box-1000']")));
			        se.selectByValue("1");
			        driver.findElement(By.xpath("//span[text()='Add to cart']")).click();
			        
			    	
			}
		
		
	

		  @BeforeTest
		  public void beforeTest() {
			
			  Library.LaunchBrowser();
		  }

		 		  @BeforeSuite
		  public void beforeSuite() {
			  System.out.println("inside beforeSuite");
			  Library.ReadPropertiesFile();
		  }

				
	
}
