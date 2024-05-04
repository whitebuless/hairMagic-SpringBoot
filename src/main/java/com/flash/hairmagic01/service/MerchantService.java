package com.flash.hairmagic01.service;

import com.flash.hairmagic01.entity.Merchant;
import com.flash.hairmagic01.exception.ServiceException;
import com.flash.hairmagic01.mapper.MerchantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能：
 * 作者：whitebuless
 * 日期：2024/4/16 19:27
 */
@Service
public class MerchantService {
    @Autowired
    MerchantMapper merchantMapper;

    public Merchant[] getMerchantByUserId(int id){
        Merchant[] dbMerchant=merchantMapper.findMerchantByUserId(id);
        if(dbMerchant.length==0){
            throw new ServiceException("该账号下没有店铺");
        }
        return dbMerchant;
    }

    public Merchant[] getMerchantByAll(Merchant merchant){
        Merchant[] dbMerchant=merchantMapper.findMerchantByAll(merchant);
        if(dbMerchant.length==0){
            throw new ServiceException("该账号下没有店铺");
        }
        return dbMerchant;
    }

//    新增
    public void addMerchant(Merchant merchant){
        merchantMapper.addMerchant(merchant);
    }
}
