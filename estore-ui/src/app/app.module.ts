import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { BrowseComponent } from './components/browse/browse.component';
import { ProductComponent } from './components/product/product.component';
import { AnimalService } from './services/animal.service';
import { HttpClientModule } from '@angular/common/http';
import { CartComponent } from './components/cart/cart.component';
import { WishlistComponent } from './components/wishlist/wishlist.component';
import { AddProductComponent } from './components/add-product/add-product.component';

@NgModule({
    declarations: [
        AppComponent,
        LoginComponent,
        BrowseComponent,
        ProductComponent,
        CartComponent,
        WishlistComponent,
        AddProductComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule
    ],
    providers: [AnimalService],
    bootstrap: [AppComponent]
})
export class AppModule { }
