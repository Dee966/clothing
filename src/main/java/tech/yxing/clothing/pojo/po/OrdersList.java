package tech.yxing.clothing.pojo.po;

import tech.yxing.clothing.pojo.vo.OrdersListVo;

public class OrdersList {
    private Integer ordersListId;
    private String size;
    private Double price;
    private Integer quantities;
    private Double total;
    private String appraise;
    private Integer goodsId;
    private Integer orderId;
    private Integer userId;

    public OrdersList(){

    }

    public OrdersList(OrdersListVo ordersListVo){
        this.size = ordersListVo.getSize();
        this.price = ordersListVo.getPrice();
        this.quantities = ordersListVo.getQuantities();
        this.total = ordersListVo.getPrice();
        this.appraise = ordersListVo.getSize();
        this.goodsId = ordersListVo.getGoodsId();
        this.orderId = ordersListVo.getGoodsId();
        this.userId = ordersListVo.getUserId();
    }

    public OrdersList(Integer ordersListId, String size, Double price, Integer quantities, Double total, String appraise, Integer goodsId, Integer orderId, Integer userId) {
        this.ordersListId = ordersListId;
        this.size = size;
        this.price = price;
        this.quantities = quantities;
        this.total = total;
        this.appraise = appraise;
        this.goodsId = goodsId;
        this.orderId = orderId;
        this.userId = userId;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "OrdersList{" +
                "ordersListId=" + ordersListId +
                ", size='" + size + '\'' +
                ", price=" + price +
                ", quantities=" + quantities +
                ", total=" + total +
                ", appraise='" + appraise + '\'' +
                ", goodsId=" + goodsId +
                ", orderId=" + orderId +
                ", userId=" + userId +
                '}';
    }
}
