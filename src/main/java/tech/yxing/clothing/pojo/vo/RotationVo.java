package tech.yxing.clothing.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author Joe
 * @Date
 */
@ApiModel(value = "RotationVo", description = "轮播图数据结构")
public class RotationVo {
    @ApiModelProperty(value = "轮播图地址", name = "imge")
    private String imge;

    @ApiModelProperty(value = "操作管理员id", name = "managerId")
    private Integer managerId;

    @ApiModelProperty(value = "商品id", name = "goodsID")
    private Integer goodsID;

    public RotationVo() {
    }

    public RotationVo(String imge, Integer managerId, Integer goodsID) {
        this.imge = imge;
        this.managerId = managerId;
        this.goodsID = goodsID;
    }

    public String getImge() {
        return imge;
    }

    public void setImge(String imge) {
        this.imge = imge;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Integer getGoodsID() {
        return goodsID;
    }

    public void setGoodsID(Integer goodsID) {
        this.goodsID = goodsID;
    }

    @Override
    public String toString() {
        return "RotationVo{" +
                "imge='" + imge + '\'' +
                ", managerId=" + managerId +
                ", goodsID=" + goodsID +
                '}';
    }
}
