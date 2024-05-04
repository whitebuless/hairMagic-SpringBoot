package com.flash.hairmagic01.mapper;

import com.flash.hairmagic01.entity.Staff;
import org.apache.ibatis.annotations.*;

@Mapper
public interface StaffMapper {
    @Select("select * from staff where merchant=#{id}")
    Staff[] findStaffByMerchantId(int id);
    @Insert("insert into `staff`(`name`,`phone_number`,`merchant`,`price`,`merchant_name`,`detail`,`years`,`gender`) " +
            "values(#{name},#{phoneNumber},#{merchant},#{price},#{merchantName},#{detail},#{years},#{gender})")
    void addStaff(Staff staff);
    @Delete("DELETE FROM `staff` where id=#{id}")
    void deleteStaffById(int id);

    @Update("UPDATE staff SET " +
            "name = #{name}, " +
            "phone_number = #{phoneNumber}, " +
            "price = #{price}, " +
            "detail = #{detail}, " +
            "years = #{years}, " +
            "gender = #{gender} " +
            "WHERE id = #{id}")
    void updateStaff(Staff staff);
}
