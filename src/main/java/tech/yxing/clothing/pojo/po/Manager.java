package tech.yxing.clothing.pojo.po;

import tech.yxing.clothing.pojo.vo.LoginVo;
import tech.yxing.clothing.pojo.vo.ManagerVo;

import java.util.Date;

public class Manager {
    private Integer managerId;
    private String username;
    private String password;
    private String name;
    private String sex;
    private String telephone;
    private Date workTime;

    public Manager(){

    }

    public Manager(LoginVo loginVo){
        this.username = loginVo.getUsername();
        this.password = loginVo.getPassword();
    }

    public Manager(int managerId,ManagerVo managerVo){
        this.managerId = managerId;
        this.name = managerVo.getName();
        this.sex = managerVo.getSex();
        this.telephone = managerVo.getTelephone();
        this.workTime = managerVo.getWorkTime();
    }

    public Manager(Integer managerId, String username, String password, String name, String sex, String telephone, Date workTime) {
        this.managerId = managerId;
        this.username = username;
        this.password = password;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        return "Manager{" +
                "managerId=" + managerId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", telephone='" + telephone + '\'' +
                ", workTime=" + workTime +
                '}';
    }
}
