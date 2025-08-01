package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	
	public static RequestSpecification reqSpec;
	ResponseSpecification respSpec;
	
	
	
	
	public RequestSpecification requestSpecBuilder() throws IOException {
		
		if (reqSpec==null) {
			PrintStream strObj = new PrintStream(new FileOutputStream("logging.txt"));
			
			reqSpec = new RequestSpecBuilder().setBaseUri(getConfig()).addQueryParam("key","qaclick123").setAccept("ContentType.JSON")
					 .addFilter(RequestLoggingFilter.logRequestTo(strObj))
					 .addFilter(ResponseLoggingFilter.logResponseTo(strObj))
					.build();
			return reqSpec;
			
		} 
		
		return reqSpec;
	
	}
	
	public ResponseSpecification resPonseSpecBuilder() {
		
		respSpec = new ResponseSpecBuilder().expectStatusCode(200)
				  .build();
		return respSpec;
		
	}
	
	public String getConfig() throws IOException {
		FileInputStream fis = new FileInputStream("C:\\Users\\SASMITA\\eclipse-workspace\\API_framework\\src\\test\\java\\resources\\config.properties");
		Properties obj = new Properties();
		obj.load(fis);
		
		return (obj.getProperty("url"));
		
	}
	
	


}
