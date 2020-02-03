package tech.yxing.clothing.pojo.po;

public class GoodsType {
    private Integer goodsTypeId;//商品类别id
    private String name;//商品类别名称

    public Integer getGoodsTypeId() {
        return goodsTypeId;
    }

    public void setGoodsTypeId(Integer goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GoodsType{" +
                "goodsTypeId=" + goodsTypeId +
                ", name='" + name + '\'' +
                '}';
    }
}
