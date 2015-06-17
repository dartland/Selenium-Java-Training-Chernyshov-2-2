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

public class FoundFilmTest extends ru.st.selenium.pages.TestBase {
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Test
  public void FoundFilmTest() throws Exception {    
	
    WebElement SearchField = driver.findElement(By.id("q"));
    
	  
	SearchField.clear();
    SearchField.sendKeys("selenium"+Keys.RETURN); //в данном случае, в базе есть фильмы с ключевым словом "selenium"
    //------------------------------

    ExpectedCondition<Boolean> pageLoadFinishedCondition = new ExpectedCondition<Boolean>() {
        public Boolean apply(WebDriver driver) 
        {return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");    }
    };

    WebDriverWait wait = new WebDriverWait(driver, 30);
	wait.until(pageLoadFinishedCondition);	
    
	WebElement FilmContainer = driver.findElement(By.id("results"));
    //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("a")));
    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("a")));

    
	List<WebElement> Films = FilmContainer.findElements(By.tagName("a"));
	System.out.println("---------------");
	for (WebElement Film : Films) {
		System.out.println(Film.getText());
     	
    }
    //--------------------------------
	
	assertNotEquals(Films.size(),0);
	
	
	
	
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