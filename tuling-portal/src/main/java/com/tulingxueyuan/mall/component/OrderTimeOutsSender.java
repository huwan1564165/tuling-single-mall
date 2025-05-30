package com.tulingxueyuan.mall.component;

import com.tulingxueyuan.mall.modules.oms.model.OmsOrder;
import com.tulingxueyuan.mall.modules.oms.service.OmsOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 订单超时取消并解锁库存的定时器
 */
@Component
@Slf4j
public class OrderTimeOutsSender {
    @Autowired
    private OmsOrderService orderService;

//    @Scheduled(cron = "0 0/1 * ? * ?")
    private void cancelOverTimeOrder(){
        log.info("-----cancelOverTimeOrder订单超时取消并解锁库存的定时器开始-----");
        orderService.cancelOverTimeOrder();
        log.info("-----cancelOverTimeOrder订单超时取消并解锁库存的定时器结束-----");
    }
}
