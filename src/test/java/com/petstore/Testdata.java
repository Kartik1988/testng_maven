package com.petstore;

public enum Testdata {

    Test1("PetName"),
    U1111("UpdatePetId"),
    CatTest("CatagoryTestName"),
    TagTestName1("TagName1"),
    TagTestName2("TagNAme2");

    private String value;

    Testdata(String value) {
        this.value = value;
    }


}