package stepdefinition;

import org.testng.Assert;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import wrapperpackage.WrapperClass;

public class UpdatepersonalInfo extends WrapperClass{
	@Given ("Click my personal information")
	public void clickmypersonalinformation() {
		click(locateElement("xpath", "//a[@title='Information']//span"));
	}
	
	@And ("Enter updated firstname")
	public void enterupdatedfirstname() {
		type(locateElement("id", "firstname"), "updatedfirstname");
	}

	@And ("Enter current password")
	public void entercurrentpassword() {
		type(locateElement("id", "old_passwd"), password);
	}
	
	@When ("Select Save button to save changes")
	public void clicksave() {
		click(locateElement("xpath", "(//button[@type='submit'])[2]/span"));
	}
	
	
	@Then ("check whether saved successfully message is getting displayed")
	public void checksavedsuccessfully() {
		String savedText = getText(locateElement("xpath", "//div[@id='center_column']//p"));
		Assert.assertTrue(savedText.equalsIgnoreCase("Your personal information has been successfully updated."));
	
	}
	
	
	
}
