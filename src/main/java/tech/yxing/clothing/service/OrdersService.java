package tech.yxing.clothing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.yxing.clothing.dao.GoodsDao;
import tech.yxing.clothing.dao.OrdersDao;
import tech.yxing.clothing.dao.UserDao;
import tech.yxing.clothing.exception.GlobleException;
import tech.yxing.clothing.pojo.dto.AllOrdersDto;
import tech.yxing.clothing.pojo.dto.OrdersAndListGoodsDto;
import tech.yxing.clothing.pojo.po.Goods;
import tech.yxing.clothing.pojo.po.Orders;
import tech.yxing.clothing.pojo.po.OrdersList;
import tech.yxing.clothing.pojo.po.User;
import tech.yxing.clothing.pojo.vo.ChangeStateVo;
import tech.yxing.clothing.pojo.vo.OrdersListVo;
import tech.yxing.clothing.result.CodeMsg;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrdersService {
    @Autowired
    private OrdersDao ordersDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private GoodsDao goodsDao;

    /**
     * @methodDesc: 用户下单
     * @Param: orderId
     * @return:
     * @Author: Joe
     */
    public Orders ordersSubmit(OrdersListVo ordersListVo){
        //1.用ordersListVo里的userId把用户信息查出来
        User user = userDao.showInfo(ordersListVo.getUserId());
        //把数据写入订单表
        Orders orders = new Orders(
                null,
                new Date(),
                ordersListVo.getPrice() * ordersListVo.getQuantities(),
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
        OrdersList ordersList = new OrdersList(
                null,
                ordersListVo.getSize(),
                ordersListVo.getPrice(),
                ordersListVo.getQuantities(),
                orders.getTotal(),
                null,
                ordersListVo.getGoodsId(),
                orders.getOrderId(),
                user.getUserId()
        );
        ordersDao.insertOrderList(ordersList);
        //4.库存减去购买数量
        goodsDao.reduceStock(ordersList.getGoodsId(),ordersList.getQuantities());
        //5.减去该订单码数的数量
        goodsDao.reduceSizeStock(ordersList.getSize(),ordersList.getQuantities(),ordersList.getGoodsId());
        return orders;
    }

    /**
     * @methodDesc: 用户支付
     * @Param: orderId
     * @return:
     * @Author: Joe
     */
    public void ordersPay(int orderId){
        //1.改状态为1：已付款
        ordersDao.ordersPay(orderId);
        //2.查出该订单的所有信息取userId
        Orders orders = ordersDao.findUserByOrderId(orderId);
        //3.通过用户id修余额
        userDao.userPay(orders);
        //4.在orders_log里记录支付时间
        ordersDao.markPayTime(new Date(),orderId);
    }
    /**
     * @methodDesc: 用户订单查询
     * @Param: orderId
     * @return:
     * @Author: Joe
     */
    public List<Orders> ordersShow(int orderId){
        return ordersDao.ordersShow(orderId);
    }
    /**
     * @methodDesc: 用户查询某订单状态的订单
     * @Param: orderId
     * @return:
     * @Author: Joe
     */
    public List<OrdersAndListGoodsDto> ordersState(int state, int userId){
        List<OrdersAndListGoodsDto> ordersAndOrdersListDtos = ordersDao.listOrdersAndListGoodsDto(state,userId);
        System.out.println(ordersAndOrdersListDtos.toString());
        return ordersAndOrdersListDtos;
//        //1.根据订单状态和userId查出符合的订单  如：2个 1个
//        List<Orders> orders = ordersDao.ordersState(state,userId);
//        //1.1如果orders为空
//        if (orders.isEmpty()){
//            throw new GlobleException(CodeMsg.STATE_ORDER_NULL);
//        }
//        //2.根据上面符合订单的订单id查出订单列表
//        List<OrdersListDto> ordersListDtos = new ArrayList<>();
//        for (Orders order : orders) {//1次 1次
//            List<OrdersListVo> ordersListVos = new ArrayList<>();
//            List<OrdersList> listsOrdersList = ordersDao.getOrderList(order.getOrderId());
//            for (OrdersList ordersList: listsOrdersList) {//2次 一次
//                OrdersListVo ordersListVo = new OrdersListVo(ordersList.getSize(),ordersList.getPrice(),ordersList.getQuantities(),ordersList.getGoodsId(),ordersList.getUserId());
//                ordersListVos.add(ordersListVo);
//            }
//            OrdersListDto ordersListDto = new OrdersListDto(order.getOrderTime(),order.getTotal(),order.getState(),ordersListVos);
//
//            ordersListDtos.add(ordersListDto);
//        }
//        return ordersListDtos;

    }

    /**
     * @methodDesc: 管理员查询订单表里的所有订单
     * @Param:
     * @return:
     * @Author: Joe
     */
    public List<Orders> ordersShowAll(){
        List<Orders> orders = ordersDao.ordersShowAll();
        //如果orders为空
        if (orders.isEmpty()){
            throw new GlobleException(CodeMsg.ORDER_NULL);
        }
        //不为空返回orders
        return orders;
    }

    /**
     * @methodDesc: 管理员修改订单状态,记录日志
     * @Param: orderId,managerId
     * @return:
     * @Author: Joe
     */
    public void ordersLog(ChangeStateVo changeStateVo){
        int state = 2;
        ordersDao.orderOut(state,changeStateVo.getOrderId());
        ordersDao.ordersLog(changeStateVo.getOrderId(),changeStateVo.getManagerId(),new Date());

    }

    /**
     * @methodDesc: 用户收货后对商品进行评价
     * @Param: orderListId,appraise
     * @return:
     * @Author: Joe
     */
    public void goodsAppraise(int orderListId,String appraise){
        ordersDao.goodsAppraise(orderListId,appraise);
    }

    /**
     * @methodDesc: 查询某商品所有评价
     * @Param: goodsId
     * @return: Result<List<String>>
     * @Author: Joe
     */
    public List<String> ordersAppraise(int goodsId){
        List<OrdersList> ordersLists = ordersDao.ordersAppraise(goodsId);
        List<String> list = new ArrayList<>();
        for (OrdersList ordersList : ordersLists) {
            if (ordersList.getAppraise() != null){
                list.add(ordersList.getAppraise());
                System.out.println(ordersList.getAppraise());
            }
        }
        return list;
    }

    public List<AllOrdersDto> getOrders(int userId){
        List<AllOrdersDto> allOrdersDtos = new ArrayList<>();
        List<OrdersList> ordersLists = ordersDao.getOrderListByUserId(userId);
        for (OrdersList ordersList : ordersLists) {
            if (ordersList.getGoodsId() == null){
                continue;
            }
            Goods goods = ordersDao.getGoodsByGoodsId(ordersList.getGoodsId());
            Orders orders = ordersDao.getOrdersByOrderId(ordersList.getOrderId());
            String state = "";
            if (orders.getState() == 0) state = "待付款";
            if (orders.getState() == 1) state = "待发货";
            if (orders.getState() == 2) state = "待收货";
            if (orders.getState() == 3) state = "待评价";
            AllOrdersDto allOrdersDto = new AllOrdersDto(
                    goods.getName(),
                    goods.getImge(),
                    ordersList.getSize(),
                    ordersList.getPrice(),
                    ordersList.getQuantities(),
                    ordersList.getTotal(),
                    state,
                    ordersList.getOrdersListId(),
                    orders.getOrderId(),
                    orders.getOrderTime()
            );
            allOrdersDtos.add(allOrdersDto);
        }
        return allOrdersDtos;
    }

    public List<AllOrdersDto> getStateOrder(int state,int userId){
//        List<OrdersAndListGoodsDto> ordersAndOrdersListDtos = ordersDao.listOrdersAndListGoodsDto(state,userId);
//        return ordersAndOrdersListDtos;
        List<AllOrdersDto> allOrdersDtos = new ArrayList<>();
        String orderState = "";
        if (state == 0) orderState = "待付款";
        if (state == 1) orderState = "待发货";
        if (state == 2) orderState = "待收货";
        if (state == 3) orderState = "待评价";
        List<Orders> orders = ordersDao.ordersState(state,userId);
        for (Orders order : orders) {
            List<OrdersList> ordersLists = ordersDao.getOrderList(order.getOrderId());
            for (OrdersList ordersList : ordersLists) {
                if (ordersList.getGoodsId() == null){
                    continue;
                }
                Goods goods = ordersDao.getGoodsByGoodsId(ordersList.getGoodsId());
                AllOrdersDto allOrdersDto = new AllOrdersDto(
                        goods.getName(),
                        goods.getImge(),
                        ordersList.getSize(),
                        ordersList.getPrice(),
                        ordersList.getQuantities(),
                        ordersList.getTotal(),
                        orderState,
                        ordersList.getOrdersListId(),
                        order.getOrderId(),
                        order.getOrderTime()
                );
                allOrdersDtos.add(allOrdersDto);
            }
        }
        return allOrdersDtos;
    }

    public void confirmGoods(int orderId){
        ordersDao.confirmGoods(orderId);
        ordersDao.logGetTime(new Date());
    }
}
