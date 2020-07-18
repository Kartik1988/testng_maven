package com.petstore;

import com.petstore.dto.Category;
import com.petstore.dto.Pet;
import com.petstore.Base;
import com.petstore.dto.Status;
import com.petstore.dto.Tag;
import com.petstore.restassured.PetsRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.testng.Assert.assertEquals;

public class PetTests {

    PetsRestAssured petsRestAssured;
    PetBuilder petbuilder;
    RequestSpecification res;
    public String id;
    public String headerName;
    public String headerValue;
    public Pet petResponseadd;
    public Map headers = new HashMap<>();
    Response response;

    @BeforeClass
    public void beforeClass() {
        petsRestAssured = new PetsRestAssured();
        petbuilder = new PetBuilder();
    }

    @Test(priority = 0)
    public void verifyAdd() {
        petbuilder.pet.setName(Testdata.Test1.toString());
        headers.put("accept", "application/json");
        petResponseadd = petsRestAssured.addPet(petbuilder.pet,headers);
        // assertThat(petResponseadd, is(samePropertyValuesAs(petbuilder.pet)));
        assertThat(petResponseadd.getName().toString(),is(equalTo(Testdata.Test1.toString())));
        id = petResponseadd.getId().toString();
    }

    @Test(priority = 1)

    public void verifyAddCheckStatus()
    {
        petbuilder.pet.setName(Testdata.Test1.toString());
        response = petsRestAssured.addPetStatus(petbuilder.pet);
        int status = response.getStatusCode();
        assertThat(status,is(equalTo(200)));
        System.out.println(" this is the " + status);
    }
    @Test(priority = 6)
    public void verifySchema()
    {
        petbuilder.pet.setName("Add");
        response = petsRestAssured.addPetStatus(petbuilder.pet);
        response.then().assertThat().body(matchesJsonSchemaInClasspath("Schema.json"));
    }

    @Test(priority = 2)
    public void verifyNewPet() {
        Pet petResponse = petsRestAssured.getPet(id);
        //assertThat(petResponse, is(samePropertyValuesAs(petResponseadd)));
        assertEquals(petResponse.getCategory().getId(),petResponseadd.getCategory().getId());
        for(int i = 0;i <petResponse.getTags().size();i++ ) {
            assertEquals(petResponse.getTags().get(i).getId(), petResponseadd.getTags().get(i).getId());
            //System.out.println("This is the Response " + petResponseadd.getTags().get(i).getId());
        }
    }

    @Test(priority = 3)
    public void updatePet() {
        petbuilder = new PetBuilder();
        petbuilder.pet.setName("Update of my pet");
        petbuilder.pet.setId("11111");
        Pet petResponse = petsRestAssured.updatePet(petbuilder.pet);
        // assertThat(petResponse, is(samePropertyValuesAs(petbuilder.pet)));
    }

    @Test(priority = 4)
    public void verifyUpdate() {
        petbuilder.pet.setName("Update of my pet");
        petbuilder.pet.setId("11111");
        Pet petResponse = petsRestAssured.getPet("11111");
        assertEquals(petResponse.getId().toString(),"11111");
        assertEquals(petResponse.getName().toString(),"Update of my pet");
    }

    @Test(priority = 5)
    public void deletePetAndDoCheck() {
        petsRestAssured.deletePet(id);
        petsRestAssured.verifyPetDeleted(id);
    }
}
