package tech.yxing.clothing.result;

public class CodeMsg {
    private int code;
    private String msg;

    //通用模块错误码
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
    public static CodeMsg BIND_ERROR = new CodeMsg(500101, "参数校验异常：%s");

    //用户模块模块错误码10
    public static CodeMsg USER_INFO_INSERT_ERROR = new CodeMsg(10101,"信息添加失败，系统错误...");
    public static CodeMsg USERNAME_NOT_EXIST = new CodeMsg(10102,"用户名不存在");
    public static CodeMsg PASSWORD_ERROR = new CodeMsg(10103,"密码错误");
    public static CodeMsg USER_EXIST = new CodeMsg(10104,"用户名已存在，请重新注册。");
    public static CodeMsg NOT_YET_LOGIN = new CodeMsg(10106,"未登录或登录失效，请先进行登录");

    //商品模块模块错误码20
    public static CodeMsg SEARCH_GOODS_NULL = new CodeMsg(20101,"找不到相关商品！");
    public static CodeMsg ROTATION_NULL = new CodeMsg(20102,"找不到任何轮播图！");
    public static CodeMsg GOODS_NULL = new CodeMsg(20103,"找不到任何商品！");
    public static CodeMsg TYPE_GOODS_NULL = new CodeMsg(20104,"找不到该分类的任何商品！");

    //购物车模块错误码30
    public static CodeMsg CART_NULL = new CodeMsg(30101,"购物车没有任何商品，快去选购吧！");
    public static CodeMsg CART_ORDER_ERROR = new CodeMsg(30102,"下单失败，系统错误...");

    //订单模块错误码40
    public static CodeMsg ORDER_ERROR = new CodeMsg(40101,"下单失败，系统错误...");
    public static CodeMsg STATE_ORDER_NULL = new CodeMsg(40102,"没有符合条件的订单。");
    public static CodeMsg ORDER_NULL = new CodeMsg(40103,"找不到任何订单。");
    public static CodeMsg GET_STATE_ORDER_ERROR = new CodeMsg(40104,"查询失败，系统错误...");

    private CodeMsg() {
    }

    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    //可以返回带参数的校验码
    public CodeMsg fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.msg, args);
        return new CodeMsg(code, message);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "CodeMsg{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
