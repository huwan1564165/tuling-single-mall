package com.tulingxueyuan.mall.modules.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeAdvertise;
import com.tulingxueyuan.mall.modules.sms.mapper.SmsHomeAdvertiseMapper;
import com.tulingxueyuan.mall.modules.sms.service.SmsHomeAdvertiseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 首页轮播广告表 服务实现类
 * </p>
 *
 * @author XuShu
 * @since 2025-05-19
 */
@Service
public class SmsHomeAdvertiseServiceImpl extends ServiceImpl<SmsHomeAdvertiseMapper, SmsHomeAdvertise> implements SmsHomeAdvertiseService {

    @Override
    public List<SmsHomeAdvertise> getHomeBanners() {

        QueryWrapper<SmsHomeAdvertise> smsHomeAdvertiseQueryWrapper = new QueryWrapper<>();
        smsHomeAdvertiseQueryWrapper.lambda()
                .eq(SmsHomeAdvertise::getType,0)
                .eq(SmsHomeAdvertise::getStatus,1)
                .orderByAsc(SmsHomeAdvertise::getSort);


        return this.list(smsHomeAdvertiseQueryWrapper);

    }
}
