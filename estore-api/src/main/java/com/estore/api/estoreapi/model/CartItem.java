package com.estore.api.estoreapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CartItem {
    //ID of item
    //Amount of item in cart
    //If wishlist, default amt to 1

    static final String STRING_FORMAT = "CartItem [amount=%d, animalId=%d]";

    @Override
    public String toString() {
        return String.format(STRING_FORMAT,amount,animalId);
    }

    public CartItem(
        @JsonProperty("amount") int amount,
        @JsonProperty("animalId") int animalId
    ){
        this.amount = amount;
        this.animalId = animalId;
    }

    @JsonProperty("amount") private int amount;
    public void setAmount(int amount){this.amount = amount;}
    public int getAmount(){return this.amount;}

    @JsonProperty("animalId") private int animalId;
    public void setAnimal(int animalId){this.animalId = animalId;}
    public int getAnimal(){return this.animalId;}
}

