@Restapi
Feature: API test
  Please use URL for API is https://reqres.in/


  Scenario: 1 Should see LIST USER of all existing users
    Given I get the default list of users for on 1st page
    When I get the list of all users within every page
    Then I should see total user count equals the number of products is 12
    
  Scenario: 2 Fetch a single user by user id
    Given the ReqRes API client is available
    When the user fetches user with id 2
    Then the API response status should be 200
    And the response should contain first name "Janet"

  Scenario: 3 Fetch a single user by user id
    Given the ReqRes API client is available
    When the user fetches user with id 12
    Then the API response status should be 200
    And the response should contain first name "Rachel"
    
  Scenario: 4 Create a new user
    Given the ReqRes API client is available
    When the user creates a new ReqRes user with name "Vedlogic" and job "QA Engineer"
    Then the API response status should be 201
    And the created response should contain name "Vedlogic"

  Scenario: 5 Delete a user
    Given the ReqRes API client is available
    When the user deletes user with id 2
    Then the API response status should be 204
    
  Scenario: 6 Should see SINGLE USER NOT FOUND error code
    Given the ReqRes API client is available
    When the user fetches user with id 55
    Then the API response status should be 404

  Scenario Outline: 7 CREATE a user
 	Given the ReqRes API client is available
  	When the user creates a new ReqRes user with name "<Name>" and job "<Job>"
  	Then the API response status should be 201
  	And the created response should contain name "<Name>"

	Examples:
	  | Name  | Job     |
	  | Peter | Manager |
	  | Liza  | Sales   |


  Scenario: 8 LOGIN - SUCCESSFUL by a user
    Given I login API with the following data
      | Email              | Password   |
      | eve.holt@reqres.in | cityslicka |
    Then the API response status should be 200
        
  Scenario: 9 LOGIN - UNSUCCESSFUL BLANK password
    Given I login API with the following data
      | Email              | Password |
      | eve.holt@reqres.in |          |
    Then the API response status should be 400
    And I should see the following response message:
      | "error": "Missing password" |

  Scenario: 10 LOGIN - UNSUCCESSFUL BLANK username
    Given I login API with the following data
      | Email              | Password   |
      |                    | cityslicka |
    Then the API response status should be 400
    And I should see the following response message:
      | "error": "Missing email or username" |

