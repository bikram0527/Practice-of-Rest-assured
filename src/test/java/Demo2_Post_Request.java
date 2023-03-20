import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Demo2_Post_Request {
	
	@Test
	public void postRequestBooksAPI() 
	{ 
	  //Specify base URI
	   RestAssured.baseURI = "https://demoqa.com"; 
	   
	  //Request object created.......here httpRequest is any object name... we can use hp instead of that.
	   RequestSpecification httprequest = RestAssured.given();
	   
	   
	  // JSONObject is a class that represents a Simple JSON. 
	// Ques2 in excel table:We can add Key-Value pairs using the put method..so firstly create object of predefined JSONObject class. 
	   JSONObject requestParams = new JSONObject(); 
	   
	   requestParams.put("userId", "TQ123"); 
	   requestParams.put("isbn", "9781449325862"); 
	   
	  // Add a header stating the Request body is a JSON 
	   httprequest.header("Content-Type", "application/json"); // Add the Json to the body of the request
	   
	   httprequest.body(requestParams.toJSONString()); // Post the request and check the response
	   
	 //Response object created..... we can use any name...... here we use response as an object of predefined Response class.
	   Response response = httprequest.request(Method.POST,"/BookStore/V1/Books");
	   
	 //Print Response in a console window
	 		String responseBody = response.getBody().asString();
	 		System.out.println("Respons body is "+responseBody);
	 		
	 		//status code verification
	 		int statusCode =response.getStatusCode();
	 		System.out.println("Status code is :"+statusCode);
	 		Assert.assertEquals(statusCode, 504);
	 		
	 		// print status received
	 		 System.out.println("The status received: " + response.statusLine());
	 		 
	 		// Ques scenario 3 in the excel table: to print and verify some of the attibutes and their corresponding vlues??
	 		String ContentType= response.header("Content-Type");
	 		String Connection= response.header("Connection");
	 		System.out.println("Content-Type is "+ContentType);
	 		System.out.println("Connection is "+Connection);
	 		// now their verification
	 		Assert.assertEquals(ContentType ,"text/html");
	 		Assert.assertEquals(Connection,"keep-alive");;
	 		
	 		 
	 		
	 		 // Ques scenario 4 in the excel table: to print all the header element in a key value pair
	 		 Headers allheader=response.headers();
	 		 
	 		 for(Header header:allheader) {
	 			 System.out.println(header.getName()+"..."+header.getValue());
	 		 }
	 		 
	        
	 		// Ques : Suppose if we want to get value of any attribute then we use this syntax(unsolved ques due to url)
	 		 //but syntax is right
	 		 //String title =response.jsonPath().get("SucessCode");
	 		 //System.out.println("title of my json body is: "+title);
	   
	   
	 		 
	 		 /*
	 		  * import static io.restassured.RestAssured.*;
                import static io.restassured.matcher.RestAssuredMatchers.*;
				import static org.hamcrest.Matchers.*;
				
				//now
				 * use this website for json path finder  https://jsonpathfinder.com/ 
				
				after finding path u can directly use  as 
				
				@Test 
	            public void get_Request() {
		
		        baseURI="http://localhost:3000";
		        given().
		           get("/users").
		        then().
		           statusCode(200).
		           body("data[1].id",equalTo(2)).
		           log().all();
				
	 		  */
	}

}
