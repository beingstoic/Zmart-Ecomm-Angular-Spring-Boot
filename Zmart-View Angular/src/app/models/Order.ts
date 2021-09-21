import { OrderItem } from "./OrderItem";


export class Order{
    public orderId:number;
    public userId:number;
	public amount:number;
	public orderDate:Date;
	public deliveryDate:Date;
    public orderItems:OrderItem[];
    public status:string;
	public paymentType:string;
    public address:string;
    public contact :string;
    public name:string;
    public paymentId:string;
  
}