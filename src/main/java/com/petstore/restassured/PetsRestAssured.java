package com.petstore.restassured;

import com.petstore.dto.Pet;
import com.petstore.dto.Status;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static com.petstore.Base.*;
import static com.petstore.Base.BASE_URL;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;


public class PetsRestAssured {
    public static String endPoints = BASE_URL + "/pet";
    private RequestSpecification request;

    public PetsRestAssured() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(BASE_URL);
        requestSpecBuilder.setContentType(ContentType.JSON);
        requestSpecBuilder.addHeader("accept","application/json");
        requestSpecBuilder.log(LogDetail.ALL);
        request = requestSpecBuilder.build();
    }

    public Pet addPet(Pet pet) {
        return given(request)
                .body(pet)
                .post(endPoints).as(Pet.class);

    }

    public List<Pet> getStatus(Status status) {
        return given(request)
                .queryParam("status", Status.available.toString())
                .get(endPoints + "/findByStatus")
                .then().log().all()
                .extract().body()
                .jsonPath().getList("", Pet.class);

    }

    public void deletePet(Pet pet) {
        given(request)
                .pathParam("petId", pet.getId())
                .delete(endPoints + "/{petId}");
    }

    public void verifyPetDeleted(Pet pet) {
        given(request)
                .pathParam("petId", pet.getId())
                .get(endPoints + "/{petId}")
                .then()
                .body(containsString("Pet not found"));
    }

    public Pet getPet(Pet pet) {
        return given(request)
                .pathParam("petId", pet.getId())
                .get(endPoints + "/{petId}").as(Pet.class);
    }

    public Pet updatePet(Pet pet) {
        return given(request)
                .body(pet)
                .put(endPoints).as(Pet.class);
    }


}
