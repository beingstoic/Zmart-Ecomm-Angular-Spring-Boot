package com.cg.zmart.cms.service;

import com.cg.zmart.cms.model.CartItem;
import com.cg.zmart.cms.model.CartModel;

public interface CartServcie {
	
	
	public CartModel viewCart(long userId);
	
	public boolean removeItem(long userId,long productId);
	
	public CartModel addItem(long userId, CartItem cartItem);
	
	public boolean registerCart(long userId);
	
	public CartModel changeQuantity(long userId, long productId, int quantity);
	
}
