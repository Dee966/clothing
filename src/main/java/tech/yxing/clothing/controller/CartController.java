package tech.yxing.clothing.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.yxing.clothing.myshiro.MyShiro;
import tech.yxing.clothing.pojo.dto.CartAndGoodsDto;
import tech.yxing.clothing.pojo.po.Orders;
import tech.yxing.clothing.pojo.po.ShoppingCart;
import tech.yxing.clothing.pojo.po.User;
import tech.yxing.clothing.pojo.vo.CartVo;
import tech.yxing.clothing.result.CodeMsg;
import tech.yxing.clothing.result.Result;
import tech.yxing.clothing.service.CartService;
import tech.yxing.clothing.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author Joe
 * @Date 2019-12-26 10:07
 */
@Api(tags = "用户端-购物车服务接口列表")
@RestController
@RequestMapping("cart")
public class CartController {

    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;
    /**
     * @Param:
     * @return: Result<>
     * @Author: Joe
     */
    @ApiOperation(value = "商品加入购物车", notes = "用户端-商品添加购物车")
    @PostMapping("/cart_insert")
    public Result<Object> cartInsert(HttpServletRequest request, @RequestBody CartVo cartVo){
        //提取token的username
        String username = MyShiro.drawUsername(request);
        User user = userService.getUserByUname(username);
        if (user == null){
            return Result.error(CodeMsg.NOT_YET_LOGIN);
        }
        Integer result = MyShiro.checkToken(request.getHeader("Authorization"),user.getUsername(),user.getPassword());
        //判断result是否为null
        if (result == null) {
            //为null返回错误信息
            return Result.error(CodeMsg.NOT_YET_LOGIN);
        }
        //result不为null继续操作
        cartService.cartInsert(cartVo);
        return Result.success(null);
    }

    /**
     * @Param: userId
     * @return: List<ShoppingCart>
     * @Author: Joe
     */
    @ApiOperation(value = "购物车列表", notes = "用户端-显示购物车")
    @GetMapping("/cart_show")
    public Result<List<CartAndGoodsDto>> cartShow(HttpServletRequest request){
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
//        //result不为null继续操作
        return Result.success(cartService.cartShow(user.getUserId()));
    }

    /**
     * @Param: cartId
     * @return: Result
     * @Author: Joe
     */
    @ApiOperation(value = "删除购物车", notes = "用户端-删除购物车")
    @DeleteMapping("/cart_delete/{cartId}")
    public Result<Object> cartDelete(@PathVariable int cartId){
        cartService.cartDelete(cartId);
        return Result.success(null);
    }

    /**
     * @methodDesc: 通过购物车下单
     * @Param: shoppingCart
     * @return: Result<>
     * @Author: xiaoman
     */
    @ApiOperation(value = "购物车下单", notes = "用户端-通过购物车下单")
    @PostMapping("/cart_order")
    public Result<Orders> cartOrder(@RequestBody List<ShoppingCart> shoppingCartList){
        System.out.println("check:"+shoppingCartList);
        return Result.success(cartService.cartOrder(shoppingCartList));
    }
}
