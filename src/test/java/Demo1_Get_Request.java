
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Demo1_Get_Request {

	
	@Test
	public void GetBookDetails() {
		
		//Specify base URI
		RestAssured.baseURI="https://demoqa.com/BookStore";
		
		//Request object created.......here httpRequest is any object name... we can use hp instead of that.
		RequestSpecification httpRequest=RestAssured.given();
		
		//Response object created..... we can use any name...... here we use response as an object of predefined Response class.
		Response response = httpRequest.request(Method.GET, "/v1/Books");
		
		//Print Response in a console window
		String responseBody = response.getBody().asString();
		System.out.println("Respons body is "+responseBody);
		
		// now 
 		
 		// Ques (5th scenario in the excel table): Get_Validate any value in JSON Response :
 		//means to validate that our response body(as printed above) contains some particular value or not 
		// In this eg: we have "publisher":"O'Reilly Media" in our response body
 		Assert.assertEquals(responseBody.contains("O'Reilly Media"), true);  //thid is used to verify only one value.
 		
 		
 		 
 		//now Ques: (6th scenario in the excel table):if we want to verify all the values in the response body then ?
 		JsonPath js=response.jsonPath();
 		
 	    System.out.println(js.get("title"));
 	    System.out.println(js.get("subTitle"));
 	    System.out.println(js.get("author"));
 	    System.out.println(js.get("publish_date"));
 	    System.out.println(js.get("pages"));
 	    
 		//note: the ans of the above is showing null bcoz of the url, as it contains books not book.but the code is right.
 		
 		
 		
		
		//status code verification
		int statusCode =response.getStatusCode();
		System.out.println("Status code is :"+statusCode);
		Assert.assertEquals(statusCode, 200);
		
		//status line verification
		String statusLine = response.getStatusLine();
		System.out.println("Ststus Line is : "+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
		
	}
}
