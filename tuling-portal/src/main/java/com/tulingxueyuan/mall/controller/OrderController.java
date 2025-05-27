package com.tulingxueyuan.mall.controller;

import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.dto.ConfirmOrderDTO;
import com.tulingxueyuan.mall.modules.oms.service.OmsOrderService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "OrderController" ,description = "订单服务接口")
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OmsOrderService orderService;
    /**
     *初始化确认订单的商品和收货地址信息
     * .post(
     *           "/order/generateConfirmOrder",
     *           Qs.stringify({ itemIds: constStore.itemids },
     */
    @RequestMapping(value = "/generateConfirmOrder",method = RequestMethod.POST)
    public CommonResult generateConfirmOrder(
            @RequestParam("itemIds") List<Long> ids
    ){
        ConfirmOrderDTO confirmOrderDTO=orderService.generateConfirmOrder(ids);
        return CommonResult.success(confirmOrderDTO);
    }
}
