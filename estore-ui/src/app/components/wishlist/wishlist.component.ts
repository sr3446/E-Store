import { Component, OnInit } from '@angular/core';
import { AnimalService } from 'src/app/services/animal.service';
import { Animal } from 'src/app/model/animal';
import { CartItem } from 'src/app/model/cartitem';

@Component({
  selector: 'app-wishlist',
  templateUrl: './wishlist.component.html',
  styleUrls: ['./wishlist.component.css']
})
export class WishlistComponent implements OnInit {
    // cartItems: CartItem[]  = [];
    // animals: Animal[] = [];

    items: CartItemAndAnimal[] = [];

    user = JSON.parse(localStorage.getItem("currentUser")!);

    constructor(private animalService: AnimalService){
    }
    ngOnInit(): void {
        this.user = JSON.parse(localStorage.getItem("currentUser")!);       
        this.translateWishlistItemsToAnimals();
    }
    translateWishlistItemsToAnimals(){
        this.items = [];
        let user = JSON.parse(localStorage.getItem("currentUser")!);

        if(user.wishlist === undefined || user.wishlist.length === 0){
            return;
        }

        for(let i = 0; i < user.wishlist.length; i++){
            this.animalService.getAnimal(user.wishlist[i].animalId).subscribe((animal: Animal) => {
                let item = {
                    index: i,
                    cartItem: user.wishlist[i],
                    animal: animal
                }
                this.items.push(item);
            });
        }
    }
    remove(index:number){
        this.animalService.removeFromWishlist(index).subscribe(() => {
            this.translateWishlistItemsToAnimals();
        });
    }
    moveToCart(index:number){
        let user = JSON.parse(localStorage.getItem("currentUser")!);

        if(user.wishlist === undefined || user.wishlist.length === 0){
            return;
        }

        let cartItem = user.wishlist[index];

        this.animalService.addToCart(cartItem).subscribe(() => {
            this.animalService.removeFromWishlist(index).subscribe(() => {
                this.translateWishlistItemsToAnimals();
            });
        });
    }
}
interface CartItemAndAnimal{
    index: number,
    cartItem: CartItem,
    animal: Animal
}


