package tech.yxing.clothing.redis;

public class GoodsKey extends BasePrefix {
    private GoodsKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static GoodsKey allGoods = new GoodsKey(3600*12,"all");
    public static GoodsKey getById = new GoodsKey(3600*12,"id");
}
