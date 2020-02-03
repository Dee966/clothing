package tech.yxing.clothing.pojo.po;

import tech.yxing.clothing.pojo.vo.GoodsUploadVo;

import java.util.Date;

//商品PO
public class Goods {
    private Integer goodsId;//商品id
    private String name;//商品名称
    private Double price;//商品价格
    private String imge;//商品图片地址
    private Date goodDate;//商品上架时间
    private String desc;//商品描述
    private Integer stock;//库存
    private Integer goodsTypeId;//商品类型id

    public Goods(){

    }

    public Goods(GoodsUploadVo goodsUploadVo){
        this.name = goodsUploadVo.getName();
        this.price = goodsUploadVo.getPrice();
        this.imge = goodsUploadVo.getImge();
        this.goodDate = goodsUploadVo.getGoodDate();
        this.desc = goodsUploadVo.getDesc();
        this.stock = goodsUploadVo.getStock();
        this.goodsTypeId = goodsUploadVo.getGoodsTypeId();
    }

    public Goods(Integer goodsId, String name, Double price, String imge, Date goodDate, String desc, Integer stock, Integer goodsTypeId) {
        this.goodsId = goodsId;
        this.name = name;
        this.price = price;
        this.imge = imge;
        this.goodDate = goodDate;
        this.desc = desc;
        this.stock = stock;
        this.goodsTypeId = goodsTypeId;
    }

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

    @Override
    public String toString() {
        return "Goods{" +
                "goodsId=" + goodsId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", imge='" + imge + '\'' +
                ", goodDate=" + goodDate +
                ", desc='" + desc + '\'' +
                ", stock=" + stock +
                ", goodsTypeId=" + goodsTypeId +
                '}';
    }
}
