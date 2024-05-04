package com.flash.hairmagic01.service;

import com.flash.hairmagic01.entity.Staff;
import com.flash.hairmagic01.mapper.StaffMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能：
 * 作者：whitebuless
 * 日期：2024/4/17 19:29
 */
@Service
public class StaffService {
    @Autowired
    StaffMapper staffMapper;

    public Staff[] findStaffByMerchantId(int id){
        Staff[] dbStaff=staffMapper.findStaffByMerchantId(id);
        return dbStaff;
    }
    public void addStaff(Staff staff){
        staffMapper.addStaff(staff);
    }
    public void deleteStaffById(int id){
        staffMapper.deleteStaffById(id);
    }
//    更新员工信息
    public void updateStaff(Staff staff){
        staffMapper.updateStaff(staff);
    }
}
