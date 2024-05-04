package com.flash.hairmagic01.controller;

import com.flash.hairmagic01.common.Result;
import com.flash.hairmagic01.entity.Staff;
import com.flash.hairmagic01.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

/**
 * 功能：
 * 作者：whitebuless
 * 日期：2024/4/17 19:31
 */
@CrossOrigin
@RestController
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    StaffService staffService;
    @GetMapping("/getbyid")
    public Result findStaffByMerchantId(int id){
        return Result.success(staffService.findStaffByMerchantId(id));
    }
    @PostMapping("/add")
    public Result addStaff(@RequestBody Staff staff){
        try{
            staffService.addStaff(staff);
        }catch (Exception e){
            if(e instanceof DuplicateKeyException)
                return Result.error("插入数据库错误");
            else
                return Result.error("系统错误");
        }
        return Result.success("新增成功");
    }
    @GetMapping("/delete")
    public Result deleteStaffById(int id){
        try{
            staffService.deleteStaffById(id);
        }catch (Exception e){
            if(e instanceof DuplicateKeyException)
                return Result.error("删除数据库信息错误");
            else
                return Result.error("删除失败");
        }
        return Result.success("删除成功");
    }
//    更新员工信息
    @PostMapping("/update")
    public Result updateStaffByIf(@RequestBody Staff staff){
        staffService.updateStaff(staff);
        return Result.success("更新成功");
    }

}