package com.employeeapi.base;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;


import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {

	public static RequestSpecification httpRequest;
	public static Response response;
	public String empID = "17"; // Hard coded - Input for Get details of Single Employee & update employee

	public Logger logger;

	@BeforeClass
	public void setup() {

		logger = LogManager.getLogger(this.getClass());// added Logger
	}

}
