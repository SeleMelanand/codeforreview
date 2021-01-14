package wrapperpackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WrapperClass {

	public static RemoteWebDriver driver;
	public Properties prop;
	public String url, userName, password;

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
	public void startApp(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			driver = new ChromeDriver();
		} else {
			System.out.println("Please include code for the respective browser and retry");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(url);
		click(locateElement("class", "login"));
		type(locateElement("id", "email"), userName);
		type(locateElement("id", "passwd"), password);
		click(locateElement("id", "SubmitLogin"));

	}

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
			System.out.println("Exception occured on locate elements");
		} catch (WebDriverException e) {
			System.out.println("Exception occured on locate elements");

		}
		return null;
	}

	public void click(WebElement ele) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			ele.click();
		} catch (Exception e) {
			System.out.println("Exception occured on element click");
		}

	}

	public void type(WebElement ele, String data) {

		try {
			ele.clear();
			ele.sendKeys(data);
		} catch (Exception e) {
			System.out.println("Invalid element exception occured on element type");
		}

	}

	public String getText(WebElement ele) {
		String textValue = "";
		try {
			textValue = ele.getText();
			System.out.println(textValue);
		} catch (WebDriverException e) {
			System.out.println("Exception occured on get text");
		}
		return textValue;
	}
	
	public void selectValueByvalue(WebElement ele, String value) {
		try {
			new Select(ele).selectByVisibleText(value);
			System.out.println("Element selected successfully"+ ele);
		} catch (Exception e) {
			System.out.println("Exception occured on select element");
		}
	}

	public void closeAllBrowsers() {
		try {
			driver.quit();
			System.out.println("Browser closed successfully");
		} catch (Exception e) {
			System.out.println("Exception occured while closing the browser");
		}
	}

}
