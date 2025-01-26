package com.estore.api.estoreapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.estore.api.estoreapi.model.Animal;
import com.estore.api.estoreapi.model.User;

@Component
public class EstoreFileDAO implements EstoreDAO {

    private Map<Integer, Animal> animals_cache;
    private static int animal_nextId;
    private String animals_filename;

    
    private Map<Integer, User> users_cache;
    private static int user_nextId;
    private String users_filename;
    
    private ObjectMapper objectMapper;


    public EstoreFileDAO(
        @Value("${products.file}") String animals_filename, 
        @Value("${users.file}") String users_filename, 
        ObjectMapper objectMapper
    ) throws IOException{
        this.animals_filename = animals_filename;
        this.users_filename = users_filename;
        this.objectMapper = objectMapper;
        load();
    }

    private synchronized static int animal_nextId() {
        animal_nextId++;
        return animal_nextId -1;
    }
    private synchronized static int user_nextId() {
        user_nextId++;
        return user_nextId -1;
    }

    private boolean load() throws IOException {

        System.out.println("Loading Animal data from file: " + animals_filename);
        System.out.println("Loading User data from file: " + users_filename);

        ////ANIMALS
        animals_cache = new TreeMap<>();
        animal_nextId = 0;
        Animal[] allAnimals = objectMapper.readValue(
            new File(animals_filename), 
            Animal[].class
        );
        
        //add animals to treemap
        for(Animal animal : allAnimals){
            animals_cache.put(animal.getId(), animal);
            if(animal.getId() > animal_nextId)
                animal_nextId = animal.getId() + 1;
        }

        ////USERS
        users_cache = new TreeMap<>();
        user_nextId = 0;
        User[] allUsers = objectMapper.readValue(
            new File(users_filename),
            User[].class
        );

        for(User user : allUsers){
            users_cache.put(user.getId(), user);
            if(user.getId() > user_nextId)
                user_nextId = user.getId() + 1;
        }

        return true;
    }
    private boolean save() throws IOException {
        objectMapper.writeValue(new File(animals_filename), getAnimalsArray());
        objectMapper.writeValue(new File(users_filename), getUsers());
        return true;
    }

    private Animal[] getAnimalsArray(){
        ArrayList<Animal> animals = new ArrayList<>();

        for (Animal a : animals_cache.values()) {
                animals.add(a);
        }

        Animal[] animalsArray = new Animal[animals.size()];
        animals.toArray(animalsArray);
        return animalsArray;
    }
    private Animal[] getAnimalsArray(String nameStartsWith){
        ArrayList<Animal> animals = new ArrayList<>();

        for (Animal a : animals_cache.values()) {
            if (nameStartsWith == null || a.getSpecies().toLowerCase().startsWith(nameStartsWith.toLowerCase())){
                animals.add(a);
            }
        }

        Animal[] animalsArray = new Animal[animals.size()];
        animals.toArray(animalsArray);
        return animalsArray;
    }
    //TODO


    ///////INTERFACE INHERITED
    //ANIMALS
    @Override
    public Animal[] getAnimals() throws IOException {
        synchronized(animals_cache) {
            return getAnimalsArray();
        }    
    }

    @Override
    public Animal[] findAnimals(String nameContainsText) throws IOException {
        return getAnimalsArray(nameContainsText);
    }

    @Override
    public Animal getAnimal(int id) throws IOException {
        if(animals_cache.containsKey(id)){
            return animals_cache.get(id);
        }else{
            return null;
        }
    }

    @Override
    public Animal createAnimal(Animal animal) throws IOException {
        var newAnimal = animal.clone();
        newAnimal.setId(animal_nextId());
        animals_cache.put(newAnimal.getId(), newAnimal);
        save();
        return newAnimal;
    }

    @Override
    public Animal updateAnimal(Animal newAnimal) throws IOException {
        if(!animals_cache.containsKey(newAnimal.getId()))
            return null;
        
        animals_cache.put(newAnimal.getId(), newAnimal);
        save();
        return newAnimal;
    }

    @Override
    public boolean deleteAnimal(int id) throws IOException {
        if(animals_cache.containsKey(id)){
            animals_cache.remove(id);
            return save();
        }
        else
            return false;
    }

    //USERS
    @Override
    public User getUser(int id) throws IOException {
        if(users_cache.containsKey(id)){
            return users_cache.get(id);
        }else{
            return null;
        }
    }

    @Override
    public User[] getUsers() throws IOException {
        synchronized(users_cache) {
            ArrayList<User> users = new ArrayList<>();

            for (User a : users_cache.values()) {
                    users.add(a);
            }

            User[] usersArray = new User[users.size()];
            users.toArray(usersArray);
            return usersArray;
        }    
    }

    @Override
    public User findUser(String name) throws IOException {
        for (User a : users_cache.values()) {
            if (a.getName().equals(name)) {
                return a;
            }
        }

        return null;
    }

    @Override
    public User createUser(User user) throws IOException {
        var newUser = user.clone();
        newUser.setId(user_nextId());
        users_cache.put(newUser.getId(), newUser);
        save();
        return newUser;
    }

    @Override
    public User updateUser(User user) throws IOException {
        if(!users_cache.containsKey(user.getId()))
            return null;
        
        users_cache.put(user.getId(), user);
        save();
        return user;
    }

    @Override
    public boolean deleteUser(int id) throws IOException {
        if(users_cache.containsKey(id)){
            users_cache.remove(id);
            return save();
        }
        else
            return false;
    }
    
}
