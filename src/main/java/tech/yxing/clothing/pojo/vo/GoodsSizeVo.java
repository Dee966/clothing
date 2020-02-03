package tech.yxing.clothing.pojo.vo;

public class GoodsSizeVo {
    private String goodsSize;
    private Integer goodsSizeStock;

    public GoodsSizeVo(){

    }

    public GoodsSizeVo(String goodsSize, Integer goodsSizeStock) {
        this.goodsSize = goodsSize;
        this.goodsSizeStock = goodsSizeStock;
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

    @Override
    public String toString() {
        return "GoodsSizeVo{" +
                "goodsSize='" + goodsSize + '\'' +
                ", goodsSizeStock=" + goodsSizeStock +
                '}';
    }
}
