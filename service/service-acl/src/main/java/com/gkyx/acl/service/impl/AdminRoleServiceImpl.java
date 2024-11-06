package com.gkyx.acl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gkyx.acl.mapper.AdminRoleMapper;
import com.gkyx.acl.service.AdminRoleService;
import com.gkyx.model.acl.AdminRole;
import org.springframework.stereotype.Service;

@Service
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper, AdminRole> implements AdminRoleService {
}
