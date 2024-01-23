Feature: Validating Place API's
@AddPlace
Scenario Outline: Verify if place is being succesfully added using AddPlaceAPI
	Given Add Place Payload with "<name>","<language>","<address>"
	When user calls "AddPlaceAPI" with "Post" http request
	Then the API call got success with Status Code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_id created maps to "<name>" using "GetPlaceAPI"

Examples:
	|	name	|	language	|	address	|
	|	woong	|	Korean		|	Denver	|
#	|	min		|	English		|	Boulder	|
	
@DeletePlace
Scenario: Verify if Delete Plate functionally is working
	Given DeletePlace Payload
	When user calls "DeletePlaceAPI" with "Delete" http request
	Then the API call got success with Status Code 200
	And "status" in response body is "OK"
	

