package tech.yxing.clothing.pojo.po;

import tech.yxing.clothing.pojo.vo.GoodsSizeVo;
import tech.yxing.clothing.pojo.vo.GoodsUploadVo;

public class GoodsSize {
    private Integer goodsSizeId;
    private String goodsSize;
    private Integer goodsSizeStock;
    private Integer goodsId;

    public GoodsSize(){

    }

    public GoodsSize(Integer goodsId, GoodsSizeVo goodsSizeVo){
        this.goodsSize = goodsSizeVo.getGoodsSize();
        this.goodsSizeStock = goodsSizeVo.getGoodsSizeStock();
        this.goodsId = goodsId;

    }

    public GoodsSize(Integer goodsSizeId, String goodsSize, Integer goodsSizeStock, Integer goodsId) {
        this.goodsSizeId = goodsSizeId;
        this.goodsSize = goodsSize;
        this.goodsSizeStock = goodsSizeStock;
        this.goodsId = goodsId;
    }

    public Integer getGoodsSizeId() {
        return goodsSizeId;
    }

    public void setGoodsSizeId(Integer goodsSizeId) {
        this.goodsSizeId = goodsSizeId;
    }

    public String getGoodsSize() {
        return goodsSize;
    }

    public void setGoodsSize(String goodsSize) {
        this.goodsSize = goodsSize;
    }

    public Integer getGoodsSizeStock() {
        return goodsSizeStock;
    }

    public void setGoodsSizeStock(Integer goodsSizeStock) {
        this.goodsSizeStock = goodsSizeStock;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    @Override
    public String toString() {
        return "GoodsSize{" +
                "goodsSizeId=" + goodsSizeId +
                ", goodsSize='" + goodsSize + '\'' +
                ", goodsSizeStock=" + goodsSizeStock +
                ", goodsId=" + goodsId +
                '}';
    }
}
