package com.estore.api.estoreapi.persistence;

import java.io.IOException;
import com.estore.api.estoreapi.model.Animal;
import com.estore.api.estoreapi.model.User;


public interface EstoreDAO {
    //for browsing and searching
    Animal[] getAnimals() throws IOException;
    Animal[] findAnimals(String nameContainsText) throws IOException;

    Animal getAnimal(int id) throws IOException;
    Animal createAnimal(Animal animal) throws IOException;
    Animal updateAnimal(Animal newAnimal) throws IOException;
    boolean deleteAnimal(int id) throws IOException;


    User[] getUsers() throws IOException;
    User findUser(String username) throws IOException;

    User getUser(int id) throws IOException;
    User createUser(User user) throws IOException;
    User updateUser(User user) throws IOException;
    boolean deleteUser(int id) throws IOException;
}
