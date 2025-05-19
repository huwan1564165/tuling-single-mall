package com.tulingxueyuan.mall.modules.pms.model.dto;

import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttributeValue;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="spu的数据传输对象", description="主要用于商品详情展示")
public class PmsProductAttributeValueDTo extends PmsProductAttributeValue {
    private String attrName;
}
