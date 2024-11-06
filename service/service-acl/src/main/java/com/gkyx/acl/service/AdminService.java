package com.gkyx.acl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gkyx.model.acl.Admin;
import com.gkyx.vo.acl.AdminQueryVo;

public interface AdminService extends IService<Admin> {

    //1 用户列表
    IPage<Admin> selectPageUser(Page<Admin> pageParam, AdminQueryVo adminQueryVo);
}
