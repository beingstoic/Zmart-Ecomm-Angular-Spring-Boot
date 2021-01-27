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
import com.zmart.zoms.exception.OrderException;
import com.zmart.zoms.exception.UserException;
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
			result.setAddress(source.getAddress());
			result.setContact(source.getContact());
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
			result.setAddress(source.getAddress());
			result.setContact(source.getContact());
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
		    result.setProductName(source.getProductName());
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
		    result.setProductName(source.getProductName());
		}
		return result;
	}
	
	@Override
	public OrderModel placeOrder(OrderModel order) throws OrderException, UserException {
		OrderModel model=null;
		if(order!=null)
		{ 
			OrderEntity entity=of(order);
			if(entity.getUserId()==0)
			{
				throw new UserException("Invalid user ID");
			}
			else if(entity.getPaymentType()==null)
			{
				throw new OrderException("Payment not completed. Order not booked. ");
			}
			else if(entity.getOrderItems()==null)
			{
				throw new OrderException("No products in order. Order not booked");
			}
			else {
				entity.setStatus(Status.BOOKED);
				entity.setOrderDate(LocalDate.now());
				entity.setDeliveryDate(LocalDate.now().plusDays(7));
				entity=orderRepo.save(entity);
				model=of(entity);
			}
			
				
		}
		else
			throw new OrderException("Order Cannot be empty");
		return model;
	}
	
	@Override
	public List<OrderModel> getAllOrder() {
		List<OrderEntity> ordersEntities=orderRepo.findAll();
		List<OrderModel> orderModels=null;
		if(ordersEntities!=null)
		{
			orderModels=ordersEntities.stream().map(entity->of(entity)).collect(Collectors.toList());     	
		}
		return orderModels;
	}

	@Override
	public List<OrderModel> getAllOrderList(long userId) {
		
		List<OrderEntity> ordersEntities=orderRepo.findAllByUserId(userId);
		List<OrderModel> orderModels=null;
		if(ordersEntities!=null)
		{
			orderModels=ordersEntities.stream().map(entity->of(entity)).collect(Collectors.toList());     	
			
		}
		return orderModels;
	}

	@Override
	public OrderModel getOrder(long orderId) {
		
		OrderModel order=null;
		if(orderRepo.existsById(orderId)) {
			 order=of(orderRepo.findById(orderId).get());
		}
		return order;
	}


	@Override
	public List<OrderModel> getOrdersByDate(LocalDate orderDate) {
	
		List<OrderEntity> ordersEntities=orderRepo.findAllByOrderDate(orderDate);
		List<OrderModel> orderModels=null;
		if(ordersEntities!=null)
		{
			orderModels=ordersEntities.stream().map(entity->of(entity)).collect(Collectors.toList());     	
			
		}
		return orderModels;
	}

	@Override
	public OrderModel updateOrderStatus(long orderId, Status status) throws OrderException {
		OrderModel model=null;
		if(orderRepo.existsById(orderId)) {
			OrderEntity entity=orderRepo.findById(orderId).get();
			
			if(entity.getStatus()==Status.DELIVERED){
				
			      throw new OrderException("Order delivered. Cannot change status");	
			}
			else if(entity.getStatus()==Status.CANCELLED){
				
				throw new OrderException("Order already cancelled. Cannot change status");
			}
			else {
				entity.setStatus(status);
			    entity=orderRepo.save(entity);
			    model=of(entity);
			}
		}
		else
			throw new OrderException("Order does not exist");
		return model;
	}

}
