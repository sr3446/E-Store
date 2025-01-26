package com.estore.api.estoreapi.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    
    static final String STRING_FORMAT = "User [id=%d, name=%s, password=%s]";  //add whshlist
    

    @Override
    public String toString() {
        return String.format(STRING_FORMAT,id,name,password);
    }

    public User(
        @JsonProperty("id") int id,
        @JsonProperty("name") String name,
        @JsonProperty("password") String password,
        @JsonProperty("cart") ArrayList<CartItem> cart,
        @JsonProperty("wishlist") ArrayList<CartItem> wishlist
    ){
        this.id = id;
        this.name = name;
        this.password = password;
        this.cart = cart;
        this.wishlist = wishlist;
    }

    @JsonProperty("id") private int id;
    public int getId(){return id;}
    public void setId(int id){this.id = id;}

    @JsonProperty("name") private String name;
    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    @JsonProperty("password") private String password;
    public String getPassword(){return password;}
    public void setPassword(String password){this.password = password;}

    @JsonProperty("cart") private ArrayList<CartItem> cart;
    public ArrayList<CartItem> getCart(){return cart;}
    public void setCart(ArrayList<CartItem> cart){this.cart = cart;}

    @JsonProperty("wishlist") private ArrayList<CartItem> wishlist;
    public ArrayList<CartItem> getWishlist(){return wishlist;}
    public void setWishlist(ArrayList<CartItem> wishlist){this.wishlist = wishlist;}

    public User clone(){
        return new User(id, name, password, cart, wishlist);
    }
}