package tech.yxing.clothing.pojo.po;

import tech.yxing.clothing.pojo.vo.LoginVo;
import tech.yxing.clothing.pojo.vo.UserVo;

import java.util.Date;

public class User {
    private Integer userId;
    private String username;
    private String password;
    private String name;
    private String telephone;
    private String sex;
    private Date birthday;
    private String area;
    private String wechat;
    private String address;
    private Double balance;

    public User(){

    }

    public User(LoginVo loginVo){
        this.username = loginVo.getUsername();
        this.password = loginVo.getPassword();
    }

    public User(Integer userId,UserVo userVo) {
        this.userId = userId;
        this.name = userVo.getName();
        this.telephone = userVo.getTelephone();
        this.sex = userVo.getSex();
        this.birthday = userVo.getBirthday();
        this.area = userVo.getArea();
        this.wechat = userVo.getWechat();
        this.address = userVo.getAddress();
    }

    public User(Integer userId, String username, String password, String name, String telephone, String sex, Date birthday, String area, String wechat, String address, Double balance) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.telephone = telephone;
        this.sex = sex;
        this.birthday = birthday;
        this.area = area;
        this.wechat = wechat;
        this.address = address;
        this.balance = balance;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", telephone='" + telephone + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                ", area='" + area + '\'' +
                ", wechat='" + wechat + '\'' +
                ", address='" + address + '\'' +
                ", balance=" + balance +
                '}';
    }
}
