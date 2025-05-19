package com.tulingxueyuan.mall.modules.pms.service.impl;

import com.tulingxueyuan.mall.modules.pms.model.PmsProduct;
import com.tulingxueyuan.mall.modules.pms.mapper.PmsProductMapper;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductDetailDTO;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author XuShu
 * @since 2025-05-13
 */
@Service
public class PmsProductServiceImpl extends ServiceImpl<PmsProductMapper, PmsProduct> implements PmsProductService {
    @Autowired
    PmsProductMapper pmsProductMapper;
    /**
     * 获取商品详情
     * @param id
     * @return
     */
    @Override
    public ProductDetailDTO getProductDetail(Long id) {
        return pmsProductMapper.getProductDetail(id);
    }
}
