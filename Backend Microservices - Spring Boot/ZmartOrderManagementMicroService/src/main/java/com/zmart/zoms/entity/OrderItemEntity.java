package com.zmart.zoms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="order_item")
public class OrderItemEntity {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name="product_id",nullable=false)
	private long productId;
	@Column(name="price",nullable=false)
	private double price;
	@Column(name="quantity",nullable=false)
	private int quantity;
	
	public OrderItemEntity(long id, long productId, double price, int quantity) {
		super();
		this.id = id;
		this.productId = productId;
		this.price = price;
		this.quantity = quantity;
	}

	public OrderItemEntity() {
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
		return "OrderItemEntity [id=" + id + ", productId=" + productId + ", price=" + price + ", quantity=" + quantity
				+ "]";
	}
	
	
}
