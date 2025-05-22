package com.tulingxueyuan.mall.modules.pms.service;

import com.tulingxueyuan.mall.modules.pms.model.PmsProduct;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.dto.ProductDetailDTO;

/**
 * <p>
 * 商品信息 服务类
 * </p>
 *
 * @author XuShu
 * @since 2025-05-13
 */
public interface PmsProductService extends IService<PmsProduct> {
    /**
     * 获取商品详情
     */
    ProductDetailDTO getProductDetail(Long id);
}
