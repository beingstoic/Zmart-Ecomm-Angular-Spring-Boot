package com.zmart.zoms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zmart.zoms.model.OrderModel;
import com.zmart.zoms.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping 
	public ResponseEntity<OrderModel> addOrder(@RequestBody OrderModel order)
	{
		return new ResponseEntity<>(orderService.placeOrder(order),HttpStatus.OK);
	}
}
