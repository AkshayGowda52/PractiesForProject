package com.crm.ObjectRepository;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SwiggyHotelManuPage 
{
	    //Step 1:declaration
		@FindBy(xpath = "//div[@class='_129-b']/div[@class='MZy1T']/div/a/div/div/div/div[@class='nA6kb']")
		private static List<WebElement> dishName;
		
		@FindBy(xpath = "//div[@class='styles_detailsContainer__22vh8']/div[@class='styles_itemPortionContainer__1u_tj']/span/span[@class='rupee']")
		private static List<WebElement> dishPrice;
		
		@FindBy(xpath = "//div[@class='_1WDSQ']/div[@class='_1BpLF']/div[@class='_2aZit _2fC4N']/div[@class='_2iUp9 ']/div/span")
		private static List<WebElement> rating;
		
		public SwiggyHotelManuPage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}

		public static List<WebElement> getDishName() {
			return dishName;
		}

		public static List<WebElement> getDishPrice() {
			return dishPrice;
		}

		public static List<WebElement> getRating() {
			return rating;
		}
		
		

		
		
}
