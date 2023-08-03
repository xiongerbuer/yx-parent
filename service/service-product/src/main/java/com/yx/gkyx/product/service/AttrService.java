package com.yx.gkyx.product.service;

import com.yx.gkyx.model.product.Attr;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品属性 服务类
 * </p>
 */
public interface AttrService extends IService<Attr> {

    //根据平台属性分组id查询
    List<Attr> getAttrListByGroupId(Long groupId);
}
