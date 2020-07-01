package com.petstore;

import com.petstore.dto.Category;
import com.petstore.dto.Pet;
import com.petstore.dto.Status;
import com.petstore.dto.Tag;
import com.petstore.restassured.PetsRestAssured;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.testng.Assert.assertEquals;


public class PetTests {
    private static final String PHOTO_URL = "https://homepages.cae.wisc.edu/~ece533/images/airplane.png";
    PetsRestAssured petsRestAssured;

    Pet pet = new Pet.Builder()
            .withId(RandomStringUtils.randomNumeric(15))
            .withName("Test")
            .withPhotoUrls(Collections.singletonList(PHOTO_URL))
            .withStatus(Status.available)
            .withTags(Collections.singletonList(new Tag(101, "german shephard")))
            .inCategory(new Category(1010, "dogs")).build();

    @BeforeClass
    public void beforeClass() {
        petsRestAssured = new PetsRestAssured();
    }

    @Test(priority = 0)
    public void addNewPet() {
        Pet petResponse = petsRestAssured.addPet(pet);
        assertThat(petResponse, is(samePropertyValuesAs(pet)));
        assertEquals(petResponse.getId().toString(),pet.getId().toString());
    }

    @Test(priority = 1)
    public void verifyNewPet() {
        Pet petResponse = petsRestAssured.getPet(pet);
        assertThat(petResponse, is(samePropertyValuesAs(pet)));
    }

    @Test(priority = 2)
    public void updatePet() {
        pet.setName("Update of my pet");
        Pet petResponse = petsRestAssured.updatePet(pet);
        assertThat(petResponse, is(samePropertyValuesAs(pet)));
    }

    @Test(priority = 3)
    public void verifyUpdate() {
        Pet petResponse = petsRestAssured.getPet(pet);
        assertThat(petResponse, is(samePropertyValuesAs(pet)));
    }

    @Test(priority = 4)
    public void deletePetAndDoCheck() {
        petsRestAssured.deletePet(pet);
        petsRestAssured.verifyPetDeleted(pet);
    }
}
