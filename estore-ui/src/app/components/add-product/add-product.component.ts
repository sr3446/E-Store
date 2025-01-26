import { Component, OnInit } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { AnimalService } from 'src/app/services/animal.service';
import { Animal } from 'src/app/model/animal';
import { CartItem } from 'src/app/model/cartitem';
import { User } from 'src/app/model/user';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit{
    user: User;
    // Item: Animal[] = [];
    newAnimal: Animal = {    
        id: -1,
        price: -1,
        stock: -1,
        description: "NO DESCRIPTION",
        species: "NO SPECIES NAME",
        imageUrl: "NO IMAGE URL",
        reviews: []
    };
    constructor(
        private animalService: AnimalService, 
        private route: ActivatedRoute,
        private router: Router
        ) {
        this.user = JSON.parse(localStorage.getItem("currentUser")!);
    }
    ngOnInit() {
        let id = this.route.snapshot.params['id'];
        this.animalService.getAnimal(id).subscribe((newAnimal: Animal) => {
            this.newAnimal = newAnimal;
        });
    }
   
    submit(){
        if(this.newAnimal.id == -1){
            if(this.newAnimal.price == -1 || this.newAnimal.stock == -1 || this.newAnimal.description == "NO DESCRIPTION" || this.newAnimal.species == "NO SPECIES NAME" || this.newAnimal.imageUrl == "NO IMAGE URL"){
                alert("Please fill out all fields");
                return;
            }

            this.animalService.addAnimals(this.newAnimal).subscribe((animal: Animal) => {
                this.newAnimal = animal;
            });
            console.log("Animal submitted");
            //route to browse page
            this.router.navigate(['/browse']);
        }else{
            this.animalService.updateAnimal(this.newAnimal).subscribe((animal: Animal) => {
                this.newAnimal = animal;
            });
            console.log("Animal updated");
            //route to browse page
            this.router.navigate(['/browse']);
        }
    }
    setPrice(new_price: string){
        this.newAnimal.price = new_price as unknown as number;
    }
    setStock(newStock: string){
        this.newAnimal.stock = newStock as unknown as number;
    }
    setDescription(new_description: string){
        this.newAnimal.description = new_description;
    }
    setSpecies(new_species: string){
        this.newAnimal.species = new_species;
    }
    setImageUrl(imageUrl: string){
        this.newAnimal.imageUrl = imageUrl;
    }

}
