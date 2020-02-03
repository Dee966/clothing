package tech.yxing.clothing.pojo.dto;

public class CartDto {
    private String name;
    private String imge;
    private String size;
    private Double price;
    private Integer quantities;
    private Double total;
    private Integer cartId;
    private Integer goodsId;

    public CartDto(){

    }

    public CartDto(String name, String imge, String size, Double price, Integer quantities, Double total, Integer cartId, Integer goodsId) {
        this.name = name;
        this.imge = imge;
        this.size = size;
        this.price = price;
        this.quantities = quantities;
        this.total = total;
        this.cartId = cartId;
        this.goodsId = goodsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImge() {
        return imge;
    }

    public void setImge(String imge) {
        this.imge = imge;
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

    @Override
    public String toString() {
        return "CartDto{" +
                "name='" + name + '\'' +
                ", imge='" + imge + '\'' +
                ", size='" + size + '\'' +
                ", price=" + price +
                ", quantities=" + quantities +
                ", total=" + total +
                ", cartId=" + cartId +
                ", goodsId=" + goodsId +
                '}';
    }
}

