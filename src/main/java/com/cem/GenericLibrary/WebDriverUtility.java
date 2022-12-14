package com.cem.GenericLibrary;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;
/**
 * This class consists of all generic methods to webdriverActions
 * @author aksha
 *
 */
public class WebDriverUtility 
{
	/**
	 * This method will maximize the window
	 * @param driver
	 */
	public void maximizeWindow(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	/**
	 * This method will wait for 20 seconds for page to load
	 * @param driver
	 */
	public void waitForPageLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	/**
	 * This method will wait for 10 second for an element to be clickable
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeClickable(WebDriver driver,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	/**
	 * This method will wait for 10 second for the element to be visible
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeVisible(WebDriver driver,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));		  
	}
	/**
	 * This method is selected data from drop down using index
	 * @param element
	 * @param index
	 */
	public void select(WebElement element,int index)
	{
		Select sel = new Select(element);
		sel.selectByIndex(index);
	}
	/**
	 * This method will select data from drop down using visible text
	 * @param element
	 * @param Text
	 */
	public void select(WebElement element,String Text)
	{
		Select sel = new Select(element);
		sel.selectByVisibleText(Text);
	}
	/**
	 * This method will select data from drop down using visible text
	 * @param value
	 * @param element
	 */
	public void select(String value, WebElement element)
	{
		Select sel=new Select(element);
		sel.selectByValue(value);
	}
	/**
	 * This method will perform mouse hover action
	 * @param driver
	 * @param element
	 */
	public void mouserHover(WebDriver driver,WebElement element)
	{
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}
	/**
	 * This method will perform drag and drop action
	 * @param driver
	 * @param src
	 * @param target
	 */
	public void dragAndDrop(WebDriver driver,WebElement src,WebElement target)
	{
		Actions act = new Actions(driver);
		act.dragAndDrop(src, target).perform();		  
	}
	/**
	 * This method will double click on element
	 * @param driver
	 * @param element
	 */
	public void doubleClickAction(WebDriver driver,WebElement element)
	{
		Actions act = new Actions(driver);
		act.doubleClick(element).perform();
	}
	/**
	 * This method will double click on webpage
	 * @param driver
	 */
	public void doubleClickAction(WebDriver driver)
	{
		Actions act = new Actions(driver);
		act.doubleClick().perform();

	}
	/**
	 * This method will right click on webpage
	 * @param driver
	 */
	public void rightClick(WebDriver driver)
	{
		Actions act = new Actions(driver);
		act.contextClick().perform();
	}
	/**
	 * This method will right click on element
	 * @param driver
	 * @param element
	 */
	public void rightClick(WebDriver driver,WebElement element)
	{
		Actions act = new Actions(driver);
		act.contextClick(element).perform();
	}
	/**
	 * This method will press enter key
	 * @param driver
	 */
	public void enterKeyPress(WebDriver driver)
	{
		Actions act = new Actions(driver);
		act.sendKeys(Keys.ENTER).perform();
	}
	//This method will press enter key
	// public void enterKey()
	// {
	//	  Robot rb = new Robot();
	//	  rb.KeyPress(KeyEvent.vk_ENTER);
	// }
	//This method will release enter key
	// public void enterRelease()
	// {
	//	  Robot rb = new Robot();
	//	  rb.KeyRelease(KeyEvent.vk_ENTER);
	// }
	/**
	 * This method will switch the frame based on index
	 * @param driver
	 * @param index
	 */
	public void switchToFrame(WebDriver driver,int index)
	{
		driver.switchTo().frame(index);
	}
	/**
	 * This method will switch the frame based on name or ID
	 * @param driver
	 * @param nameOrId
	 */
	public void switchToFrame(WebDriver driver,String nameOrId)
	{
		driver.switchTo().frame(nameOrId);
	}
	/**
	 * This method will switch the frame based on address of the element+
	 * 
	 * @param driver
	 * @param address
	 */
	public void switchToFrame(WebDriver driver, WebElement address)
	{
		driver.switchTo().frame(address);
	}
	/**
	 * This method will accept alert pop up
	 * @param driver
	 */
	public void accetAlert(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	/**
	 * This method will cancel alert pop up
	 * @param driver
	 */
	public void cancelAlert(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	/**
	 * This method will switch one window to other window
	 * @param driver
	 * @param partialWindowTitle
	 */
	public void switchToWindow(WebDriver driver,String partialWindowTitle)
	{
		//Step 1: use getWindowHndles to capture all window ids
		Set<String> windows = driver.getWindowHandles();

		//Step 2: iterate through the windows
		Iterator<String> it = windows.iterator();

		//Step 3: Check whether there is next window
		while(it.hasNext())
		{
			//Step 4: Capture current window id
			String winId = it.next();

			//Step 5: switch to current window and capture title
			String currentWinTitle = driver.switchTo().window(winId).getTitle();

			//Step 6: check whether the current window is expected
			if(currentWinTitle.contains(partialWindowTitle))
			{
				break;
			}

		}
	}

	/**
	 * This method will take a screenshot and store it in folder called as screenshot
	 * @param driver
	 * @param screenShotName
	 * @throws Throwable 
	 */
	public String getScreenShot(WebDriver driver,String screenShotName) throws Throwable
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		String path = "./ScreenShots/"+screenShotName+ ".png";
		File dst=new File(path);
		Files.copy(src, dst);
		return dst.getAbsolutePath();
	}
	/**
	 * This method will perform random scroll
	 * @param driver
	 */
	public void scrollAction(WebDriver driver)
	{
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("window,scrollBy(0,500)", "");

	}
	/**
	 * This method will scroll until the specified element is found
	 * @param driver
	 * @param element
	 */
	public void scrollAction(WebDriver driver,WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		int y = element.getLocation().getY();
		js.executeScript("window.scrollBy(0,"+y+")", element);
		//js.executeScript("argument[0].scrollIntoView()",element);
	}
	/**
	 * by the help of this method we can customize the wait
	 * @param element
	 * @throws Throwable 
	 */
	public void customeWait(WebElement element) throws Throwable
	{
		int count=0;
		while(count<20)
		{
			try {
				element.click();
				break;
			}
			catch (Exception e) {
				Thread.sleep(1000);
				count++;
			}

		}
	}	  

}


















