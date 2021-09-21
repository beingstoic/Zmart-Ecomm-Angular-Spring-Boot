package com.zmart.zoms.service;

import java.time.LocalDate;
import java.util.List;

import com.zmart.zoms.entity.Status;
import com.zmart.zoms.model.OrderModel;

public interface OrderService {

	List<OrderModel> getAllOrder();
	
	List<OrderModel> getAllOrderList(long userId);
	
	OrderModel getOrder(long orderId);
	
	OrderModel placeOrder(OrderModel order);
	
	List<OrderModel> getOrdersByDate(LocalDate orderDate);
	
	OrderModel updateOrderStatus(long orderId,Status status);
}
