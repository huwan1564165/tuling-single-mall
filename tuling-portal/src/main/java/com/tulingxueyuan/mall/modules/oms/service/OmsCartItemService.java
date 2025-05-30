package com.tulingxueyuan.mall.modules.oms.service;

import com.tulingxueyuan.mall.dto.AddCarDTO;
import com.tulingxueyuan.mall.dto.CartItemStockDTO;
import com.tulingxueyuan.mall.modules.oms.model.OmsCartItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 购物车表 服务类
 * </p>
 *
 * @author XuShu
 * @since 2025-05-22
 */
public interface OmsCartItemService extends IService<OmsCartItem> {

    Boolean add(AddCarDTO addCarDTO);
    /**
     * 初始化状态栏的购物车商品数量
     * this.axios.get('/car/products/sum')
     */
    Integer getCarProductSum();

    /**
     * 初始化购物车数据
     * @return
     */
    List<CartItemStockDTO> getList();
    /**
     * 更新商品的数量
     * this.axios.post('/car/update/quantity',Qs.stringify({
     * id:item.id,
     * quantity:item.quantity   当前数量
     * })
     */
    Boolean updateQuantity(Long id, Long quantity);

    /**
     * 删除
     * @param ids
     * @return
     */
    Boolean delete(Long ids);
}
