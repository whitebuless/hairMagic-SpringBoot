package com.flash.hairmagic01.mapper;

        import com.flash.hairmagic01.entity.Merchant;
        import org.apache.ibatis.annotations.Insert;
        import org.apache.ibatis.annotations.Mapper;
        import org.apache.ibatis.annotations.Select;
        import org.apache.ibatis.annotations.SelectProvider;
        import org.apache.ibatis.jdbc.SQL;

@Mapper
public interface MerchantMapper {
    @Select("select * from `merchant` where user_id=#{id}")
    Merchant[] findMerchantByUserId(int id);
//    @Select("select * from `merchant` where location=#{location}")
//    Merchant[] findMerchantByAll(Merchant merchant);

    @SelectProvider(type = MerchantSqlProvider.class, method = "findMerchantByAll")
    Merchant[] findMerchantByAll(Merchant merchant);
    class MerchantSqlProvider {
        public String findMerchantByAll(Merchant merchant) {
            return new SQL() {{
                SELECT("*");
                FROM("merchant");
                if (merchant.getId() != 0 ) {
                    WHERE("id = #{id}");
                }
                if (merchant.getName() != null && !merchant.getName().isEmpty()) {
                    WHERE("name = #{name}");
                }
                if (merchant.getUserId() != 0 ) {
                    WHERE("user_id = #{userId}");
                }
                if (merchant.getLocation() != null && !merchant.getLocation().isEmpty()) {
                    WHERE("location = #{location}");
                }
                if (merchant.getRank() != 0) {
                    WHERE("rank = #{rank}");
                }
                if (merchant.getCreatTime() != null) {
                    WHERE("create_time = #{createTime}");
                }
                // 可以根据其他条件继续添加 WHERE 子句
                // if (merchant.getXXX() != null) {
                //     WHERE("xxx = #{xxx}");
                // }
            }}.toString();
        }
    }

    @Insert("insert into `merchant`(`name`,`user_id`,`description`,`location`,`rank`,`create_time`,`phone_number`) " +
            "values(#{name},#{userId},#{description},#{location},4,NOW(),#{phoneNumber})")
    void addMerchant(Merchant merchant);
}
