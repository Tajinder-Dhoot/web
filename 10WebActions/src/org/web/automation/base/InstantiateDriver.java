package org.web.automation.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class InstantiateDriver {
	
	WebDriver driver = null;
	@BeforeMethod
	public void startBrowser() throws Exception {
		System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://thetestingworld.com/");
		
		String parent = driver.getWindowHandle();
		
		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL).click(driver.findElement(By.xpath("//a[text()='Login']")))
		.keyUp(Keys.CONTROL).build().perform();
		
		action.keyDown(Keys.CONTROL).click(driver.findElement(By.xpath("//a[text()='Registration ']")))
		.keyUp(Keys.CONTROL).build().perform();

		action.keyDown(Keys.CONTROL).click(driver.findElement(By.xpath("//a[text()='Quick Demo']")))
		.keyUp(Keys.CONTROL).build().perform();
		
		Set<String> windowHandles= driver.getWindowHandles();
		
		ArrayList<String> tabs = new ArrayList<String>(windowHandles);
		
		driver.switchTo().window(tabs.get(2));
		Thread.sleep(2000);
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(2000);
		driver.switchTo().window(tabs.get(3));
		Thread.sleep(2000);
		
		/*for(String child:windowHandles) {
			
			if(!child.equalsIgnoreCase(parent)) {
				driver.switchTo().window(child);
				Thread.sleep(3000);
			}
		}
		*/
		
		driver.switchTo().window(parent);
		
	}
	
	//@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}

}
