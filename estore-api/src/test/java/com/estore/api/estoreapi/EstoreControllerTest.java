package com.estore.api.estoreapi;

import com.estore.api.estoreapi.controller.EstoreController;
import com.estore.api.estoreapi.model.Animal;
import com.estore.api.estoreapi.model.Review;
import com.estore.api.estoreapi.model.User;
import com.estore.api.estoreapi.persistence.EstoreFileDAO;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EstoreControllerTest {

	@Autowired
	private EstoreController controller;
    ObjectMapper mockObjectMapper;

    @BeforeEach
    public void setupEstoreController() throws IOException {
        mockObjectMapper = mock(ObjectMapper.class);

        var testUsers = new User[3];
        testUsers[0] = new User(0, "Sammy Corwin", "1234", new ArrayList<>(),new ArrayList<>());
        testUsers[1] = new User(1, "Jack Corey", "password", new ArrayList<>(),new ArrayList<>());
        testUsers[2] = new User(2, "Alex Black", "abcdefg", new ArrayList<>(),new ArrayList<>());

        when(mockObjectMapper
            .readValue(new File("user_doesnt_matter.txt"),User[].class))
            .thenReturn(testUsers);

        var testAnimals = new Animal[3];
        testAnimals[0] = new Animal(0,"Human", "Just like us", 10, 812381, "Homo Sapien", null);
        testAnimals[1] = new Animal(1,"Human2", "Literal alien", 11, 812381, "Homo Sapien", null);
        testAnimals[2] = new Animal(2,"Ice monster", "From antarctica", 11, 3, "Homo Icemonsterus", null);

        when(mockObjectMapper
            .readValue(new File("animal_doesnt_matter.txt"),Animal[].class))
            .thenReturn(testAnimals);

        
        var estoreFileDAO = new EstoreFileDAO("animal_doesnt_matter.txt","user_doesnt_matter.txt", mockObjectMapper);    //calls load
        this.controller = new EstoreController(estoreFileDAO);
    }
	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

    @Test
    public void testGetAnimal() {
        var response = controller.getAnimal(0);
        HttpStatus statusCode = response.getStatusCode();
        Animal body = response.getBody();

        assertEquals(HttpStatus.OK, statusCode);
        try{
            assertEquals("Human", body.getSpecies());
            assertEquals("Just like us",body.getDescription());
            assertEquals(10,body.getPrice());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testGetAnimalNotFound() {
        var response = controller.getAnimal(100);
        HttpStatus statusCode = response.getStatusCode();
        Animal body = response.getBody();

        assertEquals(HttpStatus.NOT_FOUND, statusCode);
        assertEquals(null, body);
    }

    @Test
    public void testGetAnimals() {
        var response = controller.getAnimals();
        HttpStatus statusCode = response.getStatusCode();
        Animal[] body = response.getBody();

        assertEquals(HttpStatus.OK, statusCode);
        try{
            assertEquals("Human", body[0].getSpecies());
            assertEquals("Human2", body[1].getSpecies());
            assertEquals("Ice monster", body[2].getSpecies());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteAnimal(){
        var response = controller.deleteAnimal(0);
        HttpStatus statusCode = response.getStatusCode();
        Animal body = response.getBody();

        assertEquals(HttpStatus.OK, statusCode);
        try{
            assertEquals("Human", body.getSpecies());
            assertEquals("Just like us",body.getDescription());
            assertEquals(10,body.getPrice());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteAnimalNotFound(){
        var response = controller.deleteAnimal(100);
        HttpStatus statusCode = response.getStatusCode();
        Animal body = response.getBody();

        assertEquals(HttpStatus.NOT_FOUND, statusCode);
        assertEquals(null, body);
    }

    @Test
    public void testCreateAnimal(){
        var response = controller.createAnimal(new Animal(
            3, "A", "b", 10.0, 10, "c", new ArrayList<Review>()));    
        HttpStatus statusCode = response.getStatusCode();
        Animal body = response.getBody();
        
        assertEquals(HttpStatus.CREATED, statusCode);
        try{
            assertEquals("A", body.getSpecies());
            assertEquals("b", body.getDescription());
            assertEquals(10.0,body.getPrice());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdateAnimal(){
        var response = controller.updateAnimal(new Animal(
            0, "A", "b", 10.0, 10, "c", new ArrayList<Review>()));
        HttpStatus statusCode = response.getStatusCode();
        Animal body = response.getBody();

        assertEquals(HttpStatus.CREATED, statusCode);
        try{
            assertEquals("A", body.getSpecies());
            assertEquals("b", body.getDescription());
            assertEquals(10.0,body.getPrice());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdateAnimalNotFound(){
        var response = controller.updateAnimal(new Animal(
            100, "A", "b", 10.0, 10, "c", new ArrayList<Review>()));
        HttpStatus statusCode = response.getStatusCode();
        Animal body = response.getBody();

        assertEquals(HttpStatus.NOT_FOUND, statusCode);
        assertEquals(null, body);
    }
}