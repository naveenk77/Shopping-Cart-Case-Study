import { ItemModel } from "./item-model";



export class CartModel {
    constructor(
        public id: number,
        public userId: number,
        public items: Map<Number, ItemModel>,
        public total: number
    ){}
}
