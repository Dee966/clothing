package tech.yxing.clothing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.yxing.clothing.dao.ManagerDao;
import tech.yxing.clothing.exception.GlobleException;
import tech.yxing.clothing.myshiro.MyShiro;
import tech.yxing.clothing.pojo.dto.GoodsSizeDto;
import tech.yxing.clothing.pojo.po.Goods;
import tech.yxing.clothing.pojo.po.GoodsSize;
import tech.yxing.clothing.pojo.po.Manager;
import tech.yxing.clothing.pojo.po.User;
import tech.yxing.clothing.pojo.vo.LoginVo;
import tech.yxing.clothing.pojo.vo.ManagerVo;
import tech.yxing.clothing.pojo.vo.TokenId;
import tech.yxing.clothing.result.CodeMsg;

import java.util.Date;

@Service
public class ManagerService {
    @Autowired
    private ManagerDao managerDao;


    /**
     * @methodDesc: 管理员信息管理 修改信息
     * @Param: managerVo
     * @return: managerVo
     * @Author: Joe
     */
    public ManagerVo managerInfo(int managerId,ManagerVo managerVo){
        managerDao.managerInfo(new Manager(managerId,managerVo));
        Manager manager = managerDao.managerShow(managerVo.getManagerId());
        ManagerVo managerVo1 = new ManagerVo(manager.getName(),manager.getSex(),
                manager.getTelephone(),manager.getWorkTime());
        return managerVo1;
    }

    /**
     * @Param: managerId
     * @Return: Manager
     * @Author Joe
     * @Date
     */
    public ManagerVo managerShow(int managerId){
        Manager manager = managerDao.managerShow(managerId);
        ManagerVo managerVo = new ManagerVo(manager.getName(),manager.getSex(),
                manager.getTelephone(),manager.getWorkTime());
        return managerVo;
    }

    /**
     * @Param: loginVo
     * @Return: Integer
     * @Author Joe
     * @Date
     */
    public Integer managerRegister(LoginVo loginVo){
        if (loginVo == null){
            throw new GlobleException(CodeMsg.SERVER_ERROR);
        }
        String username = loginVo.getUsername();
        String password = loginVo.getPassword();
        System.out.println(username+"..."+password);
        //判断用户名是否已存在
        Manager manager = managerDao.getByUsername(username);
        if (manager != null){
            throw new GlobleException(CodeMsg.USER_EXIST);
        }
        Manager manager1 = new Manager(new LoginVo(username,password));
        managerDao.managerRegister(manager1);
        return manager1.getManagerId();
    }

    public TokenId managerLogin(LoginVo loginVo){
        if (loginVo == null){
            throw new GlobleException(CodeMsg.SERVER_ERROR);
        }
        String username = loginVo.getUsername();
        String password = loginVo.getPassword();
        //判断用户名是否存在
        Manager manager = managerDao.getByUsername(username);
        if (manager == null){
            throw new GlobleException(CodeMsg.USERNAME_NOT_EXIST);
        }
        //验证密码
        String dBPassword = manager.getPassword();
        String calcPassword = password;
        if (!dBPassword.equals(calcPassword)){
            throw new GlobleException(CodeMsg.PASSWORD_ERROR);
        }
        //生成token
        String token = MyShiro.createToken(loginVo);
        TokenId tokenId = new TokenId(token,manager.getManagerId());
        return tokenId;
    }

    public Manager getManagerByUname(String username){
        return managerDao.getByUsername(username);
    }

    public void editGoods(GoodsSizeDto goodsSizeDto){
        int stock = 0;

        for (GoodsSize goodsSize : goodsSizeDto.getGoodsSizes()){
            managerDao.editGoodsSize(goodsSize);
            stock = stock + goodsSize.getGoodsSizeStock();
        }
        Goods goods = new Goods(
                goodsSizeDto.getGoodsId(),
                goodsSizeDto.getName(),
                goodsSizeDto.getPrice(),
                goodsSizeDto.getImge(),
                goodsSizeDto.getGoodDate(),
                goodsSizeDto.getDesc(),
                stock,
                goodsSizeDto.getGoodsTypeId()
        );
        managerDao.editGoods(goods);
    }
}
