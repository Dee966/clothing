package tech.yxing.clothing.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author Hoki(谭鸿兴)
 * @Date 2019-12-25 22:19
 */
@ApiModel(value = "LoginVo", description = "用户数据结构")
public class LoginVo {

    @ApiModelProperty(value = "用户名", name = "name")
    private String username;

    @ApiModelProperty(value = "用户密码", name = "pword")
    private String password;

    public LoginVo() {
    }

    public LoginVo(String username, String password) {
        this.username = username;
        this.password = password;
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

    @Override
    public String toString() {
        return "LoginVo{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
