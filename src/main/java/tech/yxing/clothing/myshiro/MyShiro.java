package tech.yxing.clothing.myshiro;

import org.springframework.util.DigestUtils;
import tech.yxing.clothing.exception.GlobleException;
import tech.yxing.clothing.pojo.vo.LoginVo;
import tech.yxing.clothing.result.CodeMsg;
import tech.yxing.clothing.result.Result;

import javax.servlet.http.HttpServletRequest;

public class MyShiro {

    //盐，用于混交md5
    private static final String slat = "@gnihtolc#namoaix$";

    /**
     * @methodDesc 密码加密
     * @param password
     * @return String
     */
    public static String securityPassword(String password){
        String secPassword = "clot" + password + "hing";
        return secPassword;
    }

    /**
     * @methodDesc 生成token
     * @param loginVo
     * @return String
     */
    public static String createToken(LoginVo loginVo){
        String base = loginVo.getPassword() +"/"+slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        String token = loginVo.getUsername()+"."+ md5 ;
        return token;
    }

    /**
     * @methodDesc token校验
     * @param token,password
     * @return String
     */
    public static Integer checkToken(String token,String username,String password){
        if (!token.equals(priToken(username,password))){
            return null;
        }
        return 1;
    }
    /**
     * @methodDesc 私有creatToken
     * @param password
     * @return String
     */
    private static String priToken(String username,String password) {
        String base = password+"/"+slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        String cheToken = username+ "." +md5;
        return cheToken;
    }
    /**
     * @methodDesc 提取username
     * @param request
     * @return String
     */
    public static String drawUsername(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        System.out.println("token:"+token);
        if (token == null){
            throw new GlobleException(CodeMsg.NOT_YET_LOGIN);
        }
        try {
            String username = token.substring(0,token.indexOf("."));
            System.out.println("username:"+username);
            return username;
        } catch (Exception e){
            throw new GlobleException(CodeMsg.NOT_YET_LOGIN);
        }

    }

    public static void main(String[] args){
        LoginVo loginVo1 = new LoginVo("966966","966966");
        LoginVo loginVo2 = new LoginVo("efg","456");
        String token1 = MyShiro.createToken(loginVo1);
        String token2 = MyShiro.createToken(loginVo2);
        System.out.println(token1);
        System.out.println(token1.substring(0,token1.indexOf(".")));
//        System.out.println("D:\\work\\workspace\\clothing\\static\\1.jpg".substring("D:\\work\\workspace\\clothing\\static\\1.jpg".indexOf("g")+1));
    }
}
