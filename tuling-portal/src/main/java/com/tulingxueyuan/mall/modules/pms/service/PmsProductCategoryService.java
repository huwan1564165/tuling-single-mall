package com.tulingxueyuan.mall.modules.pms.service;

import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.dto.HomeMenusDTO;

import java.util.List;

/**
 * <p>
 * 产品分类 服务类
 * </p>
 *
 * @author XuShu
 * @since 2025-05-13
 */
public interface PmsProductCategoryService extends IService<PmsProductCategory> {

    List<HomeMenusDTO> getMenus();
}
