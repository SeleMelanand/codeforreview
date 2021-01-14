Feature: Update Firstname in my Account 

Background: User to login 
	Given User navigate to loginpage 
	When Enter Username and password 
	Then User logged in 
	
#Scenario: Update Personal Information (positive) 
#	Given Click my personal information 
#	And Enter updated firstname 
#	And Enter current password 
#	When Select Save button to save changes 
#	Then check whether saved successfully message is getting displayed 

Scenario: Placing an order and verifying the order details
	Given Click Tshirts option
	And Get the product details of the product added to the cart
	And Click on the item to Add to cart
	When Click Proceed to checkout
	Then Verify the product details are added correctly to the cart
	