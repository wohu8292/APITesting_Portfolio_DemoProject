package step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utility;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Assert;

public class stepDefinition1 extends Utility{
	RequestSpecification res;
	TestDataBuild data = new TestDataBuild();
	ResponseSpecification resspec;
	Response response;
	static String place_id;				//keep same value in every test execution

	@Given("Add Place Payload with {string},{string},{string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		res = given().spec(requestSpecification()).body(data.addPlacePayload(name, language, address));
}
	
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		APIResources resourceAPI=APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		
		resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(method.equalsIgnoreCase("POST")) 
		 response = res.when().post(resourceAPI.getResource());
		else if(method.equalsIgnoreCase("GET")) 
			response = res.when().get(resourceAPI.getResource());
		else if(method.equalsIgnoreCase("DELETE"))
			response = res.when().delete(resourceAPI.getResource());
	}
	
	@Then("the API call got success with Status Code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
	    Assert.assertEquals(response.statusCode(), 200);
	}
	
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String expectedValue) {
		Assert.assertEquals(getJsonPath(response,key),expectedValue);	
		}
	
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
		place_id=getJsonPath(response, "place_id");
		res=given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource,"Get");
		String actualName = getJsonPath(response, "name");
		Assert.assertEquals(actualName, expectedName);
	}
	
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		res = given().spec(requestSpecification()).body(data.deletPlacePayload(place_id));
	}
}
