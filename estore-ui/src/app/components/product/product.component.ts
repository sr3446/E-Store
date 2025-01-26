import { Component, OnInit } from '@angular/core';
import {Animal} from 'src/app/model/animal';
import { AnimalService } from 'src/app/services/animal.service';
import { User } from 'src/app/model/user';
import { ActivatedRoute, Router } from '@angular/router';
import { Review } from 'src/app/model/review';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
    product: Animal | null;
    allAnimals: Animal[];
    user: User;

    review: Review;

    constructor(
        private animalService: AnimalService,
        private route: ActivatedRoute,
        private router: Router
    ) {
        this.allAnimals = [];
        this.product = null;
        this.user = JSON.parse(localStorage.getItem("currentUser")!);
        this.review = {
            review: "",
            name: this.user.name,
        }

    }
    ngOnInit() {
        let id = this.route.snapshot.params['id'];
        this.allAnimals = [];
        this.animalService.getAnimal(id).subscribe((newAnimal: Animal) => {
           this.product = newAnimal;
        });
    }

    //adds selected product into the cart
    addToCart(): void{
        let cartItem = {
            amount: 1,
            animalId: this.product!.id
        };
        this.animalService.addToCart(cartItem);
        console.log("Added to cart");
    }
    //adds selected product into the wishlist
    addToWishlist(): void{
        let cartItem = {
            amount: 1,
            animalId: this.product!.id
        };
        this.animalService.addToWishlist(cartItem);
        console.log("Added to wishlist");
    }
    
    deleteProduct(): void{
        this.animalService.deleteAnimal(this.product!.id).subscribe((newAnimal: Animal) => {
            console.log("Deletetion successful");
        });
        this.router.navigate(['/browse']);
    }
    editProduct(): void{
        this.router.navigate(['/add-product', this.product!.id]);
    }

    setReview(review: string){
        this.review.review = review;
    }
    addReview(): void{
        this.product!.reviews.push(this.review);
        this.animalService.updateAnimal(this.product!).subscribe((newAnimal: Animal) => {
            console.log("Review added");
            this.product = newAnimal;
        });
    }
    deleteReview(review: Review): void{
        this.product!.reviews = this.product!.reviews.filter(r => r != review);
        this.animalService.updateAnimal(this.product!).subscribe((newAnimal: Animal) => {
            console.log("Review deleted");
            this.product = newAnimal;
        });
    }
}
