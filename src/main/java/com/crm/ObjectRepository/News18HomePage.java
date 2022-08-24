package com.crm.ObjectRepository;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class News18HomePage 
{
	public News18HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class='nw18-dfp-ad  ' and contains(@data-adcode,'/1039154/NW18_ENG_Desktop/NW18_ENG_Home/NW18_ENG_Home_Home/NW18_ENG_HOM')]//iframe")
	private  List<WebElement> iframeIDs;
	
	@FindBy(xpath = "//span[text()='PROMOTED CONTENT']")
	private  WebElement promotedContent;
	
	

	public  List<WebElement> getIframeIDs() {
		return iframeIDs;
	}
	
	

	public  WebElement getPromotedContent() {
		return promotedContent;
	}

	public void ids() throws Throwable
	{
		Thread.sleep(3000);
		//String id = iframeIDs.getAttribute("id");
		for(int i =0 ; i<iframeIDs.size(); i++)
		{
			System.out.println(iframeIDs.size());
			String s= iframeIDs.get(i).getAttribute("id");
		System.out.println(s);
	
		}
	}	
	public void scroolaction(WebDriver driver)
	{
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
//		js.executeScript("arguments[0].scrollIntoView();", getPromotedContent() );
		js.executeScript("window.scrollBy(0,600)");
	}
	
}
