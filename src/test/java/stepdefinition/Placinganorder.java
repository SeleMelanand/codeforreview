package stepdefinition;

import org.testng.Assert;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import wrapperpackage.WrapperClass;

public class Placinganorder extends WrapperClass {
	String productDetails = "";
	String qty = "4";
	String size = "M";
	String addedProductDetails = "";
	String addedProductSize = "";
	String addedQty = "";

	@Given("Click Tshirts option")
	public void clicktsirtoption() {
		click(locateElement("xpath", "(//a[text()='T-shirts'])[2]"));
	}

	@And("Get the product details of the product added to the cart")
	public void getproductdetails() {
		productDetails = getText(locateElement("class", "product-name"));
		click(locateElement("xpath", "//a[@class='product_img_link']//img"));
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