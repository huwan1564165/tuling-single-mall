package com.tulingxueyuan.mall.modules.ums.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tulingxueyuan.mall.modules.ums.model.UmsMember;
import com.tulingxueyuan.mall.modules.ums.model.UmsMemberReceiveAddress;
import com.tulingxueyuan.mall.modules.ums.mapper.UmsMemberReceiveAddressMapper;
import com.tulingxueyuan.mall.modules.ums.service.UmsMemberReceiveAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.modules.ums.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 会员收货地址表 服务实现类
 * </p>
 *
 * @author XuShu
 * @since 2025-05-19
 */
@Service
public class UmsMemberReceiveAddressServiceImpl extends ServiceImpl<UmsMemberReceiveAddressMapper, UmsMemberReceiveAddress> implements UmsMemberReceiveAddressService {

    @Autowired
    UmsMemberService memberService;

    @Override
    public Boolean add(UmsMemberReceiveAddress address) {
        Long id = memberService.getCurrentMember().getId();
        address.setMemberId(id);
        return this.save(address);
    }

    @Override
    public Boolean edit(UmsMemberReceiveAddress address) {
        return this.updateById(address);
    }

    @Override
    public Boolean delete(Long id) {
        return this.removeById(id);
    }

    @Override
    public List<UmsMemberReceiveAddress> listByMemberId() {
        QueryWrapper<UmsMemberReceiveAddress> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UmsMemberReceiveAddress::getMemberId,memberService.getCurrentMember().getId() );
        return this.list(queryWrapper);
    }
}
