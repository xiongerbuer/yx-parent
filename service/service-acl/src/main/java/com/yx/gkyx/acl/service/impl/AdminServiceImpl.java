package com.yx.gkyx.acl.service.impl;

import com.yx.gkyx.acl.mapper.AdminMapper;
import com.yx.gkyx.acl.service.AdminService;
import com.yx.gkyx.model.acl.Admin;
import com.yx.gkyx.vo.acl.AdminQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    //1 用户列表
    @Override
    public IPage<Admin> selectPageUser(Page<Admin> pageParam, AdminQueryVo adminQueryVo) {
        String username = adminQueryVo.getUsername();
        String name = adminQueryVo.getName();
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        if(!StringUtils.isEmpty(username)) {
            wrapper.eq(Admin::getUsername,username);
        }
        if(!StringUtils.isEmpty(name)) {
            wrapper.like(Admin::getName,name);
        }
        return baseMapper.selectPage(pageParam, wrapper);
    }
}
