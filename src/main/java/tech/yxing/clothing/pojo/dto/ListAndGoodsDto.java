package tech.yxing.clothing.pojo.dto;

import tech.yxing.clothing.pojo.po.Goods;

import java.util.List;

public class ListAndGoodsDto {
    private Integer ordersListId;
    private String size;
    private Double price;
    private Integer quantities;
    private Double total;
    private String appraise;
    private Integer goodsId;
    private Integer orderId;
    private List<Goods> goodsList;

    public ListAndGoodsDto(){}

    public ListAndGoodsDto(Integer ordersListId, String size, Double price, Integer quantities, Double total, String appraise, Integer goodsId, Integer orderId, List<Goods> goodsList) {
        this.ordersListId = ordersListId;
        this.size = size;
        this.price = price;
        this.quantities = quantities;
        this.total = total;
        this.appraise = appraise;
        this.goodsId = goodsId;
        this.orderId = orderId;
        this.goodsList = goodsList;
    }

    public Integer getOrdersListId() {
        return ordersListId;
    }

    public void setOrdersListId(Integer ordersListId) {
        this.ordersListId = ordersListId;
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

    public String getAppraise() {
        return appraise;
    }

    public void setAppraise(String appraise) {
        this.appraise = appraise;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }
}
