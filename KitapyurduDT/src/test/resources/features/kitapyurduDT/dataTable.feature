Feature: Test Login Page	
	
	Background: To Launch the browser
		Given Launch the browser
		
	Scenario: Login with existing user
		When Launch the web site
		And Launch the login page
		And Accept cookies
		And user enter valid data on the page
		| Fields	| Values				|
		| Email		| bsra.6141@outlook.com	|	
		| Password	| Kaptan6571.			|
		Then Login should success