Feature: Update Firstname in my Account 

Scenario: Update Personal Information (positive)
	Given Click my personal information
	And Enter updated firstname
	And Enter current password
	When Select Save button to save changes
	Then check whether saved successfully message is getting displayed