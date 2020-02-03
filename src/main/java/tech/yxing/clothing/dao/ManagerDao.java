package tech.yxing.clothing.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import tech.yxing.clothing.pojo.po.Manager;

@Mapper
@Repository
public interface ManagerDao {
    @Select("select * from manager where manager_id=#{managerId};")
    Manager managerShow(int managerId);

    @Update("update manager set " +
            "name=#{name},sex=#{sex},telephone=#{telephone},work_time=#{workTime} " +
            "where manager_id=#{managerId}")
    void managerInfo(Manager manager);

    @Select("select * from manager where username=#{username}")
    Manager getByUsername(String username);

    @Insert("insert into manager values(null,#{username},#{password},null,null,null,null)")
    @Options(useGeneratedKeys = true,keyProperty = "managerId",keyColumn = "manager_id")
    Integer managerRegister(Manager manager);


}
