package com.zmart.zoms.service;

import java.time.LocalDate;
import java.util.List;

import com.zmart.zoms.entity.Status;
import com.zmart.zoms.exception.OrderException;
import com.zmart.zoms.exception.UserException;
import com.zmart.zoms.model.OrderModel;

public interface OrderService {

	List<OrderModel> getAllOrder();
	
	List<OrderModel> getAllOrderList(long userId);
	
	OrderModel getOrder(long orderId);
	
	OrderModel placeOrder(OrderModel order) throws OrderException, UserException;
	
	List<OrderModel> getOrdersByDate(LocalDate orderDate);
	
	OrderModel updateOrderStatus(long orderId,Status status) throws OrderException;
}
