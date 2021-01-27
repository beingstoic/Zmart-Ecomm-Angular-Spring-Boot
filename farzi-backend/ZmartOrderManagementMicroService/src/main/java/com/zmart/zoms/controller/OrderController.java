package com.zmart.zoms.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zmart.zoms.entity.Status;
import com.zmart.zoms.exception.OrderException;
import com.zmart.zoms.exception.UserException;
import com.zmart.zoms.model.OrderModel;
import com.zmart.zoms.service.OrderService;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping 
	public ResponseEntity<OrderModel> addOrder(@RequestBody OrderModel order) throws OrderException, UserException
	{
		return new ResponseEntity<>(orderService.placeOrder(order),HttpStatus.OK);
	}
	
	@GetMapping("/orders")
	public ResponseEntity<List<OrderModel>> getAllOrders(){
		ResponseEntity<List<OrderModel>> response=null;
		List<OrderModel> orderList=orderService.getAllOrder();
		if(orderList==null) {
			response=new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else {
			response=new ResponseEntity<>(orderList,HttpStatus.OK);
		}
		return response;
	}
	
	@GetMapping("/orders/userId/{userId}")
	public ResponseEntity<List<OrderModel>> getAllOrderList(@PathVariable("userId") long userId) throws OrderException{
		ResponseEntity<List<OrderModel>> response=null;
		List<OrderModel> orderList=orderService.getAllOrderList(userId);
		if(orderList==null) {
			throw new OrderException("No orders found for this user Id");
		}
		else {
			response=new ResponseEntity<>(orderList,HttpStatus.OK);
		}
		return response;
	}
	
	@GetMapping("/orders/orderId/{orderId}")
	public ResponseEntity<OrderModel> getOrderByOrderId(@PathVariable("orderId") long orderId) throws OrderException{
		ResponseEntity<OrderModel> response=null;
		OrderModel order=orderService.getOrder(orderId);
		if(order==null) {
			//response=new ResponseEntity<>(HttpStatus.NOT_FOUND);
			throw new OrderException("Order Id Does Not Exist ");
		}
		else {
			response=new ResponseEntity<>(order,HttpStatus.OK);
		}
		return response;
	}
	
	@GetMapping("/orders/orderDate/{orderDate}")
	public ResponseEntity<List<OrderModel>> getOrderByOrderId(@PathVariable("orderDate") String orderDate) throws OrderException{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		 // String date = "16/08/2016";

		  //convert String to LocalDate
		  LocalDate date = LocalDate.parse(orderDate, formatter);
		ResponseEntity<List<OrderModel>> response=null;
		List<OrderModel> orderList=orderService.getOrdersByDate(date);
		if(orderList==null) {
			throw new OrderException("No orders exist on this date");
		}
		else {
			response=new ResponseEntity<>(orderList,HttpStatus.OK);
		}
		return response;
	}
	
	@PutMapping("/orders/updateOrder/{orderId}/{status}")
	public ResponseEntity<OrderModel> updateOrderStatus(@PathVariable("orderId") long orderId,@PathVariable("status") Status status) throws OrderException {
		
			OrderModel updatedOrder=orderService.updateOrderStatus(orderId, status);
			return new ResponseEntity<>(updatedOrder,HttpStatus.OK);
		
	}
}
