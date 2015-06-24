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
 * @author ƒартланд
 *
 */
public class FoundFilmTest extends ru.st.selenium.pages.TestBase {
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Test
  public void FoundFilmTest() throws Exception {    
	
	  
	  String SearchString = "selenium";
      WebElement SearchField = driver.findElement(By.id("q"));
      SearchField.clear();
      SearchField.sendKeys(Keys.RETURN); //в каталоге в наличии все фильмы
      //------------------------------ сохран€ем старый список фильмов
      WebElement FilmContainerOld = driver.findElement(By.id("results"));
      List<WebElement> FilmsOld = FilmContainerOld.findElements(By.tagName("a"));
      SearchField.clear(); SearchField.sendKeys(SearchString+Keys.RETURN);
      for (int count = 0;; count ++) {
    	    if (count >= 30)
    	        throw new TimeoutException();
    	    try {
    	    	driver.manage().timeouts(). implicitlyWait(0, TimeUnit.SECONDS); 
    	    	FilmsOld.get(0).getText();
    	        
    	    } catch (StaleElementReferenceException e) 
    	    	{  break; }
    	    Thread.sleep(1000);
      }
      
      driver.manage().timeouts(). implicitlyWait(30, TimeUnit.SECONDS); 
	  WebDriverWait wait = new WebDriverWait(driver, 30);
	  //этот элемент тоже исчезает и по€вл€етс€, ждем его по€влени€
	  wait.until(ExpectedConditions.presenceOfElementLocated(By.id("results")));
	  WebElement FilmContainer = driver.findElement(By.id("results"));
      for (int count = 0;; count ++) {
  	    if (count >= 30) { throw new TimeoutException();}
  	    List<WebElement> film = FilmContainer.findElements(By.tagName("a")); 
  	    if (film.size()>0) {break;}
  	    Thread.sleep(1000);
  	  }
      String NameFilm;
      List<WebElement> film = FilmContainer.findElements(By.tagName("a")); 
      Assert.assertNotEquals(film.size(),0); 
      for (int i = 0; i < film.size(); i++) {
		WebElement film_cell = film.get(i);
		NameFilm = film_cell.findElement(By.className("title")).getText();
		//System.out.println("»м€ фильма ="+NameFilm);
		CharSequence cs1 = SearchString;
		Assert.assertTrue(NameFilm.toLowerCase().contains(cs1));
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