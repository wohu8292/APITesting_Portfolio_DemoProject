package step_definitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class hooks {
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		stepDefinition1 m = new stepDefinition1();
		if(stepDefinition1.place_id==null) {
		m.add_place_payload_with("Shetty", "French", "Asia");
		m.user_calls_with_http_request("AddPlaceAPI", "POST");
		m.verify_place_id_created_maps_to_using("Shetty", "GetPlaceAPI");
		}
		
		
	}
}
