package ru.st.selenium;

import java.util.List;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.testng.*;
import org.testng.annotations.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class NotFoundFilmTest extends ru.st.selenium.pages.TestBase {
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Test
  public void NotFoundFilmTest() throws Exception {    
	
	
	driver.findElement(By.xpath("//a[contains(text(),'Home')]")).click();
    WebElement SearchField = driver.findElement(By.id("q"));
	SearchField.clear();
    SearchField.sendKeys("abrakadabra"+Keys.RETURN);

 
	for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if ("No movies where found.".equals(driver.findElement(By.cssSelector("div.content")).getText())) 
    		break; } catch (Exception e) {}
    	    }
	

  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}