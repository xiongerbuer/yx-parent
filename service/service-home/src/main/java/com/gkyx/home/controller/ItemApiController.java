package com.gkyx.home.controller;

import com.gkyx.common.auth.AuthContextHolder;
import com.gkyx.common.result.Result;
import com.gkyx.home.service.ItemService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api(tags = "商品详情")
@RestController
@RequestMapping("api/home")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class ItemApiController {

    private ItemService itemService;

    @GetMapping("item/{id}")
    public Result<Map<String,Object>> index(@PathVariable Long id) {
        Long userId = AuthContextHolder.getUserId();
        return Result.ok(itemService.item(id,userId));
    }
}
