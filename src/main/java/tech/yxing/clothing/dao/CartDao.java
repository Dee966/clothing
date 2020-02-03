package tech.yxing.clothing.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import tech.yxing.clothing.pojo.dto.CartAndGoodsDto;
import tech.yxing.clothing.pojo.po.Goods;
import tech.yxing.clothing.pojo.po.ShoppingCart;

import java.util.List;

@Mapper
@Repository
public interface CartDao {
    @Insert("insert into shopping_cart " +
            "values(null,#{size},#{price},#{quantities},#{total},#{goodsId},#{userId})")
    void cartInsert(ShoppingCart shoppingCart);

    @Select("select * from shopping_cart where user_id=#{userId}")
    List<ShoppingCart> cartShow(int userId);

    @Delete("delete from shopping_cart where cart_id=#{cartId}")
    void cartDelete(int cartId);

    @Select("select * from shopping_cart where user_id=#{userId}")
    @Results({
            //备份goodsId
            @Result(property = "goodsId", column = "goods_id"),
            @Result(property = "goods", column = "goods_id", one = @One(select = "tech.yxing.clothing.dao.CartDao.listGoods"))
    })
    List<CartAndGoodsDto> getCartAndGoods(int userId);

    //结合getCartAndGoods查询
    @Select("select name,imge from goods where goods_id=#{goodsId};")

    Goods listGoods(int goodsId);

}
