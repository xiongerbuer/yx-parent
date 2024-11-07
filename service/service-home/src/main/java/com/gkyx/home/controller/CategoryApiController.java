package com.gkyx.home.controller;

import com.gkyx.client.product.ProductFeignClient;
import com.gkyx.common.result.Result;
import com.gkyx.model.product.Category;
import io.swagger.annotations.Api;
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
    @GetMapping("category")
    public Result<List<Category>> categoryList() {
        return Result.ok(productFeignClient.findAllCategoryList());
    }
}
