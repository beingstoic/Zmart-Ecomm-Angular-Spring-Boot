package com.cg.zmart.cms.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CartEntity {
	
	@Id
	private long userId;
	
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
	private List<CartItemEntity> cartItem;

	

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public List<CartItemEntity> getCartItem() {
		return cartItem;
	}

	public void setCartItem(List<CartItemEntity> cartItem) {
		this.cartItem = cartItem;
	}

	
	
	
	
}
