package com.cg.zmart.cms.model;

import java.util.List;

public class CartModel {

	
	private long userId;
	
	private List<CartItem> cartItem;


	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public List<CartItem> getCartItem() {
		return cartItem;
	}

	public void setCartItem(List<CartItem> cartItem) {
		this.cartItem = cartItem;
	}

	

	
	
	
	
}
