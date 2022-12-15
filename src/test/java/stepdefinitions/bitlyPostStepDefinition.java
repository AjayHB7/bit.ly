package stepdefinitions;

import org.json.simple.JSONObject;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class bitlyPostStepDefinition {

	RequestSpecification httprequest;
	Response httpresponse;
	String responseBody;

	@Given("i have authentication {string} and {string} to the api")
	public void i_have_authentication_and_to_the_api(String token, String BaseURI) {
		// passing base URI
		RestAssured.baseURI = BaseURI;

		// getting http request
		httprequest = RestAssured.given();

		// configuring the header with Authorization
		httprequest.header("Authorization", "Bearer " + token);

	}

	////////////

	@When("I pass the {string} {string} and {string} to json object")
	public void i_pass_the_and_to_json_object(String long_url, String group_guid, String title) {

		// configuring the header with content-type and Authorization
		httprequest.header("content-type", "application/json");

		// creating a json object
		JSONObject jobject = new JSONObject();
		jobject.put("long_url", long_url);
		jobject.put("domain", "bit.ly");
		jobject.put("group_guid", group_guid);
		jobject.put("title", title);

		// adding the JsoObject to the html request
		httprequest.body(jobject.toJSONString());

		System.out.println(jobject.toString());
	}

	@When("I make the POST request with {string}")
	public void i_make_the_post_request_with(String endpoint) {
		// sending the request
		httpresponse = httprequest.request(Method.POST, endpoint);
		// System.out.println(httpresponse.getStatusCode());
		responseBody = httpresponse.getBody().asPrettyString();

	}

	@Then("I verify the status code to be {int}")
	public void i_verify_the_status_code_to_be(int statuscode) {
		System.out.println("Response Body = " + responseBody);
		Assert.assertEquals(httpresponse.getStatusCode(), statuscode);

	}

	@Then("I verify the {string} in the response body")
	public void i_verify_the_in_the_response_body(String title) {
		Assert.assertEquals(responseBody.contains(title), true);

	}

	@When("I pass the {string} and {string} to json object")
	public void i_pass_the_and_to_json_object(String group_guid, String title) {

		// configuring the header with content-type and Authorization
		httprequest.header("content-type", "application/json");
		// creating a json object
		JSONObject jobject = new JSONObject();
		jobject.put("domain", "bit.ly");
		jobject.put("group_guid", group_guid);
		jobject.put("title", title);

		// adding the JsoObject to the html request
		httprequest.body(jobject.toJSONString());

	}

	//////////////////////////////////////////////

	@When("I make a Get request with {string} and {string}")
	public void i_make_a_get_request_with_and(String endpoint, String group_guid) {

		httpresponse = httprequest.request(Method.GET, endpoint + group_guid);
		responseBody = httpresponse.getBody().asPrettyString();
		System.out.println(responseBody);

	}

	@Then("I validate the status code {int}")
	public void i_validate_the_status_code(int statuscode) {

		// validating the response code
		Assert.assertEquals(httpresponse.getStatusCode(), statuscode);

	}

	@Then("I validate the response objects {string} and {string}")
	public void i_validate_the_response_objects_and(String name, String group_guid) {

		// validating the name and group_guid from the response object
		Assert.assertEquals(responseBody.contains(name), true);
		Assert.assertEquals(responseBody.contains(group_guid), true);

	}

	////////////////
	@When("I make a Get request with {string} having {string} and {string}")
	public void i_make_a_get_request_with_having_and(String endpoint, String group_guid, String sortQuery) {

		httpresponse = httprequest.request(Method.GET, group_guid+endpoint+sortQuery);

	}

}
