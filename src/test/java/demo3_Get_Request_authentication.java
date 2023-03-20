import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class demo3_Get_Request_authentication {
	

	@Test
	public void GetBookDetails() {
		
		//Specify base URI
		RestAssured.baseURI="https://api.github.com/user/repos";
		
		//ques : 7th scenario in the excel file
		
		//Basic authentication // this website is not working on this but code is correct.
		PreemptiveBasicAuthScheme auth = new PreemptiveBasicAuthScheme();
		auth.setUserName("bikram0527");
		auth.setPassword("MUdhal78");
		
		
		//Request object created.......here httpRequest is any object name... we can use hp instead of that.
		RequestSpecification httpRequest=RestAssured.given();
		
		//Response object created..... we can use any name...... here we use response as an object of predefined Response class.
		Response response = httpRequest.request(Method.GET, "");
		
		
		
		
		//Print Response in a console window
		String responseBody = response.getBody().asString();
		System.out.println("Respons body is "+responseBody);
		
		
		//status code verification
				int statusCode =response.getStatusCode();
				System.out.println("Status code is :"+statusCode);
				Assert.assertEquals(statusCode, 200);
				

		

}
}
