package com.yx.gkyx.product.controller;


import com.yx.gkyx.common.result.Result;
import com.yx.gkyx.model.product.Attr;
import com.yx.gkyx.product.service.AttrService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 商品属性 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/admin/product/attr")
//@CrossOrigin
public class AttrController {

    @Autowired
    private AttrService attrService;

    //平台属性列表方法
    //根据平台属性分组id查询
//    url: `${api_name}/${groupId}`,
//    method: 'get'
    @ApiOperation(value = "根据平台属性分组id查询")
    @GetMapping("{groupId}")
    public Result<List<Attr>> list(@PathVariable Long groupId) {
        List<Attr> list = attrService.getAttrListByGroupId(groupId);
        return Result.ok(list);
    }

    @ApiOperation(value = "获取")
    @GetMapping("get/{id}")
    public Result<Attr> get(@PathVariable Long id) {
        Attr attr = attrService.getById(id);
        return Result.ok(attr);
    }

    @ApiOperation(value = "新增")
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody Attr attr) {
        return Result.ok(attrService.save(attr));
    }

    @ApiOperation(value = "修改")
    @PutMapping("update")
    public Result<Boolean> updateById(@RequestBody Attr attr) {
        return Result.ok(attrService.updateById(attr));
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("remove/{id}")
    public Result<Boolean> remove(@PathVariable Long id) {
        return Result.ok(attrService.removeById(id));
    }

    @ApiOperation(value = "根据id列表删除")
    @DeleteMapping("batchRemove")
    public Result<Boolean> batchRemove(@RequestBody List<Long> idList) {
        return Result.ok(attrService.removeByIds(idList));
    }
}

