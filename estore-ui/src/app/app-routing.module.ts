import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddProductComponent } from './components/add-product/add-product.component';
import { BrowseComponent } from './components/browse/browse.component';
import { CartComponent } from './components/cart/cart.component';
import { LoginComponent } from './components/login/login.component';
import { ProductComponent } from './components/product/product.component';
import { WishlistComponent } from './components/wishlist/wishlist.component';

const routes: Routes = [
    {path: "", redirectTo: "/login", pathMatch: "full"},
    {path: "browse", component: BrowseComponent},
    {path: "login", component: LoginComponent},
    {path: "product/:id", component: ProductComponent},
    {path: "cart", component: CartComponent},
    {path: "add-product", component: AddProductComponent},
    {path: "add-product/:id", component: AddProductComponent},
    {path: "wishlist", component: WishlistComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
