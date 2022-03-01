/******************************************************
Test Name: Delete an employee record
URI: http://dummy.restapiexample.com/api/v1/delete/{id}
Request Type: DELETE
Request Payload(Body): NA

********* Validations **********
Response Payload(Body) : {"success":{"text":"successfully! deleted Records"}}
Status Code : 200
Status Line : HTTP/1.1 200 OK
Content Type : text/html; charset=UTF-8
Server Type :  nginx/1.14.1
Content Encoding : gzip
**********************************************************/
package com.employeeapi.testCases;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class TC005_Delete_Employee_Record extends TestBase {

	@BeforeClass
	void deleteEmployee() throws InterruptedException {

		logger.info("*********Started TC005_Delete_Employee_Record **********");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();

		response = httpRequest.request(Method.GET,"/employees");
		// First get the JsonPath object instance from the Response interface
		JsonPath jpath = response.jsonPath();


		// Capture id

		List<Object> data = jpath.getList("data");		
		
		Map<Object,Object> emp =(Map<Object, Object>) data.get(0);
				
		int empID=(Integer) emp.get("id");
		
		//System.out.println(empID);

		response = httpRequest.request(Method.DELETE, "/delete/" + empID);
		Thread.sleep(5000);

	}

	@Test
	void checkResponseBody() {
		String responseBody = response.getBody().asString();
		Assert.assertEquals(responseBody.contains("Successfully! Record has been deleted"), true);

	}

	@Test
	void checkStatusCode() {
		int statusCode = response.statusCode();
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	void checkStatusLine() {
		String statusLine = response.statusLine();
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}

	@Test
	void checkcontentType() {
		String contentType = response.contentType();
		Assert.assertEquals(contentType, "application/json");
	}
	
	@Test
	void checkserverType() {
		String serverType=response.header("Server");
		Assert.assertEquals(serverType, "nginx");
	}
	@Test
	void checkcontentEncoding()
	{
		String contentEncoding = response.header("Content-Encoding");
		//Assert.assertEquals(contentEncoding, "gzip");

	}
	@AfterClass
	void tearDown() {
		logger.info("*********Finished TC005_Delete_Employee_Record **********");
	}


}
