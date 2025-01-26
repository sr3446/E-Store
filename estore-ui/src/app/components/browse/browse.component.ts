import { Component, OnInit } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { Animal } from 'src/app/model/animal';
import { AnimalService } from 'src/app/services/animal.service';
import {
    debounceTime, distinctUntilChanged, switchMap
} from 'rxjs/operators';
import { User } from 'src/app/model/user';

//OnInit means
//When the component is initialized, the ngOnInit() method is called after the constructor

@Component({
  selector: 'app-browse',
  templateUrl: './browse.component.html',
  styleUrls: ['./browse.component.css']
})
export class BrowseComponent {
    user: User;
    
    searchTerms = new Subject<string>();
    allAnimals: Animal[] = [];
    searchResults: Animal[] = [];

    
    constructor(private animalService: AnimalService) {
        this.user = JSON.parse(localStorage.getItem("currentUser")!);
    }
    
    ngOnInit(): void {
        this.animalService.getAnimals().subscribe((animals: Animal[]) => {
            this.allAnimals = animals;
        });
    }

    search(term: string): void {
        // Push the search term into the observable stream.
        this.searchTerms.next(term);
      
        // Subscribe to the observable stream and assign the results to the searchResults property
        this.searchTerms.pipe(
            debounceTime(300),
            distinctUntilChanged(),
            switchMap((term: string) => this.animalService.searchAnimals(term))
        ).subscribe((animals: Animal[]) => {
            this.searchResults = animals;
        });
    }
}
