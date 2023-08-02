package com.atguigu.ssyx.sys.controller;


import com.yx.ssyx.common.result.Result;
import com.atguigu.ssyx.model.sys.Ware;
import com.atguigu.ssyx.sys.service.WareService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * <p>
 * 仓库表 前端控制器
 * </p>
 *
 * @author xiongyou
 * @since 2023-04-03
 */
@RestController
@Api(tags = "仓库信息接口")
@RequestMapping(value = "/admin/sys/ware", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor(onConstructor_ = @Autowired)
//@CrossOrigin
public class WareController {

    private WareService wareService;

    //查询所有仓库列表
//    url: `${api_name}/findAllList`,
//    method: 'get'
    @ApiOperation(value = "查询所有仓库列表")
    @GetMapping("findAllList")
    public Result<List<Ware>> findAllList() {
        List<Ware> list = wareService.list();
        return Result.ok(list);
    }
}

