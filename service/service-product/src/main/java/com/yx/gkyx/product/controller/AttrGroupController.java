package com.yx.gkyx.product.controller;


import com.yx.gkyx.common.result.Result;
import com.yx.gkyx.model.product.AttrGroup;
import com.yx.gkyx.product.service.AttrGroupService;
import com.yx.gkyx.vo.product.AttrGroupQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 属性分组 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/admin/product/attrGroup")
//@CrossOrigin
public class AttrGroupController {

    @Autowired
    private AttrGroupService attrGroupService;

    //    url: `${api_name}/${page}/${limit}`,
//    method: 'get',
//    params: searchObj
    @ApiOperation(value = "平台属性分组列表")
    @GetMapping("{page}/{limit}")
    public Result list(@PathVariable Long page,
                       @PathVariable Long limit,
                       AttrGroupQueryVo attrGroupQueryVo) {
        Page<AttrGroup> pageParam = new Page<>(page, limit);
        IPage<AttrGroup> pageModel = attrGroupService.selectPageAttrGroup(pageParam, attrGroupQueryVo);
        return Result.ok(pageModel);
    }

    //查询所有平台属性分组列表
//    url: `${api_name}/findAllList`,
//    method: 'get'
    @ApiOperation(value = "查询所有平台属性分组列表")
    @GetMapping("findAllList")
    public Result<List<AttrGroup>> findAllList() {
        List<AttrGroup> list = attrGroupService.findAllListAttrGroup();
        return Result.ok(list);
    }

    @ApiOperation(value = "获取")
    @GetMapping("get/{id}")
    public Result<AttrGroup> get(@PathVariable Long id) {
        AttrGroup attrGroup = attrGroupService.getById(id);
        return Result.ok(attrGroup);
    }

    @ApiOperation(value = "新增")
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody AttrGroup attrGroup) {
        attrGroupService.save(attrGroup);
        return Result.ok(attrGroupService.save(attrGroup));
    }

    @ApiOperation(value = "修改")
    @PutMapping("update")
    public Result<Boolean> updateById(@RequestBody AttrGroup attrGroup) {
        return Result.ok(attrGroupService.updateById(attrGroup));
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("remove/{id}")
    public Result<Boolean> remove(@PathVariable Long id) {
        attrGroupService.removeById(id);
        return Result.ok(attrGroupService.removeById(id));
    }

    @ApiOperation(value = "根据id列表删除")
    @DeleteMapping("batchRemove")
    public Result<Boolean> batchRemove(@RequestBody List<Long> idList) {
        attrGroupService.removeByIds(idList);
        return Result.ok(attrGroupService.removeByIds(idList));
    }
}

