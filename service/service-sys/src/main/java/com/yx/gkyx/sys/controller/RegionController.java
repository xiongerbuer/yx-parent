package com.yx.gkyx.sys.controller;


import com.yx.gkyx.common.result.Result;
import com.yx.gkyx.model.sys.Region;
import com.yx.gkyx.sys.service.RegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * <p>
 * 地区表 前端控制器
 * </p>
 *
 */
@RestController
@Api(tags = {"区域信息接口"})
@RequestMapping(value = "/admin/sys/region", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor(onConstructor_ = @Autowired)
//@CrossOrigin
public class RegionController {

    private RegionService regionService;

    //根据区域关键字查询区域列表信息
//    url: `${api_name}/findRegionByKeyword/${keyword}`,
//    method: 'get'
    @ApiOperation(value = "根据区域关键字查询区域列表信息")
    @GetMapping("findRegionByKeyword/{keyword}")
    public Result<List<Region>> findRegionByKeyword(@PathVariable("keyword") String keyword) {
        List<Region> list = regionService.getRegionByKeyword(keyword);
        return Result.ok(list);
    }
}

