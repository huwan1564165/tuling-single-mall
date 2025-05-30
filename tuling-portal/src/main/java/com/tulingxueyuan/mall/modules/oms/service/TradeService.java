package com.tulingxueyuan.mall.modules.oms.service;

import com.tulingxueyuan.mall.common.api.CommonResult;

public interface TradeService {
    /**
     * 生成当面付二维码
     * @param orderId
     * @param payType 1.支付宝2.微信
     * @return
     */
    CommonResult tradeQrCode(Long orderId, Integer payType);
}
