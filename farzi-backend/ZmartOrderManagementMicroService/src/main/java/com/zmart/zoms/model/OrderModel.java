package com.zmart.zoms.model;

import java.time.LocalDate;
import java.util.List;

import com.zmart.zoms.entity.OrderItemEntity;
import com.zmart.zoms.entity.Status;

public class OrderModel {

	private long orderId;
	private long userId;
	private double amount;
	private LocalDate orderDate;
	private LocalDate deliveryDate;
	private List<OrderItemModel> orderItems;
	private Status status;
	private String paymentType;
	private String address;
	private String contact;
	
	public OrderModel() {
		super();
		
	}
	public OrderModel(long orderId, long userId, double amount, LocalDate orderDate, LocalDate deliveryDate,
			List<OrderItemModel> orderItems, Status status,String paymentType,String address,String contact) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.amount = amount;
		this.orderDate = orderDate;
		this.deliveryDate = deliveryDate;
		this.orderItems = orderItems;
		this.status = status;
		this.paymentType= paymentType;
		this.address=address;
		this.contact=contact;
		
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public List<OrderItemModel> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItemModel> orderItems) {
		this.orderItems = orderItems;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType=paymentType;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	@Override
	public String toString() {
		return "OrderModel [orderId=" + orderId + ", userId=" + userId + ", amount=" + amount + ", orderDate="
				+ orderDate + ", deliveryDate=" + deliveryDate + ", orderItems=" + orderItems + ", status=" + status
				+ ", paymentType=" + paymentType + ", address=" + address + ", contact=" + contact + "]";
	}
	
}
