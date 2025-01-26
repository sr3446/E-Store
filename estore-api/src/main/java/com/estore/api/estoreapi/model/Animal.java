package com.estore.api.estoreapi.model;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Animal {

    // Package private for tests
    static final String STRING_FORMAT = "Animal [id=%d, species=%s, description=%s, price=%f, stock=%d, imageUrl=%s]";

    @Override
    public String toString() {
        return String.format(STRING_FORMAT,id,species,description,price,stock,imageUrl);
    }

    @JsonProperty("id") private int id;
    public int getId(){return id;}
    public void setId(int id){this.id = id;}

    @JsonProperty("species") private String species;
    public String getSpecies(){return species;}
    public void setSpecies(String species){this.species = species;}

    @JsonProperty("description") private String description;
    public String getDescription(){return description;}
    public void setDescription(String description){this.description = description;}

    @JsonProperty("price") private double price;
    public double getPrice(){return price;}
    public void setPrice(double price){this.price = price;}

    @JsonProperty("stock") private int stock;
    public int getQuantity(){return stock;}
    public void setQuantity(int stock){this.stock = stock;}    

    @JsonProperty("imageUrl") private String imageUrl;
    public String getImageUrl(){return imageUrl;}
    public void setImageUrl(String imageUrl){this.imageUrl = imageUrl;}
    
    @JsonProperty("reviews") private ArrayList<Review> reviews;
    public ArrayList<Review> getReviews(){return reviews;}
    public void setReviews(ArrayList<Review> reviews){this.reviews = reviews;}


    public Animal(
        @JsonProperty("id") int id,
    
        @JsonProperty("species") String species,
        @JsonProperty("description") String description,

        @JsonProperty("price")  double price,
        @JsonProperty("stock") int stock,
        
        @JsonProperty("imageUrl") String imageUrl,

        @JsonProperty("reviews") ArrayList<Review> reviews
    ){
        this.id = id;
        
        this.species = species;
        this.description = description;

        this.price = price;
        this.stock = stock;

        this.imageUrl = imageUrl;

        this.reviews = reviews;
    }

    @Override
    public boolean equals(Object other){

        if(this == other) return true;

        if (!(other instanceof Animal)) {
            return false;
        }
        
        Animal other_animal = (Animal) other;

        return (
            this.id == other_animal.id &&
            this.species == other_animal.species &&
            this.description == other_animal.description &&
            this.price == other_animal.price &&
            this.stock == other_animal.stock &&
            this.imageUrl == other_animal.imageUrl
        );
    }

    public Animal clone(){
        return new Animal(id, species, description, price, stock, imageUrl, (ArrayList<Review>) reviews.clone());
    }
}
