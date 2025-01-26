import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Animal } from '../model/animal';
import { User } from '../model/user';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { CartItem } from '../model/cartitem';


@Injectable({
    providedIn: 'root'
})
export class AnimalService {
    private animalssUrl = 'http://localhost:8080';  // URL to web api


    httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };
    constructor(
        private http: HttpClient
    ) { }

    /**
     * this function adds animals by taking in an animal and returning the newly created animal
     * @param animal 
     * @returns new animal
     */
    addAnimals(animal: Animal): Observable<Animal> {
        return this.http.post<Animal>(this.animalssUrl+"/animals", animal, this.httpOptions);
    }

    /**
     * This function searches for animals by taking in an term which is a string and returns the searched animal
     * @param term 
     * @returns an array of animals that match the search parameters.
     */
    searchAnimals(term: string): Observable<Animal[]> {
        if (!term.trim()) {
            return of([]);
        }
        return this.http.get<Animal[]>(`${this.animalssUrl+"/animals"}/?name=${term}`).pipe(
            tap(x => x.length ?
                console.log(`found animals matching "${term}"`) :
                console.log(`no animals matching "${term}"`)),
            catchError(this.handleError<Animal[]>('searchAnimals', []))
        );
    }
    
    /**
     * This function gets all the animals
     * @returns all animals
     */
    getAnimals(): Observable<Animal[]> {
        let animal$ = this.http.get<Animal[]>(this.animalssUrl+"/animals");
        return animal$;
    }

    /**
     * This function gets a single animal by its 'id' which is a number.
     * @param id 
     * @returns animal
     */
    getAnimal(id: number): Observable<Animal> {
        let animal$ = this.http.get<Animal>(this.animalssUrl+"/animals" + "/" + id);
        return animal$;
    }
    /**
     * This function deletes an animal by its 'id' which is a number.
     * @param id
     * @returns the deleted animal
     */
    deleteAnimal(id: number): Observable<Animal>{
        console.log("Deleting: " + id);
        let animal$ = this.http.delete<Animal>(this.animalssUrl+"/animals" + "/" + id);
        
        return animal$;
    }

    /**
     * This function updates the animal by the animal being inputed
     * @param animal 
     * @returns the updated animal.
     */
    updateAnimal(animal: Animal): Observable<Animal>{
        let animal$ = this.http.put<Animal>(this.animalssUrl+"/animals", animal);
        return animal$;
    }

    /**
     * This function creates the inputed animal
     * @param animal 
     * @returns the created animal
     */
    createAnimal(animal: Animal): Observable<Animal>{
        let animal$ = this.http.post<Animal>(this.animalssUrl+"/animals", animal);
        return animal$;
    }

    /**
     * This function handles the errors.
     * @param operation 
     * @param result 
     * @returns the handled error
     */
    private handleError<T>(operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {
            console.error(error);
            console.log(`${operation} failed: ${error.message}`);
            return of(result as T);
        };
    }

    /**
     * This function returns the current user
     * @param username 
     * @returns the current user
     */
    getUser(username: String): Observable<User> {
        let user$ = this.http.get<User>(this.animalssUrl + "/users/" + username);
            user$.subscribe((user: User) => {
                localStorage.setItem("currentUser", JSON.stringify(user));
            }
        );
        return user$;
    }

    /**
     * This function adds the inputed user to the list of user.
     * @param user 
     * @returns the added user.
     */
    addUser(user: User): Observable<User> {
        let user$ = this.http.post<User>(this.animalssUrl + "/users", user, this.httpOptions);
        return user$;
    }

    /**
     * This function updates the user.
     * @param user 
     * @returns The updated user.
     */
    updateUser(user: User): Observable<User> {
        let user$ = this.http.put<User>(this.animalssUrl + "/users", user, this.httpOptions);
        user$.subscribe((user: User) => {
            localStorage.setItem("currentUser", JSON.stringify(user));
        });
        return user$;
    }
    
    /**
     * This function delets the inputed user.
     * @param userId 
     * @returns the deleted user.
     */
    deleteUser(userId: String): Observable<User> {
        let user$ = this.http.delete<User>(this.animalssUrl + "/users/" + userId);
        return user$;
    }

    //cart stuff
    addToCart(cartItem: CartItem): Observable<User>{

        let currentUser = JSON.parse(localStorage.getItem("currentUser")!);
        //check if cart exists
        if(currentUser.cart == null){
            currentUser.cart = [];
        }

        //check if item is already in cart
        let itemInCart = currentUser.cart.find((item: CartItem) => item.animalId == cartItem.animalId);
        if(itemInCart){
            itemInCart.amount += cartItem.amount;
        }else{
            currentUser.cart.push(cartItem);
        }

        let user$ = this.updateUser(currentUser);
        user$.subscribe((user: User) => {
            localStorage.setItem("currentUser", JSON.stringify(user));
        });
        return user$;

    }
    
    /**
     * This function Adds the cart item to the cart array.
     * @param cartItems 
     * @returns the added cart item
     */
    addToCartArray(cartItems: Array<CartItem>): Observable<User> {

        let currentUser = JSON.parse(localStorage.getItem("currentUser")!);

        //check if cart exists
        if(currentUser.cart == null){
            currentUser.cart = [];
        }

        for(let i = 0; i < cartItems.length; i++){
            //check if item is already in cart
            let cartItem = cartItems[i];
            let itemInCart = currentUser.cart.find((item: CartItem) => item.animalId == cartItem.animalId);
            if(itemInCart){
                itemInCart.amount += cartItem.amount;
            }else{
                currentUser.cart.push(cartItem);
            }
        }
        let user$ = this.updateUser(currentUser);
        user$.subscribe((user: User) => {
            localStorage.setItem("currentUser", JSON.stringify(user));
        });
        return user$;
    }

    /**
     * This function removes the cart index from the cart
     * @param cartIndex 
     * @returns removed cart index
     */
    removeFromCart(cartIndex: number): Observable<User> {

        let currentUser = JSON.parse(localStorage.getItem("currentUser")!);
        //print before
        console.log(localStorage.getItem(currentUser));

        currentUser.cart.splice(cartIndex, 1);
        
        let user$ = this.updateUser(currentUser);
        user$.subscribe((user: User) => {
            localStorage.setItem("currentUser", JSON.stringify(user));
            //print after
            console.log(user);
        });
        return user$;    
    }

    /**
     * This function clears the cart
     * @returns the user with the updated cart.
     */
    clearCart(): Observable<User>{
        let currentUser = JSON.parse(localStorage.getItem("currentUser")!);
        currentUser.cart = [];
        let user$ = this.updateUser(currentUser);
        user$.subscribe((user: User) => {
            localStorage.setItem("currentUser", JSON.stringify(user));
        });
        return user$;
    }

    //wishlist stuff
    addToWishlist(cartItem: CartItem): Observable<User>{
        let currentUser = JSON.parse(localStorage.getItem("currentUser")!);
        //if wishlist doesnt exist, create it
        if(currentUser.wishlist == null){
            currentUser.wishlist = [];
        }
        //check if item is already in wishlist
        let itemInWishlist = currentUser.wishlist.find((item: CartItem) => item.animalId == cartItem.animalId);
        if(itemInWishlist){
            itemInWishlist.amount += cartItem.amount;
        }else{
            currentUser.wishlist.push(cartItem);
        }

        let user$ = this.updateUser(currentUser);
        user$.subscribe((user: User) => {
            localStorage.setItem("currentUser", JSON.stringify(user));
        });
        return user$;
    }

    /**
     * This function removes the cartindex from the wishlist
     * @param cartIndex 
     * @returns the user with the updated wishlist
     */
    removeFromWishlist(cartIndex: number): Observable<User>{
        let currentUser = JSON.parse(localStorage.getItem("currentUser")!);
        currentUser.wishlist.splice(cartIndex, 1);
        let user$ = this.updateUser(currentUser);
        user$.subscribe((user: User) => {
            localStorage.setItem("currentUser", JSON.stringify(user));
        });
        return user$;
    }

    /**
     * This function clears the wishlist
     * @returns the user and the updated wishlist.
     */
    clearWishtlist(): Observable<User>{
        let currentUser = JSON.parse(localStorage.getItem("currentUser")!);
        currentUser.wishlist = [];
        let user$ = this.updateUser(currentUser);
        user$.subscribe((user: User) => {
            localStorage.setItem("currentUser", JSON.stringify(user));
        });
        return user$;
    }

}
