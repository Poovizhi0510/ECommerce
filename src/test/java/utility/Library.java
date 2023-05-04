package utility;


import java.io.File;
import java.io.FileInputStream;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;


public class Library {
	public static WebDriver driver;
	public static Properties objProperties;

	public static void ReadPropertiesFile() {
	
		File objFile = new File(System.getProperty("user.dir") + "//src//test//resources//Config.Properties");
		FileInputStream ObjInputStream;
		try {
			ObjInputStream = new FileInputStream(objFile);
			objProperties = new Properties();
			objProperties.load(ObjInputStream);
			System.out.println(objProperties.getProperty("browser"));

		} catch (Exception e) {
				e.printStackTrace();
		}
	}

	public static void LaunchBrowser() {
		String browserFromPropertiesFile = objProperties.getProperty("browser");
		switch (browserFromPropertiesFile) {
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "IE":
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		
		}

		driver.manage().window().maximize();
	
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

		
	

	public static void PageLoadTimeOut() {
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
	}

	
	
}
