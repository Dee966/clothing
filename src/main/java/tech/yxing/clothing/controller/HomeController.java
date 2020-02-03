package tech.yxing.clothing.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.yxing.clothing.pojo.po.Goods;
import tech.yxing.clothing.pojo.po.Rotation;
import tech.yxing.clothing.pojo.vo.RotationVo;
import tech.yxing.clothing.result.Result;
import tech.yxing.clothing.service.HomeService;

import java.util.List;

/**
 * @Author Joe
 * @Date 2019-12-26 10:07
 */
@Api(tags = "用户端-主页服务接口列表")
@RestController
@RequestMapping("/home")
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private HomeService homeService;
    /**
     * @Param: keyWord
     * @Author Joe
     * @Date
     */
    @ApiOperation(value = "搜索商品", notes = "用户端-查询商品")
    @GetMapping("/search/{keyWord}")
    public Result<List<Goods>> search(@PathVariable String keyWord){
        return Result.success(homeService.search(keyWord));
    }

    /**
     * @methodDesc: 管理员上传轮播图1：上传图片
     * @Param: MultipartFile
     * @Author: Joe
     * @Date
     */
    @ApiOperation(value = "上传轮播图", notes = "管理员端-管理员上传轮播图")
    @PostMapping("/rotation_upload")
    public Result<String> rotationImgUpload(@RequestParam("imge") MultipartFile picture) throws Exception {
        String pictureUrl = homeService.rotationImgUpload(picture);
        return Result.success(pictureUrl);
    }

    /**
     * @Param: rotationVo
     * @Author: Joe
     * @Date
     */
    @ApiOperation(value = "添加轮播图信息", notes = "管理员端-管理员添加轮播图信息")
    @PostMapping("/rotation_insert")
    public Result<Object> rotationInsert(@RequestBody RotationVo rotationVo) {
        homeService.rotationUpload(rotationVo);
        return Result.success(null);
    }

    /**
     * @Param:
     * @Author Joe
     * @Date
     */
    @ApiOperation(value = "轮播图展示", notes = "用户端-查询所有轮播图")
    @GetMapping("/rotation_chart")
    public Result<List<Rotation>> rotationChart(){
        List<Rotation> rotationList = homeService.rotationChart();
        return Result.success(rotationList);
    }

    /**
     * @Param: rotationId
     * @Author Joe
     * @Date
     */
    @ApiOperation(value = "删除轮播图", notes = "管理员端-删除轮播图")
    @DeleteMapping("/rotation_delete/{rotationId}")
    public Result<Object> rotationDelete(@PathVariable int rotationId){
        homeService.rotationDelete(rotationId);
        return Result.success(null);
    }

}
