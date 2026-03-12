package base;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.builder.RequestSpecBuilder;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import io.restassured.specification.RequestSpecification;

public class BaseTest {
	
	  public static RequestSpecification request;
	  public static Response response;

	    public static RequestSpecification requestSpecification() throws FileNotFoundException {
	        PrintStream log = new PrintStream(new FileOutputStream("logging.txt"), true);

	        request = new RequestSpecBuilder()
	                .setBaseUri("https://reqres.in")
	                .setContentType(ContentType.JSON)
	                .addHeader("x-api-key", "reqres_c634581307424516871da09e519d2ece")
	                .addFilter(new RequestLoggingFilter(log))
	                .addFilter(new ResponseLoggingFilter(log))
	                .build();

	        return request;
	    }
}
