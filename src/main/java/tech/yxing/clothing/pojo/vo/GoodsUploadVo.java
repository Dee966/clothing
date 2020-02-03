package tech.yxing.clothing.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import tech.yxing.clothing.pojo.po.GoodsSize;

import java.util.Date;
import java.util.List;

/**
 * @Author Joe
 * @Date
 */
@ApiModel(value = "GoodsUploadVo", description = "商品信息数据结构")
public class GoodsUploadVo {
    @ApiModelProperty(value = "商品名称", name = "name")
    private String name;
    @ApiModelProperty(value = "商品价格", name = "price")
    private Double price;
    @ApiModelProperty(value = "商品图片地址", name = "imge")
    private String imge;
    @ApiModelProperty(value = "上架日期", name = "goodDate")
    private Date goodDate;
    @ApiModelProperty(value = "商品描述", name = "desc")
    private String desc;
    @ApiModelProperty(value = "库存", name = "stock")
    private Integer stock;
    @ApiModelProperty(value = "类别id", name = "goodsTypeId")
    private Integer goodsTypeId;

    @ApiModelProperty(value = "尺码列表", name = "goodsSizeList")
    private List<GoodsSizeVo> goodsSizeVos;

    public GoodsUploadVo(){

    }

    public GoodsUploadVo(String name, Double price, String imge, Date goodDate, String desc, Integer stock, Integer goodsTypeId, List<GoodsSizeVo> goodsSizeVos) {
        this.name = name;
        this.price = price;
        this.imge = imge;
        this.goodDate = goodDate;
        this.desc = desc;
        this.stock = stock;
        this.goodsTypeId = goodsTypeId;
        this.goodsSizeVos = goodsSizeVos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImge() {
        return imge;
    }

    public void setImge(String imge) {
        this.imge = imge;
    }

    public Date getGoodDate() {
        return goodDate;
    }

    public void setGoodDate(Date goodDate) {
        this.goodDate = goodDate;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getGoodsTypeId() {
        return goodsTypeId;
    }

    public void setGoodsTypeId(Integer goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }

    public List<GoodsSizeVo> getGoodsSizeVos() {
        return goodsSizeVos;
    }

    public void setGoodsSizeVos(List<GoodsSizeVo> goodsSizeVos) {
        this.goodsSizeVos = goodsSizeVos;
    }

    @Override
    public String toString() {
        return "GoodsUploadVo{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", imge='" + imge + '\'' +
                ", goodDate=" + goodDate +
                ", desc='" + desc + '\'' +
                ", stock=" + stock +
                ", goodsTypeId=" + goodsTypeId +
                ", goodsSizeVos=" + goodsSizeVos +
                '}';
    }
}
