package News18_ObjectRepository;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class News18_Gujarati_HomePage 
{
	@FindBy(xpath = "'[class=\\\"jsx-2746090766 Menu_top_search_icond__lwBVu\\\"]:nth-child(2)',':before'")
	private WebElement searchicon;
	
	public News18_Gujarati_HomePage(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getSearchicon() {
		return searchicon;
	}
	
	public void clickOnSearchicon(WebDriver driver)
	{

		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		js.executeScript("document.querySelector('[class=\"jsx-2746090766 Menu_top_search_icond__lwBVu\"]:nth-child(2)',':before').click();");
		
	
	}
}
