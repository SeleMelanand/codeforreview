package stepdefinition;

import java.util.List;

import org.junit.internal.runners.model.EachTestNotifier;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import wrapperpackage.WrapperClass;

public class UpdatepersonalInfo extends WrapperClass {
	String productDetails = "";
	String size = "M";
	String productColor = "";
	String addedProductDetails = "";
	String addedProductSize = "";
	String addedQty = "";
	String orderRefNumber = "";
	String addedProductColor = "";

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
		switchToFrame(locateElement("class", "fancybox-iframe"));
		productDetails = getText(locateElement("xpath", "//body[@id='product']/div/div/div[2]/h1"));
		click(locateElement("xpath", "//p[@id='quantity_wanted_p']//input"));
		selectValueByvalue(locateElement("id", "group_1"), size);
		productColor = locateElement("xpath", "//a[@class='color_pick selected']").getAttribute("name");
	}

	@And("Click on the item to Add to cart")
	public void addproducttocart() {
		click(locateElement("xpath", "//p[@id='add_to_cart']//span"));
		switchOutOfFrame();
	}

	@And("Click Proceed to checkout")
	public void proceedtocheckout() {
		click(locateElement("xpath", "//a[@title='Proceed to checkout']/span"));
		pagedown();
		click(locateElement("xpath", "//p[contains(@class,'cart_navigation')]//a/span"));
		pagedown();
		click(locateElement("xpath", "//p[contains(@class,'cart_navigation')]//button/span"));
		click(locateElement("id", "uniform-cgv"));
		click(locateElement("xpath", "//p[contains(@class,'cart_navigation')]//button/span"));
		click(locateElement("class", "bankwire"));

	}

	@When("User confirm order")
	public void userconfirmorder() {
		pagedown();
		click(locateElement("xpath", "//p[contains(@class,'cart_navigation')]//button/span"));
		String ordertext = getText(locateElement("class", "box"));

		String[] abc = ordertext.split("-");
		String[] abcd = abc[5].split(" ");
		orderRefNumber = abcd[9];
		click(locateElement("xpath", "//a[@title='Back to orders']"));
	}

	@And("get order details from order history")
	public void getorderdetailsfromorderhistory() {
		List<WebElement> orderlist = findListOElements("//table[@id='order-list']/tbody/tr//a");

		for (WebElement ordernumdetails : orderlist) {
			if (ordernumdetails.getText().equalsIgnoreCase(orderRefNumber)) {
				System.out.println("Identified correct order");
				click(ordernumdetails);
				break;
			}
		}
		pageDownToSpecificElement(locateElement("xpath", "//div[@id='order-detail-content']//thead/tr"));
	}

	@Then("Verify the product details are added correctly to the cart")
	public void verifyproductdetails() {
		String proddetails = getText(locateElement("xpath", "//div[@id='order-detail-content']//tbody//td[2]/label"));
		String[] proddetail1 = proddetails.split(",");
		String[] proddetail2 = proddetail1[1].split(":");
		addedProductSize = proddetail2[1].trim();
		Assert.assertTrue(addedProductSize.contentEquals(size));

		String[] proddetail3 = proddetails.split(" -");
		addedProductDetails = proddetail3[0].trim();
		Assert.assertTrue(addedProductDetails.equalsIgnoreCase(productDetails));

		String[] proddetail4 = proddetails.split(":");
		String[] proddetail5 = proddetail4[1].split(",");

		addedProductColor = proddetail5[0].trim();
		Assert.assertTrue(addedProductColor.equalsIgnoreCase(productColor));

		System.out.println("end of execution");

	}

}
