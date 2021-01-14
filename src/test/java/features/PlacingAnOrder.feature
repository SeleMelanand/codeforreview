Feature: Placing an order and make sure the order details are correct 

Scenario: Placing an order and verifying the order details
	Given Click Tshirts option
	And Get the product details of the product added to the cart
	And Click on the item to Add to cart
	When Click Proceed to checkout
	Then Verify the product details are added correctly to the cart
	