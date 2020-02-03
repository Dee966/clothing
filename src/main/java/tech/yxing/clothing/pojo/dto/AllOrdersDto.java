package tech.yxing.clothing.pojo.dto;

import java.util.Date;

public class AllOrdersDto {
    private String name;
    private String imge;
    private String size;
    private Double price;
    private Integer quantities;
    private Double total;
    private String state;
    private Integer ordersListId;
    private Integer orderId;
    private Date orderTime;

    public AllOrdersDto(){

    }

    public AllOrdersDto(String name, String imge, String size, Double price, Integer quantities, Double total, String state, Integer ordersListId, Integer orderId, Date orderTime) {
        this.name = name;
        this.imge = imge;
        this.size = size;
        this.price = price;
        this.quantities = quantities;
        this.total = total;
        this.state = state;
        this.ordersListId = ordersListId;
        this.orderId = orderId;
        this.orderTime = orderTime;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getOrdersListId() {
        return ordersListId;
    }

    public void setOrdersListId(Integer ordersListId) {
        this.ordersListId = ordersListId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    @Override
    public String toString() {
        return "AllOrdersDto{" +
                "name='" + name + '\'' +
                ", imge='" + imge + '\'' +
                ", size='" + size + '\'' +
                ", price=" + price +
                ", quantities=" + quantities +
                ", total=" + total +
                ", state='" + state + '\'' +
                ", ordersListId=" + ordersListId +
                ", orderId=" + orderId +
                ", orderTime=" + orderTime +
                '}';
    }
}
