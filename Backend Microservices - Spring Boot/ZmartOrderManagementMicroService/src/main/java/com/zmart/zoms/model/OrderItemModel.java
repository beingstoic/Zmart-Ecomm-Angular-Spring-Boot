package com.zmart.zoms.model;

public class OrderItemModel {

	private long id;
	private long productId;
	private double price;
	private int quantity;
	public OrderItemModel(long id, long productId, double price, int quantity) {
		super();
		this.id = id;
		this.productId = productId;
		this.price = price;
		this.quantity = quantity;
	}
	public OrderItemModel() {
		// TODO Auto-generated constructor stub
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "OrderItemModel [id=" + id + ", productId=" + productId + ", price=" + price + ", quantity=" + quantity
				+ "]";
	}
	
}
