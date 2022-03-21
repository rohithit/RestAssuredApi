package rest_sample;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class RestAssuredAssignment_3 {

		@SuppressWarnings("unchecked")
		@Test(enabled = true)
		public void createUser(ITestContext val)
		{
			RestAssured.baseURI="https://petstore.swagger.io/v2";
			JSONObject obj = new JSONObject();
			obj.put("username", "HarryPotter");
			obj.put("firstName", "Harry");
			obj.put("lastName", "Potter");
			obj.put("email", "HarryPotter@gmail.com");
			obj.put("password", "Harry@123");
			obj.put("phone", "7091414160");
			
			String u_name="HarryPotter";
			

			
			
			given()
			.contentType(ContentType.JSON)
			.body(obj.toJSONString()).
		when()
			.post("/user").
		then()
			.statusCode(200)
			.log()
			.all();
			
			val.setAttribute("username", u_name);
		}
		
		@Test(enabled = true, dependsOnMethods="createUser")
		public void login()
		{
			RestAssured.baseURI="https://petstore.swagger.io/v2";
			given().
			when()
				.queryParam("username","HarryPotter")
				.queryParam("password","Harry@123")
				.get("/user/login").
			then()
				.statusCode(200)
				.log()
				.all();
		}
		
		@SuppressWarnings("unchecked")
		@Test(enabled = true, dependsOnMethods= "login")
		public void edit(ITestContext val)
		{
			RestAssured.baseURI="https://petstore.swagger.io/v2";
			JSONObject obj = new JSONObject();
			obj.put("username", "RohitJsr");
			obj.put("firstName", "Rohit");
			obj.put("lastName", "Mahato");
			obj.put("email", "rohitjsr@gmail.com");
			obj.put("password", "Rm@123");
			obj.put("phone", "7091414160");
			
			String u_name="RohitJsr";
			

			
			given()
			.contentType(ContentType.JSON)
			.body(obj.toJSONString()).
		when()
			.put("/user/"+val.getAttribute("username")).
		then()
			.statusCode(200)
			.log()
			.all();

			val.setAttribute("username", u_name);
			
		}
		
		@Test(enabled = true, dependsOnMethods= "edit")
		public void logout()
		{
			RestAssured.baseURI="https://petstore.swagger.io/v2";
			given().
			when()
				.get("/user/logout").
			then()
				.statusCode(200)
				.log()
				.all();
		}
		
		@Test(enabled = true, dependsOnMethods="logout")
		public void delete(ITestContext val)
		{
			RestAssured.baseURI="https://petstore.swagger.io/v2";
			


			
			given().
			when()
			.delete("/user/"+val.getAttribute("username").toString()).
		then()
			.statusCode(200)
			.log()
			.all();
			

		}
		
}
	
	
	


