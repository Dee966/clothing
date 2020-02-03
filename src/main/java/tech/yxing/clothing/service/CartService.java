package tech.yxing.clothing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.yxing.clothing.dao.CartDao;
import tech.yxing.clothing.dao.GoodsDao;
import tech.yxing.clothing.dao.OrdersDao;
import tech.yxing.clothing.dao.UserDao;
import tech.yxing.clothing.exception.GlobleException;
import tech.yxing.clothing.pojo.dto.CartAndGoodsDto;
import tech.yxing.clothing.pojo.po.*;
import tech.yxing.clothing.pojo.vo.CartVo;
import tech.yxing.clothing.result.CodeMsg;

import java.util.Date;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartDao cartDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private OrdersDao ordersDao;
    @Autowired
    private GoodsDao goodsDao;

    /**
     * @methodDesc: 商品加入购物车
     * @Param: cartVo
     * @return:
     * @Author: Joe
     */
    public void cartInsert(CartVo cartVo){
        double total = cartVo.getPrice() * cartVo.getQuantities();
        cartDao.cartInsert(new ShoppingCart(total,cartVo));
    }
    /**
     * @methodDesc: 查询所有购物车
     * @Param: userId
     * @return: List<ShoppingCart>
     * @Author: Joe
     */
    public List<CartAndGoodsDto> cartShow(int userId){
//        List<CartDto> cartDtos = new ArrayList<>();
//        List<ShoppingCart> shoppingCarts = cartDao.cartShow(userId);
        //shoppingCarts为空
//        if (shoppingCarts.isEmpty()){
//            throw new GlobleException(CodeMsg.CART_NULL);
//        }
//        //不为空
//        for (ShoppingCart shoppingCart : shoppingCarts) {
//            Goods goods = goodsDao.goodsShow(shoppingCart.getGoodsId());
//            CartDto cartDto = new CartDto(
//                    goods.getName(),
//                    goods.getImge(),
//                    shoppingCart.getSize(),
//                    shoppingCart.getPrice(),
//                    shoppingCart.getQuantities(),
//                    shoppingCart.getTotal(),
//                    shoppingCart.getCartId(),
//                    goods.getGoodsId()
//            );
//            cartDtos.add(cartDto);
//        }
//        return cartDtos;
        List<CartAndGoodsDto> cartAndGoodsDtos = cartDao.getCartAndGoods(userId);
        if (cartAndGoodsDtos.isEmpty()){
            throw new GlobleException(CodeMsg.CART_NULL);
        }
        return cartAndGoodsDtos;
    }
    /**
     * @methodDesc: 用cartId删除购物车
     * @Param: cartId
     * @return:
     * @Author: Joe
     */
    public void cartDelete(int cartId){
        cartDao.cartDelete(cartId);
    }
    /**
     * @methodDesc: 用购物车下单
     * @Param: shoppingCart
     * @return:
     * @Author: Joe
     */
    public Orders cartOrder(List<ShoppingCart> shoppingCartList){
        Double total = 0.0;
        //1.用shoppingCart里的userId把用户信息查出来
        User user = userDao.showInfo(shoppingCartList.get(0).getUserId());
        for (int i = 0;i < shoppingCartList.size();i++) {
            total = total + shoppingCartList.get(i).getTotal();
        }
        //把数据写入订单
        Orders orders = new Orders(
                null,
                new Date(),
                total,
                0,
                user.getName(),
                user.getAddress(),
                user.getTelephone(),
                user.getUserId()
        );
        ordersDao.insertOrder(orders);
        //2.在orders_log里记录下单时间
        ordersDao.markOrderTime(orders.getOrderTime(),orders.getOrderId());
        //3.把订单写入订单列表
        OrdersList ordersList = new OrdersList();
        for (ShoppingCart shoppingCart : shoppingCartList) {
            ordersList.setSize(shoppingCart.getSize());
            ordersList.setPrice(shoppingCart.getPrice());
            ordersList.setQuantities(shoppingCart.getQuantities());
            ordersList.setTotal(shoppingCart.getTotal());
            ordersList.setGoodsId(shoppingCart.getGoodsId());
            ordersList.setOrderId(orders.getOrderId());
            ordersList.setUserId(user.getUserId());
            ordersDao.insertOrderList(ordersList);
            //4.库存减去购买数量
            goodsDao.reduceStock(shoppingCart.getGoodsId(),shoppingCart.getQuantities());
            //5.减去该订单码数的数量
            goodsDao.reduceSizeStock(shoppingCart.getSize(),shoppingCart.getQuantities(),shoppingCart.getGoodsId());
            //6.删除已下单的购物车商品
            cartDao.cartDelete(shoppingCart.getCartId());
        }
        return orders;

    }
}
