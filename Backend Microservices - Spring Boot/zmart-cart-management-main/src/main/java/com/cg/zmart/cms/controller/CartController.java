package com.cg.zmart.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.zmart.cms.model.CartItem;
import com.cg.zmart.cms.service.CartServcie;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	CartServcie cartService;
	
	@PostMapping("/registercart/{userId}")
	public ResponseEntity<?> registerCart(@PathVariable long userId){
		return ResponseEntity.ok(cartService.registerCart(userId));
	}
	
	@GetMapping("/viewcart/{userId}")
	public ResponseEntity<?> viewCart(@PathVariable long userId){
		return ResponseEntity.ok(cartService.viewCart(userId));
	}
	
	@DeleteMapping("/removeitem/{userId}/{productId}")
	public ResponseEntity<?> removeCart(@PathVariable long userId, @PathVariable long productId){
		return ResponseEntity.ok(cartService.removeItem(userId, productId));
	}
	
	@PutMapping("/additem/{userId}")
	public ResponseEntity<?> addItem(@PathVariable long userId, @RequestBody CartItem cartItem){
		return ResponseEntity.ok(cartService.addItem(userId, cartItem));
	}
	
	@PutMapping("/changequantity/{userId}/{productId}/{quantity}")
	public ResponseEntity<?> changeQuantity(@PathVariable long userId, @PathVariable long productId, @PathVariable int quantity){
		return ResponseEntity.ok(cartService.changeQuantity(userId, productId, quantity));
	}

}
