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
import com.pages.loginpage;

import utility.Library;

	public class LoginValidation   extends Library{

		
		
			
			@Test(priority =1)
			public void VerifyTitle() {
				
				driver.get(objProperties.getProperty("URL"));
				PageLoadTimeOut();
				String Title = driver.getTitle();
				System.out.println("Title:"+Title);
				Assert.assertEquals(Title,objProperties.getProperty("Title") );
				
			}
			@Test(priority=2)
			
				public void login()
				{
					
					driver.findElement(loginpage.ebay).click();
					driver.findElement(loginpage.conID).click();
					String ErrorMsg=driver.findElement(loginpage.Errormsg).getText();
					Assert.assertEquals(ErrorMsg,objProperties.getProperty("ErrorMsg") );
					
					
					

				
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

