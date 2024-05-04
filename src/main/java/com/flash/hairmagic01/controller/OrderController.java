package com.flash.hairmagic01.controller;

import com.flash.hairmagic01.common.Result;
import com.flash.hairmagic01.entity.Order;
import com.flash.hairmagic01.entity.OrderSummary;
import com.flash.hairmagic01.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 功能：
 * 作者：whitebuless
 * 日期：2024/4/27 10:11
 */
@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/getbyall")
    public Result getOrdersByOrderTime(@RequestBody Order order) {
        return Result.success(orderService.findByOrderTime(order));
    }

    @PostMapping("/add")
    public  Result addOrder(@RequestBody Order order){
        try{
            orderService.addOrder(order);
        }catch (Exception e){
            if(e instanceof DuplicateKeyException)
                return Result.error("预约插入数据库错误，请联系管理员");
            else
                return Result.error("系统错误，请联系管理员或稍后再试");
        }
        return Result.success("预约成功");
    }
//    更新订单状态
    @PostMapping("/update")
    public Result updateOrderById(@RequestBody Order order){
        orderService.updateOrderById(order);
        return Result.success("更新成功");
    }

//    日期查询
    @GetMapping("/priceSumByDay")
    public List<OrderSummary> getPriceSumByDay(
            @RequestParam int merchantId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return orderService.getPriceSumByDay(merchantId,startDate, endDate);
    }
}
