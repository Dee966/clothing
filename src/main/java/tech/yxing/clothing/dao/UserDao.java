package tech.yxing.clothing.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import tech.yxing.clothing.pojo.po.Orders;
import tech.yxing.clothing.pojo.po.User;
import tech.yxing.clothing.pojo.vo.UserVo;

import java.util.List;

@Mapper
@Repository
public interface UserDao {
    @Select("select * from user where user_id=#{userId}")
    User showInfo(int userId);

    @Update("update user set balance=balance-#{total} where user_id=#{userId}")
    void userPay(Orders orders);

    @Update("update user set " +
            "name=#{name},telephone=#{telephone},sex=#{sex}," +
            "birthday=#{birthday},area=#{area},wechat=#{wechat}," +
            "address=#{address} where user_id=#{userId}")
    void insertUserInfo(User user);

    @Select("select * from user where username=#{username}")
    User getByUsername(String username);

    @Insert("insert into user values(null,#{username},#{password},null,null,null,null,null,null,null,null)")
    @Options(useGeneratedKeys = true,keyProperty = "userId",keyColumn = "user_id")
    Integer registerUser(User user);

    @Select("select * from user where username=#{username}")
    User getUserByUname(String username);

    @Delete("delete from user where user_id = #{userId}")
    void delUser(int userId);

    @Select("select * from user")
    List<User> listUser();

}
