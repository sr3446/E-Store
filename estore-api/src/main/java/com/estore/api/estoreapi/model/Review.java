package com.estore.api.estoreapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Review {
    static final String STRING_FORMAT = "Review [name=%s, review=%s]";

    @Override
    public String toString() {
        return String.format(STRING_FORMAT,name,review);
    }

    public Review(
        @JsonProperty("name") String name,
        @JsonProperty("review") String review
    ){
        this.name = name;
        this.review = review;
    }

    @JsonProperty("name") private String name;
    public void setname(String name){this.name = name;}
    public String getname(){return this.name;}

    @JsonProperty("review") private String review;
    public void setAnimal(String review){this.review = review;}
    public String getAnimal(){return this.review;}
}
