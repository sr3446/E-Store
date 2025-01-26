import { CartItem } from "./cartitem";

export interface User {
    id: number;
    name: string;
    password: string;
    cart: Array<CartItem>;
    wishlist: Array<CartItem>;
}