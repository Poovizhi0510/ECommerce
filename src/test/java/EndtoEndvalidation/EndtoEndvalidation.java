package EndtoEndvalidation;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
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

public class EndtoEndvalidation extends Library {

	@Test(priority = 1)
	public void VerifyTitle() {

		driver.get(objProperties.getProperty("URL"));
		PageLoadTimeOut();
		String Title = driver.getTitle();
		System.out.println("Title:" + Title);
		Assert.assertEquals(Title, objProperties.getProperty("Title"));

	}

	@Test(priority = 2)

	public void EndToEnd() throws InterruptedException {

		driver.findElement(EndtoEndvalidationpage.search).sendKeys("bike");
		driver.findElement(EndtoEndvalidationpage.searchbtn).click();

		String FirstPdt = driver.findElement(By.xpath("/html[1]/body[1]/div[5]/div[4]/div[2]/div[1]/div[2]/ul[1]/li[2]/div[1]/div[1]/div[1]/a[1]/div[1]/img")).getAttribute("alt");
		System.out.println("The first product added to cart is  " + FirstPdt);
		driver.findElement(By.xpath("/html[1]/body[1]/div[5]/div[4]/div[2]/div[1]/div[2]/ul[1]/li[2]/div[1]/div[1]/div[1]/a[1]/div[1]")).click();
		String parentwindow = driver.getWindowHandle();
		Set<String> wstr = driver.getWindowHandles();
		Iterator<String> itr = wstr.iterator();

		while (itr.hasNext()) {
			String ChildWindow = itr.next();
			if (!parentwindow.equalsIgnoreCase(ChildWindow)) {
				driver.switchTo().window(ChildWindow);
			}
		}
		Select dropdown = new Select(driver.findElement(EndtoEndvalidationpage.addtocart));
		dropdown.selectByIndex(1);

		driver.findElement(By.xpath("//span[contains(text(),'Add to cart')]")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Thread.sleep(1000);
		WebElement we = (WebElement) js.executeScript("return document.querySelector(\"#vas-interstitial-target-d\").shadowRoot.querySelector(\"#vas-spoke-container > div.bottom-ctas > div > button\")");

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", we);
		String ProdcutinTheCart = driver.findElement(By.xpath(
				"//*[@id=\"mainContent\"]/div/div[3]/div[1]/div[1]/div[1]/ul/li/div[1]/div/div/div[1]/div/div[1]/div/div/img"))
				.getAttribute("alt");
		System.out.println("The Product in The Cart is " + ProdcutinTheCart);
		Assert.assertEquals(FirstPdt, ProdcutinTheCart);
		driver.quit();
	}

	@BeforeTest
	public void beforeTest() {

		Library.LaunchBrowser();
	}

	@BeforeSuite
	public void beforeSuite() {
		Library.ReadPropertiesFile();
	}

}
