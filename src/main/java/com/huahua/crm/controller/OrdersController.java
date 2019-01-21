package com.huahua.crm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huahua.crm.pojo.Customer;
import com.huahua.crm.pojo.OrderItem;
import com.huahua.crm.pojo.OrderItemExample;
import com.huahua.crm.pojo.Orders;
import com.huahua.crm.pojo.OrdersExample;
import com.huahua.crm.pojo.Pager;
import com.huahua.crm.service.IOrderItemService;
import com.huahua.crm.service.IOrdersService;
import com.huahua.crm.utils.Operation;

/**
 * 订单控制器
 * @author huangqingwen
 *
 */
@Operation(name="订单控制器")
@Controller
@RequestMapping("/orders")
public class OrdersController {

	@Autowired
	private IOrdersService ordersService;
	
	@Autowired
	private IOrderItemService orderItemService;
	
	/**
	 * 获取客户的历史订单信息
	 * @param customer
	 * @return
	 * @author huangqingwen
	 */
	@Operation(name="获取客户的历史订单")
	@RequiresAuthentication
	@RequestMapping("/getHistoryOrdersByCustomer")
	@ResponseBody
	public Map<String, Object> getHistoryOrdersByCustomer(Long page, Long limit, Customer customer){
		
		Map<String, Object> maps = new HashMap<String, Object>(16);
		
		Pager pager = new Pager(page.intValue(), limit.intValue());
		OrdersExample ordersExample = new OrdersExample();
		ordersExample.createCriteria().andCustomerIdEqualTo(customer.getId());
		List<Orders> orders = ordersService.selectByOrdersExample(ordersExample, pager);
		maps.put("data", orders);
		maps.put("count", pager.getTotal());
		maps.put("code", 0);
		return maps;
	}
	
	/**
	 * 获取订单的订单项
	 * @param orderItem
	 * @return
	 * @author huangqingwen
	 */
	@Operation(name="获取订单的订单项")
	@RequiresAuthentication
	@RequestMapping("/getOrderItem")
	@ResponseBody
	public Map<String, Object> getOrderItem(Long page, Long limit, Orders orders){
		
		Map<String, Object> maps = new HashMap<String, Object>(16);
		
		Pager pager = new Pager(page.intValue(), limit.intValue());
		
		OrderItemExample orderItemExample = new OrderItemExample();
		orderItemExample.createCriteria().andOrdersIdEqualTo(orders.getId());
		List<OrderItem> orderItems = orderItemService.selectByOrderItemExample(orderItemExample, pager);
		
		maps.put("code", 0);
		maps.put("data", orderItems);
		maps.put("count", pager.getTotal());
		return maps;
	}
	
}
