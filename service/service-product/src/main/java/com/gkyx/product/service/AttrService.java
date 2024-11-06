package com.gkyx.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gkyx.model.product.Attr;

import java.util.List;

/**
 * <p>
 * 商品属性 服务类
 * </p>
 *
 * @author atguigu
 * @since 2023-04-04
 */
public interface AttrService extends IService<Attr> {

    //根据平台属性分组id查询
    List<Attr> getAttrListByGroupId(Long groupId);
}
