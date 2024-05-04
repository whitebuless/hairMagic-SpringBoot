package com.flash.hairmagic01.mapper;

import com.flash.hairmagic01.entity.Order;
import com.flash.hairmagic01.entity.OrderSummary;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Mapper
public interface OrderMapper {

    @SelectProvider(type = OrderSqlProvider.class, method = "findOrderByAll")
    Order[] findOrderByAll(Order merchant);

    @UpdateProvider(type = OrderSqlProvider.class, method = "updateOrderById")
    void updateOrderById(Order order);

    @Insert("insert into `order`(`name`,`phone_number`,`price`,`client_id`,`merchant_id`,`number`,`status`,`order_time`,`create_time`) " +
            "VALUES(#{name},#{phoneNumber},#{price},#{clientId},#{merchantId},#{number},#{status},#{orderTime},NOW())")
    void addOrder(Order order);

//    日期查询
    @Select("SELECT DATE(order_time) AS order_date, SUM(price) AS total_price " +
            "FROM `order` " +
            "WHERE merchant_id = #{merchantId} AND status = '已完成' AND order_time BETWEEN #{startDate} AND #{endDate} " +
            "GROUP BY DATE(order_time) ORDER BY DATE(order_time)")
    List<OrderSummary> getPriceSumByDay(@Param("merchantId") int merchantId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    class OrderSqlProvider {
        public String findOrderByAll(Order order) {
            return new SQL() {{
                SELECT("*");
                FROM("`order`"); // 注意这里是 order 表名，不是 merchant
                if (order.getId() != 0) {
                    WHERE("id = #{id}");
                }
                if (order.getName() != null && !order.getName().isEmpty()) {
                    WHERE("name = #{name}");
                }
                if (order.getPhoneNumber() != null && !order.getPhoneNumber().isEmpty()) {
                    WHERE("phone_number = #{phoneNumber}");
                }
                if (order.getClientId() != 0) {
                    WHERE("client_id = #{clientId}");
                }
                if (order.getMerchantId() != 0) {
                    WHERE("merchant_id = #{merchantId}");
                }
                if (order.getNumber() != 0) {
                    WHERE("number = #{number}");
                }
                if (order.getStatus() != null && !order.getStatus().isEmpty()) {
                    WHERE("status = #{status}");
                }
                if (order.getOrderTime() != null) {
                    WHERE("DATE(order_time) = DATE(#{orderTime})");
                }
                if (order.getCreateTime() != null) {
                    WHERE("create_time = #{createTime}");
                }
            }}.toString();
        }

        public String updateOrderById(Order order) {
            return new SQL() {{
                UPDATE("`order`");
                if (order.getName() != null) {
                    SET("name = #{name}");
                }
                if (order.getPhoneNumber() != null) {
                    SET("phone_number = #{phoneNumber}");
                }
                if (order.getClientId() != 0) {
                    SET("client_id = #{clientId}");
                }
                if (order.getMerchantId() != 0) {
                    SET("merchant_id = #{merchantId}");
                }
                if (order.getNumber() != 0) {
                    SET("number = #{number}");
                }
                if (order.getPrice() != 0) {
                    SET("price = #{price}");
                }
                if (order.getStatus() != null) {
                    SET("status = #{status}");
                }
                if (order.getOrderTime() != null) {
                    SET("order_time = #{orderTime}");
                }
                WHERE("id = #{id}");
            }}.toString();
        }
    }







}