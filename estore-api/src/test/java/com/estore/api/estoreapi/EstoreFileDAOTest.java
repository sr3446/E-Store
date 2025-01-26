package com.estore.api.estoreapi;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;


import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.estore.api.estoreapi.persistence.*;
import com.estore.api.estoreapi.model.*;
import com.estore.api.estoreapi.controller.*;



@Tag("Persistence-tier")
public class EstoreFileDAOTest {

    EstoreFileDAO estoreFileDAO;
    ObjectMapper mockObjectMapper;

    Animal[] testAnimals;
    User[] testUsers;


    @BeforeEach
    public void setupAnimalFileDAO() throws IOException {
        mockObjectMapper = mock(ObjectMapper.class);

        testUsers = new User[3];
        testUsers[0] = new User(0, "Sammy Corwin", "1234", new ArrayList<>(),new ArrayList<>());
        testUsers[1] = new User(1, "Jack Corey", "password", new ArrayList<>(),new ArrayList<>());
        testUsers[2] = new User(2, "Alex Black", "abcdefg", new ArrayList<>(),new ArrayList<>());

        when(mockObjectMapper
            .readValue(new File("user_doesnt_matter.txt"),User[].class))
            .thenReturn(testUsers);

        testAnimals = new Animal[3];
        testAnimals[0] = new Animal(0,"Human", "Just like us", 10, 812381, "Homo Sapien", null);
        testAnimals[1] = new Animal(1,"Human2", "Literal alien", 11, 812381, "Homo Sapien", null);
        testAnimals[2] = new Animal(2,"Ice monster", "From antarctica", 11, 3, "Homo Icemonsterus", null);

        when(mockObjectMapper
            .readValue(new File("animal_doesnt_matter.txt"),Animal[].class))
            .thenReturn(testAnimals);

        
        estoreFileDAO = new EstoreFileDAO("animal_doesnt_matter.txt","user_doesnt_matter.txt", mockObjectMapper);    //calls load
    }

    //Animal
    @Test
    public void testGetAnimals() {
        try {
            var animals = estoreFileDAO.getAnimals();
            
            assertEquals(testAnimals[0], animals[0]);
            assertEquals(testAnimals[1], animals[1]);
            assertEquals(testAnimals[2], animals[2]);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testFindAnimals() {
        try {
            var animals = estoreFileDAO.findAnimals("Hu");
            
            assertEquals(testAnimals[0], animals[0]);
            assertEquals(testAnimals[1], animals[1]);

        } catch (IOException e) {
            e.printStackTrace();
        }   
    }
    @Test
    public void testGetAnimal() {
        try {
            assertEquals(testAnimals[0], estoreFileDAO.getAnimal(0));
            assertEquals(testAnimals[1], estoreFileDAO.getAnimal(1));
            assertEquals(testAnimals[2], estoreFileDAO.getAnimal(2));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testDeleteAnimal() {
        try {
            assertTrue(estoreFileDAO.deleteAnimal(0));
            assertNull(estoreFileDAO.getAnimal(0));            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testCreateAnimal() {
        try {
            Animal new_animal = estoreFileDAO.createAnimal(new Animal(3, "Dog", "Common pet", 1000, 150, "Wolfius Dogius", null));
            assertNotNull(new_animal);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testUpdateAnimal() {
        try {
            Animal animal = estoreFileDAO.updateAnimal(new Animal(2, "Dog", "Common pet", 1000, 150, "Wolfius Dogius", null));
            assertNotEquals(testAnimals[0], animal);
            assertEquals(new Animal(2, "Dog", "Common pet", 1000, 150, "Wolfius Dogius", null), animal);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //User
    @Test
    public void testGetUser() {
        try {
            var user = estoreFileDAO.getUsers();

            assertEquals(testUsers[0], user[0]);
            assertEquals(testUsers[1], user[1]);
            assertEquals(testUsers[2], user[2]);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testFindUsers() {
        try {
            var user = estoreFileDAO.findUser("Sammy Corwin");
            
            assertEquals(testUsers[0], user);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testDeleteUser() {
        try {
            assertTrue(estoreFileDAO.deleteUser(0));
            assertNull(estoreFileDAO.getUser(0));           
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testCreateUser() {
        try {
            User new_user = estoreFileDAO.createUser(new User(3, "Zach LastName", "lmnopqrs", new ArrayList<>(), new ArrayList<>()));
            assertNotNull(new_user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testUpdateUser() {
        try {
            User user = estoreFileDAO.updateUser(new User(0, "Zach LastName", "lmnopqrs", new ArrayList<>(), new ArrayList<>()));
            assertNotEquals(testUsers[0], user);
            assertEquals(new User(0, "Zach LastName", "lmnopqrs", new ArrayList<>(), new ArrayList<>()).toString(), user.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // @Test
    // public void testGetAnimalsArray() {}
    // @Test
    // public void testGetAnimalsArray(String nameContains) {}

    // @Test
    // public void testSaveException() {}
    // @Test
    // public void testGetAnimalNotFound() {}
    // @Test
    // public void testDeleteAnimalNotFound() {}
    // @Test
    // public void testUpdateAnimalNotFound() {}
    // @Test
    // public void testConstructorException() throws IOException {
    //     // Setup
    //     ObjectMapper mockObjectMapper = mock(ObjectMapper.class);
        
    //     doThrow(new IOException()).when(mockObjectMapper).readValue(new File("doesnt_matter.txt"),Animal[].class);
        
    //     // Invoke & Analyze
    //     assertThrows(IOException.class,() -> new EstoreFileDAO("doesnt_matter.txt",mockObjectMapper),"IOException not thrown");
    // }
}
