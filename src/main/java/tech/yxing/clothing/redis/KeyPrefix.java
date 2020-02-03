package tech.yxing.clothing.redis;

public interface KeyPrefix {
    public int expireSeconds();//过期时间

    public String getPrefix();//前缀
}
