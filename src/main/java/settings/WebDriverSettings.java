package settings;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class WebDriverSettings {

	
 public	static WebDriver driver;
 
 public static WebDriver getWebDriver() {

	  
	  System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		driver = new ChromeDriver();
	  
	 return driver;
 }
 

}
