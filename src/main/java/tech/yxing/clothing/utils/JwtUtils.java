package tech.yxing.clothing.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tech.yxing.clothing.pojo.po.User;
import tech.yxing.clothing.pojo.vo.LoginVo;
import tech.yxing.clothing.redis.RedisService;
import tech.yxing.clothing.service.UserService;

import java.io.UnsupportedEncodingException;
import java.util.Date;


@Component
public class JwtUtils {
    //签名私钥
    @Value("${token.secret}")
    private String SECRET;
    //签名的失效时间
    @Value("${token.expire-time}")
    private Long EXPIRE_TIME;

    public String getSECRET() {
        return SECRET;
    }

    public void setSECRET(String SECRET) {
        this.SECRET = SECRET;
    }

    public Long getEXPIRE_TIME() {
        return EXPIRE_TIME;
    }

    public void setEXPIRE_TIME(Long EXPIRE_TIME) {
        this.EXPIRE_TIME = EXPIRE_TIME;
    }

    /**
     * 校验token是否正确
     * @param token 密钥
     * @return 是否正确
     */
    public boolean verify(String token, String username){// UnsupportedEncodingException
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    /**
     * 获得token中的信息无需secret解密也能获得
     * @return token中包含的用户名
     */
    public String getUserName(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("username").asString();
    }

    /**
     * 刷新生成签名,5min后过期
     * @param token
     * @return
     */
    public String refreshToken(String token) {
        String userName = getUserName(token);
        Algorithm algorithm = null;
        try {
            algorithm = Algorithm.HMAC256(SECRET);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JWTVerifier verifier = JWT.require(algorithm)
                .withClaim("username", userName)
                .build();
        DecodedJWT jwt = verifier.verify(token);
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        return JWT.create()
                .withClaim("username", userName)
                .withExpiresAt(date)
                .sign(algorithm);
    }
    /**
     * 生成签名,5min后过期
     * @param user 用户UserDto
     * @return 加密的token
     */
    public String sign(LoginVo user) throws UnsupportedEncodingException {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        UserService userService = new UserService();
        User username = userService.getUserByUname(user.getUsername());
        String userStr = RedisService.beanToString(user);
        System.out.println(userStr);
        return JWT.create()
                .withClaim("username", username.getUsername())
                .withExpiresAt(date)
                .sign(algorithm);
    }
}
