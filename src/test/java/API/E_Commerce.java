package API;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
public class E_Commerce {
public static String baseurl = "https://ecommerceservice.herokuapp.com/";
public String accesstoken;
public String id;
public String email;
@Test(priority = 0,enabled=false)
public void signup()
{
	RestAssured.baseURI = baseurl;
	String requestbody = "{\n"
			+"\"email\": \"devmm656@gmail.com\",\n"
			+ "	\"password\": \"devmm@666\"\n"
			+ "}";
	Response response =  given()
		.header("content-Type","application/json")
		.body(requestbody)
		.when()
		.post("/user/signup")
		.then()
		.assertThat().statusCode(201).and().contentType(ContentType.JSON)
		.extract().response();
	String jsonresponse = response.asString();
	JsonPath responsebody = new JsonPath(jsonresponse);
	System.out.println(responsebody.get("message"));
}
@Test(priority = 1)
public void login()
{
	RestAssured.baseURI = baseurl;
	String requestbody = "{\n"
			+"\"email\": \"devmm656@gmail.com\",\n"
			+ "	\"password\": \"devmm@666\"\n"
			+ "}";
	Response response =  given()
		.header("content-Type","application/json")
		.body(requestbody)
		.when()
		.post("/user/login")
		.then()
		.assertThat().statusCode(200).and().contentType(ContentType.JSON)
		.extract().response();
	String jsonresponse = response.asString();
	JsonPath responsebody = new JsonPath(jsonresponse);
	System.out.println(responsebody.get("accessToken"));
	accesstoken = responsebody.get("accessToken");
}
@Test(priority = 2)
public void getusers()
	{
	RestAssured.baseURI = baseurl;
	Response response =  given()
			.header("content-Type","application/json")
			.header("Authorization","bearer "+accesstoken)
			.when()
			.get("/user")
			.then()
			.assertThat().statusCode(200).and().contentType(ContentType.JSON)
			.extract().response();
	String jsonresponse = response.asString();
	JsonPath responsebody = new JsonPath(jsonresponse);
	System.out.println(responsebody.get("users[200]._id"));
	id = responsebody.get("users[200]._id");
	}
@Test(priority = 3)
public void delete()
	{
	RestAssured.baseURI = baseurl;
	Response response =  given()
			.header("content-Type","application/json")
			.header("Authorization","bearer "+accesstoken)
			.when()
			.delete("/user/"+id)
			.then()
			.assertThat().statusCode(200).and().contentType(ContentType.JSON)
			.extract().response();
	String jsonresponse = response.asString();
	JsonPath responsebody = new JsonPath(jsonresponse);
	System.out.println(responsebody.get("message"));
	}
}
