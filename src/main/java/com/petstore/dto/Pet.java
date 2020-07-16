package com.petstore.dto;

import javafx.util.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;




public class Pet {

    private String id;
    private Category category;
    private String name;
    private List<String> photoUrls = new ArrayList<>();
    private List<Tag> tags = new ArrayList<>();
    private Status status;
    private Pet() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    //The toString() method returns the string representation of the object.
// If you print any object, java compiler internally invokes the toString() method on the object
    /* @Override
    public String toString() {
        return "Pet{" +
                "id='" + id + '\'' +
                ", category=" + category +
                ", name='" + name + '\'' +
                ", photoUrls=" + photoUrls +
                ", tags=" + tags +
                ", status=" + status +
                '}';
    }
    //The equal method is defined into object class,so we need to inherited its from object class
 /*   @Override
    public boolean equals(Object o) {
        if (this == o) { //Checking the same object inour case
            return true;
        }
        if (o == null || getClass() != o.getClass()) {//ifthis object and incoming object has different type then false
            return false;
        }
        Pet pet = (Pet) o;//Type casting the object//one object reference to type cast into other object
        return Objects.equals(this.id, pet.id) &&
                Objects.equals(this.category, pet.category) &&
                Objects.equals(this.name, pet.name) &&
                Objects.equals(this.photoUrls, pet.photoUrls) &&
                Objects.equals(this.tags, pet.tags) &&
                Objects.equals(this.status, pet.status);
    }
*/
    public static class Builder1 extends Pet {
        private String id;
        private Category category;
        private String name;
        private List<String> photoUrls = new ArrayList<>();
        private List<Tag> tags = new ArrayList<>();
        private Status status;

        public Builder1() {

        }

        public Builder1 withId(String id) {
            this.id = id;
            return this;
        }

        public Builder1 inCategory(Category category) {
            this.category = category;
            return this;
        }

        public Builder1 withName(String name) {
            this.name = name;
            return this;
        }

        public Builder1 withPhotoUrls(List<String> photoUrls) {
            this.photoUrls = photoUrls;
            return this;
        }

        public Builder1 withTags(List<Tag> tags) {
            this.tags = tags;
            return this;
        }

        public Builder1 withStatus(Status status) {
            this.status = status;
            return this;
        }

        public Pet build() {
            Pet pet = new Pet();
            pet.id = this.id;
            pet.name = this.name;
            pet.category = this.category;
            pet.photoUrls = this.photoUrls;
            pet.tags = this.tags;
            pet.status = this.status;
            return pet;
        }


    }

}

