package com.tulingxueyuan.mall.modules.oms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.dto.ConfirmOrderDTO;
import com.tulingxueyuan.mall.dto.OrderDetailDTO;
import com.tulingxueyuan.mall.dto.OrderListDTO;
import com.tulingxueyuan.mall.dto.OrderParamDTO;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrder;

import java.util.List;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author XuShu
 * @since 2025-05-26
 */
public interface OmsOrderService extends IService<OmsOrder> {
    /**
     * 初始化确认订单的商品和收货地址信息
     * @param ids
     * @return
     */
    ConfirmOrderDTO generateConfirmOrder(List<Long> ids);

    /**
     * 生成订单(下单)
     * @param orderParamDTO
     * @return
     */
    OmsOrder generateOrder(OrderParamDTO orderParamDTO);

    /**
     * 读取下单成功后的订单详情
     * @param id
     * @return
     */
    OrderDetailDTO getOrderDetail(Long id);

    void cancelOverTimeOrder();


    IPage<OrderListDTO> getMyOrders(Integer pageSize, Integer pageNum);

    void paySuccess(Long orderId, Integer payType);
}
