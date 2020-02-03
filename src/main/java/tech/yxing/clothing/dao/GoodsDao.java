package tech.yxing.clothing.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import tech.yxing.clothing.pojo.po.Goods;
import tech.yxing.clothing.pojo.po.GoodsSize;
import tech.yxing.clothing.pojo.po.GoodsType;

import java.util.List;

@Mapper
@Repository
public interface GoodsDao {
    @Select("select * from goods;")
    List<Goods> goodsShowAll();

    @Select("select * from goods where goods_id=#{goodsId};")
    Goods goodsShow(int goodsId);

    @Select("select * from goods_type;")
    List<GoodsType> goodsType();

    @Select("select * from goods where goods_type_id=#{typeId}")
    List<Goods> typeShow(int typeId);

    @Delete("delete from goods_size where goods_id=#{goodsId};")
    void goodsDelete(int goodsId);

    @Delete("delete from goods where goods_id=#{goodsId};")
    void goodsSizeDelete(int goodsId);

    @Insert("insert into goods " +
            "values(null,#{name},#{price},#{imge},#{goodDate},#{desc},#{stock},#{goodsTypeId});")
    @Options(useGeneratedKeys = true,keyProperty = "goodsId",keyColumn = "goods_id")
    Integer goodsInsert(Goods goods);

    @Insert("insert into goods_size " +
            "values(null,#{goodsSize},#{goodsSizeStock},#{goodsId})")
    void goodsSizeInsert(GoodsSize goodsSize);

    @Update("update goods set stock=stock-#{quantities} where goods_id=#{goodsId}")
    void reduceStock(@Param("goodsId") int goodsId,@Param("quantities") int quantities);

    @Update("update goods_size set goods_size_stock=goods_size_stock-#{quantities} " +
            "where goods_id=#{goodsId} and goods_size=#{goodsSize}")
    void reduceSizeStock(@Param("goodsSize") String goodsSize,@Param("quantities") int quantities,@Param("goodsId") int goodsId);

    @Select("select * from goods_size where goods_id=#{goodsId}")
    List<GoodsSize> goodsSize(int goodsId);
}
