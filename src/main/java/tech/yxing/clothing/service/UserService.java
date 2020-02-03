package tech.yxing.clothing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.yxing.clothing.dao.UserDao;
import tech.yxing.clothing.exception.GlobleException;
import tech.yxing.clothing.myshiro.MyShiro;
import tech.yxing.clothing.pojo.po.User;
import tech.yxing.clothing.pojo.vo.LoginVo;
import tech.yxing.clothing.pojo.vo.TokenId;
import tech.yxing.clothing.pojo.vo.UserVo;
import tech.yxing.clothing.redis.RedisService;
import tech.yxing.clothing.redis.UserKey;
import tech.yxing.clothing.result.CodeMsg;
import tech.yxing.clothing.result.Result;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisService redisService;

    /**
     * @methodDesc: 用户登录
     * @Param: loginVo
     * @return: String
     * @Author: Joe
     */
    public TokenId userLogin(LoginVo loginVo){
        if (loginVo == null){
            throw new GlobleException(CodeMsg.SERVER_ERROR);
        }
        String username = loginVo.getUsername();
        String password = loginVo.getPassword();
        //判断用户名是否存在
        User user = userDao.getByUsername(username);
        if (user == null){
            throw new GlobleException(CodeMsg.USERNAME_NOT_EXIST);
        }
        //验证密码
        String dBPassword = user.getPassword();
        String calcPassword = password;
        if (!dBPassword.equals(calcPassword)){
            throw new GlobleException(CodeMsg.PASSWORD_ERROR);
        }
        //生成token
        String token = MyShiro.createToken(loginVo);
        Integer userId = user.getUserId();
        TokenId tokenId = new TokenId(token,userId);
        return tokenId;
    }

    /**
     * @methodDesc: 用户注册
     * @Param: loginVo
     * @return: int
     * @Author: Joe
     */
    public int userRegister(LoginVo loginVo){
        if (loginVo == null){
            throw new GlobleException(CodeMsg.SERVER_ERROR);
        }
        String username = loginVo.getUsername();
        String password = loginVo.getPassword();
        //判断用户名是否已存在
        User user = userDao.getByUsername(username);
        if (user != null){
            throw new GlobleException(CodeMsg.USER_EXIST);
        }
        User user1 = new User(new LoginVo(username,password));
        userDao.registerUser(user1);
        return user1.getUserId();
    }

    /**
     * @methodDesc: 用户信息管理
     * @Param: userVo
     * @return: Result<UserVo>
     * @Author: Joe
     */
    public UserVo insertUserInfo(int userId,UserVo userVo){
        try {
            userDao.insertUserInfo(new User(userId,userVo));
            User user = userDao.showInfo(userId);
            UserVo userVo1 = new UserVo(user.getName(),user.getTelephone(),user.getSex(),
                    user.getBirthday(),user.getArea(),user.getWechat(),user.getAddress());
            return userVo1;
        } catch (Exception e){
            throw new GlobleException(CodeMsg.USER_INFO_INSERT_ERROR);
        }
    }

    /**
     * @methodDesc: 查询用户信息
     * @Param: userId
     * @return: Result<User>
     * @Author: Joe
     */
    public UserVo getUserInfo(int userId){
        //缓存中取用户对象
        User user = redisService.get(UserKey.infoById, ""+userId, User.class);
        if (user != null){
            return new UserVo(user.getName(),user.getTelephone(),user.getSex(),
                    user.getBirthday(),user.getArea(),user.getWechat(),user.getAddress());
        }
        //走数据库
        user = userDao.showInfo(userId);
        if (user != null){
            redisService.set(UserKey.infoById, ""+user.getUserId(), user);
        }

        return new UserVo(user.getName(),user.getTelephone(),user.getSex(),
                user.getBirthday(),user.getArea(),user.getWechat(),user.getAddress());
    }

    /**
     * @methodDesc: 查询用户余额
     * @Param: userId
     * @return: Double
     * @Author: Joe
     */
    public Double getBalance(int userId){
        User user = userDao.showInfo(userId);
        //取缓存
        String balance1 = redisService.get(UserKey.balanceById, ""+user.getUserId(), String.class);
        if (balance1 != null){
            return Double.parseDouble(balance1);
        }
        //缓存中没有再走数据库
        Double balance = user.getBalance();
        String yue = String.valueOf(balance);
        if (balance != null){
            redisService.set(UserKey.balanceById, "" + user.getUserId(), yue);
        }
        return balance;
    }

    /**
     * @methodDesc: 用用户名查询密码
     * @Param: username
     * @return: String
     * @Author: Joe
     */
    public User getUserByUname(String username){
        User user = userDao.getUserByUname(username);
        return user;
    }
}
