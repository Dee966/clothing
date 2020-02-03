package tech.yxing.clothing.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.yxing.clothing.exception.GlobleException;
import tech.yxing.clothing.myshiro.MyShiro;
import tech.yxing.clothing.pojo.po.User;
import tech.yxing.clothing.pojo.vo.UserVo;
import tech.yxing.clothing.redis.RedisService;
import tech.yxing.clothing.redis.UserKey;
import tech.yxing.clothing.result.CodeMsg;
import tech.yxing.clothing.result.Result;
import tech.yxing.clothing.service.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Joe
 * @Date 2019-12-26 10:07
 */
@Api(tags = "用户端-用户服务接口列表")
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;

    /**
     * @methodDesc: 用户信息管理
     * @Param: userVo
     * @return: Result<UserVo>
     * @Author: Joe
     */
    @ApiOperation(value = "修改用户信息管理", notes = "用户端-添加用户信息")
    @PutMapping("/user_info/{userId}")
    public Result<UserVo> insertUserInfo(@PathVariable int userId,@RequestBody UserVo userVo){
        logger.info(userId+userVo.toString());
        return Result.success(userService.insertUserInfo(userId,userVo));
    }

    /**
     * @methodDesc: 用户信息管理
     * @Param: userVo
     * @return: Result<UserVo>
     * @Author: Joe
     */
    @ApiOperation(value = "修改用户信息管理", notes = "用户端-添加用户信息")
    @PutMapping("/update_info")
    public Result<UserVo> updateUserInfo(HttpServletRequest request,@RequestBody UserVo userVo){
        logger.info(userVo.toString());
        String token = request.getHeader("Authorization");
        logger.info("token:"+token);
        if (token == null || "".equals(token)){
            return Result.error(CodeMsg.NOT_YET_LOGIN);
        }
        String username = token.substring(0,token.indexOf("."));
        logger.info("username:"+username);
        User user = userService.getUserByUname(username);
        logger.info(user.toString());
        if (user == null){
            return Result.error(CodeMsg.NOT_YET_LOGIN);
        }
        Integer result = MyShiro.checkToken(token,user.getUsername(),user.getPassword());
        //判断result是否为null
        if (result == null) {
            //为null返回错误信息和登录url
            return Result.error(CodeMsg.NOT_YET_LOGIN);
        }
        return Result.success(userService.insertUserInfo(user.getUserId(),userVo));
    }


    /**
     * @methodDesc: 用户信息管理（查询）
     * @Param: userId
     * @return: Result<User>
     * @Author: Joe
     */
    @ApiOperation(value = "查询用户信息", notes = "用户端-查询用户信息")
    @GetMapping("/user_info")
    public Result<UserVo> getUserInfo(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        logger.info("token:"+token);
        if (token == null || "".equals(token)){
            return Result.error(CodeMsg.NOT_YET_LOGIN);
        }
        String username = token.substring(0,token.indexOf("."));
        logger.info("username:"+username);
//        User user = redisService.get(UserKey.usrByUname, ""+username, User.class);
        User user = userService.getUserByUname(username);
        logger.info(user.toString());
        if (user == null){
            return Result.error(CodeMsg.NOT_YET_LOGIN);
        }
        Integer result = MyShiro.checkToken(token,user.getUsername(),user.getPassword());
        //判断result是否为null
        if (result == null) {
            //为null返回错误信息和登录url
            return Result.error(CodeMsg.NOT_YET_LOGIN);
        }
        UserVo userVo = userService.getUserInfo(user.getUserId());
        return Result.success(userVo);
    }

    /**
     * @methodDesc: 显示余额（查询）
     * @Param: token
     * @return: Result<Double>
     * @Author: Joe
     */
    @ApiOperation(value = "查询用户信息", notes = "用户端-查询用户信息")
    @GetMapping("/user_balance")
    public Result<String> getBalance(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        logger.info("token:"+token);
        if (token == null || "".equals(token)){
            return Result.error(CodeMsg.NOT_YET_LOGIN);
        }
        String username = token.substring(0,token.indexOf("."));
        logger.info("username:"+username);
        User user = userService.getUserByUname(username);
        logger.info(user.toString());
        if (user == null){
            return Result.error(CodeMsg.NOT_YET_LOGIN);
        }
        Integer result = MyShiro.checkToken(token,user.getUsername(),user.getPassword());
        //判断result是否为null
        if (result == null) {
            //为null返回错误信息和登录url
            return Result.error(CodeMsg.NOT_YET_LOGIN);
        }
        Double balance = userService.getBalance(user.getUserId());
        return Result.success(String.valueOf(balance));
    }
}
