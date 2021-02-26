Feature: Get the page title and the count of individual Dresses

Background: User to login 
	Given User navigate to loginpage 
	When Enter Username and password 
	Then User logged in
	
@EndtoEnd
Scenario: get title and product count on dresses (positive) 
	Given Move to Dresses and click casual dresses
	And Get the title of the page of casual dresses
	And get the total count of casual dresses available
	And Move to Dresses and click evening dresses
	And Get the title of the page of evening dresses
	And get the total count of evening dresses available
	And Move to Dresses and click summer dresses
	And Get the title of the page of summer dresses 
	