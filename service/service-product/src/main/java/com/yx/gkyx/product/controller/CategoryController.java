package com.yx.gkyx.product.controller;


import com.yx.gkyx.common.result.Result;
import com.yx.gkyx.model.product.Category;
import com.yx.gkyx.product.service.CategoryService;
import com.yx.gkyx.vo.product.CategoryQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 商品三级分类 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/admin/product/category")
//@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

//    url: `${api_name}/${page}/${limit}`,
//    method: 'get',
//    params: searchObj
    @ApiOperation(value = "商品分类列表")
    @GetMapping("{page}/{limit}")
    public Result<IPage<Category>> list(@PathVariable Long page,
                                        @PathVariable Long limit,
                                        CategoryQueryVo categoryQueryVo) {
        Page<Category> pageParam = new Page<>(page, limit);
        IPage<Category> pageModel = categoryService.selectPageCategory(pageParam, categoryQueryVo);
        return Result.ok(pageModel);
    }

    @ApiOperation(value = "获取商品分类信息")
    @GetMapping("get/{id}")
    public Result<Category> get(@PathVariable Long id) {
        Category category = categoryService.getById(id);
        return Result.ok(category);
    }

    @ApiOperation(value = "新增商品分类")
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody Category category) {
        return Result.ok(categoryService.save(category));
    }

    @ApiOperation(value = "修改商品分类")
    @PutMapping("update")
    public Result<Boolean> updateById(@RequestBody Category category) {
        return Result.ok(categoryService.updateById(category));
    }

    @ApiOperation(value = "删除商品分类")
    @DeleteMapping("remove/{id}")
    public Result<Boolean> remove(@PathVariable Long id) {
        return Result.ok(categoryService.removeById(id));
    }

    @ApiOperation(value = "根据id列表删除商品分类")
    @DeleteMapping("batchRemove")
    public Result<Boolean> batchRemove(@RequestBody List<Long> idList) {
        return Result.ok(categoryService.removeByIds(idList));
    }

    //      url: `${api_name}/findAllList`,
    //      method: 'get'
    @ApiOperation(value = "查询所有商品分类")
    @GetMapping("findAllList")
    public Result<List<Category>> findAllList() {
        List<Category> list = categoryService.list();
        return Result.ok(list);
    }
}

