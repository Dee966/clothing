package tech.yxing.clothing.pojo.dto;

import tech.yxing.clothing.pojo.po.OrdersList;

import java.util.Date;
import java.util.List;

public class OrdersAndListGoodsDto {
    private Integer orderId;
    private Date orderTime;
    private Double total;
    private Integer state;
    private String name;
    private String address;
    private String telephone;
    private List<ListAndGoodsDto> listAndGoodsDtos;

    public OrdersAndListGoodsDto(){}

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

    public List<ListAndGoodsDto> getListAndGoodsDtos() {
        return listAndGoodsDtos;
    }

    public void setListAndGoodsDtos(List<ListAndGoodsDto> listAndGoodsDtos) {
        this.listAndGoodsDtos = listAndGoodsDtos;
    }

    public OrdersAndListGoodsDto(Integer orderId, Date orderTime, Double total, Integer state, String name, String address, String telephone, List<ListAndGoodsDto> listAndGoodsDtos) {
        this.orderId = orderId;
        this.orderTime = orderTime;
        this.total = total;
        this.state = state;
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.listAndGoodsDtos = listAndGoodsDtos;
    }

    @Override
    public String toString() {
        return "OrdersAndListGoodsDto{" +
                "orderId=" + orderId +
                ", orderTime=" + orderTime +
                ", total=" + total +
                ", state=" + state +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                ", listAndGoodsDtos=" + listAndGoodsDtos +
                '}';
    }
}
