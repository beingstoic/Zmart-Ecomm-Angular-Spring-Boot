package com.zmart.zoms.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zmart.zoms.dao.OrderDao;
import com.zmart.zoms.entity.OrderEntity;
import com.zmart.zoms.entity.OrderItemEntity;
import com.zmart.zoms.entity.Status;
import com.zmart.zoms.model.OrderItemModel;
import com.zmart.zoms.model.OrderModel;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderRepo;
	
	OrderEntity of(OrderModel source)
	{
		OrderEntity result=null;
		if(source!=null) {
			result = new OrderEntity();
			result.setOrderId(source.getOrderId());
			result.setUserId(source.getUserId());
			result.setPaymentType(source.getPaymentType());
			result.setAmount(source.getAmount());
			result.setDeliveryDate(source.getDeliveryDate());
			result.setOrderDate(source.getOrderDate());
			result.setDeliveryDate(source.getDeliveryDate());
			result.setStatus(source.getStatus());
			result.setOrderItems(source.getOrderItems().stream().map((x)->of(x)).collect(Collectors.toList()));
		}
		return result;
	}
	
	OrderModel of(OrderEntity source)
	{
		OrderModel result=null;
		if(source!=null) {
			result = new OrderModel();
			result.setOrderId(source.getOrderId());
			result.setUserId(source.getUserId());
			result.setPaymentType(source.getPaymentType());
			result.setAmount(source.getAmount());
			result.setDeliveryDate(source.getDeliveryDate());
			result.setOrderDate(source.getOrderDate());
			result.setDeliveryDate(source.getDeliveryDate());
			result.setStatus(source.getStatus());
			result.setOrderItems(source.getOrderItems().stream().map(x->of(x)).collect(Collectors.toList()));
		}
		return result;
	}
	
	OrderItemEntity of(OrderItemModel source)
	{
		OrderItemEntity result=null;
		if(source!=null)
		{
		    result=	new OrderItemEntity();
		    result.setId(source.getId());
		    result.setProductId(source.getProductId());
		    result.setPrice(source.getPrice());
		    result.setQuantity(source.getQuantity());
		    
		}
		return result;
	}
	
	OrderItemModel of(OrderItemEntity source)
	{
		OrderItemModel result=null;
		if(source!=null)
		{
		    result=	new OrderItemModel();
		    result.setId(source.getId());
		    result.setProductId(source.getProductId());
		    result.setPrice(source.getPrice());
		    result.setQuantity(source.getQuantity());
		    
		}
		return result;
	}
	
	@Override
	public OrderModel placeOrder(OrderModel order) {
		OrderModel model=null;
		if(order!=null)
		{ 
			OrderEntity entity=of(order);
			entity.setOrderDate(LocalDate.now());
			entity.setDeliveryDate(LocalDate.now().plusDays(7));
			entity=orderRepo.save(entity);
			order=of(entity);
		}
		return model;
	}
	
	@Override
	public List<OrderModel> getAllOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderModel> getAllOrderList(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderModel getOrder(long orderId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<OrderModel> getOrdersByDate(LocalDate orderDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderModel updateOrderStatus(long orderId, Status status) {
		// TODO Auto-generated method stub
		return null;
	}

}
