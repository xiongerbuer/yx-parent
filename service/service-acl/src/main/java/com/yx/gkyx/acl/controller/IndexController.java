package com.yx.gkyx.acl.controller;

import com.yx.gkyx.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(tags = "登录接口")
@RestController
@RequestMapping(value = "/admin/acl/index", produces = APPLICATION_JSON_VALUE)
//@CrossOrigin //跨域
public class IndexController {

    //   http://localhost:8201/admin/acl/index/login
    //1 login登录
    @ApiOperation(value = "登录")
    @PostMapping("login")
    public Result login() {
        //返回token值
        Map<String,String> map = new HashMap<>();
        map.put("token","token-admin");
        return Result.ok(map);
    }

//    url: '/admin/acl/index/info',
//    method: 'get',
    //2 getInfo 获取信息
    @ApiOperation(value = "获取信息")
    @GetMapping("info")
    public Result info() {
        Map<String,String> map = new HashMap<>();
        map.put("name","admin");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return Result.ok(map);
    }

//    url: '/admin/acl/index/logout',
//    method: 'post'
    //3 logout 退出
    @ApiOperation(value = "退出")
    @PostMapping("logout")
    public Result logout() {
        return Result.ok(null);
    }
}
