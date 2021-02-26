package wrapperpackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WrapperClass {

	public static RemoteWebDriver driver;
	public Properties prop;
	public String url, userName, password;

	// url, username & password are loaded with constructor
	public WrapperClass() {
		prop = new Properties();

		try {
			prop.load(new FileInputStream(new File("./src/main/resources/config.properties")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		url = prop.getProperty("URL");
		userName = prop.getProperty("UserName");
		password = prop.getProperty("Password");
	}

	// Method used to launch chrome browser.
	public RemoteWebDriver startApp(String browser) {
		switch (browser) {
		case ("chrome"):
			WebDriverManager.chromedriver().setup();
			//System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			driver = new ChromeDriver();
			System.out.println("Browser launched successfully");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get(url); click(locateElement("class", "login"));
			
			return driver;
		case ("firefox"):
			WebDriverManager.firefoxdriver().setup();
			//System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", true);
			driver = new FirefoxDriver();

			System.out.println("Browser launched successfully");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get(url); click(locateElement("class", "login"));
			return driver;

		}
		return null;

		}

	// Method used to identify the element in the page
	public WebElement locateElement(String locator, String locValue) {
		try {
			switch (locator) {
			case ("id"):
				return driver.findElementById(locValue);
			case ("link"):
				return driver.findElementByLinkText(locValue);
			case ("xpath"):
				return driver.findElementByXPath(locValue);
			case ("name"):
				return driver.findElementByName(locValue);
			case ("class"):
				return driver.findElementByClassName(locValue);
			case ("tag"):
				return driver.findElementByTagName(locValue);
			}
		} catch (NoSuchElementException e) {
			System.out.println("Exception occured on locate elements : " + locValue);
		} catch (WebDriverException e) {
			System.out.println("Exception occured on locate elements : " + locValue);

		}
		return null;
	}

	// Method used to perform click action in the web element
	public void click(WebElement ele) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			ele.click();
			System.out.println("Click action performed on element : " + ele);
		} catch (Exception e) {
			System.out.println("Exception occured on element click");
		}

	}

	// Method used to type a value in the given edit box
	public void type(WebElement ele, String data) {

		try {
			ele.clear();
			ele.sendKeys(data);
			System.out.println("Type action performed on element : " + ele);
		} catch (Exception e) {
			System.out.println("Invalid element exception occured on element type");
		}

	}

	// Method used to retrieve the value from the webelement
	public String getText(WebElement ele) {
		String textValue = "";
		try {
			textValue = ele.getText();
			System.out.println("value retrieved for the element : " + textValue);
		} catch (WebDriverException e) {
			System.out.println("Exception occured on get text");
		}
		return textValue;
	}

	// Method used to select a value from the dropdown
	public void selectValueByvalue(WebElement ele, String value) {
		try {
			new Select(ele).selectByVisibleText(value);
			System.out.println("Element selected successfully" + ele);
		} catch (Exception e) {
			System.out.println("Exception occured on select element");
		}
	}

	// To scroll the page dowwn upto a specific limit
	public void pagedown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		System.out.println("Page scrolled down successfully");
	}

	// To scroll the page till the element is found
	public void pageDownToSpecificElement(WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0]. scrollIntoView(true);", ele);
		System.out.println("Page scrolled down successfully to the specific element");

	}

	// Method used to switch the control in to the frame
	public void switchToFrame(WebElement ele) {
		try {
			driver.switchTo().frame(ele);
			System.out.println("switch In to the Frame : " + ele);
		} catch (NoSuchFrameException e) {
			System.out.println("No Such Frame Exception : " + e);
		} catch (WebDriverException e) {
			System.out.println("WebDriverException : " + e);
		}
	}

	// Method used to switch the control out of the frame
	public void switchOutOfFrame() {
		try {
			driver.switchTo().defaultContent();
			System.out.println("switch out of the Frame");
		} catch (NoSuchFrameException e) {
			System.out.println("No Such Frame Exception : " + e);
		} catch (WebDriverException e) {
			System.out.println("WebDriverException : " + e);
		}
	}

	// cursor move to specific element
	public void cursorToSpecificElementAndClick(WebElement ele1) {

		Actions action1 = new Actions(driver);
		// action1.moveToElement(ele1).perform();
		action1.moveToElement(ele1).click().build().perform();
	}

	public void movetoSpecificElement(WebElement ele1) {
		Actions action1 = new Actions(driver);
		action1.moveToElement(ele1).perform();

	}

	// get the title of the page
	public String getTitleName() {
		return driver.getTitle();
	}

	// Method used to get the list of elements from th web pageS
	public List<WebElement> findListOElements(String locatorvalue) {
		List<WebElement> listOfElements = null;
		try {
			listOfElements = driver.findElementsByXPath(locatorvalue);
			System.out.println("List of elements identified : " + locatorvalue);
		} catch (Exception e) {
			System.out.println("Execption occured : " + e);
		}
		return listOfElements;
	}

	// Method used to close the browser
	public void browserClose(String xpathval) {
		try {
			locateElement("xpath", xpathval).click();
			driver.quit();
			System.out.println("Browser closed successfully");
		} catch (Exception e) {
			System.out.println("Exception occured while closing the browser");
		}
	}

}
