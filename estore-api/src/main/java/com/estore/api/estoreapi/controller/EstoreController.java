package com.estore.api.estoreapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.estore.api.estoreapi.model.Animal;
import com.estore.api.estoreapi.persistence.EstoreDAO;

@RestController
@RequestMapping("animals")  //this means i need the animals thing http://localhost:8080/animals
public class EstoreController {
    private static final Logger LOG = Logger.getLogger(EstoreController.class.getName());
    private EstoreDAO estoreDao;

    /**
     * Creates a REST API controller to reponds to requests
     * 
     * @param AnimalDao The {@link AnimalDAO Animal Data Access Object} to perform CRUD operations
     * <br>
     * This dependency is injected by the Spring Framework
     */
    public EstoreController(EstoreDAO estoreDAO){
        this.estoreDao = estoreDAO;
    }

    /**
     * Responds to the GET request for a {@linkplain Animal animal} for the given id
     * 
     * @param id The id used to locate the {@link Animal animal}
     * 
     * @return ResponseEntity with {@link Animal animal} object and HTTP status of OK if found<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("/{id}")    //this means i need the number after the animals thing http://localhost:8080/animals/0
    public ResponseEntity<Animal> getAnimal(@PathVariable int id) {
        LOG.info("GET /animals/" + id);
        try {
            Animal animal = estoreDao.getAnimal(id);
            if (animal != null)
                return new ResponseEntity<Animal>(animal,HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Responds to the GET request for all {@linkplain Animal Animales}
     * 
     * @return ResponseEntity with array of {@link Animal Animal} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("")
    public ResponseEntity<Animal[]> getAnimals() {
        LOG.info("GET /animals");
        try{
            Animal[] animals = estoreDao.getAnimals();
            return new ResponseEntity<Animal[]>(animals, HttpStatus.OK);
        }catch(IOException e) {
           LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Responds to the GET request for all {@linkplain Animal animals} whose name contains
     * the text in name
     * 
     * @param name The name parameter which contains the text used to find the {@link Animal animals}
     * 
     * @return ResponseEntity with array of {@link Animal animal} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     * <p>
     * Example: Find all Animales that contain the text "ma"
     * GET http://localhost:8080/animales/?name=ma
     */
    @GetMapping("/")
    public ResponseEntity<Animal[]> searchAnimals(@RequestParam String name) {
        LOG.info("GET /animals/?name="+name);

        try{
            Animal[] animals = estoreDao.findAnimals(name);
            return new ResponseEntity<Animal[]>(animals, HttpStatus.OK);

        }catch(IOException e){
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Creates a {@linkplain Animal animal} with the provided Animal object
     * 
     * @param animal - The {@link Animal animals} to create
     * 
     * @return ResponseEntity with created {@link Animal animal} object and HTTP status of CREATED<br>
     * ResponseEntity with HTTP status of CONFLICT if {@link Animal animals} object already exists<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PostMapping("")
    public ResponseEntity<Animal> createAnimal(@RequestBody Animal animal) {
        LOG.info("POST /animals " + animal);

        try {
            estoreDao.createAnimal(animal);
            return new ResponseEntity<Animal>(animal, HttpStatus.CREATED);
        } catch (IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Updates the {@linkplain Animal animal} with the provided {@linkplain Animal animal} object, if it exists
     * 
     * @param animal The {@link Animal animal} to update
     * 
     * @return ResponseEntity with updated {@link Animal animal} object and HTTP status of OK if updated<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PutMapping("")
    public ResponseEntity<Animal> updateAnimal(@RequestBody Animal animal) {
        LOG.info("PUT /animals " + animal);

        try {
            if(null != estoreDao.updateAnimal(animal)){
                return new ResponseEntity<Animal>(animal, HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Deletes a {@linkplain Animal animal} with the given id
     * 
     * @param id The id of the {@link Animal animal} to deleted
     * 
     * @return ResponseEntity HTTP status of OK if deleted<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Animal> deleteAnimal(@PathVariable int id) {
        LOG.info("DELETE /animals/" + id);

        try {
            if(estoreDao.deleteAnimal(id)){
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
