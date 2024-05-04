package com.flash.hairmagic01.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 功能：
 * 作者：whitebuless
 * 日期：2024/5/3 13:11
 */
@Data
public class OrderSummary {
    private Date orderDate;
    private BigDecimal totalPrice;
}
