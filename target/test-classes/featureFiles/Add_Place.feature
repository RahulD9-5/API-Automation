

Feature: Validating Place APIs.

  @AddPlaceAPI
  Scenario Outline: Verify if PLace is getting added successfully using Add Place API
    Given Add Place Payload
    When user calls "AddPlaceAPI" with post http request with parameters <name>,<address>,<language>
    Then the "addPlaceAPI" gives a successfull response with status code 200
    And "status" of "addPlaceAPI" in response body is "OK"
    And "scope" of "addPlaceAPI" in response body is "APP"
    And verify place created maps to <name> using "getPlaceAPI"
    
    Examples:
    |name             |address                    |language   |
    |"Frontline house"|"29, side layout, cohen 09"|"French-IN"|
    |"Backline house" |"30, front layout,cohen 08"|"Tamil"    | 
   
   @DeletePlaceAPI
   Scenario: Verify if PLace is getting deleted successfully using Delete Place API
    Given Delete Place Payload
    When user calls "deletePlaceAPI" with post http request
    Then the "deletePlaceAPI" gives a successfull response with status code 200
    And  "status" of "deletePlaceAPI" in response body is "OK"
    
    
   

  