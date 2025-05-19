package com.tulingxueyuan.mall.modules.ums.service.impl;

import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tulingxueyuan.mall.common.exception.ApiException;
import com.tulingxueyuan.mall.common.exception.Asserts;
import com.tulingxueyuan.mall.modules.ums.mapper.UmsMemberLoginLogMapper;
import com.tulingxueyuan.mall.modules.ums.model.UmsMember;
import com.tulingxueyuan.mall.modules.ums.mapper.UmsMemberMapper;
import com.tulingxueyuan.mall.modules.ums.model.UmsMemberLoginLog;
import com.tulingxueyuan.mall.modules.ums.service.UmsMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author XuShu
 * @since 2025-05-19
 */
@Service
public class UmsMemberServiceImpl extends ServiceImpl<UmsMemberMapper, UmsMember> implements UmsMemberService {

}
