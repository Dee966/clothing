package tech.yxing.clothing.pojo.vo;

public class TokenId {
    private String token;
    private Integer userId;

    public TokenId(){

    }

    public TokenId(String token, Integer userId) {
        this.token = token;
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "TokenId{" +
                "token='" + token + '\'' +
                ", userId=" + userId +
                '}';
    }
}
