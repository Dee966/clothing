package tech.yxing.clothing.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
import tech.yxing.clothing.dao.GoodsDao;
import tech.yxing.clothing.exception.GlobleException;
import tech.yxing.clothing.pojo.dto.GoodsDto;
import tech.yxing.clothing.pojo.dto.GoodsSizeDto;
import tech.yxing.clothing.pojo.po.Goods;
import tech.yxing.clothing.pojo.po.GoodsSize;
import tech.yxing.clothing.pojo.po.GoodsType;
import tech.yxing.clothing.pojo.vo.GoodsSizeVo;
import tech.yxing.clothing.pojo.vo.GoodsUploadVo;
import tech.yxing.clothing.redis.GoodsKey;
//import tech.yxing.clothing.redis.RedisConfig;
//import tech.yxing.clothing.redis.RedisPoolFactory;
//import tech.yxing.clothing.redis.RedisService;
import tech.yxing.clothing.result.CodeMsg;
import tech.yxing.clothing.utils.MyUpload;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class GoodsService {
    @Autowired
    private GoodsDao goodsDao;
//    @Autowired
//    private RedisService redisService;
//    @Autowired
//    private JedisPool jedisPool;


    /**
     * @methodDesc: 查询所有商品
     * @Param:
     * @return: List<Goods>
     * @Author: Joe
     */
    public List<Goods> goodsShowAll(){
        //缓存中取所有商品
//        List<Goods> goodsList = redisService.getList(GoodsKey.allGoods, "all", Goods.class);
//        if (goodsList != null){
//            return goodsList;
//        }
        //走数据库
        List<Goods> goodsList = goodsDao.goodsShowAll();
        if (goodsList.isEmpty()){
            throw new GlobleException(CodeMsg.GOODS_NULL);
        }
//        else {
//            redisService.set(GoodsKey.allGoods, "all", goodsList);
//        }
        //不为空返回goodsList
        return goodsList;
    }
    /**
     * @methodDesc: 通过id查某个商品
     * @Param: goodsId
     * @return: Goods
     * @Author: Joe
     */
    public Goods goodsShow(int goodsId){
        //缓存中查goods
//        Goods goods = redisService.get(GoodsKey.getById, ""+goodsId, Goods.class);
//        if (goods != null){
//            return goods;
//        }
        Goods goods = goodsDao.goodsShow(goodsId);
//        if (goods != null){
//            redisService.set(GoodsKey.getById, ""+goodsId, goods);
//        }
        return goods;
    }
    /**
     * @methodDesc: 查询所有分类
     * @Param:
     * @return: GoodsType
     * @Author: Joe
     */
    public List<GoodsType> goodsType(){
        return goodsDao.goodsType();
    }

    /**
     * @methodDesc: 查询某分类下的所有商品
     * @Param:goodsTypeId
     * @return: GoodsType
     * @Author: Joe
     */
    public List<Goods> typeShow(int typeId){
        List<Goods> goodsList = goodsDao.typeShow(typeId);
        //goodsList
        if (goodsList.isEmpty()){
            throw new GlobleException(CodeMsg.TYPE_GOODS_NULL);
        }
        //不为空返回goodsList
        return goodsList;
    }
    /**
     * @methodDesc: 根据商品id删除商品
     * @Param:goodsId
     * @return:
     * @Author: Joe
     */
    public void goodsDelete(int goodsId){
        goodsDao.goodsDelete(goodsId);
        goodsDao.goodsSizeDelete(goodsId);
    }

    /**
     * @methodDesc: 上传商品图片
     * @Param: MultipartFile
     * @return: 图片url
     * @Author: Joe
     */
    public String goodsImgUpload(MultipartFile picture) throws Exception {
        return MyUpload.pictureUpload(picture);
    }

    /**
     * @methodDesc: 写入新商品
     * @Param:goodsUploadVo
     * @return:
     * @Author: Joe
     */
    public void goodsInsert(GoodsUploadVo goodsUploadVo) {
        //将商品部分的数据写入goods，返回主键goodsId
        Goods goods = new Goods(goodsUploadVo);
        goodsDao.goodsInsert(goods);
        //将返回的goodsId和尺码部分写入goods_size 存七次
        for (GoodsSizeVo goodsSizeVo: goodsUploadVo.getGoodsSizeVos()) {
            GoodsSize goodsSize = new GoodsSize(goods.getGoodsId(),goodsSizeVo);
            goodsDao.goodsSizeInsert(goodsSize);
        }
    }

    public List<GoodsSize> goodsSize(int goodsId){
        return goodsDao.goodsSize(goodsId);
    }

    public GoodsSizeDto getGoodsAndSize(int goodsId){
        return goodsDao.getGoodsAndSize(goodsId);
    }
}
