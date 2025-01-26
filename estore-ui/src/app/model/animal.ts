import { Review } from "./review";

export interface Animal {
    id: number;
    species: string;
    description: string;
    price: number;
    stock: number;
    imageUrl: string;
    reviews: Review[];
}

  