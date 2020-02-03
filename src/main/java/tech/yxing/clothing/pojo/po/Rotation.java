package tech.yxing.clothing.pojo.po;

import tech.yxing.clothing.pojo.vo.RotationVo;

//轮播图PO
public class Rotation {
    private Integer rotationId;//轮播图id
    private String imge;//轮播图图片地址
    private Integer goodsId;//商品id
    private Integer managerId;//上传该轮播图的管理员的id

    public Rotation() {
    }

    public Rotation(RotationVo rotationVo) {
        this.imge = rotationVo.getImge();
        this.goodsId = rotationVo.getGoodsID();
        this.managerId = rotationVo.getManagerId();
    }

    public Rotation(Integer rotationId, String imge, Integer goodsId, Integer managerId) {
        this.rotationId = rotationId;
        this.imge = imge;
        this.goodsId = goodsId;
        this.managerId = managerId;
    }

    public Integer getRotationId() {
        return rotationId;
    }

    public void setRotationId(Integer rotationId) {
        this.rotationId = rotationId;
    }

    public String getImge() {
        return imge;
    }

    public void setImge(String imge) {
        this.imge = imge;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    @Override
    public String toString() {
        return "RotationVo{" +
                "rotationId=" + rotationId +
                ", imge='" + imge + '\'' +
                ", goodsId=" + goodsId +
                ", managerId=" + managerId +
                '}';
    }
}
