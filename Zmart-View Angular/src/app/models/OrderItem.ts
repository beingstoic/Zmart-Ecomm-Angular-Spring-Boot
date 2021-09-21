export class OrderItem{
    id:number;
    productId:number;
	price:number;
    quantity:number;
    productName:string;
    imageUrl:string;
    constructor(item){
        if(item.hasOwnProperty('id')){
            this.id=item.id;
            this.price=item.price;
            this.productId=item.productId;
            this.quantity=item.quantity;
            this.productName=item.productName;
            this.imageUrl=item.imgageUrl;
        }
    }
}