package tech.yxing.clothing.pojo.dto;

import tech.yxing.clothing.pojo.po.Goods;
import tech.yxing.clothing.pojo.po.GoodsSize;

import java.util.Date;
import java.util.List;

public class GoodsSizeDto {
    private Integer goodsId;//商品id
    private String name;//商品名称
    private Double price;//商品价格
    private String imge;//商品图片地址
    private Date goodDate;//商品上架时间
    private String desc;//商品描述
    private Integer stock;//库存
    private Integer goodsTypeId;//商品类型id
    private List<GoodsSize> goodsSizes;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImge() {
        return imge;
    }

    public void setImge(String imge) {
        this.imge = imge;
    }

    public Date getGoodDate() {
        return goodDate;
    }

    public void setGoodDate(Date goodDate) {
        this.goodDate = goodDate;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getGoodsTypeId() {
        return goodsTypeId;
    }

    public void setGoodsTypeId(Integer goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }

    public List<GoodsSize> getGoodsSizes() {
        return goodsSizes;
    }

    public void setGoodsSizes(List<GoodsSize> goodsSizes) {
        this.goodsSizes = goodsSizes;
    }
}
