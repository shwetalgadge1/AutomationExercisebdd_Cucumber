Feature: Login and Print Product Details

  Scenario: Login and print all product names and prices
    Given I am on the login page
    When I login with valid credentials
    And I navigate to the products page
    Then I print all product names and prices

 