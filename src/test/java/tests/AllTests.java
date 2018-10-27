package tests;

import org.testng.annotations.Test;

import testSlot.App;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;


public class AllTests {
	
	public	static WebDriver driver;
	
  @Test (priority =1)
  public void Test1() {
	  App slot = new App();
	  slot.spinButtonClick();
	  
	  
  }
  
  @Test (priority =2)
  public void Test2() {
	  App slot = new App();
	  slot.chengeBetUp();
	  int creditsBeforeSpin= slot.getCredits();
	  slot.spinButtonClick();
	  slot.checkCredits(creditsBeforeSpin);
  }
  
  @Test (priority =2)
  public void Test3() {
	  App slot = new App();
	  slot.spinToWin();
  }
  
  
  
  @BeforeClass
  public void beforeClass() {
	  
	  driver= settings.WebDriverSettings.getWebDriver();

	  driver.get("http://slotmachinescript.com/");
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

  @AfterClass
  public void afterClass() {
	  driver.close();
  }

}
