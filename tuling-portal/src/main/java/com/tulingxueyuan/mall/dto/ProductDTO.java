package com.tulingxueyuan.mall.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="商品的数据传输对象", description="商品的数据传输对象")
public class ProductDTO {
    private Long id;

    private String name;

    private String pic;

    @ApiModelProperty(value = "促销价格")
    private BigDecimal promotionPrice;

    @ApiModelProperty(value = "商品价格")
    private BigDecimal price;

    @ApiModelProperty(value = "市场价")
    private BigDecimal originalPrice;

    @ApiModelProperty(value = "副标题")
    private String subTitle;

    @ApiModelProperty(value = "控制价格是否要加上 xx元起，如果=1就不需要加，如果=0则需要加")
    private Integer sub;
}
