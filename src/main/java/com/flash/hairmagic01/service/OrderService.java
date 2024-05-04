package com.flash.hairmagic01.service;

import com.flash.hairmagic01.entity.Order;
import com.flash.hairmagic01.entity.OrderSummary;
import com.flash.hairmagic01.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 功能：
 * 作者：whitebuless
 * 日期：2024/4/27 10:09
 */
@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;

    public Order[] findByOrderTime(Order order) {
        return orderMapper.findOrderByAll(order);
    }

    public void addOrder(Order order){ orderMapper.addOrder(order);}
//    更新订单
    public void updateOrderById(Order order){orderMapper.updateOrderById(order);}
//    日期查询
    public List<OrderSummary> getPriceSumByDay(int merchantId, Date startDate, Date endDate) {
        return orderMapper.getPriceSumByDay(merchantId,startDate, endDate);
    }
}