package com.testing.test;

import groovy.json.JsonException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
//import io.restassured.itest.java.support.WithJetty;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyExtractionOptions;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
//import org.junit.Test;

import java.util.*;

import static io.restassured.RestAssured.*;
import static io.restassured.parsing.Parser.JSON;
import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Inter1ApplicationTests {
	
	@BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
    }

	@Test
	void contextLoads() {
		
	}
	
	
	public void
	example_resource_returns_200_() {
		
		
		 given().
	        when().
	                get("/employees").
	        then().
	                assertThat().
	                statusCode(200);
	   /* when().
	            get(baseURI+"/employees").
	    then().assertThat().statusCode(200);*/
	            

	}
	
	
	@Test 
	public void
	example_del_resource_returns_200_() {

	    when().
	            delete(baseURI+"/delete/1").
	    then().assertThat().statusCode(200);
	            

	}
	
     @Test
	 public void  GetEmployee(){
    	 
    	   // @formatter:off
         Response response = given().
                             when().
                                 get("/employees").
                             then().
                                 extract().
                                     response();
         
         int validId = response.jsonPath().getInt("data.id[0]");
         String name = response.jsonPath().getString("data.employee_name[0]");
    	 
    Response resp=	 given().
	        when().
	                get("/employee/"+validId).
	        then().extract().response();   
    
    JsonPath jsonPathEvaluator = resp.jsonPath(); 
 
 assertThat(
 jsonPathEvaluator.get("data.id").equals(validId));
 assertThat(jsonPathEvaluator.get("data.employee_name").equals(name));
    
     }
          
       
	
     @Test 
 	public void
 	example_del_resource_returns_200_Msg_vali() {

    	 Response resp=	 given().when().
 	            delete(baseURI+"/delete/1").then().extract().response();
    	 JsonPath jsonPathEvaluator = resp.jsonPath(); 
    	 
    	 assertThat(jsonPathEvaluator.get("message").equals("Successfully! Record has been deleted"));
    	 
 	            

 	}
	

}
