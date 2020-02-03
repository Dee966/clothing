package tech.yxing.clothing.pojo.vo;

import java.util.Date;

public class UserVo {
    private String name;
    private String telephone;
    private String sex;
    private Date birthday;
    private String area;
    private String wechat;
    private String address;

    public UserVo(){

    }

    public UserVo(String name, String telephone, String sex, Date birthday, String area, String wechat, String address) {
        this.name = name;
        this.telephone = telephone;
        this.sex = sex;
        this.birthday = birthday;
        this.area = area;
        this.wechat = wechat;
        this.address = address;
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

    @Override
    public String toString() {
        return "UserVo{" +
                "name='" + name + '\'' +
                ", telephone='" + telephone + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                ", area='" + area + '\'' +
                ", wechat='" + wechat + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
