package tech.yxing.clothing.pojo.dto;

import tech.yxing.clothing.pojo.vo.OrdersListVo;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

public class OrdersListDto {
    private Date orderTime;
    private Double total;
    private Integer state;
    private List<OrdersListVo> ordersListVos;

    //private List<Object> listOrdersList;

    public OrdersListDto(){

    }

    public OrdersListDto(Date orderTime, Double total, Integer state, List<OrdersListVo> ordersListVos) {
        this.orderTime = orderTime;
        this.total = total;
        this.state = state;
        this.ordersListVos = ordersListVos;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public List<OrdersListVo> getOrdersListVos() {
        return ordersListVos;
    }

    public void setOrdersListVos(List<OrdersListVo> ordersListVos) {
        this.ordersListVos = ordersListVos;
    }

    @Override
    public String toString() {
        return "OrdersListDto{" +
                "orderTime=" + orderTime +
                ", total=" + total +
                ", state=" + state +
                ", ordersListVos=" + ordersListVos +
                '}';
    }
}
