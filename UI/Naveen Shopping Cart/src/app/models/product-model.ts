export class ProductModel {
    constructor(
        public id: number,
        public name: string,
        public category: string,
        public price: number,
        public image: string,
        public description: string,
        public specifications: Map<string,string>
    ) {}
}
