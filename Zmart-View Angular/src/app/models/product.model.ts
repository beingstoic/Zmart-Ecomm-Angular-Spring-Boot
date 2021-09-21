
export class Product {
        id: number;
        productName: string;
        productDescription: string;
        unitPrice: number;
        imageUrl: string;
        productQuantity: number;
        productCategory:string;

        constructor() {
                this.id = 0;
                this.productName = '';
                this.productDescription = '';
                this.unitPrice = 1;
                this.imageUrl = '';
                this.productQuantity=1;
                this.productCategory='';
        }
}