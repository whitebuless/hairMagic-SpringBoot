package com.flash.hairmagic01.service;

import com.flash.hairmagic01.entity.Share;
import com.flash.hairmagic01.mapper.ShareMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能：
 * 作者：whitebuless
 * 日期：2024/4/14 18:45
 */
@Service
public class ShareService {
    @Autowired
    ShareMapper shareMapper;
    public Share[] findShare(String title){
        Share[] shares=shareMapper.findShare((title));
        return shares;
    }

    public void uploadShare(Share share){
        shareMapper.uploadShare(share);
    }

//    通过商家id查询所属的share
    public  Share[] findShareByShopId(int id){return shareMapper.findShareByShopId(id);}
}


