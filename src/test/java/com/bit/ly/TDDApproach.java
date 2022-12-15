package com.bit.ly;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TDDApproach {
	
	@Test(enabled = false, testName = "creating a new link with Bit.ly (POST)")
	public void post1() {
		// defining BaseURI and EndPoint
		String BaseURI = "https://api-ssl.bitly.com/v4";
		String EndPoint = "/bitlinks";

		// passing base URI
		RestAssured.baseURI = BaseURI;

		// getting http request
		RequestSpecification httpreq = RestAssured.given();

		// configuring the header with content-type and Authorization
		httpreq.header("content-type", "application/json");
		httpreq.header("Authorization", "Bearer f16ba6c8d21066734a42500c5dfca63645cc69cf");

		// creating a json object
		JSONObject jobject = new JSONObject();
		jobject.put("long_url", "https://climate.nasa.gov/news/2469/10-interesting-things-about-earth/");
		jobject.put("domain", "bit.ly");
		jobject.put("group_guid", "BmcdcgExvgQ");
		jobject.put("title", "10 interesting things about earth");

		// adding the JsoObject to the html request
		httpreq.body(jobject.toJSONString());

		// sending the request
		Response httpresponse = httpreq.request(Method.POST, EndPoint);

		// printing response body
		String responseBody = httpresponse.getBody().asString();
		System.out.println(httpresponse.getBody().asPrettyString());

		// getting the jsonPath
		JsonPath jsonpath = httpresponse.jsonPath();
		System.out.println("Shorten link = " + jsonpath.get("link"));
		
		Assert.assertEquals(httpresponse.statusCode(),201);

		Assert.assertEquals(responseBody.contains("10 interesting things about earth"), true);
		
		Assert.assertEquals(
				responseBody.contains("https://climate.nasa.gov/news/2469/10-interesting-things-about-earth/"), true);

	}

	@Test(enabled= true, testName="Getting groups with group id")
	public void getGroupInfo(){

		// defining BaseURI and EndPoint
		String groupid = "BmcdcgExvgQ";
		String BaseURI = "https://api-ssl.bitly.com/v4";
		String EndPoint = "/groups/"+groupid;

		// passing base URI
		RestAssured.baseURI = BaseURI;

		// getting http request
		RequestSpecification httprequest = RestAssured.given();
		// passing authoriation
		httprequest.header("Authorization", "Bearer f16ba6c8d21066734a42500c5dfca63645cc69cf");
		//making the get request
		Response httpresponse = httprequest.request(Method.GET,EndPoint);
		
		
		// taing json body as string
		String responsebody = httpresponse.getBody().asPrettyString();
		System.out.println("Response Body = "+responsebody);
		
		//validating the response code 
		Assert.assertEquals(httpresponse.statusCode(),200);
		
		//validating  name and group id from the response object
		JsonPath jsonpath = httpresponse.jsonPath();
		
		System.out.println("group id = " + jsonpath.get("guid"));
		System.out.println("username = " + jsonpath.get("name"));
		
		System.out.println(responsebody.contains("ajaykumarhb"));
		System.out.println(responsebody.contains(groupid));
		
		
	}
	
	@Test(enabled= false, testName="Getting groups with group id and sortQuery")
	public void getGroupNsort(){

		// defining BaseURI and EndPoint
		String sort = "";
		String groupid = "BmcdcgExvgQ";
		String BaseURI = "https://api-ssl.bitly.com/v4";
		String EndPoint = "/groups/"+groupid+sort;

		// passing base URI
		RestAssured.baseURI = BaseURI;

		// getting http request
		RequestSpecification httprequest = RestAssured.given();
		// passing authoriation
		httprequest.header("Authorization", "Bearer f16ba6c8d21066734a42500c5dfca63645cc69cf");
		//making the get request
		Response httpresponse = httprequest.request(Method.GET,EndPoint);
		
		String responsebody = httpresponse.getBody().asPrettyString();
		// validating the reponse code
		Assert.assertEquals(httpresponse.statusCode(),200);
		
		
	}
	

}
