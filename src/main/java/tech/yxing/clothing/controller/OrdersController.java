package tech.yxing.clothing.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.yxing.clothing.myshiro.MyShiro;
import tech.yxing.clothing.pojo.dto.AllOrdersDto;
import tech.yxing.clothing.pojo.dto.OrdersAndListGoodsDto;
import tech.yxing.clothing.pojo.po.Orders;
import tech.yxing.clothing.pojo.po.User;
import tech.yxing.clothing.pojo.vo.AppraiseVo;
import tech.yxing.clothing.pojo.vo.ChangeStateVo;
import tech.yxing.clothing.pojo.vo.OrdersListVo;
import tech.yxing.clothing.rebbitmq.MQReceiver;
import tech.yxing.clothing.rebbitmq.MQSender;
import tech.yxing.clothing.result.CodeMsg;
import tech.yxing.clothing.result.Result;
import tech.yxing.clothing.service.OrdersService;
import tech.yxing.clothing.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author Joe
 * @Date 2019-12-26 10:07
 */
@Api(tags = "用户端-订单服务接口列表")
@RestController
@RequestMapping("orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private UserService userService;
    @Autowired
    private MQReceiver mqReceiver;
    @Autowired
    private MQSender mqSender;
    /**
     * @methodDesc: 用户购买商品
     * @Param: ordersList
     * @return: Result<Orders>
     * @Author: Joe
     */
    @ApiOperation(value = "添加订单", notes = "用户端-通过页面传值把商品的信息提交到确认订单页面，点击提交订单把该订单信息存到order_list、order，并在order_log里记录下单时间。")
    @PostMapping("/orders_submit")
    public Result<Orders> ordersSubmit(HttpServletRequest request, @RequestBody OrdersListVo ordersListVo){
        String username = MyShiro.drawUsername(request);
        User user = userService.getUserByUname(username);
        if (user == null){
            return Result.error(CodeMsg.NOT_YET_LOGIN);
        }
        Integer result = MyShiro.checkToken(request.getHeader("Authorization"),user.getUsername(),user.getPassword());
        if (result == null) {
            //为null返回错误信息
            return Result.error(CodeMsg.NOT_YET_LOGIN);
        }
        //result不为null继续操作
        return Result.success(ordersService.ordersSubmit(ordersListVo));
    }

    /**
     * @methodDesc: 用户支付
     * @Param: orderId
     * @return: Result<Object>
     * @Author: Joe
     */
    @ApiOperation(value = "用户支付", notes = "用户端-提交订单后根据订单id，查询该订单的价钱，完成支付，在order_log中记录支付时间提示支付成功，返回首页")
    @PutMapping("/order_pay/{orderId}")
    public Result<Object> ordersPay(@PathVariable int orderId){
        ordersService.ordersPay(orderId);
        return Result.success(null);
    }

    /**
     * @methodDesc: 查询所有订单
     * @Param: userId
     * @return: Result<>
     * @Author: Joe
     */
    @ApiOperation(value = "查询所有订单", notes = "用户端-用户id查询所有订单")
    @GetMapping("/orders_show/{userId}")
    public Result<List<Orders>> ordersShow(@PathVariable int userId){
        return Result.success(ordersService.ordersShow(userId));
    }

    /**
     * @methodDesc: 查询某订单状态的订单
     * @Param: state,userId
     * @return: Result<List<Orders>>
     * @Author: Joe
     */
    @ApiOperation(value = "查询某状态订单", notes = "用户端-查询某订单状态的订单")
    @GetMapping("/orders_state")
    public Result<List<OrdersAndListGoodsDto>> ordersState(int state, int userId){
        return Result.success(ordersService.ordersState(state,userId));
    }

    /**
     * @methodDesc: 用户对某商品的评价
     * @Param: appraise,orderListId,userId
     * @return: Result<>
     * @Author: Joe
     */
    @ApiOperation(value = "添加评论", notes = "用户端-当订单查询到为待评论状态，点击待评论跳转到评论页面（携带该商品的订单列表信息），在评论页面提交评论，把评论和订单列表id传到后端，添加评论")
    @PutMapping("/orders_appraise/{orderListId}")
    public Result<Object> ordersAppraise(@PathVariable int orderListId, @RequestBody AppraiseVo appraiseVo){
        ordersService.goodsAppraise(orderListId,appraiseVo.getAppraise());
        return Result.success(null);
    }

    /**
     * @methodDesc: 管理员查询所有订单
     * @Param:
     * @return: Result<List<Orders>>
     * @Author: Joe
     */
    @ApiOperation(value = "查询所有订单", notes = "管理员端-获取用户下的所有订单")
    @GetMapping("/orders_show_all")
    public Result<List<Orders>> ordersShowAll(){
        return Result.success(ordersService.ordersShowAll());
    }

    /**
     * @methodDesc: 管理员修改订单状态,记录日志
     * @Param: orderId,managerId
     * @return: Result<>
     * @Author: Joe
     */
    @PutMapping("/orders_log")
    @ApiOperation(value = "修改订单状态,记录日志", notes = "管理员端-管理员通过orderId修改订单状态,记录日志")
    public Result<Object> ordersLog(@RequestBody ChangeStateVo changeStateVo){
        ordersService.ordersLog(changeStateVo);
        return Result.success(null);
    }
    /**
     * @methodDesc: 获取评论
     * @Param: goodsId
     * @return: Result<String>
     * @Author: Joe
     */
    @ApiOperation(value = "显示某商品所有评价", notes = "用户端-查询某商品的累计评价")
    @GetMapping("/goods_appraise/{goodsId}")
    public Result<List<String>> ordersAppraise(@PathVariable int goodsId){
        System.out.println(goodsId);
        return Result.success(ordersService.ordersAppraise(goodsId));
    }

    /**
     * @methodDesc: 获取所有订单（AllOrderDto）
     * @Param: userId
     * @return: Result<AllOrderDto>
     * @Author: Joe
     */
    @ApiOperation(value = "用户查询所有订单", notes = "用户端-用户查询所有订单")
    @GetMapping("/get_orders/{userId}")
    public Result<List<AllOrdersDto>> getOrders(@PathVariable int userId){
        System.out.println(userId);
        return Result.success(ordersService.getOrders(userId));
    }

    /**
     * @methodDesc: 查询某订单状态的订单
     * @Param: state,userId
     * @return: Result<List<AllOrdersDto>>
     * @Author: Joe
     */
    @ApiOperation(value = "查询某状态订单", notes = "用户端-查询某订单状态的订单")
    @GetMapping("/get_state")
    public Result<List<AllOrdersDto>> getStateOrder(int state,int userId){
        return Result.success(ordersService.getStateOrder(state,userId));
    }

    /**
     * @methodDesc: 确认收货
     * @Param: orderId
     * @return: Result<>
     * @Author: Joe
     */
    @ApiOperation(value = "修改订单状态2-->3", notes = "用户端-用户确认订单")
    @PutMapping("/confirm_goods/{orderId}")
    public Result<Object> confirmGoods(@PathVariable int orderId){
        ordersService.confirmGoods(orderId);
        return Result.success(null);
    }

    @PostMapping("/mq")
    public Result<String> mq(){
        mqSender.send("hello,imooc");
        return Result.success("Hello , world");
    }

    @PostMapping("/mq/topic")
    public Result<String> topic(){
        mqSender.sendTopic("hello,imooc");
        return Result.success("Hello , world");
    }

    @PostMapping("/mq/fanout")
    public Result<String> fanout(){
        mqSender.sendFanout("hello,imooc");
        return Result.success("Hello , world");
    }

    @PostMapping("/mq/header")
    public Result<String> header(){
        mqSender.sendHeader("hello,imooc");
        return Result.success("Hello , world");
    }
}
