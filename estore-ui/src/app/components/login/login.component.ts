import { Component } from '@angular/core';
import { User } from 'src/app/model/user';
import { AnimalService } from 'src/app/services/animal.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
    username: string = "";
    password: string = "";

    user: User | null = null;

    constructor(
        private animalService: AnimalService,
        private router: Router      
    ){}
    ngOnInit(): void {
        //set user to null
        this.user = null;
        //clear user from localstorage
        localStorage.removeItem("currentUser");
        console.log("Login page loaded")
    }

    setUsername(username: string){
        this.username = username;
        this.animalService.getUser(this.username).subscribe((newUser: User) => {
            this.user = newUser;
        });
    }
    setPassword(password: string){
        this.password = password;
    }
    submitLogin(){

        
        if(this.user == null || this.user.name != this.username){
            alert("New user created");
            let user: User = {
                id: 0, name: this.username, password: this.password,
                cart: [],
                wishlist: []
            };

            this.animalService.addUser(user).subscribe((newUser: User) => {
                this.user = newUser;
                this.router.navigate(['/browse']);
            });
            //switch to browse page
        }else if(this.user.name == this.username && this.user.password == this.password){
            console.log("Login successful");
            alert("Login successful");
            //switch to browse page
            this.router.navigate(['/browse']);
        }else{
            alert("Login failed: username or password was incorrect\n"+
            "Username: " + this.username + " Password: " + this.password + 
            "\nreal Username: " + this.user.name + " Password: " + this.user.password);
        }
    
    }
}
