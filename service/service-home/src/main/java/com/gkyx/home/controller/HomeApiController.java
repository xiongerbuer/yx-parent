package com.gkyx.home.controller;

import com.gkyx.common.auth.AuthContextHolder;
import com.gkyx.common.result.Result;
import com.gkyx.home.service.HomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Api(tags = "首页接口")
@RestController
@RequestMapping("api/home")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class HomeApiController {

    private HomeService homeService;

    @ApiOperation("首页数据显示接口")
    @GetMapping("index")
    public Result<Map<String,Object>> index(HttpServletRequest request) {
        Long userId = AuthContextHolder.getUserId();
        return Result.ok(homeService.homeData(userId));
    }

}
