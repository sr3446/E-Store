import { Component, OnInit } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { AnimalService } from 'src/app/services/animal.service';
import { Animal } from 'src/app/model/animal';
import { CartItem } from 'src/app/model/cartitem';
import {
    debounceTime, distinctUntilChanged, switchMap
} from 'rxjs/operators';
import { User } from 'src/app/model/user';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
    // cartItems: CartItem[]  = [];
    // animals: Animal[] = [];

    items: CartItemAndAnimal[] = [];

    user = JSON.parse(localStorage.getItem("currentUser")!);

    constructor(private animalService: AnimalService) {
    }
    ngOnInit(): void {
        this.user = JSON.parse(localStorage.getItem("currentUser")!);
        this.translateCartItemsToAnimals();
    }
    totalPrice = 0;
    translateCartItemsToAnimals(){
        this.totalPrice = 0;
        this.items = []
        let user = JSON.parse(localStorage.getItem("currentUser")!);
        for(let i = 0; i < user.cart.length; i++){
            this.animalService.getAnimal(user.cart[i].animalId).subscribe((animal: Animal) => {
                let item = {
                    index: i,
                    cartItem: user.cart[i],
                    animal: animal
                }
                this.items.push(item);
                this.totalPrice += (item.animal.price*item.cartItem.amount);
                // this.totalPrice.toFixed(2);
            });
        }
    }
    remove(index:number){
        this.animalService.removeFromCart(index).subscribe(() => {
            this.translateCartItemsToAnimals();
        });
    }
    clearCart(){
        this.animalService.clearCart().subscribe(() => {
            this.translateCartItemsToAnimals();
        });
    }
}

interface CartItemAndAnimal{
    index: number,
    cartItem: CartItem,
    animal: Animal
}

