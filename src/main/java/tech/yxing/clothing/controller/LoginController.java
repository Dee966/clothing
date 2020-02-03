package tech.yxing.clothing.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.yxing.clothing.exception.GlobleException;
import tech.yxing.clothing.pojo.vo.LoginVo;
import tech.yxing.clothing.pojo.vo.TokenId;
import tech.yxing.clothing.result.CodeMsg;
import tech.yxing.clothing.result.Result;
import tech.yxing.clothing.service.UserService;
import tech.yxing.clothing.utils.JwtUtils;

import java.io.UnsupportedEncodingException;

/**
 * @Author Joe
 * @Date 2019-12-26 10:07
 */
@Api(tags = "用户端-登录服务接口列表")
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;
    /**
     * @methodDesc: 用户登录
     * @Param: username.password
     * @return: Result<>
     * @Author: xiaoman
     */

    @ApiOperation(value = "用户登录", notes = "用户端-用户登录")
    @PostMapping("/user_login")
    public Result<TokenId> userLogin(@RequestBody LoginVo loginVo){

        System.out.println(loginVo.toString());
//        LoginVo loginVo = new LoginVo(username,password);
        return Result.success(userService.userLogin(loginVo));
    }

    /**
     * @methodDesc: 用户注册
     * @Param: username.password
     * @return: Result<> userId
     * @Author: xiaoman
     */
    @ApiOperation(value = "用户注册", notes = "用户端-用户注册")
    @PostMapping("/user_register")
    public Result<Integer> managerLogin(@RequestBody LoginVo loginVo){
        return Result.success(userService.userRegister(loginVo));
    }

    /**
     * @methodDesc: 用户登录 测试接口
     * @Param: username.password
     * @return: Result<>
     * @Author: xiaoman
     */

    @ApiOperation(value = "用户登录-测试", notes = "用户端-用户登录-测试")
    @PostMapping("/test_login")
    public Result<String> userLoginTest(@RequestBody LoginVo loginVo) throws UnsupportedEncodingException {
//        //获取当前用户
//        Subject subject = SecurityUtils.getSubject();
//        //封装用户的登录数据
//        UsernamePasswordToken token = new UsernamePasswordToken(loginVo.getUsername(),loginVo.getPassword());
//        try {
//            subject.login(token);//执行登录方法，如果没有异常就说明ok了
//            //生成返回的token
////            JwtUtils jwtUtils = new JwtUtils();
////            String jwtToken = jwtUtils.refreshToken(loginVo.getUsername());
//            return Result.success(token.toString());
//        } catch (UnknownAccountException e) {
//            throw new GlobleException(CodeMsg.USERNAME_NOT_EXIST);
//        } catch (IncorrectCredentialsException e){
//            throw new GlobleException(CodeMsg.PASSWORD_ERROR);
//        }
        JwtUtils jwtUtils = new JwtUtils();
        String token = jwtUtils.sign(loginVo);
        return Result.success(token);
    }
}
