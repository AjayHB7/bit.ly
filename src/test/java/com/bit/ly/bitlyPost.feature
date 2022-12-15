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
Feature: creating a new Bitly Link with API

  @tag1
  Scenario Outline: i create new shortened link with required inputs
    Given i have authentication <token> and <BaseURI> to the api
    When I pass the <long_url> <group_guid> and <title> to json object
    And I make the POST request with <endpoint>
    Then I verify the status code to be <statuscode>
    And I verify the <title> in the response body

    Examples: 
      | token                                      | BaseURI                        | long_url                                      | group_guid    | title             | endpoint    | statuscode |
      | "f16ba6c8d21066734a42500c5dfca63645cc69cf" | "https://api-ssl.bitly.com/v4" | "https://www.google.com/intl/en_in/business/" | "BmcdcgExvgQ" | "Google Business" | "/bitlinks" |        200 |
      

  @tag2
  Scenario Outline: i try to create new shortened link without mandatory inputs
    Given i have authentication <token> and <BaseURI> to the api
    When I pass the <group_guid> and <title> to json object
    And I make the POST request with <endpoint>
    Then I verify the status code to be <statuscode>

    Examples: 
      | token                                      | BaseURI                        | group_guid    | title             | endpoint    | statuscode |
      | "f16ba6c8d21066734a42500c5dfca63645cc69cf" | "https://api-ssl.bitly.com/v4" | "BmcdcgExvgQ" | "Google Business" | "/bitlinks" |        400 |





  #@tag3
  Scenario Outline: i create new shortened link with required inputs which alreadly exist
    Given i have authentication <token> and <BaseURI> to the api
    When I pass the <long_url> <group_guid> and <title> to json object
    And I make the POST request with <endpoint>
    Then I verify the status code to be <statuscode>

    Examples: 
      | token                                      | BaseURI                        | long_url                                      | group_guid    | title             | endpoint    | statuscode |
      | "f16ba6c8d21066734a42500c5dfca63645cc69cf" | "https://api-ssl.bitly.com/v4" | "https://www.google.com/intl/en_in/business/" | "BmcdcgExvgQ" | "Google Business" | "/bitlinks" |        200 |
