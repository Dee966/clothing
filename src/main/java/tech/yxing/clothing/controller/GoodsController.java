package tech.yxing.clothing.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.yxing.clothing.pojo.dto.GoodsSizeDto;
import tech.yxing.clothing.pojo.po.Goods;
import tech.yxing.clothing.pojo.po.GoodsSize;
import tech.yxing.clothing.pojo.po.GoodsType;
import tech.yxing.clothing.pojo.vo.GoodsUploadVo;
import tech.yxing.clothing.result.Result;
import tech.yxing.clothing.service.GoodsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @Author Joe
 * @Date 2019-12-26 10:07
 */
@Api(tags = "用户端-商品服务接口列表")
@RestController
@RequestMapping("goods")
@CrossOrigin
public class GoodsController {

    private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private GoodsService goodsService;
    /**
     * @Param:
     * @return: Result<>
     * @Author: Joe
     */
    @ApiOperation(value = "显示所有商品", notes = "用户端-查询所有商品")
    @GetMapping("/goods_show_all")
    public Result<List<Goods>> goodsShowAll(){
        return Result.success(goodsService.goodsShowAll());
    }
    /**
     * @Param:goodsId
     * @return: Result<>
     * @Author: Joe
     */
    @ApiOperation(value = "某个商品的页面", notes = "用户端-查询某个商品")
    @GetMapping("/goods_show/{goodsId}")
    public Result<Goods> goodsShow(@PathVariable int goodsId){
        return Result.success(goodsService.goodsShow(goodsId));
    }

    /**
     * @Param:
     * @return: Result<>
     * @Author: Joe
     */
    @ApiOperation(value = "显示所有分类", notes = "用户端-查询所有分类")
    @GetMapping("/goods_type")
    public Result<List<GoodsType>> goodsType(){
        return Result.success(goodsService.goodsType());
    }
    /**
     * @Param:
     * @return: Result<>
     * @Author: Joe
     */
    @ApiOperation(value = "分类信息页面展示", notes = "用户端-查询某个分类的商品")
    @GetMapping("/type_show/{typeId}")
    public Result<List<Goods>> typeShow(@PathVariable int typeId){
        return Result.success(goodsService.typeShow(typeId));
    }
    /**
     * @Param: goodsId
     * @return: Result<GoodsSize>
     * @Author: Joe
     */
    @ApiOperation(value = "商品尺码信息页面展示", notes = "用户端-查询某个商品的尺码")
    @GetMapping("/goods_size/{goodsId}")
    public Result<List<GoodsSize>> goodsSize(@PathVariable int goodsId){
        return Result.success(goodsService.goodsSize(goodsId));
    }

    /**
     * @Param: goodsId
     * @return: Result<>
     * @Author: Joe
     */
    @ApiOperation(value = "下架商品", notes = "管理员端-删除商品")
    @DeleteMapping("/goods_delete/{goodsId}")
    public Result<Object> goodsDelete(@PathVariable int goodsId){
        goodsService.goodsDelete(goodsId);
        return Result.success(null);
    }

    /**
     * @methodDesc: 管理员上架某商品1：上传图片
     * @Param: MultipartFile
     * @return: Result<>
     * @Author: xiaoman
     */
    @ApiOperation(value = "上架商品-上传图片", notes = "管理员端-上架商品-上传图片")
    @PostMapping("/goods_upload")
    public Result<String> goodsInsert(@RequestParam("imge") MultipartFile picture) throws Exception {
        String pictureUrl = goodsService.goodsImgUpload(picture);
        return Result.success(pictureUrl);
    }

    /**
     * @methodDesc: 管理员上架某商品2：添加商品信息
     * @Param: GoodsUploadVo
     * @return: Result<>
     * @Author: xiaoman
     */
    @ApiOperation(value = "上架商品-添加商品信息", notes = "管理员端-上架商品-添加商品信息")
    @PostMapping("/goods_insert")
    public Result<Object> goodsInsert(@RequestBody GoodsUploadVo goodsUploadVo) {
        goodsService.goodsInsert(goodsUploadVo);
        return Result.success(null);
    }

    @GetMapping("/get_edit/{goodsId}")
    public Result<GoodsSizeDto> getGoodsAndSize(@PathVariable int goodsId){
        return Result.success(goodsService.getGoodsAndSize(goodsId));
    }

    @GetMapping("/wxin")
    public void testWechat(HttpServletRequest request, HttpServletResponse response)
    {
        System.out.println("get");
    }
}
