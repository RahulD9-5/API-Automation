package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ContentHandler;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import groovyjarjarantlr4.v4.misc.Utils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import resources.APIResources;
import resources.TestDataBuild;

public class StepDefinition extends resources.Utils {

	
	Response addPlaceNew;
	Response deletePlace;
	RequestSpecification reqSpec;
	Response getPlaceResp;
	static String place_id;
	
	TestDataBuild objTestDataBuild = new TestDataBuild();
	
	
	@Given("Add Place Payload")
	public void add_place_payload() throws IOException {
		
	 reqSpec = requestSpecBuilder();
	
	}
	

	@Given("Delete Place Payload")
	public void delete_place_payload() throws IOException {
		
		
		reqSpec = requestSpecBuilder();
	
	
	}
	
	@When("user calls {string} with post http request with parameters {string},{string},{string}")
	public void user_calls_with_post_http_request(String string1,String string2,String string3,String string4) {
		APIResources resourcesObj = APIResources.valueOf(string1);
		
		addPlaceNew = given().spec(reqSpec).
				body(objTestDataBuild.addPlacePayload(string2,string3,string4))
		.when().post(resourcesObj.getResource()).then().spec(resPonseSpecBuilder()).
		
		
		log().all().extract().response();
		
		place_id =  addPlaceNew.jsonPath().getString("place_id");
		
	   
	}
	@When("user calls {string} with post http request")
	public void user_calls_with_post_http_request(String string1) {
		APIResources deletePlaceObj = APIResources.valueOf(string1);
		
		deletePlace = given().spec(reqSpec).body(objTestDataBuild.deletePlacePayload(place_id)).
				      when().post(deletePlaceObj.getResource()).then().spec(resPonseSpecBuilder()).log().all().
				      extract().response();
		
		
		
	}
	
	
	
	
	
	
	@Then("the {string} gives a successfull response with status code {int}")
	public void the_api_gives_a_successfull_response_with_status_code(String string1,Integer int1) {
		if (string1.contains("add")) {
			addPlaceNew.then().assertThat().statusCode(int1);
			
		}
		else if(string1.contains("delete")) {
			deletePlace.then().assertThat().statusCode(int1);
			
		}
		else {
			System.out.println("getPlaceAPI");
		}
	    // Write code here that turns the phrase above into concrete actions
		
	    
	}
	@And("{string} of {string} in response body is {string}")
	public void status_in_response_body_is(String keyValue,String string1,String expectedValue) {
		if (string1.contains("add")) {
			Assert.assertEquals(addPlaceNew.jsonPath().getString(keyValue),expectedValue);
			
		}
		else if(string1.contains("delete")) {
			Assert.assertEquals(deletePlace.jsonPath().getString(keyValue),expectedValue);
			
		}
		else {
			System.out.println("getPlaceAPI");
		}
	    // Write code here that turns the phrase above into concrete actions
	    
		
	}
	
	@And("verify place created maps to {string} using {string}")
	public void verify_place_created_maps_correctly(String string1,String string2) throws IOException {
		APIResources getPlaceObj = APIResources.valueOf(string2);
		RequestSpecification  getPlacereq = new RequestSpecBuilder().setBaseUri(getConfig()).setAccept("ContentType.JSON").addQueryParam("key","qaclick123").addQueryParam("place_id",place_id)
		 .build();
		
		ResponseSpecification getPlacerespSpec = new ResponseSpecBuilder().expectStatusCode(200).build();
		
		getPlaceResp = given().spec(getPlacereq).when().get(getPlaceObj.getResource()).then().spec(getPlacerespSpec).log().all().extract().response();
		
		Assert.assertEquals(string1,getPlaceResp.jsonPath().getString("name"));
		
		  
	}
	
	

}
