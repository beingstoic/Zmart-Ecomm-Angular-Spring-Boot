package com.zmart.zoms.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zmart.zoms.entity.OrderEntity;
@Repository
public interface OrderDao extends JpaRepository<OrderEntity,Long> {

	List<OrderEntity> findAll();
	
	boolean existsById(Long orderId);
	
	@Query("SELECT o FROM OrderEntity o WHERE o.orderId=:orderId")
	OrderEntity findByOrderId(long orderId);
	
	@Query("SELECT o FROM OrderEntity o WHERE o.orderDate=:date")
	List<OrderEntity> findAllByOrderDate(LocalDate date);
	
	List<OrderEntity> findAllByUserId(long userId);
}
