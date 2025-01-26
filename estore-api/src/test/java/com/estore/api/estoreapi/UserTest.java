package com.estore.api.estoreapi;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.estore.api.estoreapi.model.Animal;
import com.estore.api.estoreapi.model.CartItem;
import com.estore.api.estoreapi.model.User;
import com.estore.api.estoreapi.persistence.EstoreFileDAO;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserTest {
    
    @Test
    public void testCtor() {
        try{
            String name = "user";
            String password = "pass";
            User currUser = new User(0, name, password, new ArrayList<CartItem>(), new ArrayList<>());
            CartItem item0 = new CartItem(1, 1);
            CartItem item1 = new CartItem(2, 2);
            currUser.getCart().add(item0);
            currUser.getCart().add(item1);
            assertEquals("user", (String)currUser.getName());
            assertEquals(item0, currUser.getCart().get(0));
            assertEquals(item1, currUser.getCart().get(1));
            
        }
        catch(Exception e){
            System.err.println("An Error occured: "+ e);
        }
    }
    @Test
    public void testName() {
        try{
            String name = "user";
            String password = "pass";
            User currUser = new User(0, name, password, new ArrayList<CartItem>(), new ArrayList<>());
            assertEquals(name, currUser.getName());
            currUser.setName("newName");
            assertEquals("newName", currUser.getName());
            CartItem newItem = new CartItem(1, 1);
            currUser.getCart().add(newItem);
            assertEquals(newItem, currUser.getCart().get(0));
            currUser.setCart(new ArrayList<CartItem>());
            CartItem newCartItem = new CartItem(2,3);
            currUser.getCart().add(newCartItem);
            assertEquals(newCartItem, currUser.getCart().get(0));
        }
        catch(Exception e){
            System.err.println("An Error occured: "+ e);
        }
    }
    @Test
    public void testToString() {
        try{
            //TODO add cart functionality
            int id = 0;
            String name = "name";
            String password = "pass";
            User currUser = new User(id, name, password, null, null);
            assertEquals("User [id="+id+", name="+name+", password="+password+"]", currUser.toString());    
        }
        catch(Exception e){
            System.err.println("An Error occured: "+ e);
        }
    }
}
