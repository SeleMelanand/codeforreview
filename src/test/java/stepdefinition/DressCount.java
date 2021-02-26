package stepdefinition;

import java.util.List;

import org.openqa.selenium.WebElement;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import wrapperpackage.WrapperClass;

public class DressCount extends WrapperClass {

	@Given("Move to Dresses and click casual dresses")
	public void moveToDressesAndClickCasualDresses() throws InterruptedException {
		WebElement dresses = locateElement("xpath", "//div[text()='Categories']/../ul/li[2]/a");
		WebElement casualdresses = locateElement("xpath", "//div[text()='Categories']/../ul/li[1]/a");
		//(//a[text()='Dresses'])[2]
		//click(locateElement("xpath", "//a[@title='Orders']//span"));
		movetoSpecificElement(dresses);
		cursorToSpecificElementAndClick(casualdresses);
	}

	@And("Get the title of the page of casual dresses")
	public void getTitleofCasualDresses() {
		String casualDressTitleName = getTitleName();
		System.out.println(casualDressTitleName);
	}

	@And("get the total count of casual dresses available")
	public void getCasualDressCount() {
		List<WebElement> listOfDresses = findListOElements("//div[@class='product-image-container']");
		int countOfDresses = listOfDresses.size();
		System.out.println(countOfDresses);
	}

	@And("Move to Dresses and click evening dresses")
	public void moveToDressesAndClickEveningDresses() {
		WebElement dresses = locateElement("xpath", "//div[text()='Categories']/../ul/li[2]/a");
		WebElement evedresses = locateElement("xpath", "//div[text()='Categories']/../ul/li[2]/a");
		//cursorToSpecificElementAndClick(dresses, evedresses);
		movetoSpecificElement(dresses);
		cursorToSpecificElementAndClick(evedresses);

	}

	@And("Get the title of the page of evening dresses")
	public void getTitleOfEveningDresses() {
		String eveningDressTitleName = getTitleName();
		System.out.println(eveningDressTitleName);
	}

	@And("get the total count of evening dresses available")
	public void getEveningDressCount() {
		List<WebElement> listOfDresses = findListOElements("//div[@class='product-image-container']");
		int countOfDresses = listOfDresses.size();
		System.out.println(countOfDresses);
	}

	@And("Move to Dresses and click summer dresses")
	public void moveToDressesAndClickSummerDresses() {
		WebElement dresses = locateElement("xpath", "//div[text()='Categories']/../ul/li[2]/a");
		WebElement summerdresses = locateElement("xpath", "//div[text()='Categories']/../ul/li[3]/a");
		//cursorToSpecificElementAndClick(dresses, summerdresses);
	}

	@And("Get the title of the page of summer dresses")
	public void getTitleOfSummerDresses() {
		String eveningDressTitleName = getTitleName();
		System.out.println(eveningDressTitleName);
	}

	@And("get the total count of summer dresses available")
	public void getSummerDressCount() {
		List<WebElement> listOfDresses = findListOElements("//div[@class='product-image-container']");
		int countOfDresses = listOfDresses.size();
		System.out.println(countOfDresses);
	}

}
