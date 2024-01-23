package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utility {
	
	public static RequestSpecification req;
	public RequestSpecification requestSpecification() throws IOException {
		if(req==null) {
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		req = new RequestSpecBuilder().setBaseUri(getBaseURI("baseUri")).addQueryParam("key", "qaclick123")
		.addFilter(RequestLoggingFilter.logRequestTo(log))
		.addFilter(ResponseLoggingFilter.logResponseTo(log))
		.setContentType(ContentType.JSON).build();
		return req;
		}
	return req;
	}
	
	public String getBaseURI(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:/Users/gjdnd/Desktop/selenium/API_Project/src/test/java/resources/global.properties");
		prop.load(fis);
		String baseUri = prop.getProperty(key);
		return baseUri;
	}
	
	public String getJsonPath(Response response, String key) {
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();
	}
}
	