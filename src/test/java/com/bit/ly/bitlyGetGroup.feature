#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: retrieving a group information using its groupid

  #@tag1
  Scenario Outline: i retrive group information using valid inputs
    Given i have authentication <token> and <BaseURI> to the api
    When I make a Get request with <endpoint> and <group_guid>
    Then I validate the status code <statuscode>
    And I validate the response objects <name> and <group_guid>

    Examples: 
      | token                                      | BaseURI                        | group_guid    | name          | endpoint    | statuscode |
      | "f16ba6c8d21066734a42500c5dfca63645cc69cf" | "https://api-ssl.bitly.com/v4" | "BmcdcgExvgQ" | "ajaykumarhb" | "/groups/" |        200 |



  #@tag2
  Scenario Outline: I retrive group information using invalid group_guid
    Given i have authentication <token> and <BaseURI> to the api
    When I make a Get request with <endpoint> and <group_guid>
    Then I validate the status code <statuscode>
   

    Examples: 
      | token                                      | BaseURI                        | group_guid    | name          | endpoint    | statuscode |
      | "f16ba6c8d21066734a42500c5dfca63645cc69cf" | "https://api-ssl.bitly.com/v4" | "BmcdcgExvgQ1" | "ajaykumarhb" | "/groups/" |        403 |
 