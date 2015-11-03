package org.pigne;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SimpleIntegrationTest {
	private String baseUrl;
	private WebDriver driver;
	private ScreenshotHelper screenshotHelper;

	@Before
	public void openBrowser() {
		baseUrl = "http://localhost:9090/CountDownWebApp/countdown";
		driver = new HtmlUnitDriver();
		//driver = new FirefoxDriver();
		screenshotHelper = new ScreenshotHelper();
	}

	@Test
	public void testPage() {

		driver.get(baseUrl);
		System.out.println(driver.getPageSource());

		try {
			// Find the title of the page
			assertEquals("CountDown", driver.getTitle());

			// Find the footer that should contain "Nobody"
			WebElement element = driver.findElement(By.tagName("footer"));
			assertNotNull(element);
			assertEquals("Nobody", element.getText().trim());

		} catch (NoSuchElementException ex) {
			fail("Startup of context failed. See console output for more information, : "
					+ ex.getMessage());
		}
	}

	@Test
	public void testAuthorParameter() {

		driver.get(baseUrl + "?author=Me");
		System.out.println(driver.getPageSource());
		System.out.println("Page title is: " + driver.getTitle());

		try {

			// Find the footer that should contain "Me"
			WebElement element = driver.findElement(By.tagName("footer"));
			assertNotNull(element);
			assertEquals("Me", element.getText().trim());

		} catch (NoSuchElementException ex) {
			fail("Startup of context failed. See console output for more information, : "
					+ ex.getMessage());
		}
	}

	@After
	public void saveScreenshotAndCloseBrowser() throws IOException {
		//screenshotHelper.saveScreenshot("screenshot-"+System.nanoTime()+".png");
		driver.quit();
	}

	private class ScreenshotHelper {

		public void saveScreenshot(String screenshotFileName)
				throws IOException {
			File screenshot = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(screenshotFileName));
		}
	}

}
