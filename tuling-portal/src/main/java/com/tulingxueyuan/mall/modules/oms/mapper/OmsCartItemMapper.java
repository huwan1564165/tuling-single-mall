package com.tulingxueyuan.mall.modules.oms.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.tulingxueyuan.mall.dto.CartItemStockDTO;
import com.tulingxueyuan.mall.modules.oms.model.OmsCartItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 购物车表 Mapper 接口
 * </p>
 *
 * @author XuShu
 * @since 2025-05-22
 */
public interface OmsCartItemMapper extends BaseMapper<OmsCartItem> {

    List<CartItemStockDTO> getCartItemStork(Long id);

    List<CartItemStockDTO> getCartItemStorkById(@Param(Constants.WRAPPER) Wrapper ew);
}
