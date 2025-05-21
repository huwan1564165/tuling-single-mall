package com.tulingxueyuan.mall.modules.ums.service;

import com.tulingxueyuan.mall.modules.ums.model.UmsMember;
import com.baomidou.mybatisplus.extension.service.IService;
//import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author XuShu
 * @since 2025-05-19
 */
public interface UmsMemberService extends IService<UmsMember> {


    UmsMember register(UmsMember umsMemberParam);

    UmsMember login(@NotBlank(message = "用户名不能为空") String username, @NotBlank(message = "密码不能为空") String password);
}
