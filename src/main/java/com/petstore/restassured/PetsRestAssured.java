package com.petstore.restassured;

import com.petstore.dto.Pet;
import com.petstore.dto.Status;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.petstore.Base.BASE_URL;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;


public class PetsRestAssured {
    public static String endPoints = BASE_URL + "/pet";
    private RequestSpecification request;
    public Map defaultHeaders = new HashMap<>();
    RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();

    public PetsRestAssured() {
       // RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(BASE_URL);
        requestSpecBuilder.setContentType(ContentType.JSON);
        Map defaultHeaders = new HashMap<>();
        defaultHeaders.put("accept", "application/json");
        requestSpecBuilder.addHeaders((defaultHeaders));
        requestSpecBuilder.log(LogDetail.ALL);
        request = requestSpecBuilder.build();
    }

    public Pet addPet(Pet pet,Map headers) {
        requestSpecBuilder.addHeaders((headers));
        return given(request)
                .body(pet).post(endPoints).as(Pet.class);
    }

    public Response addPetStatus(Pet jsonData) {
        RestAssured.baseURI  = endPoints;
        return given()
                .contentType("application/json").
                        body(jsonData).
                        when().
                        post("");
             }

    /* public List<Pet> getStatus(Status status) {
        return given(request)
                .queryParam("status", Status.available1.toString())
                .get(endPoints + "/findByStatus")
                .then().log().all()
                .extract().response()
                .jsonPath().getList("", Pet.class);
    } */

    public void deletePet(String id) {
        given(request)
                .pathParam("petId",id)
                .delete(endPoints + "/{petId}");
    }

    public void verifyPetDeleted(String id) {
        given(request)
                .pathParam("petId",id)
                .get(endPoints + "/{petId}")
                .then()
                .body(containsString("Pet not found"));
    }

    public Pet getPet(String id) {
        return given(request)
                .pathParam("petId",id)
                .get(endPoints + "/{petId}").as(Pet.class);
    }

    public Pet updatePet(Pet pet) {
        return given(request)
                .body(pet)
                .put(endPoints).as(Pet.class);
    }


}
