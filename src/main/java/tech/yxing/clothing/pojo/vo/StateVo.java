package tech.yxing.clothing.pojo.vo;

public class StateVo {
    private Integer state;
    private Integer userId;

    public StateVo(){

    }

    public StateVo(Integer state, Integer userId) {
        this.state = state;
        this.userId = userId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "StateVo{" +
                "state=" + state +
                ", userId=" + userId +
                '}';
    }
}
