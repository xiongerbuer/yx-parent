package com.gkyx.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gkyx.model.product.SkuAttrValue;
import com.gkyx.product.mapper.SkuAttrValueMapper;
import com.gkyx.product.service.SkuAttrValueService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * spu属性值 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2023-04-04
 */
@Service
public class SkuAttrValueServiceImpl extends ServiceImpl<SkuAttrValueMapper, SkuAttrValue> implements SkuAttrValueService {

    //根据id查询商品属性信息列表
    @Override
    public List<SkuAttrValue> getAttrValueListBySkuId(Long id) {
        LambdaQueryWrapper<SkuAttrValue> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SkuAttrValue::getSkuId,id);
        return baseMapper.selectList(wrapper);
    }
}
