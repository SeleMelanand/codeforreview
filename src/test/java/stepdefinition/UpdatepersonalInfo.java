package stepdefinition;

import org.testng.Assert;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import wrapperpackage.WrapperClass;

public class UpdatepersonalInfo extends WrapperClass {
	String productDetails = "";
	String qty = "4";
	String size = "M";
	String addedProductDetails = "";
	String addedProductSize = "";
	String addedQty = "";

	@Given("User navigate to loginpage")
	public void navigatetologinpage() {
		startApp("chrome");

	}

	@When("Enter Username and password")
	public void enterusernameandpassword() {
		type(locateElement("id", "email"), userName);
		type(locateElement("id", "passwd"), password);
	}

	@Then("User logged in")
	public void userloggedin() {
		click(locateElement("id", "SubmitLogin"));
	}

	@Given("Click my personal information")
	public void clickmypersonalinformation() {
		click(locateElement("xpath", "//a[@title='Information']//span"));
	}

	@And("Enter updated firstname")
	public void enterupdatedfirstname() {
		type(locateElement("id", "firstname"), "updatedfirstname");
	}

	@And("Enter current password")
	public void entercurrentpassword() {
		type(locateElement("id", "old_passwd"), password);
	}

	@When("Select Save button to save changes")
	public void clicksave() {
		click(locateElement("xpath", "(//button[@type='submit'])[2]/span"));
	}

	@Then("check whether saved successfully message is getting displayed")
	public void checksavedsuccessfully() {
		String savedText = getText(locateElement("xpath", "//div[@id='center_column']//p"));
		Assert.assertTrue(savedText.equalsIgnoreCase("Your personal information has been successfully updated."));

	}

	@Given("Click Tshirts option")
	public void clicktsirtoption() {
		click(locateElement("xpath", "(//a[text()='T-shirts'])[2]"));
	}

	@And("Get the product details of the product added to the cart")
	public void getproductdetails() {
		pagedown();
		click(locateElement("xpath", "//a[@class='product_img_link']//img"));
		productDetails = getText(locateElement("xpath", "//div[@id='columns']/div[3]/div/div/div/div[3]/h1"));
		click(locateElement("xpath", "//p[@id='quantity_wanted_p']//input"));
		type(locateElement("id", "quantity_wanted"), qty);
		selectValueByvalue(locateElement("id", "group_1"), size);
	}

	@And("Click on the item to Add to cart")
	public void addproducttocart() {
		click(locateElement("xpath", "//a[@title='Proceed to checkout']/span"));
	}

	@When("Click Proceed to checkout")
	public void proceedtocheckout() {
		click(locateElement("xpath", "//a[@title='Proceed to checkout']/span"));
	}

	@Then("Verify the product details are added correctly to the cart")
	public void verifyproductdetails() {
		addedProductDetails = getText(locateElement("xpath", "//table[@id='cart_summary']/tbody//p/a"));
		Assert.assertTrue(addedProductDetails.equalsIgnoreCase(productDetails));

		addedProductSize = getText(locateElement("xpath", "//table[@id='cart_summary']/tbody//td[2]//small[2]/a"));
		Assert.assertTrue(addedProductSize.equalsIgnoreCase(size));

		addedQty = getText(locateElement("xpath", "//table[@id='cart_summary']/tbody//td[5]//input[2]"));
		Assert.assertTrue(addedQty.equalsIgnoreCase(qty));

	}

}
