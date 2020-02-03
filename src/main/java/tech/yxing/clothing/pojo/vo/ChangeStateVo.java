package tech.yxing.clothing.pojo.vo;

public class ChangeStateVo {
    private Integer managerId;
    private Integer orderId;

    public ChangeStateVo(){

    }

    public ChangeStateVo(Integer managerId, Integer orderId) {
        this.managerId = managerId;
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
        return "ChangeStateVo{" +
                "managerId=" + managerId +
                ", orderId=" + orderId +
                '}';
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
