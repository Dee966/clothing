package tech.yxing.clothing.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.yxing.clothing.myshiro.MyShiro;
import tech.yxing.clothing.pojo.dto.GoodsSizeDto;
import tech.yxing.clothing.pojo.po.Manager;
import tech.yxing.clothing.pojo.po.User;
import tech.yxing.clothing.pojo.vo.LoginVo;
import tech.yxing.clothing.pojo.vo.ManagerVo;
import tech.yxing.clothing.pojo.vo.TokenId;
import tech.yxing.clothing.result.CodeMsg;
import tech.yxing.clothing.result.Result;
import tech.yxing.clothing.service.ManagerService;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Joe
 * @Date 2019-12-26 10:07
 */
@Api(tags = "用户端-管理员服务接口列表")
@RestController
@RequestMapping("/manager")
public class ManagerController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private ManagerService managerService;
    /**
     * @methodDesc: 管理员登录
     * @Param: username.password,model
     * @return: Result<>
     * @Author: Joe
     */
    @ApiOperation(value = "管理员登录", notes = "管理员端-管理员登录")
    @PostMapping("/manager_login")
    public Result<TokenId> managerLogin(@RequestBody LoginVo loginVo){
        return Result.success(managerService.managerLogin(loginVo));
    }

    /**
     * @methodDesc: 管理员注册
     * @Param: username,password
     * @return: Result<>
     * @Author: Joe
     */
    @ApiOperation(value = "管理员注册", notes = "管理员端-管理员注册")
    @PostMapping("/manager_register")
    public Result<Integer> managerRegister(@RequestBody LoginVo loginVo){
        return Result.success(managerService.managerRegister(loginVo));
    }

    /**
     * @methodDesc: 管理员信息管理
     * @Param: managerVo
     * @return: Result<ManagerVo>
     * @Author: Joe
     */
    @ApiOperation(value = "管理员信息管理", notes = "管理员端-管理员信息管理添加、修改")
    @PutMapping("/manager_info{managerId}")
    public Result<ManagerVo> managerInfo(@PathVariable int managerId,@RequestBody ManagerVo managerVo){
        return Result.success(managerService.managerInfo(managerId,managerVo));
    }

    /**
     * @Param: managerId
     * @Author Joe
     * @Date
     */
    @ApiOperation(value = "显示管理员信息", notes = "管理员端-查询管理员信息")
    @GetMapping("/manager_show/{managerId}")
    public Result<ManagerVo> managerShow(HttpServletRequest request,@PathVariable int managerId){
        String token = request.getHeader("Authorization");
        logger.info("token:"+token);
        if (token == null){
            return Result.error(CodeMsg.NOT_YET_LOGIN);
        }
        String username = token.substring(0,token.indexOf("."));
        logger.info("username:"+username);
        Manager manager = managerService.getManagerByUname(username);
        logger.info(manager.toString());
        if (manager == null){
            return Result.error(CodeMsg.NOT_YET_LOGIN);
        }
        Integer result = MyShiro.checkToken(token,manager.getUsername(),manager.getPassword());
        //判断result是否为null
        if (result == null) {
            //为null返回错误信息和登录url
            return Result.error(CodeMsg.NOT_YET_LOGIN);
        }
        ManagerVo managerVo = managerService.managerShow(managerId);
        return Result.success(managerVo);
    }

    @PostMapping("/edit")
    public Result<Object> editGoods(@RequestBody GoodsSizeDto goodsSizeDto){
        managerService.editGoods(goodsSizeDto);
        return Result.success(null);
    }
}
