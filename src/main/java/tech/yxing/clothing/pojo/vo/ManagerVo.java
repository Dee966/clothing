package tech.yxing.clothing.pojo.vo;

import java.util.Date;

public class ManagerVo {
    private Integer managerId;
    private String name;
    private String sex;
    private String telephone;
    private Date workTime;

    public ManagerVo(){

    }

    public ManagerVo(String name, String sex, String telephone, Date workTime) {
        this.name = name;
        this.sex = sex;
        this.telephone = telephone;
        this.workTime = workTime;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Date workTime) {
        this.workTime = workTime;
    }

    @Override
    public String toString() {
        return "ManagerVo{" +
                "managerId=" + managerId +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", telephone='" + telephone + '\'' +
                ", workTime=" + workTime +
                '}';
    }
}
