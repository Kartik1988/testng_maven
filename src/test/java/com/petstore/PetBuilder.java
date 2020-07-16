package com.petstore;

import com.petstore.dto.Category;
import com.petstore.dto.Pet;
import com.petstore.dto.Status;
import com.petstore.dto.Tag;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Arrays;
import com.petstore.dto.Pet;

public class  PetBuilder {


    public static final String PHOTO_URL = "https://homepages.cae.wisc.edu/~ece533/images/airplane.png";


    testdata test1;
    Pet pet = new Pet.Builder1()
            .withId(RandomStringUtils.randomNumeric(15))
            .withName("test")
            .withPhotoUrls(Arrays.asList(PHOTO_URL, PHOTO_URL))
           .withStatus(Status.available)
            // .withTags(Collections.singletonList(new Tag(101, "german shephard")))
            .withTags(Arrays.asList(new Tag(101, "german shephard"), new Tag(102, "test")))
            .inCategory(new Category(1010, "dogs")).build();
}
