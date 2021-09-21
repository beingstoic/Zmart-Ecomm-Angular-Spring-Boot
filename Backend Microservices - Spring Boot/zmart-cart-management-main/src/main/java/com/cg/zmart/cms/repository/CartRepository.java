package com.cg.zmart.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.zmart.cms.entity.CartEntity;

public interface CartRepository extends JpaRepository<CartEntity, Long>{
	
	public CartEntity getCartByUserId(long userId);
	
}
