package com.estore.api.estoreapi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.text.DecimalFormat;

import com.estore.api.estoreapi.model.Animal;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AnimalTest {

    @Test
    public void testConstructor() throws IOException{
        try{
            int id = 0021;
            String species = "Chicken";
            double price = 10.11;
            int stock  = 123;
            String description = "A bird that doesn't fly";
            String imageUrl = "Gallus gallus domesticus";
            Animal animal = new Animal(id, species, description, price, stock, imageUrl, null);
            assertEquals(0021,(Integer)animal.getId());
            assertEquals(10.11,(Double)animal.getPrice());
            assertEquals(123,(Integer)animal.getQuantity());
            assertEquals("A bird that doesn't fly",(String)animal.getDescription());
            assertEquals("Chicken",(String)animal.getSpecies());
            assertEquals("Gallus gallus domesticus",(String)animal.getImageUrl());
        }
        catch(Exception e){
            System.err.println("An Error occured: "+ e);
        }
    }

    @Test
    public void testGetId() throws IOException{
        try{int id = 0021;
            double price = 10.11;
            int stock  = 123;
            String description = "A bird that doesn't fly";
            String species = "Chicken";
            String imageUrl = "Gallus gallus domesticus";
            Animal animal = new Animal(id, species, description, price, stock, imageUrl, null);

            assertEquals(0021,(Integer)animal.getId());
            
        }
        catch(Exception e){
            System.err.println("An Error occured: "+ e);
        }
    }

    @Test
    public void testSetId() throws IOException{
        try{int id = 0021;
            double price = 10.11;
            int stock = 123;
            String description = "A bird that doesn't fly";
            String species = "Chicken";
            String imageUrl = "Gallus gallus domesticus";
            Animal animal = new Animal(id, species, description, price, stock, imageUrl, null);

            int new_id = 123456789;
            animal.setId(new_id);
            assertEquals(123456789, animal.getId());
            
        }
        catch(Exception e){
            System.err.println("An Error occured: "+ e);
        }
    }

    @Test
    public void testGetPrice() throws IOException{
        try{int id = 0021;
            double price = 10.11;
            int stock  = 123;
            String description = "A bird that doesn't fly";
            String species = "Chicken";
            String imageUrl = "Gallus gallus domesticus";
            Animal animal = new Animal(id, species, description, price, stock, imageUrl, null);
            
            assertEquals(10.11,(Double)animal.getPrice());
        }
        catch(Exception e){
            System.err.println("An Error occured: "+ e);
        }
    }

    @Test
    public void testSetPrice() throws IOException{
        try{int id = 0021;
            double price = 10.11;
            int stock  = 123;
            String description = "A bird that doesn't fly";
            String species = "Chicken";
            String imageUrl = "Gallus gallus domesticus";
            Animal animal = new Animal(id, species, description, price, stock, imageUrl, null);
            
            double new_price = 123.987;
            animal.setPrice(new_price);
            assertEquals(123.987,(Double)animal.getPrice());
        }
        catch(Exception e){
            System.err.println("An Error occured: "+ e);
        }
    }

    @Test
    public void testGetQuantity() throws IOException{
        try{int id = 0021;
            double price = 10.11;
            int stock  = 123;
            String description = "A bird that doesn't fly";
            String species = "Chicken";
            String imageUrl = "Gallus gallus domesticus";
            Animal animal = new Animal(id, species, description, price, stock, imageUrl, null);
            
            assertEquals(123,(Integer)animal.getQuantity());
        }
        catch(Exception e){
            System.err.println("An Error occured: "+ e);
        }
    }

    @Test
    public void testSetQuantity() throws IOException{
        try{int id = 0021;
            double price = 10.11;
            int stock  = 123;
            String description = "A bird that doesn't fly";
            String species = "Chicken";
            String imageUrl = "Gallus gallus domesticus";
            Animal animal = new Animal(id, species, description, price, stock, imageUrl, null);
            
            int new_quantity = 8646;
            animal.setQuantity(new_quantity);
            assertEquals(8646,(Integer)animal.getQuantity());
        }
        catch(Exception e){
            System.err.println("An Error occured: "+ e);
        }
    }

    @Test
    public void testGetDescription() throws IOException{
        try{int id = 0021;
            double price = 10.11;
            int stock  = 123;
            String description = "A bird that doesn't fly";
            String species = "Chicken";
            String imageUrl = "Gallus gallus domesticus";
            Animal animal = new Animal(id, species, description, price, stock, imageUrl, null);
            
            assertEquals("A bird that doesn't fly",(String)animal.getDescription());
        }
        catch(Exception e){
            System.err.println("An Error occured: "+ e);
        }
    }

    @Test
    public void testSetDescription() throws IOException{
        try{
            int id = 0021;
            double price = 10.11;
            int stock  = 123;
            String description = "A bird that doesn't fly";
            String species = "Chicken";
            String imageUrl = "Gallus gallus domesticus";
            Animal animal = new Animal(id, species, description, price, stock, imageUrl, null);
            
            String new_description = "Makes for delicoius chicken nuggets";
            animal.setDescription(new_description);
            
            assertEquals("Makes for delicoius chicken nuggets",(String)animal.getDescription());
        }
        catch(Exception e){
            System.err.println("An Error occured: "+ e);
        }
    }

    @Test
    public void testGetSpecies() throws IOException{
        try{int id = 0021;
            double price = 10.11;
            int stock  = 123;
            String description = "A bird that doesn't fly";
            String species = "Chicken";
            String imageUrl = "Gallus gallus domesticus";
            Animal animal = new Animal(id, species, description, price, stock, imageUrl, null);
            
            assertEquals("Chicken",(String)animal.getSpecies());
            
        }
        catch(Exception e){
            System.err.println("An Error occured: "+ e);
        }
    }

    @Test
    public void testSetSpecies() throws IOException{
        try{int id = 0021;
            double price = 10.11;
            int stock  = 123;
            String description = "A bird that doesn't fly";
            String species = "Chicken";
            String imageUrl = "Gallus gallus domesticus";
            Animal animal = new Animal(id, species, description, price, stock, imageUrl, null);
            
            String new_species = "Nuggets";
            animal.setSpecies(new_species);
            assertEquals("Nuggets",(String)animal.getSpecies());
        }
        catch(Exception e){
            System.err.println("An Error occured: "+ e);
        }
    }

    @Test
    public void testGetFamily() throws IOException{
        try{int id = 0021;
            double price = 10.11;
            int stock  = 123;
            String description = "A bird that doesn't fly";
            String species = "Chicken";
            String imageUrl = "Gallus gallus domesticus";
            Animal animal = new Animal(id, species, description, price, stock, imageUrl, null);
            
            assertEquals("Gallus gallus domesticus",(String)animal.getImageUrl());
        }
        catch(Exception e){
            System.err.println("An Error occured: "+ e);
        }
    }

    @Test
    public void testSetFamily() throws IOException{
        try{int id = 0021;
            double price = 10.11;
            int stock  = 123;
            String description = "A bird that doesn't fly";
            String species = "Chicken";
            String imageUrl = "Gallus gallus domesticus";
            Animal animal = new Animal(id, species, description, price, stock, imageUrl, null);
            
            String new_family = "Family of Nuggets";
            animal.setImageUrl(new_family);
            assertEquals("Family of Nuggets",(String)animal.getImageUrl());
            
        }
        catch(Exception e){
            System.err.println("An Error occured: "+ e);
        }
    }

    @Test
    public void testEquals() throws IOException{
        try{
            int id = 0021;
            double price = 10.11;
            int stock  = 123;
            String description = "A bird that doesn't fly";
            String species = "Chicken";
            String imageUrl = "Gallus gallus domesticus";
            Animal animal = new Animal(id, species, description, price, stock, imageUrl, null);
            
            Animal new_animal = new Animal(
                0021,
                "Chicken",
                "A bird that doesn't fly",
                10.11,
                123,
                "Gallus gallus domesticus",
                null
            );

            assertEquals(true, animal.equals(new_animal));
        }
        catch(Exception e){
            System.err.println("An Error occured: "+ e);
        }
    }

    @Test
    public void testclone() throws IOException{
        try{
            int id = 0021;
            double price = 10.11;
            int stock  = 123;
            String description = "A bird that doesn't fly";
            String species = "Chicken";
            String imageUrl = "Gallus gallus domesticus";
            Animal animal = new Animal(id, species, description, price, stock, imageUrl, null);
            
            Animal new_animal = animal.clone();
            assertEquals(true, animal.equals(new_animal));
        }
        catch(Exception e){
            System.err.println("An Error occured: "+ e);
        }
    }

    @Test
    public void testToString() throws IOException{
        try{
            int id = 0021;
            double price = 10.11;
            int stock  = 123;
            String description = "A bird that doesn't fly";
            String species = "Chicken";
            String imageUrl = "Gallus gallus domesticus";
            Animal animal = new Animal(id, species, description, price, stock, imageUrl, null);
            
            /*All decimal will be in 0.000000 place I have to find out how I can format the price value to only 2 places */
            
            // toString Format: "Animal [id=%d, price=%f, quantity=%d, description=%s, species=%s, family=%s]"
            String check = "Animal [id=17, species=Chicken, description=A bird that doesn't fly, price=10.110000, stock=123, imageUrl=Gallus gallus domesticus]";
            assertEquals(check,animal.toString());
        }
        catch(Exception e){
            System.err.println("An Error occured: "+ e);
        }
    }

}
