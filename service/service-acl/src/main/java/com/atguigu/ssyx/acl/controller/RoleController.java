package com.atguigu.ssyx.acl.controller;

import com.atguigu.ssyx.acl.service.RoleService;
import com.yx.ssyx.common.result.Result;
import com.atguigu.ssyx.model.acl.Role;
import com.atguigu.ssyx.vo.acl.RoleQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(tags = "角色接口")
@RestController
@RequestMapping(value = "/admin/acl/role", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor(onConstructor_ = @Autowired)
//@CrossOrigin //跨域
public class RoleController {

    //注入service
    private RoleService roleService;

    //1 角色列表（条件分页查询）
    @ApiOperation(value = "角色条件分页查询")
    @GetMapping("{current}/{limit}")
    public Result<IPage<Role>> pageList(@PathVariable Long current,
                           @PathVariable Long limit,
                           RoleQueryVo roleQueryVo) {
        //1 创建page对象，传递当前页和每页记录数
        // current：当前页
        // limit: 每页显示记录数
        Page<Role> pageParam = new Page<>(current,limit);

        //2 调用service方法实现条件分页查询，返回分页对象
        IPage<Role> pageModel = roleService.selectRolePage(pageParam,roleQueryVo);

        return Result.ok(pageModel);
    }

    //2 根据id查询角色
    @ApiOperation(value = "根据id查询角色")
    @GetMapping("get/{id}")
    public Result<Role> get(@PathVariable Long id) {
        Role role = roleService.getById(id);
        return Result.ok(role);
    }

    //3 添加角色
    @ApiOperation(value = "添加角色")
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody Role role) {
        if(roleService.save(role)) {
            return Result.ok(true);
        } else {
            return Result.fail(false);
        }
    }

    //4 修改角色
    @ApiOperation(value = "修改角色")
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody Role role) {
        if(roleService.updateById(role)) {
            return Result.ok(true);
        } else {
            return Result.fail(false);
        }
    }

    //5 根据id删除角色
    @ApiOperation(value = "根据id删除角色")
    @DeleteMapping("remove/{id}")
    public Result<Boolean> remove(@PathVariable Long id) {
        if(roleService.removeById(id)) {
            return Result.ok(true);
        } else {
            return Result.fail(false);
        }
    }

    //6 批量删除角色
    // json数组[1,2,3]  --- java的list集合
    @ApiOperation(value = "批量删除角色")
    @DeleteMapping("batchRemove")
    public Result<Boolean> batchRemove(@RequestBody List<Long> idList) {
        if(roleService.removeByIds(idList)) {
            return Result.ok(true);
        } else {
            return Result.fail(false);
        }
    }

}
