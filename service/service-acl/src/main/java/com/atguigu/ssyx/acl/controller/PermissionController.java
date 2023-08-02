package com.atguigu.ssyx.acl.controller;

import com.atguigu.ssyx.acl.service.PermissionService;
import com.yx.ssyx.common.result.Result;
import com.atguigu.ssyx.model.acl.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/admin/acl/permission", produces = APPLICATION_JSON_VALUE)
@Api(tags = "菜单管理")
@AllArgsConstructor(onConstructor_ = @Autowired)
//@CrossOrigin //跨域
public class PermissionController {

    private PermissionService permissionService;

    //查询所有菜单
//    url: `${api_name}`,
//    method: 'get'
    @ApiOperation(value = "查询所有菜单")
    @GetMapping
    public Result list() {
        List<Permission> list = permissionService.queryAllPermission();
        return Result.ok(list);
    }

    //添加菜单
//    url: `${api_name}/save`,
//    method: "post",
//    data: permission
    @ApiOperation(value = "添加菜单")
    @PostMapping("save")
    public Result save(@RequestBody Permission permission) {
        permissionService.save(permission);
        return Result.ok(null);
    }

    //修改菜单
//    url: `${api_name}/update`,
//    method: "put",
//    data: permission
    @ApiOperation(value = "修改菜单")
    @PutMapping("update")
    public Result update(@RequestBody Permission permission) {
        permissionService.updateById(permission);
        return Result.ok(null);
    }

    //递归删除菜单
//    url: `${api_name}/remove/${id}`,
//    method: "delete"
    @ApiOperation(value = "递归删除菜单")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        permissionService.removeChildById(id);
        return Result.ok(null);
    }
}
