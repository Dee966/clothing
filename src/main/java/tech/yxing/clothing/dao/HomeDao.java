package tech.yxing.clothing.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tech.yxing.clothing.pojo.po.Goods;
import tech.yxing.clothing.pojo.po.Rotation;

import java.util.List;

@Mapper
@Repository
public interface HomeDao {
    @Insert("INSERT INTO rotation VALUES( null,#{imge}, #{goodsId}, #{managerId} );")
    void rotationUpload(Rotation rotation);

    @Select("SELECT * FROM goods WHERE name LIKE CONCAT(CONCAT('%',#{keyWord},'%'));")
    List<Goods> search(String keyWord);

    @Select("select * from rotation;")
    List<Rotation> rotationChart();

    @Select("delete from rotation where rotation_id=#{rotationId}")
    void rotationDelete(int rotationId);
}
