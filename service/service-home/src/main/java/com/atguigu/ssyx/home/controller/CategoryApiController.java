package com.atguigu.ssyx.home.controller;

import com.atguigu.ssyx.client.product.ProductFeignClient;
import com.yx.ssyx.common.result.Result;
import com.atguigu.ssyx.model.product.Category;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "商品分类")
@RestController
@RequestMapping("api/home")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class CategoryApiController {

    private ProductFeignClient productFeignClient;

    //查询所有分类
    @ApiOperation(value = "查询所有分类")
    @GetMapping("category")
    public Result<List<Category>> categoryList() {
        List<Category> categoryList = productFeignClient.findAllCategoryList();
        return Result.ok(categoryList);
    }
}
