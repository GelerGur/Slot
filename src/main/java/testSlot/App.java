package testSlot;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;
import settings.WebDriverSettings;

/*
 * 	Test 1: Play one round (“SPIN” button). Wait until the round is finished.

2.	Test 2: Change the “BET” by 2 points, play the round and check the “TOTAL SPINS”.

3.	Test 3: Play until you win. Wait for the winning amount per round (“LAST WIN”), and check, if the win was added correctly to “TOTAL SPINS”.

 */

public class App extends WebDriverSettings
{
	
	 By spinLocator = By.cssSelector("#spinButton");
	 By spinDisabledLocator = By.cssSelector("#spinButton.disabled");
	 By spinEnabledLocator= By.cssSelector("#spinButton[class='']");
	 By betSpinUpLocator = By.cssSelector("#betSpinUp");
	 By creditsLocator = By.cssSelector("#credits");
	 By lastWinLocator = By.cssSelector("#lastWin");
	 By betLocator = By.cssSelector("#bet");
	
	
    
    public void spinButtonClick () {
    	

    	WebDriverWait wait = new WebDriverWait(driver, 15); 	
    	   		
    	driver.findElement(spinLocator).click();
    	wait.until(ExpectedConditions.elementToBeClickable(spinDisabledLocator));
       	wait.until(ExpectedConditions.elementToBeClickable(spinEnabledLocator));
    	Assert.assertTrue("Spin is failed",driver.findElement(spinEnabledLocator).isEnabled());
    	    	
    }
    
    public int getCredits () {
    	int creditsBeforeSpin = Integer.parseInt(driver.findElement(creditsLocator).getText());
    	return creditsBeforeSpin;
    }
    
    public void checkCredits (int creditsBeforeSpin ) {
    	int creditsAfterSpin = Integer.parseInt(driver.findElement(creditsLocator).getText());
    	int expectedCredits;
    	int bet = Integer.parseInt(driver.findElement(betLocator).getText());
    	if (driver.findElement(lastWinLocator).getText().isEmpty()) {
    		expectedCredits = ( creditsBeforeSpin-bet);
    		Assert.assertEquals("Incorrect TOTAL SPINS ", expectedCredits, creditsAfterSpin);
    		System.out.println("TOTAL SPINS is ok "+expectedCredits +"="+creditsAfterSpin);
    	} else {
    	int lastWin = Integer.parseInt(driver.findElement(lastWinLocator).getText());
    	expectedCredits = ( creditsBeforeSpin-bet+ lastWin);
    	Assert.assertEquals("Incorrect TOTAL SPINS ", expectedCredits, creditsAfterSpin);
    	System.out.println("TOTAL SPINS is ok "+expectedCredits +"="+creditsAfterSpin);
    	}
    	
    	
    }
    
    public void spinToWin () {
    	int creditsBeforeSpin= getCredits();
    	spinButtonClick();
    	if (driver.findElement(lastWinLocator).getText().isEmpty()) {
    		
    	for (int i= 0; i<1;) {
    		creditsBeforeSpin= getCredits();
        	spinButtonClick();
        	if (driver.findElement(lastWinLocator).getText().isEmpty()) {
        		} else {
        			checkCredits(creditsBeforeSpin);
        			i++;
        		}
    	}
    	} else {
    		checkCredits(creditsBeforeSpin);
    	}
    	
    	
    }
    
    public void chengeBetUp () {
    	int betBeforeUp = Integer.parseInt(driver.findElement(betLocator).getText());
    	driver.findElement(betSpinUpLocator).click();
    	int betAfterUp = Integer.parseInt(driver.findElement(betLocator).getText());
    	Assert.assertEquals("Bet dont chenged",betBeforeUp+1, betAfterUp);
    }
}
