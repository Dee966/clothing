package tech.yxing.clothing.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tech.yxing.clothing.controller.HomeController;
import tech.yxing.clothing.dao.HomeDao;
import tech.yxing.clothing.exception.GlobleException;
import tech.yxing.clothing.pojo.po.Goods;
import tech.yxing.clothing.pojo.po.Rotation;
import tech.yxing.clothing.pojo.vo.RotationVo;
import tech.yxing.clothing.result.CodeMsg;
import tech.yxing.clothing.utils.MyUpload;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class HomeService {

    private static final Logger logger = LoggerFactory.getLogger(HomeService.class);

    @Autowired
    private HomeDao homeDao;

    /**
     * @methodDesc: 上传轮播图
     * @Param: MultipartFile
     * @Author: Joe
     * @Date
     */
    public String rotationImgUpload(MultipartFile picture) throws Exception {
        return MyUpload.pictureUpload(picture);
    }

    /**
     * @methodDesc: 保存轮播图数据
     * @Param: RotationVo
     * @Author: Joe
     * @Date
     */
    public void rotationUpload(RotationVo rotationVo) {
        //将rotation保存到数据库
        homeDao.rotationUpload(new Rotation(rotationVo));
    }

    /**
     * @@methodDesc: 关键字搜索
     * @Param: keyWord
     * @Author Joe
     * @Date
     */
    public List<Goods> search(String keyWord){
        List<Goods> goodsList = homeDao.search(keyWord);
        //如果goodsList，返回GOODS_NULL
        if (goodsList.isEmpty()){
            throw new GlobleException(CodeMsg.SEARCH_GOODS_NULL);
        }
        //不为空return
        return goodsList;
    }

    /**
     * @@methodDesc: 查询所有轮播图
     * @Param: keyWord
     * @Author Joe
     * @Date
     */
    public List<Rotation> rotationChart(){
        List<Rotation> rotationList = homeDao.rotationChart();
        //如果rotationList为空
        if (rotationList.isEmpty()){
            throw new GlobleException(CodeMsg.ROTATION_NULL);
        }
        //不为空返回rotationList
        return rotationList;
    }

    public void rotationDelete(int rotationId){
        homeDao.rotationDelete(rotationId);
    }
}
