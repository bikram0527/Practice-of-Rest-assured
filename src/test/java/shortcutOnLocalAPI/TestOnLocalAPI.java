package shortcutOnLocalAPI;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;        // import it by yourself.

public class TestOnLocalAPI {
	
	
	// NOTE: un-comment all tests 1 by 1 to run GET POST PUT PATCH DELETE requests on local host.
	          // chrome address  is   http://localhost:3000/
	                   // notepad file address is  C:\Users\prabh\db.json
	
	
	@Test 
	 public void get_Request() {
		
		baseURI="http://localhost:3000";
		given().get("/users").then().statusCode(200).log().all();
		 
	 }
	 
	 
	
	//@Test 
	 public void post_Request() {
		
		JSONObject js= new JSONObject();
		
		js.put("f_name","thomas");
		js.put("l_name","edson");
		js.put("subjectId","3");
		
		
		baseURI="http://localhost:3000";
		given().header("Content-Type", "application/json"). //pass header
		         //contentType(ContentType.JSON).   // this means we give content as a json and response i will accept is as json.
		        //accept(ContentType.JSON).        // its same as header option we use these 2 lines instead of header("Content-Type", "application/json")
		   body(js.toJSONString()). //pass body
		when().
		   post("/users").
		then().
		    statusCode(201).  // here status code is 201
		    log().all();
				 
	 }
	 
	 
	   //@Test 
	   public void put_Request() {
			
			JSONObject js= new JSONObject();
			
			js.put("f_name","bill");
			js.put("l_name","clinton");
			js.put("subjectId","3");
			
			
			baseURI="http://localhost:3000";
			given().
			   contentType(ContentType.JSON).   // this means we give as a json file and accept as a json file.
			   accept(ContentType.JSON).        // its same as header option we use these 2 lines instead of header("Content-Type", "application/json")
			   body(js.toJSONString()). //pass body
			when().
			   put("/users/4"). // provide id of the user where we want to make modification. here in this case i use id =4
			then().
			    statusCode(200).  // in this case status code is 200 again
			    log().all();
			
		 }
	   
	   
	   //@Test 
	   public void patch_Request() {
			
			JSONObject js= new JSONObject();
			
			js.put("l_name","gates");
			
			baseURI="http://localhost:3000";
			given().
			   contentType(ContentType.JSON).   // this means we give as a json file and accept as a json file.
			   accept(ContentType.JSON).        // its same as header option we use these 2 lines instead of header("Content-Type", "application/json")
			   body(js.toJSONString()). //pass body
			when().
			   patch("/users/4"). // provide id of the user where we want to make modification. here in this case i use id =4
			then().
			    statusCode(200).  // in this case status code is 200 again
			    log().all();
			 
		 }
	   
	   
	   //@Test 
		 public void delete_Request() {
			
			baseURI="http://localhost:3000";
			when().delete("/users/4").then().statusCode(200).log().all();
	   }
		 
		 
		 //@Test 
         public void abc() {
			 
	 		           /* import static io.restassured.RestAssured.*;
                          import static io.restassured.matcher.RestAssuredMatchers.*;
				          import static org.hamcrest.Matchers.*;
				
				       //now
				          * use this website for json path finder  https://jsonpathfinder.com/ 
				            after finding path u can directly use  as */
	
	        
	        given().
	           get("http://localhost:3000").
	        then().
	           statusCode(200).
	           body("users[1].f_name",equalTo("inder")).  // imp
	           body("users.f_name",hasItem("prabh")).    // imp concept
	           log().all();
		 }
}
