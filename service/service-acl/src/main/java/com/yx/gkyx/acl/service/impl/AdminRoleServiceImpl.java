package com.yx.gkyx.acl.service.impl;

import com.yx.gkyx.acl.mapper.AdminRoleMapper;
import com.yx.gkyx.acl.service.AdminRoleService;
import com.yx.gkyx.model.acl.AdminRole;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper, AdminRole> implements AdminRoleService {
}
