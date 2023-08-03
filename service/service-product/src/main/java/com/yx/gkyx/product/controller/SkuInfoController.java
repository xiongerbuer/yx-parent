package com.yx.gkyx.product.controller;


import com.yx.gkyx.common.result.Result;
import com.yx.gkyx.model.product.SkuInfo;
import com.yx.gkyx.product.service.SkuInfoService;
import com.yx.gkyx.vo.product.SkuInfoQueryVo;
import com.yx.gkyx.vo.product.SkuInfoVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * sku信息 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/admin/product/skuInfo")
//@CrossOrigin
public class SkuInfoController {

    @Autowired
    private SkuInfoService skuInfoService;

//    url: `${api_name}/${page}/${limit}`,
//    method: 'get',
//    params: searchObj
    @ApiOperation(value = "sku列表")
    @GetMapping("{page}/{limit}")
    public Result<IPage<SkuInfo>> list(@PathVariable Long page,
                                       @PathVariable Long limit,
                                       SkuInfoQueryVo skuInfoQueryVo) {
        Page<SkuInfo> pageParam = new Page<>(page, limit);
        IPage<SkuInfo> pageModel =
                skuInfoService.selectPageSkuInfo(pageParam, skuInfoQueryVo);
        return Result.ok(pageModel);
    }

//    url: `${api_name}/save`,
//    method: 'post',
//    data: role
    @ApiOperation(value = "添加商品sku信息")
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody SkuInfoVo skuInfoVo) {
        skuInfoService.saveSkuInfo(skuInfoVo);
        return Result.ok(Boolean.TRUE);
    }

//    url: `${api_name}/get/${id}`,
//    method: 'get'
    @ApiOperation(value = "获取sku信息")
    @GetMapping("get/{id}")
    public Result<SkuInfoVo> get(@PathVariable Long id) {
        SkuInfoVo skuInfoVo = skuInfoService.getSkuInfo(id);
        return Result.ok(skuInfoVo);
    }

//    url: `${api_name}/update`,
//    method: 'put',
//    data: role
    @ApiOperation(value = "修改sku")
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody SkuInfoVo skuInfoVo) {
        skuInfoService.updateSkuInfo(skuInfoVo);
        return Result.ok(Boolean.TRUE);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("remove/{id}")
    public Result<Boolean> remove(@PathVariable Long id) {
        return Result.ok(skuInfoService.removeById(id));
    }

    @ApiOperation(value = "根据id列表删除")
    @DeleteMapping("batchRemove")
    public Result<Boolean> batchRemove(@RequestBody List<Long> idList) {
        return Result.ok(skuInfoService.removeByIds(idList));
    }

//    url: `${api_name}/check/${id}/${status}`,
//    method: 'get'
    @ApiOperation(value = "商品审核")
    @GetMapping("check/{skuId}/{status}")
    public Result<Boolean> check(@PathVariable Long skuId,
                                 @PathVariable Integer status) {
        skuInfoService.check(skuId, status);
        return Result.ok(Boolean.TRUE);
    }

//    url: `${api_name}/publish/${id}/${status}`,
//    method: 'get'
    @ApiOperation(value = "商品上下架")
    @GetMapping("publish/{skuId}/{status}")
    public Result<Boolean> publish(@PathVariable Long skuId,
                                   @PathVariable Integer status) {
        skuInfoService.publish(skuId, status);
        return Result.ok(Boolean.TRUE);
    }

    @ApiOperation(value = "新人专享")
    @GetMapping("isNewPerson/{skuId}/{status}")
    public Result<Boolean> isNewPerson(@PathVariable("skuId") Long skuId,
                                       @PathVariable("status") Integer status) {
        skuInfoService.isNewPerson(skuId, status);
        return Result.ok(Boolean.TRUE);
    }
}

