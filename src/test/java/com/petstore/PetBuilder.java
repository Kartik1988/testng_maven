package com.petstore;

import com.petstore.dto.Category;
import com.petstore.dto.Pet;
import com.petstore.dto.Status;
import com.petstore.dto.Tag;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Arrays;

public class  PetBuilder {


    public static final String PHOTO_URL = "https://homepages.cae.wisc.edu/~ece533/images/airplane.png";
    public static String Id = RandomStringUtils.randomNumeric(15);
    public static int IdInt = Integer.valueOf(RandomStringUtils.randomNumeric(3));
    Pet pet = new Pet.Builder1()
            .withId(Id)
            .withName(Testdata.Test1.toString())
            .withPhotoUrls(Arrays.asList(PHOTO_URL, PHOTO_URL))
           .withStatus(Status.available)
            .withTags(Arrays.asList(new Tag(IdInt, Testdata.TagTestName1.toString()), new Tag(IdInt,Testdata.TagTestName2.toString())))
            .inCategory(new Category(IdInt, Testdata.CatTest.toString())).build();
}
