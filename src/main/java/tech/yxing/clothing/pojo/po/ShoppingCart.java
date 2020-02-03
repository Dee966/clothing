package tech.yxing.clothing.pojo.po;

import tech.yxing.clothing.pojo.vo.CartVo;

public class ShoppingCart {
    private Integer cartId;
    private String size;
    private Double price;
    private Integer quantities;
    private Double total;
    private Integer goodsId;
    private Integer userId;

    public ShoppingCart(){

    }

    public ShoppingCart(Double total,CartVo cartVo){
        this.size = cartVo.getSize();
        this.price = cartVo.getPrice();
        this.quantities = cartVo.getQuantities();
        this.total = total;
        this.goodsId = cartVo.getGoodsId();
        this.userId = cartVo.getUserId();
    }

    public ShoppingCart(Integer cartId, String size, Double price, Integer quantities, Double total, Integer goodsId, Integer userId) {
        this.cartId = cartId;
        this.size = size;
        this.price = price;
        this.quantities = quantities;
        this.total = total;
        this.goodsId = goodsId;
        this.userId = userId;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
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
        return "ShoppingCart{" +
                "cartId=" + cartId +
                ", size='" + size + '\'' +
                ", price=" + price +
                ", quantities=" + quantities +
                ", total=" + total +
                ", goodsId=" + goodsId +
                ", userId=" + userId +
                '}';
    }
}
