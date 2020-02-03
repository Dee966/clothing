package tech.yxing.clothing.pojo.vo;

public class CartVo {
    private String size;
    private Double price;
    private Integer quantities;
    private Integer goodsId;
    private Integer userId;

    public CartVo() {
    }

    public CartVo(String size, Double price, Integer quantities, Integer goodsId, Integer userId) {
        this.size = size;
        this.price = price;
        this.quantities = quantities;
        this.goodsId = goodsId;
        this.userId = userId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantities() {
        return quantities;
    }

    public void setQuantities(Integer quantities) {
        this.quantities = quantities;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "CartVo{" +
                "size='" + size + '\'' +
                ", price=" + price +
                ", quantities=" + quantities +
                ", goodsId=" + goodsId +
                ", userId=" + userId +
                '}';
    }
}
