package tech.yxing.clothing.pojo.po;

import java.util.Date;

public class OrdersLog {
    private Integer LogId;
    private Date orderTime;
    private Date payTime;
    private Date outTime;
    private Date getTime;
    private Integer orderId;
    private Integer managerId;

    public Integer getLogId() {
        return LogId;
    }

    public void setLogId(Integer logId) {
        LogId = logId;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public Date getGetTime() {
        return getTime;
    }

    public void setGetTime(Date getTime) {
        this.getTime = getTime;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    @Override
    public String toString() {
        return "OrdersLog{" +
                "LogId=" + LogId +
                ", orderTime=" + orderTime +
                ", payTime=" + payTime +
                ", outTime=" + outTime +
                ", getTime=" + getTime +
                ", orderId=" + orderId +
                ", managerId=" + managerId +
                '}';
    }
}
