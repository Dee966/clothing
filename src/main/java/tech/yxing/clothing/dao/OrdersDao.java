package tech.yxing.clothing.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import tech.yxing.clothing.pojo.dto.ListAndGoodsDto;
import tech.yxing.clothing.pojo.dto.OrdersAndListGoodsDto;
import tech.yxing.clothing.pojo.dto.OrdersAndOrdersListDto;
import tech.yxing.clothing.pojo.po.Goods;
import tech.yxing.clothing.pojo.po.Orders;
import tech.yxing.clothing.pojo.po.OrdersList;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface OrdersDao {
    @Insert("insert into orders " +
            "values(null,#{orderTime},#{total},#{state},#{name},#{address},#{telephone},#{userId})")
    @Options(useGeneratedKeys = true,keyProperty = "orderId",keyColumn = "order_id")
    void insertOrder(Orders orders);

    @Insert("insert into orders_list " +
            "values(null,#{size},#{quantities},#{total},#{goodsId},#{orderId},#{appraise},#{userId},#{price})")
    void insertOrderList(OrdersList ordersList);

    @Update("update orders set state=1 where order_id=#{orderId}")
    //@Options(useGeneratedKeys = true,keyProperty = "orderId",keyColumn = "order_id")
    void ordersPay(int orderId);

    @Select("select * from orders where order_id=#{orderId}")
    Orders findUserByOrderId(int orderId);

    @Insert("insert into orders_log " +
            "values(null,#{orderTime},null,null,null,#{orderId},null)")
    void markOrderTime(@Param("orderTime") Date orderTime,@Param("orderId") int orderId);

    @Update("update orders_log set pay_time=#{payTime} where order_id=#{orderId}")
    void markPayTime(@Param("payTime") Date payTime,@Param("orderId") int orderId);

    @Select("select * from orders where user_id=#{userId}")
    List<Orders> ordersShow(int userId);

    @Select("select * from orders where user_id=#{userId} and state=#{state} order by order_id desc")
    List<Orders> ordersState(@Param("state") int state,@Param("userId") int userId);

    @Select("select * from orders_list where order_id=#{orderId}")
    List<OrdersList> getOrderList(int orderId);

    @Select("select * from orders order by order_id desc")
    List<Orders> ordersShowAll();

    @Update("update orders set state=#{state} where order_id=#{orderId}")
    void orderOut(@Param("state") int state,@Param("orderId") int orderId);

    @Update("update orders_log set out_time=#{outTime},manager_id=#{managerId} where order_id=#{orderId}")
    void ordersLog(@Param("orderId") int orderId,@Param("managerId") int managerId,@Param("outTime") Date outTime);

    @Update("update orders_list set appraise=#{appraise} where orders_list_id=#{orderListId}")
    void goodsAppraise(@Param("orderListId") int orderListId,@Param("appraise") String appraise);

    @Select("select * from orders_list where goods_id=#{goodsId}")
    List<OrdersList> ordersAppraise(int goodsId);

    @Select("select * from orders_list where user_id=#{userId} order by orders_list_id desc")
    List<OrdersList> getOrderListByUserId(int userId);

    @Select("select * from goods where goods_id=#{goodsId}")
    Goods getGoodsByGoodsId(int goodsId);

    @Select("select * from orders where order_id=#{orderId}")
    Orders getOrdersByOrderId(int orderId);

    @Select("select * from orders where user_id=#{userId} and state=#{state}")
    @Results({
            @Result(property = "orderId", column = "order_id"),
            @Result(property = "listAndGoodsDtos", column = "order_id", many = @Many(select = "tech.yxing.clothing.dao.OrdersDao.listListAndGoodsDto"))
    })
    List<OrdersAndListGoodsDto> listOrdersAndListGoodsDto(@Param("state") int state,@Param("userId") int userId);


    @Select("select * from orders_list where order_id=#{orderId}")
    @Results({
            @Result(property = "goodsId", column = "goods_id"),
            @Result(property = "goodsList", column = "goods_id", many = @Many(select = "tech.yxing.clothing.dao.OrdersDao.listGoodsList"))
    })
    List<ListAndGoodsDto> listListAndGoodsDto(int orderId);


    @Select("select * from goods where goods_id=#{goodsId}")
    List<Goods> listGoodsList(int goodsId);









    @Select("select * from orders where user_id=#{userId} and state=#{state}")
    @Results({
            @Result(property = "orderId", column = "order_id"),
            @Result(property = "ordersLists",javaType = List.class, column = "order_id", many = @Many(select = "tech.yxing.clothing.dao.OrdersDao.listOrdersList"))
    })
    List<OrdersAndOrdersListDto> listOrdersAndOrdersList(@Param("state") int state, @Param("userId") int userId);

    @Select("select * from orders_list where order_id=#{orderId}")
    List<OrdersList> listOrdersList(int orderId);

    @Update("update orders set state=3 where order_id=#{orderId}")
    void confirmGoods(int orderId);

    @Update("update orders_log set get_time=#{getTime} where order_id=#{orderId}")
    void logGetTime(Date getTime);
}
