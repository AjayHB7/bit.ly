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
Feature: Title of your feature

  @tag1
  Scenario Outline: i sort the bitlinks with valid group id and valid Query
    Given i have authentication <token> and <BaseURI> to the api
    When I make a Get request with <endpoint> having <group_guid> and <sortQuery>
    Then I validate the status code <statuscode>

    Examples: 
      | token                                      | BaseURI                                | endpoint     | group_guid    | sortQuery      | statuscode |
      | "f16ba6c8d21066734a42500c5dfca63645cc69cf" | "https://api-ssl.bitly.com/v4/groups/" | "/bitlinks/" | "BmcdcgExvgQ" | "?id=bitlinks" |        200 |

  @tag2
  Scenario Outline: i sort the bitlinks with valid group id and invalid Query
    Given i have authentication <token> and <BaseURI> to the api
    When I make a Get request with <endpoint> having <group_guid> and <sortQuery>
    Then I validate the status code <statuscode>

    Examples: 
      | token                                      | BaseURI                                | endpoint     | group_guid    | sortQuery      | statuscode |
      | "f16ba6c8d21066734a42500c5dfca63645cc69cf" | "https://api-ssl.bitly.com/v4/groups/" | "/bitlinks/" | "BmcdcgExvgQ" | "created=bitlinks" |        400 |
