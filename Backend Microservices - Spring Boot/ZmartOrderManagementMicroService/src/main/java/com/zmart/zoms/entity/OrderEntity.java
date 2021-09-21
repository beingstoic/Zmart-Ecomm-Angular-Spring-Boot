package com.zmart.zoms.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name="order")
public class OrderEntity {

	@Id
	@Column(name="order_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long orderId;
	@Column(name="user_id", nullable=false)
	private long userId;
	@Column(name="amount", nullable=false)
	private double amount;
	@DateTimeFormat(iso=ISO.DATE)
	@Column(name="order_date", nullable=false)
	private LocalDate orderDate;
	@DateTimeFormat(iso=ISO.DATE)
	@Column(name="delivery_date", nullable=false)
	private LocalDate deliveryDate;
	@ElementCollection
	private List<OrderItemEntity> orderItems;
	@Enumerated(EnumType.STRING)
	@Column(name="status", nullable=false)
	private Status status;
	@Column(name="payment_mode", nullable=false)
	private String paymentType;
	public OrderEntity() {
		super();
		
	}
	public OrderEntity(long orderId, long userId, double amount, LocalDate orderDate, LocalDate deliveryDate,
			List<OrderItemEntity> orderItems, Status status,String paymentType) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.amount = amount;
		this.orderDate = orderDate;
		this.deliveryDate = deliveryDate;
		this.orderItems = orderItems;
		this.status = status;
		this.paymentType=paymentType;
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
	public List<OrderItemEntity> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItemEntity> orderItems) {
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
	@Override
	public String toString() {
		return "OrderEntity [orderId=" + orderId + ", userId=" + userId + ", amount=" + amount + ", orderDate="
				+ orderDate + ", deliveryDate=" + deliveryDate + ", orderItems=" + orderItems + ", status=" + status
				+"paymentType=" + paymentType + "]";
	}
	
	
}
