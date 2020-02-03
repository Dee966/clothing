package tech.yxing.clothing.pojo.dto;

import tech.yxing.clothing.pojo.po.Goods;

/**
 * @Author Hoki(谭鸿兴)
 * @Date 2020-01-06 23:10
 */
public class CartAndGoodsDto {
    private String size;
    private Double price;
    private Integer quantities;
    private Double total;
    private Integer cartId;
    private Integer goodsId;
    private Goods goods;

    public CartAndGoodsDto() {
    }

    public CartAndGoodsDto(String size, Double price, Integer quantities, Double total, Integer cartId, Integer goodsId, Goods goods) {
        this.size = size;
        this.price = price;
        this.quantities = quantities;
        this.total = total;
        this.cartId = cartId;
        this.goodsId = goodsId;
        this.goods = goods;
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

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "CartAndGoodsDto{" +
                "size='" + size + '\'' +
                ", price=" + price +
                ", quantities=" + quantities +
                ", total=" + total +
                ", cartId=" + cartId +
                ", goodsId=" + goodsId +
                ", goods=" + goods +
                '}';
    }
}
