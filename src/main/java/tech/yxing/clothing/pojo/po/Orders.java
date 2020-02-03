package tech.yxing.clothing.pojo.po;

import java.util.Date;

public class Orders {
    private Integer orderId;
    private Date orderTime;
    private Double total;
    private Integer state;
    private String name;
    private String address;
    private String telephone;
    private Integer userId;

    public Orders() {
    }

    public Orders(Integer orderId, Date orderTime, Double total, Integer state, String name, String address, String telephone, Integer userId) {
        this.orderId = orderId;
        this.orderTime = orderTime;
        this.total = total;
        this.state = state;
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.userId = userId;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderId=" + orderId +
                ", orderTime=" + orderTime +
                ", total=" + total +
                ", state=" + state +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                ", userId=" + userId +
                '}';
    }
}
