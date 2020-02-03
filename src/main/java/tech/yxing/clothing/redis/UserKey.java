package tech.yxing.clothing.redis;

public class UserKey extends BasePrefix {
    public UserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static UserKey balanceById = new UserKey(60*5,"uid");
    public static UserKey infoById = new UserKey(60*5,"id");
    public static UserKey usrByUname = new UserKey(60*60,"un");
}
