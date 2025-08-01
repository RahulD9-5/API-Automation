package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlaceAPI")
	public void beforeMethod() throws IOException {
		
		StepDefinition obj = new StepDefinition();
		if (StepDefinition.place_id ==null) {
			
			obj.add_place_payload();
			obj.user_calls_with_post_http_request("AddPlaceAPI","Frontline house","29, side layout, cohen 09","French-IN");
			
			
		}
		
		
	}

}
