Feature: Placing an order and updating Firstname of the loggedin Account 

Background: User to login 
	Given User navigate to loginpage 
	When Enter Username and password 
	Then User logged in 
	
Scenario: Update Personal Information (positive) 
	Given Click my personal information 
	And Enter updated firstname 
	And Enter current password 
	When Select Save button to save changes 
	Then check whether saved successfully message is getting displayed 

Scenario: Placing an order and verifying the order details
	Given Click Tshirts option
	And Get the product details of the product added to the cart
	And Click on the item to Add to cart
	And Click Proceed to checkout
	And User confirm order
	And get order details from order history
	Then Verify the product details are added correctly to the cart
	