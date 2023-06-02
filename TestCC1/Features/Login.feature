Feature: Login with Valid Credentials

@sanity
  Scenario: Successful Login with Valid Credentials
    Given User Launch browser
    And opens URL "http://localhost/"
    When User enters Email as "admin@gmail.com" and Password as "123456"
    And Select Role is "Admin" 
    And Click on Submit button
    Then User navigates to "Admin panel"

 