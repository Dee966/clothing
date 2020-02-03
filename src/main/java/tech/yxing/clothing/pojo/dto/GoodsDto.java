package tech.yxing.clothing.pojo.dto;

import tech.yxing.clothing.pojo.po.Goods;

import java.util.List;

public class GoodsDto {
    private List<Goods> goodsList;

    public GoodsDto(){}

    public GoodsDto(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    @Override
    public String toString() {
        return "GoodsDto{" +
                "goodsList=" + goodsList +
                '}';
    }
}
