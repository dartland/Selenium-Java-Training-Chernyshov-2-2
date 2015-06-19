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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Дартланд
 *
 */
public class FoundFilmTest extends ru.st.selenium.pages.TestBase {
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Test
  public void FoundFilmTest() throws Exception {    
	
	  
      WebElement SearchField = driver.findElement(By.id("q"));
      SearchField.clear();
      SearchField.sendKeys(Keys.RETURN); //в каталоге в наличии все фильмы
      //------------------------------ сохраняем старый список фильмов
      WebElement FilmContainerOld = driver.findElement(By.id("results"));
      List<WebElement> FilmsOld = FilmContainerOld.findElements(By.tagName("a"));
      SearchField.clear(); SearchField.sendKeys("selenium"+Keys.RETURN);
      for (int count = 0;; count ++) {
    	    if (count >= 30)
    	        throw new TimeoutException();
    	    try {
    	    	driver.manage().timeouts(). implicitlyWait(0, TimeUnit.SECONDS); 
    	    	FilmsOld.get(0).getText();
    	        
    	    } catch (StaleElementReferenceException e) 
    	    	{  assertTrue("Не найден спиcок фильмов",isFilmListPresentAndVisible(By.tagName("a"))); break; }
    	    Thread.sleep(1000);
    	}
     
  }
  
  
  public boolean isFilmListPresentAndVisible(By locator) { 
	  driver.manage().timeouts(). implicitlyWait(30, TimeUnit.SECONDS); 
	  WebElement FilmContainer = driver.findElement(By.id("results"));
	  List<WebElement> film = FilmContainer.findElements(locator); 
	  if (film.size() == 0) { return false; } else { return film.get(0).isDisplayed(); }
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